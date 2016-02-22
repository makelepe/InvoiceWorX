package za.co.invoiceworx.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TransactionStatus.class)
public abstract class TransactionStatus_ {

	public static volatile SingularAttribute<TransactionStatus, TransactionStatusType> statusType;
	public static volatile SingularAttribute<TransactionStatus, User> createdBy;
	public static volatile SingularAttribute<TransactionStatus, Long> id;
	public static volatile SingularAttribute<TransactionStatus, String> statusComment;
	public static volatile SingularAttribute<TransactionStatus, Date> createdTs;
	public static volatile SingularAttribute<TransactionStatus, TransactionDetail> transactionDetail;

}

