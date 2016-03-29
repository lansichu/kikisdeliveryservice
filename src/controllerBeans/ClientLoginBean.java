package controllerBeans;

import com.sun.rowset.JdbcRowSetImpl;
import entityClasses.Delivery;

import javax.sql.rowset.JdbcRowSet;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by danielchoi on 2016-03-28.
 */
public class ClientLoginBean {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/Kiki's_DeliveryService";
    static final String USER = "root";
    static final String PASS = "Iloveme711";
    private JdbcRowSet rowSet = null;

    private Connection conn;
    private Statement stmt;
    private ResultSet sentDeliveriesRs;
    private ResultSet receiveDeliveriesRs;
    private ArrayList<Delivery> sentDeliveries = new ArrayList<>();
    private ArrayList<Delivery> receivedDeliveries = new ArrayList<>();

    public ClientLoginBean(int clientID) {
        try {
            Class.forName(JDBC_DRIVER);
//            rowSet = new JdbcRowSetImpl();
//            rowSet.getURL(DB_URL);
//            rowSet.setUsername(USER);
//            rowSet.setPassword(PASS);
//            rowSet.setCommand("SELECT * FROM clients");
//            rowSet.execute();

            System.out.println("ClientLoginBean constructor called");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM delivery WHERE sender_ID = "+clientID;
            sentDeliveriesRs = stmt.executeQuery(sql);
            while (sentDeliveriesRs.next()) {
                Delivery d = new Delivery();
                d.setdID(sentDeliveriesRs.getInt("dID"));
                d.setSender_ID(sentDeliveriesRs.getInt("sender_ID"));
                d.setReceiver_ID(sentDeliveriesRs.getInt("receiver_ID"));
                d.setType(sentDeliveriesRs.getString("type"));
                d.setStatus("TEST");
                sentDeliveries.add(d);
            }

//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            stmt = conn.createStatement();
            sql = "SELECT * FROM delivery WHERE receiver_ID=" +clientID;
            receiveDeliveriesRs = stmt.executeQuery(sql);
            while (receiveDeliveriesRs.next()) {
                Delivery d = new Delivery();
                d.setdID(receiveDeliveriesRs.getInt("dID"));
                d.setSender_ID(receiveDeliveriesRs.getInt("sender_ID"));
                d.setReceiver_ID(receiveDeliveriesRs.getInt("receiver_ID"));
                d.setType(receiveDeliveriesRs.getString("type"));
                d.setStatus("TEST");
                receivedDeliveries.add(d);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Delivery> getSentDeliveries() {
        return sentDeliveries;
    }

    public ArrayList<Delivery> getReceivedDeliveries() {
        return receivedDeliveries;
    }
}
