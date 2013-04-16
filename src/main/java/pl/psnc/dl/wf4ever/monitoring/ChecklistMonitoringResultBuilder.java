package pl.psnc.dl.wf4ever.monitoring;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

/**
 * Builder for the Checklist Result.
 * 
 * @author pejot
 * 
 */
public class ChecklistMonitoringResultBuilder {

    /**
     * Hidden constructor.
     */
    protected ChecklistMonitoringResultBuilder() {
        //nope
    }


    /**
     * Build a dArceo-understable messege.
     * 
     * @param feed
     * @return the dArceo-understable message
     */
    public ChecklistMonitoringResult buildResult(SyndFeed feed) {
        for (Object ob : feed.getEntries()) {
            SyndEntry entry = (SyndEntry) ob;
            for (Object ln : entry.getLinks()) {
                System.out.println(ln.toString());
            }
        }
        return new ChecklistMonitoringResult("title", "description");
    }
}
