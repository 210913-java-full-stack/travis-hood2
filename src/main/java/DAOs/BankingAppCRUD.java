package DAOs;

import utils.datastructures.MyArrayList;

import java.sql.Array;
import java.sql.SQLException;

public interface BankingAppCRUD {
    //connection


    //create
    public void saveNew(String s, String t, String r) throws SQLException;

    //read
    public String getByUserName(String s) throws SQLException;

    //update
    public void updateByUserID(String s) throws SQLException;

    //delete
    public void deleteByPassword() throws SQLException;
}
