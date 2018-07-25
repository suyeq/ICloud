package action;

import dao.UserFileDao;
import model.UserFile;
import util.Const;
import util.StringUtl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteFileAction extends Action{

    private UserFileDao userFileDao=new UserFileDao();
    public DeleteFileAction(HttpServletRequest request, HttpServletResponse response){
        this.request=request;
        this.response=response;
    }

    public void carriryShow() throws SQLException, IOException, ClassNotFoundException {
        boolean allDelete=false;
        String [] ids=request.getParameterValues(Const.Parameter_ids);
        String action=request.getParameter(Const.Parameter_action);
        int []id= StringUtl.toInter(ids);
        for(int i=0;i<id.length;i++){
            allDelete=deleteFile(userFileDao.findsbFile(id[i]));
            if (!allDelete)
                break;
        }
        if (!allDelete){
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("删除异常");
        }else {
            for(int i=0;i<id.length;i++){
                userFileDao.deleteFile(id[i]);
            }
            if (action.equals("mydisk")){
                response.sendRedirect("icloud?method=admin&action=mydisk");
            }else if(action.equals("all")){
                response.sendRedirect("icloud?method=admin&action=all");
            }else if (action.equals("")){
                response.sendRedirect("icloud?method=admin");
            }else if(action.equals("showUser")){
                String userid=request.getParameter(Const.Parameter_id);
                response.sendRedirect("icloud?method=admin&action=showUser&id="+userid);
            }
        }
    }

    public boolean deleteFile(UserFile userFile){
        File file=new File(userFile.getPath());
        return file.delete();
    }
}
