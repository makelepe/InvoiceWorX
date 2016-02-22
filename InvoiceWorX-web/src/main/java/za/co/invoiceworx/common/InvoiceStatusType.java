package za.co.invoiceworx.common;

import java.util.Objects;

/**
 *
 * @author F4657314
 */
public enum InvoiceStatusType {

    NEW(1, "New Invoice"),
    PENDING_VERIFICATION(2, "Invoice pending verification"),
    ON_SALE(3, "Invoice advertised"),
    SOLD(4, "Invoice sold"),
    SETTLED(5, "Invoice settled by Buyer/Corporate"),
    AWAITING_PAYMENT(6, "Awaiting Payment to reflect"),
    ERROR(7, "Error");

    Integer code;
    String desc;

    InvoiceStatusType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public InvoiceStatusType getStatusType(Integer code) {
        for (InvoiceStatusType statusType : values()) {
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
