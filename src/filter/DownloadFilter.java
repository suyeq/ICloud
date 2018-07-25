package filter;

import dao.UserFileDao;
import model.User;
import model.UserFile;
import util.Const;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(filterName = "DownloadFilter",urlPatterns = "/icloud/download/*")
public class DownloadFilter implements Filter {
    private UserFile userFile=null;
    private UserFileDao userFileDao=new UserFileDao();
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter(Const.Parameter_id));
        System.out.println("id"+id);
        try {
            User user=(User)((HttpServletRequest)req).getSession().getAttribute(Const.SESSION_USER);
            userFile=userFileDao.findsbFile(id);
            if(user==null || user.getIsAdmin()==1){
                System.out.println("dhashg345");
                req.setAttribute(Const.SESSION_USERFILE,userFile);
                chain.doFilter(req, resp);
            }else if(userFile.getIsShared()==1){
                System.out.println("dhashg");
                req.setAttribute(Const.SESSION_USERFILE,userFile);
                chain.doFilter(req,resp);
            }else {
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().println("无访问权限");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
