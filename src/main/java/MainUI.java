

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainUI {
    public JFrame view = new JFrame();
    public JButton btnAddProduct = new JButton("Add New Product");
    public JButton btnAddCustomer = new JButton("Add New Customer");
    public JButton btnAddPurchase = new JButton("Add New Purchase");

    public MainUI() {
        this.view.setDefaultCloseOperation(3);
        this.view.setTitle("Store Management System");
        this.view.setSize(1000, 600);
        this.view.getContentPane().setLayout(new BoxLayout(this.view.getContentPane(), 3));
        JLabel title = new JLabel("Store Management System");
        title.setFont(title.getFont().deriveFont(24.0F));
        this.view.getContentPane().add(title);
        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(this.btnAddProduct);
        panelButtons.add(this.btnAddCustomer);
        panelButtons.add(this.btnAddPurchase);
        this.view.getContentPane().add(panelButtons);
        this.btnAddProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AddProductUI ac = new AddProductUI();
                ac.run();
            }
        });
        this.btnAddCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AddCustomerUI ac = new AddCustomerUI();
                ac.run();
            }
        });
        this.btnAddPurchase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AddPurchaseUI ap = new AddPurchaseUI();
                ap.run();
            }
        });
    }
}
