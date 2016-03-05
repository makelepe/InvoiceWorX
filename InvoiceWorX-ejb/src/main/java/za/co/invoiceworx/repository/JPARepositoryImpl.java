package za.co.invoiceworx.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 * @author <a href="mailto:makelepe1@gmail.com">Makelepe Radingwane</a>
 * @since 18 Jan 2016
 */


class JPARepositoryImpl<T> implements JPARepository<T> {
    
    private final Logger log = Logger.getLogger(JPARepositoryImpl.class);

    @PersistenceContext(unitName = "invoiceworxPU")
    private EntityManager em;
    
    @Override
    public T read(String sql, List<Object> params) {
        log.info("SQL : " + sql);
        try {
            Query query = em.createQuery(sql);
            query.setMaxResults(1);

            if (params != null) {
                for (int i = 1; i <= params.size(); i++) {
                    query.setParameter(i, params.get(i - 1));
                }
            }

            return (T) query.getSingleResult();
        } catch (Exception e) {
            log.error("ERROR executing "+this.getClass()+".read method : " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<T> readList(String sql, List<Object> params) {
        try {
            Query query = em.createQuery(sql);

            if (params != null) {
                for (int i = 1; i <= params.size(); i++) {
                    query.setParameter(i, params.get(i - 1));
                }
            }
            return (List<T>) query.getResultList();

        } catch (Exception e) {
            log.error("ERROR executing "+this.getClass()+".readList method : " + e.getMessage());
            return null;
        }
    }

    @Override
    public T add(T object) {
        em.merge(object);

        return object;
    }

    @Override
    public T update(T object) {
        return em.merge(object);
    }

    public Boolean update(String sql, List<Object> params) {
        try {
            Query query = em.createQuery(sql);

            if (params != null) {
                for (int i = 1; i <= params.size(); i++) {
                    query.setParameter(i, params.get(i - 1));
                }
            }

            return query.executeUpdate() > 0;
        } catch (Exception e) {
            log.error("ERROR executing "+this.getClass()+".update method : " + e.getMessage());
            return null;
        }
    }

    @Override
    public void remove(T object) {
        em.remove(object);
    }

    @Override
    public Boolean ping() {
        return em != null; 
    }

}
