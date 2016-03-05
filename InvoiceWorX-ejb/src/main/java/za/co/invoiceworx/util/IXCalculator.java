package za.co.invoiceworx.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import za.co.invoiceworx.entity.Invoice;

/**
 *
 * @author F4657314
 */
public class IXCalculator {

    public BigDecimal calculatePlatformFee(Invoice invoice) {
        if (invoice == null) {
            return new BigDecimal(BigInteger.ZERO);
        }
        
        return invoice.getBuyerSettlementAmount()
                .multiply(new BigDecimal(0.02))
                .add( calculateFunderCommission(invoice).multiply(new BigDecimal(0.1) ));
    }

    public BigDecimal calculateFundedAmount(Invoice invoice) {
        if (invoice == null) {
            return new BigDecimal(BigInteger.ZERO);
        }
        return invoice.getInvAmount().multiply(new BigDecimal(0.8));
    }

    public BigDecimal calculateFunderFee(Invoice invoice) {
        if (invoice == null) {
            return new BigDecimal(BigInteger.ZERO);
        }
        return invoice.getInvAmount()
                .multiply(new BigDecimal(0.8))
                .add(calculateFunderCommission(invoice))
                .subtract(calculateFunderCommission(invoice).multiply(new BigDecimal(0.1) ));
    }
    
    public BigDecimal calculateFunderCommission(Invoice invoice) {
        if (invoice == null) {
            return new BigDecimal(BigInteger.ZERO);
        }
        int numberOfdays = DateUtil.calculateDifferenceInDays(invoice.getFundedTs(), DateUtil.newDate());

        return invoice.getInvAmount()
                .multiply(new BigDecimal(0.001))
                .multiply(new BigDecimal(numberOfdays));
    }

    public BigDecimal calculateRemainingAmount(Invoice invoice) {
        if (invoice == null) {
            return new BigDecimal(BigInteger.ZERO);
        }

        BigDecimal remainingAmount = invoice.getBuyerSettlementAmount()
                .subtract(invoice.getFunderFee())
                .subtract(invoice.getPlatformFee());

        return remainingAmount;
    }

}
