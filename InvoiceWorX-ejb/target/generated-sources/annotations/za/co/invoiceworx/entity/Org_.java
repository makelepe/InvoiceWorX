package za.co.invoiceworx.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Org.class)
public abstract class Org_ {

	public static volatile SingularAttribute<Org, Date> orgRegDate;
	public static volatile SingularAttribute<Org, String> orgName;
	public static volatile SingularAttribute<Org, Address> address;
	public static volatile SingularAttribute<Org, String> orgRegNum;
	public static volatile SingularAttribute<Org, Contact> contact;
	public static volatile SingularAttribute<Org, Long> id;

}

