package pl.psnc.dl.wf4ever.monitoring;

/**
 * Exception raised by Checklist Service in case of problem with the checklist. Wrapper for some communications errors.
 */
public class ChecklistMonitoringPluginException extends RuntimeException {

    /** Serialization. */
    private static final long serialVersionUID = 1L;


    /**
     * Constructor.
     * 
     * @param message
     *            message
     * @param cause
     *            cause
     */
    public ChecklistMonitoringPluginException(String message, Exception cause) {
        super(message, cause);
    }


    /**
     * Constructor.
     * 
     * @param message
     *            massege
     */
    public ChecklistMonitoringPluginException(String message) {
        super(message);
    }

}
