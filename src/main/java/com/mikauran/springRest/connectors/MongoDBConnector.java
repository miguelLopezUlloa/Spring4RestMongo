package com.mikauran.springRest.connectors;
import java.util.List;
import java.net.UnknownHostException;
import com.mongodb.MongoClient;

/**
 * 
 * @author Mikauran
 * 
 * MongoDb class to open a connection to the database
 *
 */
public class MongoDBConnector {

	
    public MongoClient crearConexion() {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
 
        return mongo;
    }
    
    public void printDatabases(MongoClient mongo) {
        List<String> dbs = mongo.getDatabaseNames();
        for (String db : dbs) {
            System.out.println(" - " + db);
        }
    }
    
    
    public List<String> returnDatabases(MongoClient mongo) {
        List<String> dbs = mongo.getDatabaseNames();
        
        return dbs;
    }
    
    

}
