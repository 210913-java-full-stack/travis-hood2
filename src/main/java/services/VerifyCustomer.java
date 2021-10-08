package services;

import DAOs.AccountDAO;
import DAOs.CustomerDAO;
import DAOs.UserDAO;
import utils.ConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class VerifyCustomer {


    public static void NameCollect(String userName) throws SQLException {
        Connection conn = null;

        try {
            conn = ConnectionManager.getConnection();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("To finish signing up with our bank, we need your first name");

        Scanner sc = new Scanner(System.in);
        String firstName = sc.next();//collect first name


        if (firstName.matches("[a-zA-z\\D]{2,25}$")){
            System.out.println("Now, we just need your last name for you as a customer");

        } else {
            System.out.println("That isn't within our parameters, try again");
            do {
                VerifyCustomer.NameCollect(userName);
            } while (firstName.matches("[a-zA-Z\\D]{2,25}$") != true);
        }

        String lastName = sc.next();//collect last name

        if (lastName.matches("[a-zA-z\\D]{2,25}$")){
            System.out.println("Great! Just a few more steps to get you set up with us");
        } else {
            System.out.println("That isn't within our parameters, try again");
            do {
                VerifyCustomer.NameCollect(userName);
            } while (lastName.matches("[a-zA-Z\\D]{2,25}$") != true);
        }
        CustomerDAO newCustomer = new CustomerDAO(conn);

        UserDAO userID = new UserDAO(conn);
        int NewUserID = userID.getUserIDByUserN(userName, conn);//pass in userName and connection to UserDAO in order to get the UserID and persist through the customers table


        newCustomer.saveNewCustomer(NewUserID, firstName, lastName, conn);//save userID, firstname and last name into customers table

        VerifiedUser.addNewAccount(NewUserID);
    }

}
