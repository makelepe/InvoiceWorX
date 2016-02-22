package za.co.invoiceworx.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InvoiceDocumentFile.class)
public abstract class InvoiceDocumentFile_ {

	public static volatile SingularAttribute<InvoiceDocumentFile, Boolean> archived;
	public static volatile SingularAttribute<InvoiceDocumentFile, Date> uploadedTs;
	public static volatile SingularAttribute<InvoiceDocumentFile, byte[]> deliveryNote;
	public static volatile SingularAttribute<InvoiceDocumentFile, byte[]> purchaseOrder;
	public static volatile SingularAttribute<InvoiceDocumentFile, Integer> id;
	public static volatile SingularAttribute<InvoiceDocumentFile, Invoice> invoice;

}

