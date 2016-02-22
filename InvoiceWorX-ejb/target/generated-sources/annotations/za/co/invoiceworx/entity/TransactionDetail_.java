package za.co.invoiceworx.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TransactionDetail.class)
public abstract class TransactionDetail_ {

	public static volatile SingularAttribute<TransactionDetail, IxCommissionAccount> ixCommissionAccount;
	public static volatile SingularAttribute<TransactionDetail, BigDecimal> tranVatAmount;
	public static volatile SingularAttribute<TransactionDetail, Date> createdTs;
	public static volatile SingularAttribute<TransactionDetail, Date> confirmedTS;
	public static volatile SingularAttribute<TransactionDetail, IxPoolerAccount> ixPoolerAccount;
	public static volatile SingularAttribute<TransactionDetail, TransactionStatusType> currentStatusType;
	public static volatile SingularAttribute<TransactionDetail, User> confirmedBy;
	public static volatile ListAttribute<TransactionDetail, TransactionStatus> transactionStatusses;
	public static volatile SingularAttribute<TransactionDetail, Long> id;
	public static volatile SingularAttribute<TransactionDetail, Invoice> invoice;
	public static volatile SingularAttribute<TransactionDetail, BigDecimal> tranAmount;
	public static volatile SingularAttribute<TransactionDetail, String> tranRefNumber;
	public static volatile SingularAttribute<TransactionDetail, User> user;

}

