package pl.psnc.dl.wf4ever.monitoring.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;
import org.purl.wf4ever.checklist.client.EvaluationResult;

/**
 * Represent a result of monitoring plugin execution stored to compera in the other (older/tounger) results.
 * 
 * @author pejot
 * 
 */
@Entity
@Table(name = "checklist_monitorin_data")
public class ChecklistMonitoringData implements Serializable {

    /** Id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Timestamp. */
    @Column(nullable = false)
    private Date created = DateTime.now().toDate();

    /** Research Object Uri. */
    @Column(nullable = false)
    private String researchObject;


    /**
     * Constructor.
     * 
     * @param result
     *            the result given by checklist client library.
     */
    public ChecklistMonitoringData(EvaluationResult result) {

    }

}
