public interface IReceiptBuilder {
    void appendHeader(String var1);

    void appendCustomer(CustomerModel var1);

    void appendProduct(ProductModel var1);

    void appendPurchase(PurchaseModel var1);

    void appendFooter(String var1);

    String toString();
}
