package za.co.invoiceworx.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IxPoolerAccount.class)
public abstract class IxPoolerAccount_ {

	public static volatile SingularAttribute<IxPoolerAccount, Date> lastUpdated;
	public static volatile SingularAttribute<IxPoolerAccount, BigDecimal> balance;
	public static volatile SingularAttribute<IxPoolerAccount, Integer> id;

}

