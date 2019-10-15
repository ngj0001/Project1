public class CustomerModel {
    public int mPhone;
    public int mCustomerID;
    public String mCustomerName;
    public String mAddress;
    public int mPaymentInfo;

    public CustomerModel() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(this.mCustomerID).append(",");
        sb.append("\"").append(this.mCustomerName).append("\"").append(",");
        sb.append("\"").append(this.mAddress).append("\"").append(",");
        sb.append(this.mPhone).append(",");
        sb.append(this.mPaymentInfo).append(")");
        return sb.toString();
    }
}
