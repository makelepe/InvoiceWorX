package za.co.invoiceworx.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person.class)
public abstract class Person_ {

	public static volatile SingularAttribute<Person, String> fname;
	public static volatile SingularAttribute<Person, String> lname;
	public static volatile SingularAttribute<Person, Address> address;
	public static volatile SingularAttribute<Person, String> gender;
	public static volatile SingularAttribute<Person, String> race;
	public static volatile SingularAttribute<Person, String> passport;
	public static volatile SingularAttribute<Person, Contact> contact;
	public static volatile SingularAttribute<Person, Long> id;
	public static volatile SingularAttribute<Person, String> mname;
	public static volatile SingularAttribute<Person, String> title;
	public static volatile SingularAttribute<Person, String> idNumber;

}

