

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddCustomerUI {
    public JFrame view = new JFrame();
    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");
    public JTextField txtCustomerID = new JTextField(20);
    public JTextField txtCustomerName = new JTextField(20);
    public JTextField txtAddress = new JTextField(20);
    public JTextField txtPhone = new JTextField(20);
    public JTextField txtPaymentInfo = new JTextField(20);

    public AddCustomerUI() {
        this.view.setTitle("Add Customer");
        this.view.setSize(700, 400);
        this.view.getContentPane().setLayout(new BoxLayout(this.view.getContentPane(), 3));
        String[] var10000 = new String[]{"CustomerID ", "Name ", "Address ", "Phone ", "PaymentInfo"};
        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("CustomerID "));
        line1.add(this.txtCustomerID);
        this.view.getContentPane().add(line1);
        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Name "));
        line2.add(this.txtCustomerName);
        this.view.getContentPane().add(line2);
        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("Address "));
        line3.add(this.txtAddress);
        this.view.getContentPane().add(line3);
        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Phone "));
        line4.add(this.txtPhone);
        this.view.getContentPane().add(line4);
        JPanel line5 = new JPanel(new FlowLayout());
        line4.add(new JLabel("PaymentInfo "));
        line4.add(this.txtPaymentInfo);
        this.view.getContentPane().add(line5);
        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(this.btnAdd);
        panelButtons.add(this.btnCancel);
        this.view.getContentPane().add(panelButtons);
        this.btnAdd.addActionListener(new AddCustomerUI.AddButtonListener());
        this.btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AddCustomerUI.this.view.dispose();
            }
        });
    }

    public void run() {
        this.view.setVisible(true);
    }

    class AddButtonListener implements ActionListener {
        AddButtonListener() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CustomerModel customer = new CustomerModel();
            String id = AddCustomerUI.this.txtCustomerID.getText();
            if (id.length() == 0) {
                JOptionPane.showMessageDialog((Component) null, "ProductID cannot be null!");
            } else {
                try {
                    customer.mCustomerID = Integer.parseInt(id);
                } catch (NumberFormatException var11) {
                    JOptionPane.showMessageDialog((Component) null, "CustomerID is invalid!");
                    return;
                }

                String name = AddCustomerUI.this.txtCustomerName.getText();
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog((Component) null, "Customer name cannot be empty!");
                } else {
                    customer.mCustomerName = name;
                    String address = AddCustomerUI.this.txtAddress.getText();
                    if (name.length() == 0) {
                        JOptionPane.showMessageDialog((Component) null, "Address cannot be empty!");
                    } else {
                        customer.mAddress = address;
                        String phone = AddCustomerUI.this.txtPhone.getText();

                        try {
                            customer.mPhone = Integer.parseInt(phone);
                        } catch (NumberFormatException var10) {
                            JOptionPane.showMessageDialog((Component) null, "Phone is invalid!");
                            return;
                        }

                        String payment = AddCustomerUI.this.txtPaymentInfo.getText();

                        try {
                            customer.mPaymentInfo = Integer.parseInt(payment);
                        } catch (NumberFormatException var9) {
                            JOptionPane.showMessageDialog((Component) null, "Payment is invalid!");
                            return;
                        }
                        switch (StoreManager.getInstance().getDataAdapter().saveCustomer(customer)) {
                            case 1:
                                JOptionPane.showMessageDialog((Component) null, "Customer NOT added successfully! Duplicate customer ID!");
                            default:
                                JOptionPane.showMessageDialog((Component) null, "Customer added successfully!" + customer);
                        }
                    }
                }
            }
        }
    }
}