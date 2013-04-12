package pl.psnc.dl.wf4ever.monitoring;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Test;

public class ChecklistMonitoringResultTest {

    String title = "title";
    String description = "description";


    @Test
    public void testConstructor() {
        DateTime before = DateTime.now();
        ChecklistMonitoringResult result = new ChecklistMonitoringResult(title, description);
        DateTime after = DateTime.now();
        Assert.assertTrue(result.getCreationDate().isAfter(before) || result.getCreationDate().isEqual(before));
        Assert.assertTrue(result.getCreationDate().isBefore(after) || result.getCreationDate().isEqual(after));
        Assert.assertEquals(title, result.getTitle());
        Assert.assertEquals(description, result.getDescription());
    }


    @Test
    public void testTest() {

    }
}
