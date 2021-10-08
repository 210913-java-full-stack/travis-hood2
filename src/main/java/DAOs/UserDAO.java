package DAOs;


import models.UserModel;

import java.sql.*;

public class UserDAO implements BankingAppCRUD{
    private Connection conn;
    public UserDAO(Connection conn){
        this.conn = conn;
    }
    private UserModel tempModel;


    public static int getUserIDByUserN(String userName, Connection conn) throws SQLException {
        String sql = "select userID from users where userName = ?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, userName);
        ResultSet rs = stat.executeQuery();
        if (rs.next()) {
            return rs.getInt("userID");
        }
        return 0;
    }

    public void saveNewUser(String userName, String userPassword) throws SQLException {
        try{
            String sql = "INSERT INTO users (userName, userPassword) VALUES (?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);


            pstmt.setString(1, userName);
            pstmt.setString(2, userPassword);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("You probably tried to enter an invalid user ID");
        }
    }


    @Override
    public void saveNew(String s, String t, String r) throws SQLException {

    }

    @Override
    public String getByUserName(String s) throws SQLException {

        return null;
    }

    @Override
    public void updateByUserID(String s) throws SQLException {

    }

    public static UserModel getUserLogin(String userName,String userPassword, Connection conn) throws SQLException {
        UserModel user0 = null;
        String sql = "Select * FROM users WHERE userName = ?";

        PreparedStatement stat = conn.prepareStatement(sql);

        stat.setString(1, userName);


        ResultSet rs = stat.executeQuery();
        if(rs.next()) {
            //System.out.println(rs.getString("userName"));

            user0 = new UserModel(rs.getInt("userID"), rs.getString("userName"), rs.getString("userPassword"));
        }
        return user0;
    }




    public static String getUserName(Connection conn, int userID) throws SQLException {
        String sql = "Select userName from users where userID = ?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setInt(1, userID);
        ResultSet rs = stat.executeQuery();
        if (rs.next()) {
            return rs.getString("userName");
        }
        return "fail";
    }

    @Override
    public void deleteByPassword() throws SQLException {

    }


    public int getByUserID(){//
        if(tempModel != null){
            int userID = tempModel.getUserID();
            return userID;
        }
        return -1;

    }

    public void updateByID(String s) throws SQLException {

    }

}
