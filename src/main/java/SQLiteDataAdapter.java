import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDataAdapter implements IDataAdapter {
    Connection conn = null;

    public SQLiteDataAdapter() {
    }

    public int connect(String dbfile) {
        try {
            String url = "jdbc:sqlite:" + dbfile;
            this.conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            return 100;
        } catch (SQLException var3) {
            System.out.println(var3.getMessage());
            return 101;
        }
    }

    public int disconnect() {
        try {
            this.conn.close();
            return 200;
        } catch (SQLException var2) {
            System.out.println(var2.getMessage());
            return 201;
        }
    }

    public ProductModel loadProduct(int productID) {
        ProductModel product = null;

        try {
            String sql = "SELECT ProductId, Name, Price, Quantity FROM Products WHERE ProductId = " + productID;
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                product = new ProductModel();
                product.mProductID = rs.getInt("ProductId");
                product.mName = rs.getString("Name");
                product.mPrice = rs.getInt("Price");
                product.mQuantity = rs.getDouble("Quantity");
            }
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }

        return product;
    }

    public int saveProduct(ProductModel product) {
        try {
            String sql = "INSERT INTO Products(ProductId, Name, Price, Quantity) VALUES " + product;
            System.out.println(sql);
            Statement stmt = this.conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (Exception var4) {
            String msg = var4.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed")) {
                return 1;
            }
        }

        return 0;
    }

    public CustomerModel loadCustomer(int customerID) {
        CustomerModel customer = null;

        try {
            String sql = "SELECT CustomerID, CustomerName, Address, Phone, PaymentInfo FROM Customers WHERE CustomerID = " + customerID;
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                customer = new CustomerModel();
                customer.mCustomerID = rs.getInt("CustomerID");
                customer.mCustomerName = rs.getString("CustomerName");
                customer.mAddress = rs.getString("Address");
                customer.mPhone = rs.getInt("Phone");
                customer.mPaymentInfo = rs.getInt("Payment");
            }
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }

        return customer;
    }

    public int saveCustomer(CustomerModel customer) {
        try {
            String sql = "INSERT INTO Customers(CustomerID, CustomerName, Address, Phone, PaymentInfo) VALUES " + customer;
            System.out.println(sql);
            Statement stmt = this.conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (Exception var4) {
            String msg = var4.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed")) {
                return 101;
            }
        }

        return 100;
    }

    public PurchaseModel loadPurchase(int purchaseID) {
        PurchaseModel purchase = null;

        try {
            String sql = "SELECT PurchaseID, CustomerID, ProductID, Quantity  FROM Purchases WHERE PurchaseID = " + purchaseID;
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                purchase = new PurchaseModel();
                purchase.mPurchaseID = rs.getInt("PurchaseID");
                purchase.mCustomerID = rs.getInt("CustomerID");
                purchase.mProductID = rs.getInt("ProductID");
                purchase.mPrice = (double)rs.getDouble("Price");
                purchase.mQuantity = (double)rs.getInt("Quantity");
                purchase.mCost = (double)rs.getInt("Cost");
                purchase.mTax = (double)rs.getInt("Tax");
                purchase.mTotal = (double)rs.getInt("Total");
                purchase.mDate = rs.getString("Date");
            }
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }

        return purchase;
    }

    public int savePurchase(PurchaseModel purchase) {
        try {
            String sql = "INSERT INTO Purchases(PurchaseID, CustomerID, ProductID, Price, Quantity, Cost, Tax, Total, Date)  VALUES " + purchase;
            System.out.println(sql);
            Statement stmt = this.conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (Exception var4) {
            String msg = var4.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed")) {
                return 501;
            }
        }

        return 500;
    }
}
