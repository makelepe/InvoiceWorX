package za.co.invoiceworx.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Invoice.class)
public abstract class Invoice_ {

	public static volatile SingularAttribute<Invoice, User> approvedBy;
	public static volatile SingularAttribute<Invoice, BigDecimal> invVatAmount;
	public static volatile SingularAttribute<Invoice, BigDecimal> platformFee;
	public static volatile SingularAttribute<Invoice, User> fundedBy;
	public static volatile SingularAttribute<Invoice, BigDecimal> remainingAmount;
	public static volatile SingularAttribute<Invoice, Date> fundedTs;
	public static volatile SingularAttribute<Invoice, BigDecimal> supplierInitialAmount;
	public static volatile SingularAttribute<Invoice, Long> id;
	public static volatile ListAttribute<Invoice, InvoiceDocumentFile> docFiles;
	public static volatile SingularAttribute<Invoice, BigDecimal> fundedAmount;
	public static volatile SingularAttribute<Invoice, BigDecimal> platformComm;
	public static volatile SingularAttribute<Invoice, String> invStatus;
	public static volatile SingularAttribute<Invoice, BigDecimal> buyerSettlementAmount;
	public static volatile SingularAttribute<Invoice, User> verifiedBy;
	public static volatile ListAttribute<Invoice, TransactionDetail> transactions;
	public static volatile SingularAttribute<Invoice, Date> createdTs;
	public static volatile SingularAttribute<Invoice, BigDecimal> funderCommission;
	public static volatile SingularAttribute<Invoice, Date> approvedTs;
	public static volatile SingularAttribute<Invoice, InvoiceStatusType> currentStatusType;
	public static volatile ListAttribute<Invoice, InvoiceItem> invoiceItems;
	public static volatile SingularAttribute<Invoice, Date> verifiedTs;
	public static volatile SingularAttribute<Invoice, User> createdBy;
	public static volatile SingularAttribute<Invoice, BigDecimal> funderFee;
	public static volatile SingularAttribute<Invoice, BigDecimal> invAmount;
	public static volatile ListAttribute<Invoice, InvoiceStatus> invoiceStatusses;
	public static volatile SingularAttribute<Invoice, String> invRefNumber;

}

