package za.co.invoiceworx.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import org.apache.log4j.Logger;
import za.co.invoiceworx.entity.Account;
import za.co.invoiceworx.entity.InvoiceStatusType;
import za.co.invoiceworx.entity.IxCommissionAccount;
import za.co.invoiceworx.entity.IxPoolerAccount;
import za.co.invoiceworx.entity.Preferences;
import za.co.invoiceworx.entity.TransactionStatusType;
import za.co.invoiceworx.entity.User;
import za.co.invoiceworx.entity.UserType;

/**
 *
 * @author F4657314
 */
@ApplicationScoped
public class Cache {

    private final Logger log = Logger.getLogger(Cache.class);

    private final Map<Object, Object> cache = new HashMap<>();
    private final List<UserType> userTypes = new ArrayList<>();
    private final List<TransactionStatusType> transactionStatusTypes = new ArrayList<>();
    private final List<InvoiceStatusType> invoiceStatusTypes = new ArrayList<>();
    private final List<Preferences> preferences = new ArrayList<>();
    private Cache instance;
    private Map<String, List<Object>> props = new HashMap<>();

    public void init() {

        List<Object> userTypeObj = props.get("USER_TYPE");
        List<Object> invoiceStatusTypeObj = props.get("INVOICE_STATUS_TYPE");
        List<Object> tranStatusTypeObj = props.get("TRANSACTION_STATUS_TYPE");
        List<Object> preferencesObj = props.get("PREFERENCES");

        if (userTypeObj != null) {
            for (Object obj : userTypeObj) {
                userTypes.add((UserType) obj);
            }
        }
        if (invoiceStatusTypeObj != null) {
            for (Object obj : invoiceStatusTypeObj) {
                invoiceStatusTypes.add((InvoiceStatusType) obj);
            }
        }
        if (tranStatusTypeObj != null) {
            for (Object obj : tranStatusTypeObj) {
                transactionStatusTypes.add((TransactionStatusType) obj);
            }
        }
        if (preferencesObj != null) {
            for (Object obj : preferencesObj) {
                preferences.add((Preferences) obj);
            }
        }

        log.info("userTypes : " + userTypes.size());
        log.info("invoiceStatusTypes : " + invoiceStatusTypes.size());
        log.info("transactionStatusTypes : " + transactionStatusTypes.size());
        log.info("preferences : " + preferences.size());
    }

    public Cache getInstance() {
        if (instance == null) {
            instance = new Cache();
        }
        return instance;
    }

    public void put(Object key, Object value) {
        cache.put(key, value);
    }

    public Object get(Object key) {
        return cache.get(key);
    }

    public String getPreferenceValue(String key) {
        for (Preferences pref : preferences) {
            if (pref.getKey().equalsIgnoreCase(key)) {
                return pref.getValue();
            }
        }
        return null;
    }

    public Account getPoolerAccountDetails() {
        return null;
    }

    public Account getCommissionAccountDetails() {
        return null;
    }

    public User getSystemUser() {
        return null;
    }

    public UserType getUserType(String type) {
        for (UserType userType : userTypes) {
            if (userType.getRole().equalsIgnoreCase(type)) {
                return userType;
            }
        }
        return null;
    }

    public InvoiceStatusType getInvoiceStatusType(String type) {
        for (InvoiceStatusType statusType : invoiceStatusTypes) {
            if (statusType.getInvtype().equalsIgnoreCase(type)) {
                return statusType;
            }
        }
        return null;
    }

    public TransactionStatusType getTransactionStatusType(String type) {
        for (TransactionStatusType statusType : transactionStatusTypes) {
            if (statusType.getTrantype().equalsIgnoreCase(type)) {
                return statusType;
            }
        }
        return null;
    }

    public void setProps(Map<String, List<Object>> props) {
        this.props = props;
    }

}
