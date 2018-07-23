package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Action {

    protected HttpServletResponse response=null;
    protected HttpServletRequest request=null;
    protected  String Parameter_username="username";
    protected  String Parameter_pass="password";
    protected  String Parameter_pass1="password1";
    protected  String Parameter_action="action";
    protected  String Parameter_filename="filename";
    protected  String Parameter_id="id";

}
