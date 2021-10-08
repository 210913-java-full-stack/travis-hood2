package DAOs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO implements BankingAppCRUD {
    private static Connection conn;

    public CustomerDAO(Connection conn){
        this.conn = conn;
    }


    public static void saveNewCustomer(int userID, String firstName, String lastName, Connection conn) throws SQLException {
        try{
            String sql = "INSERT INTO customers (userID, firstName, lastName) VALUES (?, ?, ?)";



            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, userID);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("You probably tried to enter an invalid customer ID");
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

    @Override
    public void deleteByPassword() throws SQLException {

    }


    public String getCustomerFirstName(int i) throws SQLException {
        String sql = "Select firstName from customers where customerID = ?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setInt(1, i);
        ResultSet rs = stat.executeQuery();
        if (rs.next()) {
            return rs.getString("firstName");
        }
        return "First Name lookup failed";
    }
    public String getCustomerLastName(int i) throws SQLException{
        String sql = "SELECT lastName from customers where customerID = ?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setInt(1, i);
        ResultSet rs = stat.executeQuery();
        if(rs.next()){
            return rs.getString("lastname");
        }
        return "Last Name lookup failed";
    }


    public void deleteByID() throws SQLException {

    }

}
