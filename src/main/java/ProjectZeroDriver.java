import services.NewUserLogin;
import services.VerifiedUser;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;



public class ProjectZeroDriver {


    public static void main(String[] args) throws SQLException, IOException {

        //prompt user for integer for Account Creation, Account Access, Quit

        System.out.println("================" +
                "\nWelcome, please select from one of the following options:" +
                "\n================" +
                "\n1)Create a new account" +
                "\n2)Account login" +
                "\n3)Quit" +
                "\n================");

        Scanner sc = new Scanner(System.in);//instantiate new Scanner obj. bound to System.in
        String input = sc.next();

        if ("1".equals(input) || "one".equals(input) || "One".equals(input)) {
            NewUserLogin.createNew();
        } else if ("2".equals(input)||"two".equals(input) || "Two".equals(input)){
            VerifiedUser.verifyLogin();
        } else {
            System.out.println("Maybe next time. Thanks for visiting with us today!");
        }
    }
}



