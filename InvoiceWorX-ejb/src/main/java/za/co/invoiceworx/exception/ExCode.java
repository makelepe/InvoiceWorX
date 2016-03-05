
package za.co.invoiceworx.exception;

/**
 *
 * @author F4657314
 */
public enum ExCode {
    INVOICE_NOT_FOUND (1, "Invoice not found"),
    USER_NOT_FOUND (2, "User not found"),
    FUNDER_NOT_FOUND (3, "Funder not found"),
    BUYER_NOT_FOUND (4, "Buyer not found"),
    SUPPLIER_NOT_FOUND (5, "Supplier not found"),
    LOGIN_ERROR (6, "Error during loin"),
    INVALID_INPUT (7, "Invalid Input"),
    INVOICE_IS_NOT_NEW (15, "This is a new invoice"),
    INVOICE_NOT_APPROVED (9, "Invoice not on sale"),
    INVOICE_NOT_VERIFIED (9, "Invoice not on sale"),
    INVOICE_NOT_FOR_SALE (8, "Invoice not on sale"),
    INVOICE_NOT_SOLD (9, "Invoice not on sale"),
    INVOICE_NOT_SETTLED (9, "Invoice not on sale"),
    OTP_NOT_FOUND (10, "Invalid OTP value"),
    USER_NOT_ADMIN (11, "This is not an Admin user"),
    USER_NOT_BUYER (12, "This is not a buyer user"),
    USER_NOT_SUPPLIER (13, "This is not a supplier user"),
    USER_NOT_FUNDER (14, "This is not a funder user"),
    USER_TYPE_IS_REQUIRED (16, "User type is required"),
    TRANSACTION_NOT_FOUND (17, "Transaction not found"),
    UNKNOWN (40, "Unknown Error");
    
    Integer code;
    String description;
    
    private ExCode (Integer code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public Integer getCode () {
        return code;
    }
    
    public String getDesc () {
        return description;
    }
    
}
