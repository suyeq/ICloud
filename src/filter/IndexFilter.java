package filter;

import dao.UserFileDao;
import model.UserFile;
import util.Const;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "IndexFilter",urlPatterns = "/*")
public class IndexFilter implements Filter {
    private List<UserFile> fileList= new ArrayList<>();;
    private UserFileDao userFileDao=new UserFileDao();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse) resp;
        String filename=req.getParameter(Const.Parameter_filename);
        try{
         fileList.clear();
         if(filename==null||filename.equals("")){
            fileList=userFileDao.findAllShare();
           }else {
                    fileList=userFileDao.findAllShare(filename);
                }
        }catch (Exception e){
            e.printStackTrace();
        }
        req.setAttribute("fileList",fileList);
        //req.getRequestDispatcher("index.jsp").forward(request,response);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
