package DAOs;

import models.AccountModel;
import utils.datastructures.MyArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO implements BankingAppCRUD {
    private Connection conn;

    public AccountDAO(Connection conn){
        this.conn = conn;
    }

    public AccountDAO() {

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


    public void saveDeposit(Double d, double i) throws SQLException {
        String sql = "UPDATE accounts SET accountBalance = (? + accountBalance) WHERE accountID = (?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDouble(1, d);
        pstmt.setDouble(2, i);
        ResultSet rs = pstmt.executeQuery();
        return;
    }
    public void saveWithdraw(Double d, double i) throws SQLException {
        String sql = "UPDATE accounts SET accountBalance = (accountBalance - ?) WHERE accountID = (?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDouble(1, d);
        pstmt.setDouble(2, i);
        ResultSet rs = pstmt.executeQuery();
        return;
    }


    public String getAccountBalanceByAcctID(double accountID) throws SQLException {

            String sql = "SELECT accountBalance FROM accounts WHERE accountID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, accountID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("accountBalance");
            }
            return "fail";
    }



    public String addNewAcct2Existent(int userID, double openingBalance, String acctType) throws SQLException {
        String sql = "insert into accounts (userID, accountBalance, accountType) values (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, userID);
        pstmt.setDouble(2, openingBalance);
        pstmt.setString(3, acctType);

        pstmt.executeQuery();

        return "Account Added";
    }
    public void getAccountTotalByAcctUser(String userName) throws SQLException {
        String sql = "select accountBalance from users u join accounts a on u.userID = a.userID where userName = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userName);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {
            System.out.println("================\n****************\nTotal Balance:\n"+ rs.getDouble("accountBalance"));
        }

    }

    public void addNewAccount(int NewUserID, double accountBalance, String accountType) throws SQLException {//might make a diff. method for users that want nickname
        String sql = "insert into accounts (userID, accountBalance, accountType) values (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, NewUserID);
        pstmt.setDouble(2, accountBalance);
        pstmt.setString(3, accountType);
        pstmt.executeQuery();


    }


    public String updateAcctTypeByID(String acctType, int accountID) throws SQLException {
        String sql = "update accounts set accountType = ? where accountID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, acctType);
        pstmt.setInt(2, accountID);
        pstmt.executeQuery();

        return "Account Type Updated";


    }

    public void deleteByPassword() throws SQLException {

    }



    public MyArrayList<AccountModel> getAcctID(String userName) throws SQLException {
        String sql = "SELECT accountID from accounts a join users u on a.userID = u.userID where userName = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userName);

        ResultSet rs = pstmt.executeQuery();
        MyArrayList<AccountModel> accountIDArray = new MyArrayList<>();

        while (rs.next()) {
            accountIDArray.add(new AccountModel(rs.getInt("accountID")));
        }
        System.out.println("================\n****************\nAccounts you own:\n");
        for (int i = 0; i < accountIDArray.size(); i ++){
            System.out.println("Account#: "+ accountIDArray.get(i).getAccountID());
        }
        return accountIDArray;
    }

}
