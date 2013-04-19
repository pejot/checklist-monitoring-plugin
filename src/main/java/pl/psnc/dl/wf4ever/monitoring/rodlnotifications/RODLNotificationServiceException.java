package pl.psnc.dl.wf4ever.monitoring.rodlnotifications;

/**
 * Exception raised by Checklist Service in case of problem with the checklist. Wrapper for some communications errors.
 */
public class RODLNotificationServiceException extends RuntimeException {

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
    public RODLNotificationServiceException(String message, Exception cause) {
        super(message, cause);
    }


    /**
     * Constructor.
     * 
     * @param message
     *            massege
     */
    public RODLNotificationServiceException(String message) {
        super(message);
    }

}
