package za.co.invoiceworx.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IxCommissionAccount.class)
public abstract class IxCommissionAccount_ {

	public static volatile SingularAttribute<IxCommissionAccount, Date> lastUpdated;
	public static volatile SingularAttribute<IxCommissionAccount, BigDecimal> balance;
	public static volatile SingularAttribute<IxCommissionAccount, Integer> id;

}

