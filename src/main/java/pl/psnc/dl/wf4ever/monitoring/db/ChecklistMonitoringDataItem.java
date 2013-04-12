package pl.psnc.dl.wf4ever.monitoring.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;
import org.purl.wf4ever.checklist.client.ChecklistItem;

/**
 * Represent a part of result of monitoring plugin execution stored to compare with other (younger/older) results.
 * 
 * @author pejot
 * 
 */
@Entity
@Table(name = "checklist_monitorin_data_item")
public class ChecklistMonitoringDataItem {

    /** Id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Timestamp. */
    @Column(nullable = false)
    private Date created = DateTime.now().toDate();


    /**
     * Constructor.
     * 
     * @param item
     *            item given by checklist client library.
     */
    public ChecklistMonitoringDataItem(ChecklistItem item) {

    }
}
