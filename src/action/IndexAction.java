package action;

import dao.UserFileDao;
import model.UserFile;
import util.Const;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IndexAction extends Action{
    private List<UserFile> fileList=new ArrayList<>();
    private UserFileDao userFileDao=new UserFileDao();
    public IndexAction(HttpServletRequest request, HttpServletResponse response){
        this.request=request;
        this.response=response;
    }

    public void carriyShow() throws SQLException, IOException, ClassNotFoundException, ServletException {
        String filename=request.getParameter(Const.Parameter_filename);
        if(filename==null||filename.equals("")){
            fileList=userFileDao.findAllShare();
        }else {
            fileList=userFileDao.findAllShare(filename);
        }
        request.setAttribute("fileList",fileList);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
