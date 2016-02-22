package za.co.invoiceworx.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InvoiceStatus.class)
public abstract class InvoiceStatus_ {

	public static volatile SingularAttribute<InvoiceStatus, Long> createdBy;
	public static volatile SingularAttribute<InvoiceStatus, InvoiceStatusType> invoiceStatusType;
	public static volatile SingularAttribute<InvoiceStatus, Long> id;
	public static volatile SingularAttribute<InvoiceStatus, String> statusComment;
	public static volatile SingularAttribute<InvoiceStatus, Invoice> invoice;
	public static volatile SingularAttribute<InvoiceStatus, Date> createdTs;

}

