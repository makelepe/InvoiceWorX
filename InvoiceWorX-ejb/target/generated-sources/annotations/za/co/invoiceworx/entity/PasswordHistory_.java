package za.co.invoiceworx.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PasswordHistory.class)
public abstract class PasswordHistory_ {

	public static volatile SingularAttribute<PasswordHistory, String> password;
	public static volatile SingularAttribute<PasswordHistory, Long> id;
	public static volatile SingularAttribute<PasswordHistory, Date> changedTs;

}

