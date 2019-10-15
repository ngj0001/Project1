//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class TXTReceiptBuilder implements IReceiptBuilder {
    StringBuilder sb = new StringBuilder();

    public TXTReceiptBuilder() {
    }

    public void appendHeader(String header) {
        this.sb.append(header).append("\n");
    }

    public void appendCustomer(CustomerModel customer) {
        this.sb.append("Customer ID: ").append(customer.mCustomerID).append("\n");
        this.sb.append("Customer Name: ").append(customer.mCustomerName).append("\n");
        System.out.println(customer);
    }

    public void appendProduct(ProductModel product) {
        this.sb.append("Product ID: ").append(product.mProductID).append("\n");
        this.sb.append("Product Name: ").append(product.mName).append("\n");
        System.out.println(product);
    }

    public void appendPurchase(PurchaseModel purchase) {
        this.sb.append("PurchaseID: ").append(purchase.mPurchaseID).append("\n");
        System.out.println(purchase);
    }

    public void appendFooter(String footer) {
        this.sb.append(footer).append("\n");
    }

    public String toString() {
        return this.sb.toString();
    }
}
