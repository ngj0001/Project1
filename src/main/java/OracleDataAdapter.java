
public class OracleDataAdapter implements IDataAdapter {
    public OracleDataAdapter() {
    }

    public int connect(String dbfile) {
        return 100;
    }

    public int disconnect() {
        return 200;
    }

    public CustomerModel loadCustomer(int id) {
        return null;
    }

    public int saveCustomer(CustomerModel model) {
        return 100;
    }

    public ProductModel loadProduct(int id) {
        return null;
    }

    public int saveProduct(ProductModel model) {
        return 0;
    }

    public PurchaseModel loadPurchase(int id) {
        return null;
    }

    public int savePurchase(PurchaseModel model) {
        return 500;
    }
}
