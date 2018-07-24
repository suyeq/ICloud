package action;

import dao.UserFileDao;
import util.Const;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ShareFileAction extends Action{

    private UserFileDao userFileDao=new UserFileDao();
    public ShareFileAction(HttpServletRequest request, HttpServletResponse response){
        this.request=request;
        this.response=response;
    }

    public void carriryShow() throws SQLException, IOException, ClassNotFoundException, ServletException {
        int id=Integer.parseInt(request.getParameter(Const.Parameter_id));
        String action=request.getParameter(Const.Parameter_action);
        if(action.equals("takeshare")){
            if(userFileDao.updateShareStutas(id)>0){
                response.sendRedirect("icloud?method=admin&action=mydisk");
            }
        }else if(action.equals("cancelShare")){
            if(userFileDao.updateShareStutas(id)>0){
                response.sendRedirect("icloud?method=admin&action=myshare");
            }
        }
    }
}
