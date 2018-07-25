package dao;

import model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends DBConnection{
    private List<User> userList= new ArrayList<>();

    public Connection linkDatabase() throws SQLException, IOException, ClassNotFoundException {
        return DBConnection.getConnection();
    }

    public User login(String name, String pass) throws SQLException, IOException, ClassNotFoundException {
        String sql="select * from user where username=?";
        con=linkDatabase();
        sta=con.prepareStatement(sql);
        sta.setString(1,name);
        ResultSet set=sta.executeQuery();
        while(set.next()){
            if(set.getString(2).equals(name)){
                return new User(set.getInt(1),set.getString(2),set.getString(3),set.getInt(4));
            }
        }
        return null;
    }

    public User login(int sid) throws SQLException, IOException, ClassNotFoundException {
        String sql="select * from user where id=?";
        con=linkDatabase();
        sta=con.prepareStatement(sql);
        sta.setInt(1,sid);
        ResultSet set=sta.executeQuery();
        while(set.next()){
            if(set.getInt(1)==sid){
                return new User(set.getInt(1),set.getString(2),set.getString(3),set.getInt(4));
            }
        }
        return null;
    }

    public int  register(String name,String pass) throws SQLException, IOException, ClassNotFoundException {
        String sql="insert into user values(0,?,?,0)";
        con=linkDatabase();
        sta=con.prepareStatement(sql);
        sta.setString(1,name);
        sta.setString(2,pass);
        return sta.executeUpdate();
    }

    public List getUserList() throws SQLException, IOException, ClassNotFoundException {
        String sql="select * from user";
        con=linkDatabase();
        sta=con.prepareStatement(sql);
        ResultSet set=sta.executeQuery();
        while(set.next()){
            userList.add(new User(set.getInt(1)
                    ,set.getString(2)
                    ,set.getString(3)
                    ,set.getInt(4)));
        }
        return userList;
    }

    public int updataPass(String name,String old,String news) throws SQLException, IOException, ClassNotFoundException {
        String sql="select * from user where password=? and username=?";
        con=linkDatabase();
        sta=con.prepareStatement(sql);
        sta.setString(1,old);
        sta.setString(2,name);
        ResultSet set=sta.executeQuery();
        while(set.next()){
            String sql2="UPDATE user SET password=? where username=?";
            con=linkDatabase();
            sta=con.prepareStatement(sql2);
            sta.setString(1,news);
            sta.setString(2,name);
            int sets=sta.executeUpdate();
            if(sets>0){
                return 1;
            }
        }
        return 0;
    }

    public void deleteUsers(int id) throws SQLException, IOException, ClassNotFoundException {
        String sql="delete from user where id=?";
        con=linkDatabase();
        sta=con.prepareStatement(sql);
        sta.setInt(1,id);
        sta.executeUpdate();
    }
}
