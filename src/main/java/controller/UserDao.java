package controller;



import model.User;

import java.sql.*;

public class UserDao {

    private Connection conn;

    {
        try {
            String url = "jdbc:mysql://localhost:3306/loginproject";
            String username = "root";
            String password = "@lokSingh123";

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,username, password);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int signp(User user) throws SQLException {

        String query = "insert into users(uname,upwd,uemail,umobile) values(?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(query);

        pst.setString(1, user.getName());
        pst.setString(2, user.getPassword());
        pst.setString(3, user.getEmail());
        pst.setString(4, user.getMobile());

        return  pst.executeUpdate();
    }

    public String signin(String email, String password) throws SQLException{

        String query = "select * from users where uemail = ? and upwd = ? ";
        PreparedStatement pst = conn.prepareStatement(query);

        pst.setString(1, email);
        pst.setString(2, password);

        ResultSet rs = pst.executeQuery();
        if(rs.next()){
          return rs.getString(2);
        }else {
            return  null;
        }


    }
}
