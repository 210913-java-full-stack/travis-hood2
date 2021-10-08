package utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static Connection conn;

    private ConnectionManager(){
        //factory singleton design
    }

    public static Connection getConnection() throws SQLException, IOException {
        if(conn == null) {//if conn is null, fetch properties in the listed file location to connect to DB
            Properties props = new Properties();
            FileReader connectionProperties = new FileReader("src/main/resources/connection.properties");
            props.load(connectionProperties);

            //"jdbc:mariadb://hostname:port/databaseName?user=username&password=password"

            String connString = "jdbc:mariadb://" +
                    props.getProperty("hostname") + ":" +
                    props.getProperty("port") + "/" +
                    props.getProperty("databaseName") + "?user=" +
                    props.getProperty("user") + "&password=" +
                    props.getProperty("password");

            conn = DriverManager.getConnection(connString);
        }
        return conn;
    }
}
