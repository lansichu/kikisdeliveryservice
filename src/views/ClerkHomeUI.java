package views;

import controllerBeans.ClerkBean;
import entityClasses.Address;
import net.miginfocom.layout.Grid;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by stellafang. on 2016-03-27.
 */
public class ClerkHomeUI extends JPanel{
//    private JButton browseClients = new JButton("Browse Clients");
//    private JButton editClients = new JButton("Create or Update Clients");
//    private JButton browseDeliveries = new JButton("Browse Deliveries");
//    private JButton editDeliveries= new JButton("Create or Update Deliveries");
    private ClerkBean bean = new ClerkBean();

    public ClerkHomeUI() {
        //new GridLayout(1,1);
        setBorder(new TitledBorder(
                new EtchedBorder(), "Clerk view"));
//        setLayout(new BorderLayout(5,5));
//        initButtons().setVisible(true);
//        add(initButtons(), BorderLayout.CENTER);

        // NEW TAB
        JTabbedPane jtab = new JTabbedPane();

        //ADD TAB: BROWSE CLIENTS
        JComponent everythingClients = new JPanel();
        ClerkUI browseClients = new ClerkUI("SELECT * " +
                "FROM clients " +
                "NATURAL LEFT JOIN address"
                , "All Clients");
        browseClients.setSize(browseClients.getWidth(), browseClients.getHeight());
        everythingClients.add(browseClients);
        everythingClients.add(new ClientsUI(), new AddressUI());
        jtab.add("browse Clients", everythingClients);


        //ADD TAB: EDIT CLIENTS
        JComponent editClients = new ClientsUI();
        jtab.add("edit Clients", editClients);

        //ADD TAB: BROWSE DELIVERIES
        JComponent browseDeliveries = new ClerkUI(
                "SELECT clID, fname, lname, dID, type, status, sender_ID, receiver_ID " +
                        "FROM clients " +
                        "LEFT JOIN delivery " +
                        "ON clients.clID=delivery.sender_ID " +
                        "or clients.clID=delivery.receiver_ID"
                , "All Deliveries");
        jtab.add("browse Deliveries", browseDeliveries);

        //ADD TAB: EDIT DELIVERIES
        JComponent editDeliveries = new DeliveryUI();
        jtab.add("edit Deliveries", editDeliveries);

        //ADD TAB: BROWSE PAYMENTS
        JComponent everythingPayments = new JPanel();
        everythingPayments.setLayout(new GridLayout(1,1));
        JComponent browseCreditPayments = new ClerkUI(
                "SELECT amount, payID, onDate, credit_card.dID, credit_card_num, CSV, name, expiry_date, credit_card.type " +
                        "FROM credit_card " +
                        "LEFT JOIN delivery " +
                        "ON credit_card.dID=delivery.dID"
                , "Credit Card Payments");
        everythingPayments.add(browseCreditPayments);
        JComponent browseCashPayments = new ClerkUI(
                "SELECT amount, payID, onDate, cash.dID " +
                        "FROM cash " +
                        "LEFT JOIN delivery " +
                        "ON cash.dID=delivery.dID"
                , "Cash Payments");
        everythingPayments.add(browseCashPayments);
        jtab.add("browse Payments", everythingPayments);


        add(jtab, BorderLayout.CENTER);
        jtab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }


//    private JPanel initButtons() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
//        panel.add(browseClients);
//        browseClients.addActionListener(new ButtonHandler());
//        panel.add(editClients);
//        editClients.addActionListener(new ButtonHandler());
//        panel.add(browseDeliveries);
//        browseDeliveries.addActionListener(new ButtonHandler());
//        panel.add(editDeliveries);
//        editDeliveries.addActionListener(new ButtonHandler());
//        return panel;
//    }


//    private class ButtonHandler implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            JFrame f = new JFrame();
//            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            f.getContentPane().setLayout(new FlowLayout(FlowLayout.LEADING));
//            switch (e.getActionCommand()) {
//                case "Browse Clients":
//                    ClerkUI clerk = new ClerkUI();
//                    JTable table1 = clerk.initTable("SELECT * FROM clients LEFT JOIN " +
//                            "address ON clients.PC=address.PC and clients.house_num=address.house_num");
//                    table1.setAutoCreateRowSorter(true);
//                    //table1.getAutoResizeMode();
//                    //table1.getAutoscrolls();
//                    f.add(clerk);
//                    break;
//                case "Create or Update Clients":
//                    ClientsUI clientsUI = new ClientsUI();
//                    add(clientsUI);
//                    break;
//                case "Browse Deliveries":
//
//                    break;
//                case "Create or Update Deliveries":
//                    break;
//
//                default:
//                    JOptionPane.showMessageDialog(null,
//                            "Invalid command");
//            }
//            f.setSize(700, 800);
//            f.setVisible(true);
//        }
//    }


}