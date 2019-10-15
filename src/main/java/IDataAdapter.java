
public interface IDataAdapter {
    int CONNECTION_OPEN_OK = 100;
    int CONNECTION_OPEN_FAILED = 101;
    int CONNECTION_CLOSE_OK = 200;
    int CONNECTION_CLOSE_FAILED = 201;
    int PRODUCT_SAVED_OK = 0;
    int PRODUCT_DUPLICATE_ERROR = 1;
    int PURCHASE_SAVED_OK = 500;
    int PURCHASE_DUPLICATE_ERROR = 501;
    int CUSTOMER_SAVED_OK = 100;
    int CUSTOMER_DUPLICATE_ERROR = 101;

    int connect(String var1);

    int disconnect();

    ProductModel loadProduct(int var1);

    int saveProduct(ProductModel var1);

    CustomerModel loadCustomer(int var1);

    int saveCustomer(CustomerModel var1);

    PurchaseModel loadPurchase(int var1);

    int savePurchase(PurchaseModel var1);
}
