package service;

import dao.UserFileDao;
import model.User;
import model.UserFile;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.Const;
import util.StringUtl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UploadServlet",urlPatterns = "/icloud/upload")
public class UploadServlet extends HttpServlet {

    private UserFileDao userFileDao = new UserFileDao();
    private String UPLOAD_ROOT_PATH = null;
    private DiskFileItemFactory factory;
    private ServletFileUpload stu;

    @Override
    public void init() throws ServletException {
        super.init();
        UPLOAD_ROOT_PATH = "/home/icloud/";
        String tmpPath = "/home/icloud/tmp";
        //配置上传临时目录
        factory = new DiskFileItemFactory(1024*1024*10,new File(tmpPath));
        stu = new ServletFileUpload(factory);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/suye";
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
        String from="";
        try {
            List<FileItem> fileItemLists = stu.parseRequest(request);
            for(FileItem fileItem : fileItemLists){
                if(fileItem.isFormField()){
//                    from = fileItem.getString();
//                    System.out.println("from="+from);
                }else{
                    //上传文件名
                    String filename = fileItem.getName();
                    int index = filename.lastIndexOf("\\");
                    if(index != -1) {
                        filename = filename.substring(index+1);
                    }
                    //上传路径-->D:/upload/当前用户名/****.**
                    String root = UPLOAD_ROOT_PATH+user.getUsername();
                    //上传文件大小
                    long size = fileItem.getSize();
                    String sizeString = StringUtl.toConversion(size);
                    Timestamp ts = new Timestamp(new Date().getTime());

                    File file = new File(root,filename);

                    //解决文件同名
                    int cnt = 1;
                    while(file.exists()){
                        StringBuffer sb = new StringBuffer(filename);
                        sb.insert(sb.lastIndexOf("."), "("+cnt+")");
                        file = new File(root,sb.toString());
                        cnt++;
                    }
                    //父目录不存在则创建
                 //   File root1=new File(root);
                    System.out.println(file.getParentFile());
                    if(!file.getParentFile().exists()){
                        file.getParentFile().mkdirs();
                    }
                    try {
                        fileItem.write(file);
                        //上传成功，数据库保存记录
                        UserFile userFile = new UserFile();
                        userFile.setCreateTime(ts);
                        userFile.setFilename(file.getName());
                        userFile.setFileSize(sizeString);
                        userFile.setIsShared(0);
                        userFile.setOwnerId(user.getId());
                        userFile.setPath(file.getAbsolutePath());
                        userFileDao.save(userFile);
                        response.sendRedirect(basePath+"icloud?method=admin&action=mydisk");
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.setContentType("text/html; charset=utf8");
                        response.getWriter().print("上传出错");
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            response.setContentType("text/html; charset=utf8");
            response.getWriter().print("上传出错!!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
