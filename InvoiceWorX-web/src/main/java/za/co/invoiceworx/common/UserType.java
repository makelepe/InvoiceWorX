
package za.co.invoiceworx.common;

/**
 *
 * @author F4657314
 */
public enum UserType {
    ADMIN(1, "IX Admin User"),
    NORMAL(2, "IX Normal User"),
    FINANCE(3, "IX Finance User"),
    SUPPLIER(4, "Supplier/SMME user"),
    CORPORATE (5, "Corporate/Buyer user"),
    FUNDER (6, "Funder User");
    
    Integer code;
    String desc;
    
    UserType (Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public Integer getCode (){
        return code;
    }
    
    public String getDesc (){
        return desc;
    }
    
    
}
