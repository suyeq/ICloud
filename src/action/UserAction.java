package action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAction extends Action {

    public UserAction(HttpServletRequest request, HttpServletResponse response){
        this.request=request;
        this.response=response;
    }

    public void carriyShow() throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/user/index.jsp").forward(request,response);
    }
}
