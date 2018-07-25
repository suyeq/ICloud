package action;

import dao.UserDao;
import dao.UserFileDao;
import model.User;
import model.UserFile;
import util.Const;
import util.StringUtl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminAction extends Action{
    private List<UserFile> fileList=new ArrayList<>();
    private List<User> userList=new ArrayList<>();
    private UserFileDao userFileDao=new UserFileDao();
    private UserDao userDao=new UserDao();
    private User user=null;
    public AdminAction(HttpServletRequest request, HttpServletResponse response){
        this.request=request;
        this.response=response;
    }

    public void carriyShow() throws ServletException, IOException, SQLException, ClassNotFoundException {
        user= (User) request.getSession().getAttribute("user");
        String action=request.getParameter(Const.Parameter_action);
        String filename=request.getParameter(Const.Parameter_filename);
        String id=request.getParameter(Const.Parameter_id);
        if(action==null||action.equals("")){//Allshare
            if(filename==null||filename.equals("")){
                fileList=userFileDao.findAllShare();
            }else {
                fileList=userFileDao.findAllShare(filename);
            }
            request.setAttribute("fileList",fileList);
            request.getRequestDispatcher("WEB-INF/admin/index.jsp").forward(request,response);

        }else if (action.equals("all")){
            if(filename==null||filename.equals("")){
                fileList=userFileDao.findAllFile();
            }else {
                fileList=userFileDao.findAllFile(filename);
            }
            request.setAttribute("fileList",fileList);
            request.getRequestDispatcher("WEB-INF/admin/all.jsp").forward(request,response);

        }else if(action.equals("myshare")){
            fileList=userFileDao.findMyFile(user.getId(),1);
            request.setAttribute("fileList",fileList);
            request.getRequestDispatcher("WEB-INF/admin/myshare.jsp").forward(request,response);

        }else if(action.equals("mydisk")){
            fileList=userFileDao.findMyFile(user.getId());
            request.setAttribute("fileList",fileList);
            request.getRequestDispatcher("WEB-INF/admin/mydisk.jsp").forward(request,response);

        }else if(action.equals("userList")){
            userList=userDao.getUserList();
            request.setAttribute("userList",userList);
            request.getRequestDispatcher("WEB-INF/admin/user_list.jsp").forward(request,response);

        }else if(action.equals("showUser")){
            fileList=userFileDao.findMyFile(Integer.parseInt(id));
            user=userDao.login(Integer.parseInt(id));
            request.setAttribute("fileList",fileList);
            request.setAttribute("theUser",user);
            request.getRequestDispatcher("WEB-INF/admin/show_user.jsp").forward(request,response);

        }else  if(action.equals("edit")){
            String password=request.getParameter(Const.Parameter_pass);
            String password1=request.getParameter(Const.Parameter_pass1);
            if(password!=null){
                int isSuccess=userDao.updataPass(user.getUsername(),password,password1);
                if(isSuccess==1){
                    request.setAttribute("msgSuccess","修改成功");
                }else if(isSuccess==0){
                    request.setAttribute("msgFail","修改失败");
                }
            }
            request.getRequestDispatcher("WEB-INF/admin/edit.jsp").forward(request,response);
        }else if(action.equals("deleteUser")){
            String [] ids=request.getParameterValues(Const.Parameter_ids);
            int [] userid= StringUtl.toInter(ids);
            for(int i=0;i<userid.length;i++){
                userDao.deleteUsers(userid[i]);
            }
            response.sendRedirect("icloud?method=admin&action=userList");
        }else if(action.equals("logout")){
            request.getSession().removeAttribute(Const.SESSION_USER);
            response.sendRedirect(request.getServletContext().getContextPath().toString()+"/"+"index.jsp");
        }

    }
}
