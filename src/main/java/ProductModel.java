public class ProductModel {
    public int mProductID;
    public String mName;
    public String mVendor;
    public String mDescription;
    public double mPrice;
    public double mQuantity;

    public ProductModel() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(this.mProductID).append(",");
        sb.append("\"").append(this.mName).append("\"").append(",");
        sb.append(this.mPrice).append(",");
        sb.append(this.mQuantity).append(")");
        return sb.toString();
    }
}
