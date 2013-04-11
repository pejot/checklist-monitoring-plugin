package pl.psnc.dl.wf4ever.monitoring.service;

/**
 * Service for sending requests and receiving response from Checklist Service.
 * 
 * @author pejot
 * 
 */
public class ChecklistService {

    /** Singleton instance. */
    private ChecklistService instance;


    /**
     * Constructor.
     */
    protected ChecklistService() {

    }


    public ChecklistService getInstance() {
        return instance;
    }
}
