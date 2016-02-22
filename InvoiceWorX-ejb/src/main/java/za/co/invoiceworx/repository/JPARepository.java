package za.co.invoiceworx.repository;

import java.util.List;

/**
 * @author <a href="mailto:samuel.radingwane@ebucks.com">Samuel Radingwane</a>
 * @since 28 Mar 2015
 */
interface JPARepository<T>  extends IRepository{

    public T read(String query, List<Object> params);

    public List<T> readList(String query, List<Object> params);

    public T add(T object);

    public T update(T object);

    public void remove(T object);
    
  
}
