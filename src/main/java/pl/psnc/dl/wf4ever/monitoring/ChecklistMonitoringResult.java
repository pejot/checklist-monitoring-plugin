package pl.psnc.dl.wf4ever.monitoring;

import org.joda.time.DateTime;

import pl.psnc.dl.darceo.monitoring.MonitoringResult;

/**
 * Implementation of the result of Monitoring Checklist action. The output of the action - The massege.
 * 
 * @author pejot
 * 
 */
public class ChecklistMonitoringResult implements MonitoringResult {

    /** Timestamp. */
    private DateTime created;
    /** Title. */
    private String title;
    /** Description/target. */
    private String description;


    /**
     * Constructor.
     * 
     * @param title
     *            title
     * @param description
     *            description/message content
     */
    public ChecklistMonitoringResult(String title, String description) {
        this.created = DateTime.now();
        this.title = title;
        this.description = description;
    }


    public DateTime getCreationDate() {
        return created;
    }


    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

}
