package services;

import DAOs.AccountDAO;
import DAOs.CustomerDAO;
import DAOs.UserDAO;
import models.AccountModel;
import models.UserModel;
import utils.ConnectionManager;
import utils.datastructures.MyArrayList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountView {
    public static void AccountScreen(String userName) throws SQLException {
        Connection conn = null;

        try {
            conn = ConnectionManager.getConnection();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        AccountDAO accountID = new AccountDAO(conn);
        accountID.getAcctID(userName);


        System.out.println("================" +
                "\nWelcome, please select from one of the following options:" +
                "\n================" +
                "\n1) View Account Balance" +
                "\n2) Deposit Money" +
                "\n3) Withdraw Money" +
                "\n4) Add/create an Cash Account" +
                "\n5) Quit" +
                "\n================\n");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        switch(input){
            case "1":
                Scanner input2 = new Scanner(System.in);
                accountID.getAcctID(userName);
                System.out.println("Which account balance would you like to see?");

                double choice = input2.nextDouble();
                System.out.println("$$$$$$$$$$$$$$$$");
                System.out.println("$"+ accountID.getAccountBalanceByAcctID(choice));
                System.out.println("$$$$$$$$$$$$$$$$");
                AccountView.AccountScreen(userName);
                break;
            case "2":
                Scanner input3 = new Scanner(System.in);
                System.out.println("Which account would you like to deposit to?");
                accountID.getAcctID(userName);

                double choice2 = input3.nextDouble();
               System.out.println("How much money would you like to deposit?");
               Scanner input4 = new Scanner(System.in);
                double depositChoice = input4.nextDouble();

                if (depositChoice < 0 ){
                    System.out.println("That's a negative number, we don't do that here");
                } else {
                    accountID.saveDeposit(depositChoice, choice2);
                }
                AccountView.AccountScreen(userName);
                break;
            case "3":
                Scanner input5 = new Scanner(System.in);

                System.out.println("Which account would you like to withdraw from?");
                accountID.getAcctID(userName);
                double choice3 = input5.nextDouble();

                System.out.println("How much money would you like to withdraw?");
                Scanner input6 = new Scanner(System.in);
                double withdrawChoice = input6.nextDouble();

                if (withdrawChoice < 0 ){
                    System.out.println("That's a negative number, we don't do that here");
                } else {
                    accountID.saveWithdraw(withdrawChoice, choice3);
                }
                AccountView.AccountScreen(userName);
                break;
            case "4":
                System.out.println(
                        "What would you like to name the new account?");
                Scanner sc2 = new Scanner(System.in);
                String input7 = sc2.next();
//                accountID.getAcctID(userName);
                System.out.println("please re-enter user name\n");
                String userName1 = sc2.next();
                System.out.println("please re-enter password:\n");
                String userPassword = sc2.next();
                UserDAO checkLogin = new UserDAO(conn);
                UserModel LoginInfo = checkLogin.getUserLogin(userName1, userPassword, conn);

//                CustomerDAO NewCustomer = new CustomerDAO(conn);
//                NewCustomer.saveNewCustomer(1, firstName, lastName,conn);
                if ((LoginInfo.getUserName().equals(userName1))&&(LoginInfo.getUserPassword().equals(userPassword))) {
                    System.out.println("Show the account some love and give it an opening balance");
                }
                double openingBal = sc.nextDouble();
                if (input7.matches("[a-zA-Z0-9]{2,25}$") && openingBal > 0){
//                    MyArrayList<AccountModel> acctID = accountID.getAcctID(userName);
//                    System.out.println(acctID);
                    //AccountDAO addNewAcct = new AccountDAO();
                    int userID = UserDAO.getUserIDByUserN(userName1, conn);
                    //addNewAcct.getByUserName();
                    accountID.addNewAcct2Existent(userID,openingBal,input7);
                    AccountView.AccountScreen(userName1);
                } else {
                    System.out.println("You probably included a special character in the account name, or tried to deposit a negative number");
                    AccountView.AccountScreen(userName);
                }
                break;
            case "5":
                AccountDAO acctBal = new AccountDAO(conn);
                acctBal.getAccountTotalByAcctUser(userName);
                AccountView.AccountScreen(userName);
                break;
        }
    }
}
