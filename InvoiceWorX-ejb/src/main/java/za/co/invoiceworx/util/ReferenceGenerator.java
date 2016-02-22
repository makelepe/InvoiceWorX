package za.co.invoiceworx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author F4657314
 */
public class ReferenceGenerator {

    public static String generateInvoiceReference() {
        SimpleDateFormat ddyyMMyyyymmssSSS = new SimpleDateFormat("ddyyMMyyyymmssSSS");
        String sdate = ddyyMMyyyymmssSSS.format(DateUtil.newDate());

        return sdate;
    }

    public static String generateOTP() {
        SimpleDateFormat ssSSS = new SimpleDateFormat("ssSSS");
        String sOTP = ssSSS.format(DateUtil.newDate());

        return sOTP;
    }

    public static String generatePassword() {
        SimpleDateFormat ssSSS = new SimpleDateFormat("ssSSS");
        String pass = ssSSS.format(DateUtil.newDate());

        return "P@ssword" + pass;
    }

}
