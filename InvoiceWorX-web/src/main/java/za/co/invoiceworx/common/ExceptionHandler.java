package za.co.invoiceworx.common;

import javax.servlet.http.HttpSession;
import za.co.invoiceworx.exception.ExCode;
import za.co.invoiceworx.exception.InvoiceWorXServiceException;

/**
 *
 * @author F4657314
 */
public class ExceptionHandler {

    public void handle(HttpSession session, InvoiceWorXServiceException iwse) {
        ExCode exCode = iwse.getCode();

        switch (exCode) {
            case BUYER_NOT_FOUND:
            case FUNDER_NOT_FOUND:
            case SUPPLIER_NOT_FOUND:
            case INVOICE_NOT_FOUND:
                session.setAttribute("error_msg", "Record not found");
                break;

            case UNKNOWN:
                session.setAttribute("error_msg", "Service is currently unavailable. Please try again later");
                break;

            case INVOICE_IS_NOT_NEW:
                session.setAttribute("error_msg", "Invoice is not in a NEW status.");
                break;
            case INVOICE_NOT_APPROVED:
                session.setAttribute("error_msg", "Invoice is not approved.");
                break;
            case INVOICE_NOT_FOR_SALE:
                session.setAttribute("error_msg", "Invoice is not for sale.");
                break;
            case INVOICE_NOT_SETTLED:
                session.setAttribute("error_msg", "Invoice is not settled.");
                break;
            case INVOICE_NOT_SOLD:
                session.setAttribute("error_msg", "Invoice is not sold");
                break;
            case INVOICE_NOT_VERIFIED:
                session.setAttribute("error_msg", "Invoice is not verified");
                break;

            case LOGIN_ERROR:
                session.setAttribute("error_msg", "Login Error");
                break;

            case USER_NOT_ADMIN:
                session.setAttribute("error_msg", "This User is not an Admin User");
                break;

            case USER_NOT_BUYER:
                session.setAttribute("error_msg", "This User is not an Client User");
                break;

            case USER_NOT_FUNDER:
                session.setAttribute("error_msg", "This User is not an Funder User");
                break;

            case USER_NOT_SUPPLIER:
                session.setAttribute("error_msg", "This User is not an Supplier User");
                break;

            case USER_TYPE_IS_REQUIRED:
                session.setAttribute("error_msg", "Technical Problem. Please contact Administrator");
                break;

            default:
                session.setAttribute("error_msg", "Technical Problem. Please contact Administrator");
        }

    }

}
