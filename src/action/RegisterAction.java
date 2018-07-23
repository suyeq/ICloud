package action;


import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterAction extends Action{
    private String name=null;
    private String pass=null;

    public RegisterAction(HttpServletRequest request, HttpServletResponse response){
        this.request=request;
        this.response=response;
    }

    public void carriryShow() throws SQLException, IOException, ClassNotFoundException, ServletException {
        name=request.getParameter(Parameter_username);
        pass=request.getParameter(Parameter_pass);
        if(new UserDao().register(name,pass)>0){
            //response.sendRedirect("common/login.jsp");
            request.getRequestDispatcher("common/login.jsp").forward(request,response);
        }

    }
}
