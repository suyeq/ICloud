package action;

import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginAction extends Action {
    private String name=null;
    private String pass=null;



    public LoginAction(HttpServletRequest request,HttpServletResponse response){
        this.request=request;
        this.response=response;
    }

    public void carriryShow() throws IOException, SQLException, ClassNotFoundException, ServletException {
        name=request.getParameter(Parameter_username);
        pass=request.getParameter(Parameter_pass);
        if(name.isEmpty()||pass.isEmpty()){
            request.setAttribute("msg","账号或密码不能为空");
            request.getRequestDispatcher("common/login.jsp").forward(request,response);
        }else{
            User user=new UserDao().login(name,pass);
            if(user==null){
                request.setAttribute("msg","未注册");
                request.getRequestDispatcher("common/login.jsp").forward(request,response);
            }else{
                if(user.getIsAdmin()==1){
                    request.getSession().setAttribute("user",user);
                    response.sendRedirect("icloud?method=admin");
                }else if(!user.getPassword().equals(pass)){
                    request.setAttribute("msg","密码错误");
                    request.getRequestDispatcher("common/login.jsp").forward(request,response);
                }else  if(user.getIsAdmin()==0){
                    response.sendRedirect("icloud?method=user");
                }
            }

        }

    }



}
