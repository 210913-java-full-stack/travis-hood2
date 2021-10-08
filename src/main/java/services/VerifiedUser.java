package services;

import DAOs.AccountDAO;
import DAOs.UserDAO;
import models.UserModel;
import utils.ConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class VerifiedUser extends UserDAO {



    public VerifiedUser(Connection conn) {
        super(conn);
    }

    public static void addNewAccount(int NewUserID) throws SQLException {
        Connection conn = null;

        try {
            conn = ConnectionManager.getConnection();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        AccountDAO newAccount = new AccountDAO(conn);
        System.out.println("==========================");
        System.out.println("Great, we just need to set you up an account\nWould you like to start the account with some money?");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        double startingBalance;
        String accountType;
        if("y".equals(input)||"yes".equals(input)||"Y".equals(input)||"Yes".equals(input)){
            System.out.println("==========================");
            System.out.println("How much would you like to open the account with?");

        } else {

        }
        Scanner sc1 = new Scanner(System.in);
        startingBalance = sc1.nextDouble();
        System.out.println("Would you like to assign a moniker/nick-name to the account?\n");
        Scanner sc2 = new Scanner(System.in);
        String input2 = sc2.nextLine();

        if("y".equals(input2)||"yes".equals(input2)||"Y".equals(input2)||"Yes".equals(input2)){
            System.out.println("What would you like to name the account?\n");

        } else {
            accountType = null;
        }
        Scanner sc3 = new Scanner(System.in);
        accountType = sc3.next();
        newAccount.addNewAccount(NewUserID, startingBalance, accountType);
        VerifiedUser.verifyLogin();
    }

    public static void verifyLogin() throws SQLException {
        Connection conn = null;

        try {
            conn = ConnectionManager.getConnection();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        UserDAO u = new UserDAO(conn);
        Scanner sc = new Scanner(System.in);
        System.out.println("Please verify your user name to view account(s):\n");
        String userName = sc.next();

        System.out.println("\nPlease verify your password:\n");
        String userPassword = sc.next();



        UserModel LoginInfo = u.getUserLogin(userName, userPassword, conn);//get user's login from table for comparison

        if ((LoginInfo.getUserName().equals(userName)) && (LoginInfo.getUserPassword().equals(userPassword))) {
            AccountView.AccountScreen(userName);//pass userName through to AccountScreen method in AccountView Class
        }

    }


}
