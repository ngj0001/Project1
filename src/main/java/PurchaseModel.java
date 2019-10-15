public class PurchaseModel {
    public int mPurchaseID;
    public int mPhone;
    public int mCustomerID;
    public int mProductID;
    public double mPrice;
    public double mQuantity;
    public double mCost;
    public double mTax;
    public double mTotal;
    public String mDate;

    public PurchaseModel() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(this.mPurchaseID).append(",");
        sb.append(this.mCustomerID).append(",");
        sb.append(this.mProductID).append(",");
        sb.append(this.mPrice).append(",");
        sb.append(this.mQuantity).append(",");
        sb.append(this.mCost).append(",");
        sb.append(this.mTax).append(",");
        sb.append(this.mTotal).append(",");
        sb.append("\"").append(this.mDate).append("\"").append(")");
        return sb.toString();
    }
}
