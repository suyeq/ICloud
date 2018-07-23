package service;

import action.AdminAction;
import action.LoginAction;
import action.RegisterAction;
import action.UserAction;
import dao.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "BaseServlet",urlPatterns = "/icloud")
public class BaseServlet extends HttpServlet {
    private  static String Parameter_method ="method";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String method=request.getParameter(Parameter_method);
        try{
            if(method.equals("login")){
                new LoginAction(request,response).carriryShow();
            }else if(method.equals("register")){
                new RegisterAction(request,response).carriryShow();
            }else  if(method.equals("admin")){
                new AdminAction(request,response).carriyShow();
            }else  if(method.equals("user")){
                new UserAction(request,response).carriyShow();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
