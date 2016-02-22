package za.co.invoiceworx.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import za.co.invoiceworx.repository.UserRepository;
import za.co.invoiceworx.util.Cache;

/**
 *
 * @author F4657314
 */
@Singleton
@Startup
public class StartupBean {

    private final Logger log = Logger.getLogger(Cache.class);

    @Inject
    private Cache cache;

    @Inject
    private UserRepository userRepo;

    public enum State {

        BEFORESTARTED, STARTED, PAUSED, SHUTTINGDOWN
    };
    private State state;

    @PostConstruct
    public void initialize() {
        log.info("\n\n=========================== STARTING SERVICE ====================\n\n");
        state = State.BEFORESTARTED;
        log.info("Starting the Server");
        // Perform intialization
        
        while (!userRepo.ping()) {
            log.info("EM not ready ");
          // buy time, for all DB resources to be initialized  
        }
        log.info("EM is ready ");
        
        cache.setProps(userRepo.loadProperties());
        cache.init();
        
        state = State.STARTED;
        log.info("\n=========================== SERVICE STARTED====================\n\n");
    }

    @PreDestroy
    public void terminate() {
        state = State.SHUTTINGDOWN;
        // Perform termination
        System.out.println("Shut down in progress");
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
