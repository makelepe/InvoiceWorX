package za.co.invoiceworx.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, String> province;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, String> postalCode;
	public static volatile SingularAttribute<Address, Long> id;
	public static volatile SingularAttribute<Address, String> surburb;
	public static volatile SingularAttribute<Address, String> line1;

}

