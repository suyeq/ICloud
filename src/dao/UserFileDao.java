package dao;

import model.User;
import model.UserFile;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFileDao extends DBConnection{
    private List<UserFile> list=new ArrayList<>();
    private UserDao user=new UserDao();
    public Connection linkDatabase() throws SQLException, IOException, ClassNotFoundException {
        return DBConnection.getConnection();
    }

    public List findAllShare() throws SQLException, IOException, ClassNotFoundException {

        String sql="select * from file where isshared=1";
        con=linkDatabase();
        sta=con.prepareStatement(sql);
        ResultSet set=sta.executeQuery();
        return  AddUserFile(set);
    }

    public List findAllShare(String sfilename) throws SQLException, IOException, ClassNotFoundException {
        String sql="select * from file where isshared=1 and filename=?";
        con=linkDatabase();
        sta=con.prepareStatement(sql);
        sta.setString(1,sfilename);
        ResultSet set=sta.executeQuery();
        return  AddUserFile(set);
    }

    public List findAllFile() throws SQLException, IOException, ClassNotFoundException {
        String sql="select * from file ";
        con=linkDatabase();
        sta=con.prepareStatement(sql);
        ResultSet set=sta.executeQuery();
        return  AddUserFile(set);
    }

    public List findAllFile(String filename) throws SQLException, IOException, ClassNotFoundException {
        String sql="select * from file where filename=?";
        con=linkDatabase();
        sta=con.prepareStatement(sql);
        sta.setString(1,filename);
        ResultSet set=sta.executeQuery();
        return  AddUserFile(set);
    }

    public List findMyFile(int userid,int isShared) throws SQLException, IOException, ClassNotFoundException {
        String sql="select * from file where ownerid=? and isshared=?";
        con=linkDatabase();
        sta=con.prepareStatement(sql);
        sta.setInt(1,userid);
        sta.setInt(2,isShared);
        ResultSet set=sta.executeQuery();
        return  AddUserFile(set);
    }

    public List findMyFile(int userid) throws SQLException, IOException, ClassNotFoundException {
        String sql="select * from file where ownerid=?";
        con=linkDatabase();
        sta=con.prepareStatement(sql);
        sta.setInt(1,userid);
        ResultSet set=sta.executeQuery();
        return  AddUserFile(set);
    }

    public UserFile findsbFile(int id) throws SQLException, IOException, ClassNotFoundException {
        String sql="select * from file where id=?";
        con=linkDatabase();
        sta=con.prepareStatement(sql);
        sta.setInt(1,id);
        ResultSet set=sta.executeQuery();
        return AddUserFileone(set);
    }

    public void save(UserFile userFile) throws SQLException, IOException, ClassNotFoundException {
        String sql="insert into file values(NULL,?,?,?,?,?,?,?,NULL)";
        con=linkDatabase();
        sta=con.prepareStatement(sql);
        sta.setString(1, userFile.getFilename());
        sta.setString(2, userFile.getPath());
        sta.setTimestamp(3, userFile.getCreateTime());
        sta.setInt(4, userFile.getIsShared());
        sta.setInt(5, userFile.getOwnerId());
        sta.setString(6, userFile.getFileSize());
        sta.setInt(7, userFile.getCount());
        sta.executeUpdate();
    }

    public List AddUserFile(ResultSet set) throws SQLException, IOException, ClassNotFoundException {
        while(set.next()){
            UserFile userFile=new UserFile();
            userFile.setId(set.getInt(1));
            userFile.setFilename(set.getString(2));
            userFile.setPath(set.getString(3));
            userFile.setCreateTime(set.getTimestamp(4));
            userFile.setFileSize(set.getString(7));
            userFile.setCount(set.getInt(8));
            userFile.setIsShared(set.getInt(5));
            userFile.setOwnerId(set.getInt(6));
            userFile.setFilepassword(set.getString(9));
            userFile.setOwner(user.login(set.getInt(6)));
            list.add(userFile);
        }
        return list;
    }

    public UserFile AddUserFileone(ResultSet set) throws SQLException, IOException, ClassNotFoundException {
        UserFile userFile=new UserFile();
        while(set.next()){
            userFile.setId(set.getInt(1));
            userFile.setFilename(set.getString(2));
            userFile.setPath(set.getString(3));
            userFile.setCreateTime(set.getTimestamp(4));
            userFile.setFileSize(set.getString(7));
            userFile.setCount(set.getInt(8));
            userFile.setIsShared(set.getInt(5));
            userFile.setOwnerId(set.getInt(6));
            userFile.setFilepassword(set.getString(9));
            userFile.setOwner(user.login(set.getInt(6)));
        }
        return userFile;
    }




}
