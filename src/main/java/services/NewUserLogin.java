package services;


import DAOs.UserDAO;

import utils.ConnectionManager;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class NewUserLogin {



    public static void createNew() throws SQLException, IOException {

        Scanner userScan = new Scanner(System.in);
        System.out.println("Thanks for choosing to be a member of our bank. Enter user name:");

        String userName = userScan.next();//temp store userName

        if (userName.matches("[a-zA-Z0-9]{2,12}$")) {
            System.out.println("Please enter a password:");

        } else {
            System.out.println("That isn't within our parameters, try again");

            do {
                NewUserLogin.createNew();
            } while (userName.matches("[a-zA-Z0-9]{2,12}$") != true);
        }

        String userPassword = userScan.next();//temp store userPassword

        if(userPassword != null && userPassword.matches("[a-zA-Z0-9]{2,25}$")){

            UserDAO newUser = new UserDAO(ConnectionManager.getConnection());

            newUser.saveNewUser(userName, userPassword);//saving username and pass to DB - DB now has userID and username&pass in the users table
            VerifyCustomer.NameCollect(userName);//pass user to new interface to collect last name and first name

        }else {
            System.out.println("That isn't within our parameters, try again");
            do {
                NewUserLogin.createNew();
            } while (userPassword.matches("[a-zA-Z0-9]{2,12}$") != true);
        }
    }
}

