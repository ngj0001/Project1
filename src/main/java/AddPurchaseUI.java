import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AddPurchaseUI {
    public JFrame view = new JFrame();
    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");
    public JTextField txtPurchaseID = new JTextField(10);
    public JTextField txtCustomerID = new JTextField(10);
    public JTextField txtProductID = new JTextField(10);
    public JTextField txtQuantity = new JTextField(10);
    public JLabel labPrice = new JLabel("Product Price: ");
    public JLabel labDate = new JLabel("Date of Purchase: ");
    public JLabel labCustomerName = new JLabel("Customer Name: ");
    public JLabel labProductName = new JLabel("Product Name: ");
    public JLabel labCost = new JLabel("Cost: $0.00 ");
    public JLabel labTax = new JLabel("Tax: $0.00");
    public JLabel labTotalCost = new JLabel("Total Cost: $0.00");
    ProductModel product;
    PurchaseModel purchase;
    CustomerModel customer;

    public AddPurchaseUI() {
        this.view.setDefaultCloseOperation(2);
        this.view.setTitle("Add Purchase");
        this.view.setSize(600, 400);
        this.view.getContentPane().setLayout(new BoxLayout(this.view.getContentPane(), 3));
        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("PurchaseID "));
        line.add(this.txtPurchaseID);
        line.add(this.labDate);
        this.view.getContentPane().add(line);
        line = new JPanel(new FlowLayout());
        line.add(new JLabel("CustomerID "));
        line.add(this.txtCustomerID);
        line.add(this.labCustomerName);
        this.view.getContentPane().add(line);
        line = new JPanel(new FlowLayout());
        line.add(new JLabel("ProductID "));
        line.add(this.txtProductID);
        line.add(this.labProductName);
        this.view.getContentPane().add(line);
        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Quantity "));
        line.add(this.txtQuantity);
        line.add(this.labPrice);
        this.view.getContentPane().add(line);
        line = new JPanel(new FlowLayout());
        line.add(this.labCost);
        line.add(this.labTax);
        line.add(this.labTotalCost);
        this.view.getContentPane().add(line);
        line = new JPanel(new FlowLayout());
        line.add(this.btnAdd);
        line.add(this.btnCancel);
        this.view.getContentPane().add(line);
        this.txtProductID.addFocusListener(new AddPurchaseUI.ProductIDFocusListener());
        this.txtCustomerID.addFocusListener(new AddPurchaseUI.CustomerIDFocusListener());
        this.txtQuantity.getDocument().addDocumentListener(new AddPurchaseUI.QuantityChangeListener());
        this.btnAdd.addActionListener(new AddPurchaseUI.AddButtonListener());
        this.btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AddPurchaseUI.this.view.dispose();
            }
        });
    }

    public void run() {
        this.purchase = new PurchaseModel();
        this.purchase.mDate = Calendar.getInstance().getTime().toString();
        this.labDate.setText("Date of purchase: " + this.purchase.mDate);
        this.view.setVisible(true);
    }

    class AddButtonListener implements ActionListener {
        AddButtonListener() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            String id = AddPurchaseUI.this.txtPurchaseID.getText();
            if (id.length() == 0) {
                JOptionPane.showMessageDialog((Component)null, "PurchaseID cannot be null!");
            } else {
                try {
                    AddPurchaseUI.this.purchase.mPurchaseID = Integer.parseInt(id);
                } catch (NumberFormatException var4) {
                    JOptionPane.showMessageDialog((Component)null, "PurchaseID is invalid!");
                    return;
                }

                switch(StoreManager.getInstance().getDataAdapter().savePurchase(AddPurchaseUI.this.purchase)) {
                    case 501:
                        JOptionPane.showMessageDialog((Component)null, "Purchase NOT added successfully! Duplicate product ID!");
                    default:
                        JOptionPane.showMessageDialog((Component)null, "Purchase added successfully!" + AddPurchaseUI.this.purchase);
                        TXTReceiptBuilder build = new TXTReceiptBuilder();
                        build.appendHeader("Purchase Receipt");
                        build.appendCustomer(AddPurchaseUI.this.customer);
                        build.appendProduct(AddPurchaseUI.this.product);
                        build.appendPurchase(AddPurchaseUI.this.purchase);
                        build.appendFooter("Thank you for shopping");
                        System.out.println(build.toString());
                }
            }
        }
    }

    private class QuantityChangeListener implements DocumentListener {
        private QuantityChangeListener() {
        }

        public void changedUpdate(DocumentEvent e) {
            this.process();
        }

        public void removeUpdate(DocumentEvent e) {
            this.process();
        }

        public void insertUpdate(DocumentEvent e) {
            this.process();
        }

        private void process() {
            String s = AddPurchaseUI.this.txtQuantity.getText();
            if (s.length() != 0) {
                System.out.println("Quantity = " + s);

                try {
                    AddPurchaseUI.this.purchase.mQuantity = Double.parseDouble(s);
                } catch (NumberFormatException var3) {
                    JOptionPane.showMessageDialog((Component)null, "Error: Please enter an invalid quantity", "Error Message", 0);
                    return;
                }

                if (AddPurchaseUI.this.purchase.mQuantity <= 0.0D) {
                    JOptionPane.showMessageDialog((Component)null, "Error: Please enter an invalid quantity", "Error Message", 0);
                } else {
                    if (AddPurchaseUI.this.purchase.mQuantity > AddPurchaseUI.this.product.mQuantity) {
                        JOptionPane.showMessageDialog((Component)null, "Not enough available products!", "Information", 1);
                    }

                    AddPurchaseUI.this.purchase.mCost = AddPurchaseUI.this.purchase.mQuantity * (double)AddPurchaseUI.this.product.mPrice;
                    AddPurchaseUI.this.purchase.mTax = AddPurchaseUI.this.purchase.mCost * 0.09D;
                    AddPurchaseUI.this.purchase.mTotal = AddPurchaseUI.this.purchase.mCost + AddPurchaseUI.this.purchase.mTax;
                    AddPurchaseUI.this.labCost.setText("Cost: $" + String.format("%8.2f", AddPurchaseUI.this.purchase.mCost).trim());
                    AddPurchaseUI.this.labTax.setText("Tax: $" + String.format("%8.2f", AddPurchaseUI.this.purchase.mTax).trim());
                    AddPurchaseUI.this.labTotalCost.setText("Total: $" + String.format("%8.2f", AddPurchaseUI.this.purchase.mTotal).trim());
                }
            }
        }
    }

    private class CustomerIDFocusListener implements FocusListener {
        private CustomerIDFocusListener() {
        }

        public void focusGained(FocusEvent focusEvent) {
        }

        public void focusLost(FocusEvent focusEvent) {
            this.process();
        }

        private void process() {
            String s = AddPurchaseUI.this.txtCustomerID.getText();
            if (s.length() == 0) {
                AddPurchaseUI.this.labCustomerName.setText("Customer Name: [not specified!]");
            } else {
                System.out.println("CustomerID = " + s);

                try {
                    AddPurchaseUI.this.purchase.mCustomerID = Integer.parseInt(s);
                } catch (NumberFormatException var3) {
                    JOptionPane.showMessageDialog((Component)null, "Error: Invalid CustomerID", "Error Message", 0);
                    return;
                }

                AddPurchaseUI.this.customer = StoreManager.getInstance().getDataAdapter().loadCustomer(AddPurchaseUI.this.purchase.mCustomerID);
                if (AddPurchaseUI.this.customer == null) {
                    JOptionPane.showMessageDialog((Component)null, "Error: No customer with id = " + AddPurchaseUI.this.purchase.mCustomerID + " in store!", "Error Message", 0);
                    AddPurchaseUI.this.labCustomerName.setText("Customer Name: ");
                } else {
                    AddPurchaseUI.this.labCustomerName.setText("Customer Name: " + AddPurchaseUI.this.customer.mCustomerName);
                }
            }
        }
    }

    private class ProductIDFocusListener implements FocusListener {
        private ProductIDFocusListener() {
        }

        public void focusGained(FocusEvent focusEvent) {
        }

        public void focusLost(FocusEvent focusEvent) {
            this.process();
        }

        private void process() {
            String s = AddPurchaseUI.this.txtProductID.getText();
            if (s.length() == 0) {
                AddPurchaseUI.this.labProductName.setText("Product Name: [not specified!]");
            } else {
                System.out.println("ProductID = " + s);

                try {
                    AddPurchaseUI.this.purchase.mProductID = Integer.parseInt(s);
                } catch (NumberFormatException var3) {
                    JOptionPane.showMessageDialog((Component)null, "Error: Invalid ProductID", "Error Message", 0);
                    return;
                }

                AddPurchaseUI.this.product = StoreManager.getInstance().getDataAdapter().loadProduct(AddPurchaseUI.this.purchase.mProductID);
                if (AddPurchaseUI.this.product == null) {
                    JOptionPane.showMessageDialog((Component)null, "Error: No product with id = " + AddPurchaseUI.this.purchase.mProductID + " in store!", "Error Message", 0);
                    AddPurchaseUI.this.labProductName.setText("Product Name: ");
                } else {
                    AddPurchaseUI.this.labProductName.setText("Product Name: " + AddPurchaseUI.this.product.mName);
                    AddPurchaseUI.this.purchase.mPrice = (double)AddPurchaseUI.this.product.mPrice;
                    AddPurchaseUI.this.labPrice.setText("Product Price: " + AddPurchaseUI.this.product.mPrice);
                }
            }
        }
    }
}

   