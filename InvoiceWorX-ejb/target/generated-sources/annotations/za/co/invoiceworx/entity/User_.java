package za.co.invoiceworx.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> deactivationReason;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Date> lastPasswordChangedTs;
	public static volatile SingularAttribute<User, Org> org;
	public static volatile SingularAttribute<User, Person> person;
	public static volatile SingularAttribute<User, Boolean> active;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, UserType> userType;
	public static volatile SingularAttribute<User, Date> expiryTs;
	public static volatile SingularAttribute<User, Date> createdTs;
	public static volatile SingularAttribute<User, Account> account;
	public static volatile SingularAttribute<User, String> username;

}

