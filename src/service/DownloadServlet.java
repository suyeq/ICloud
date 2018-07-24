package service;

import dao.UserFileDao;
import model.UserFile;
import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;
import util.Const;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "DownloadServlet",urlPatterns = "/icloud/download")
public class DownloadServlet extends HttpServlet {
    private UserFile userFile=null;
    private UserFileDao userFileDao=new UserFileDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userFile=(UserFile) request.getAttribute(Const.SESSION_USERFILE);
        try {

            String filepath = userFile.getPath();
            // 为了使下载框中显示中文文件名称不出乱码！必须对文件名进行编码
            String filename = userFile.getFilename();
            filename = filenameEncoding(filename, request);
            String contentType = this.getServletContext()
                    .getMimeType(filename);//通过文件名称获取MIME类型
            String contentDisposition = "attachment;filename=" + filename;
            // 一个流
            FileInputStream input = new FileInputStream(filepath);
            //设置头
            response.setHeader("Content-Type", contentType);
            response.setHeader("Content-Disposition", contentDisposition);

            // 获取绑定了响应端的流
            ServletOutputStream output = response.getOutputStream();
            IOUtils.copy(input, output);//把输入流中的数据写入到输出流中。
            input.close();
            userFile.setCount(userFile.getCount()+1);
            userFileDao.updateDownPersons(userFile);
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html; charset=utf8");
            response.getWriter().print("下载失败,文件或已丢失!");
        }
    }

    // 用来对下载的文件名称进行编码的！
    public static String filenameEncoding(String filename, HttpServletRequest request) throws IOException {
        String agent = request.getHeader("User-Agent"); //获取浏览器
        if (agent.contains("Firefox")) {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filename = "=?utf-8?B?"
                    + base64Encoder.encode(filename.getBytes("utf-8"))
                    + "?=";
        } else if(agent.contains("MSIE")) {
            filename = URLEncoder.encode(filename, "utf-8");
        } else {
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
