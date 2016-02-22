package za.co.invoiceworx.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InvoiceItem.class)
public abstract class InvoiceItem_ {

	public static volatile SingularAttribute<InvoiceItem, BigDecimal> unitPrice;
	public static volatile SingularAttribute<InvoiceItem, Integer> quantity;
	public static volatile SingularAttribute<InvoiceItem, String> description;
	public static volatile SingularAttribute<InvoiceItem, Long> id;
	public static volatile SingularAttribute<InvoiceItem, Invoice> invoice;
	public static volatile SingularAttribute<InvoiceItem, BigDecimal> vatAmount;

}

