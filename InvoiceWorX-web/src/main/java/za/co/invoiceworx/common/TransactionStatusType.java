package za.co.invoiceworx.common;

import java.util.Objects;

/**
 *
 * @author F4657314
 */
public enum TransactionStatusType {

    FUNDER_DEBITTED(1, "Funder debitted"),
    SUPPLIER_PAID(2, "Supplier paid"),
    CORPORATE_DEBITTED(3, "Corporate debitted"),
    FUNDER_PAID(4, "Funder Paid"),
    IX_COMMISSION_PAID(5, "Invoice Worx commission paid"),
    REMAINDER_PAID_TO_SUPPLIER(5, "Remainder paid to supplier"),
    TRANSACTION_FAILURE(5, "Transaction Failure"),
    TRANSACTION_TIMED_OUT(5, "Transaction timed out"),
    BANK_CHARGES(5, "bank charges");

    Integer code;
    String desc;

    TransactionStatusType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public TransactionStatusType getStatusType(Integer code) {
        for (TransactionStatusType statusType : values()) {
            if (Objects.equals(code, statusType.getCode())) {
                return statusType;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
