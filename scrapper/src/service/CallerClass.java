package service;

import util.DatabaseConnection;
import util.Dealayo;
import util.Muncha;
import util.OkDaam;

public class CallerClass {
    public void caller(String searchkey)throws Exception{
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Muncha muncha = new Muncha();
        Dealayo dealayo = new Dealayo();
        OkDaam okDaam = new OkDaam();

        searchkey=searchkey.replaceAll(" ","+");
        muncha.fetchUrl(searchkey);
        okDaam.fetchUrl(searchkey);
        dealayo.fetchUrl(searchkey);
    }
}


