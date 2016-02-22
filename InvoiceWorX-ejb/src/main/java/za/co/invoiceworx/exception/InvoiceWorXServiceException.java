package za.co.invoiceworx.exception;

/**
 *
 * @author F4657314
 */
public class InvoiceWorXServiceException extends Exception {

    private final ExCode code;

    public InvoiceWorXServiceException(ExCode code) {
        this.code = code;
    }

    public InvoiceWorXServiceException(ExCode code, String message) {
        super(message);
        this.code = code;
    }

    public InvoiceWorXServiceException(ExCode code, Throwable throwable) {
        super(throwable);
        this.code = code;
    }

    public InvoiceWorXServiceException(ExCode code, Throwable throwable, String message) {
        super(message, throwable);
        this.code = code;
    }

    public ExCode getCode() {
        return this.code;
    }

}
