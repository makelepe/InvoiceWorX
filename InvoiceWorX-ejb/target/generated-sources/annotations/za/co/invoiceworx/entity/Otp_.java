package za.co.invoiceworx.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Otp.class)
public abstract class Otp_ {

	public static volatile SingularAttribute<Otp, Boolean> expired;
	public static volatile SingularAttribute<Otp, Long> id;
	public static volatile SingularAttribute<Otp, String> otpValue;
	public static volatile SingularAttribute<Otp, Date> createdTs;
	public static volatile SingularAttribute<Otp, Boolean> confirmed;
	public static volatile SingularAttribute<Otp, User> user;

}

