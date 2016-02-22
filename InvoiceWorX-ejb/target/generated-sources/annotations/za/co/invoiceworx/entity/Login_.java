package za.co.invoiceworx.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Login.class)
public abstract class Login_ {

	public static volatile SingularAttribute<Login, Date> logoutTs;
	public static volatile SingularAttribute<Login, Long> id;
	public static volatile SingularAttribute<Login, String> sessionId;
	public static volatile SingularAttribute<Login, User> user;
	public static volatile SingularAttribute<Login, Date> loginTs;

}

