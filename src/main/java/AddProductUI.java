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

public class AddProductUI {
    public JFrame view = new JFrame();
    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");
    public JTextField txtProductID = new JTextField(20);
    public JTextField txtName = new JTextField(20);
    public JTextField txtPrice = new JTextField(20);
    public JTextField txtQuantity = new JTextField(20);

    public AddProductUI() {
        this.view.setDefaultCloseOperation(2);
        this.view.setTitle("Add Product");
        this.view.setSize(600, 400);
        this.view.getContentPane().setLayout(new BoxLayout(this.view.getContentPane(), 3));
        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("ProductID "));
        line1.add(this.txtProductID);
        this.view.getContentPane().add(line1);
        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Name "));
        line2.add(this.txtName);
        this.view.getContentPane().add(line2);
        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("Price "));
        line3.add(this.txtPrice);
        this.view.getContentPane().add(line3);
        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Quantity "));
        line4.add(this.txtQuantity);
        this.view.getContentPane().add(line4);
        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(this.btnAdd);
        panelButtons.add(this.btnCancel);
        this.view.getContentPane().add(panelButtons);
        this.btnAdd.addActionListener(new AddProductUI.AddButtonListener());
        this.btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AddProductUI.this.view.dispose();
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
            ProductModel product = new ProductModel();
            String id = AddProductUI.this.txtProductID.getText();
            if (id.length() == 0) {
                JOptionPane.showMessageDialog((Component)null, "ProductID cannot be null!");
            } else {
                try {
                    product.mProductID = Integer.parseInt(id);
                } catch (NumberFormatException var10) {
                    JOptionPane.showMessageDialog((Component)null, "ProductID is invalid!");
                    return;
                }

                String name = AddProductUI.this.txtName.getText();
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog((Component)null, "Product name cannot be empty!");
                } else {
                    product.mName = name;
                    String price = AddProductUI.this.txtPrice.getText();

                    try {
                        product.mPrice = Double.parseDouble(price);
                    } catch (NumberFormatException var9) {
                        JOptionPane.showMessageDialog((Component)null, "Price is invalid!");
                        return;
                    }

                    String quant = AddProductUI.this.txtQuantity.getText();

                    try {
                        product.mQuantity = Double.parseDouble(quant);
                    } catch (NumberFormatException var8) {
                        JOptionPane.showMessageDialog((Component)null, "Quantity is invalid!");
                        return;
                    }

                    switch(StoreManager.getInstance().getDataAdapter().saveProduct(product)) {
                        case 1:
                            JOptionPane.showMessageDialog((Component)null, "Product NOT added successfully! Duplicate product ID!");
                        default:
                            JOptionPane.showMessageDialog((Component)null, "Product added successfully!" + product);
                    }
                }
            }
        }
    }
}
