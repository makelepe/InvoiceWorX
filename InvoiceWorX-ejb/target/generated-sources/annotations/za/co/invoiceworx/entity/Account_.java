package za.co.invoiceworx.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Account.class)
public abstract class Account_ {

	public static volatile SingularAttribute<Account, String> branchCode;
	public static volatile SingularAttribute<Account, String> accountHolder;
	public static volatile SingularAttribute<Account, String> bank;
	public static volatile SingularAttribute<Account, String> accountType;
	public static volatile SingularAttribute<Account, Long> id;
	public static volatile SingularAttribute<Account, String> accountNumber;
	public static volatile SingularAttribute<Account, String> branch;

}

