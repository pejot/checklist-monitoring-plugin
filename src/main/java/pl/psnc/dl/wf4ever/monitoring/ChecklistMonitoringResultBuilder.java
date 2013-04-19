package pl.psnc.dl.wf4ever.monitoring;

import pl.psnc.dl.wf4ever.monitoring.rostate.ROStateService;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sun.syndication.feed.synd.SyndFeed;

/**
 * Builder for the Checklist Result.
 * 
 * @author pejot
 * 
 */
@Singleton
public class ChecklistMonitoringResultBuilder {

    /**
     * Service whick collects history of RO states (checklist evaluation results).
     */
    @Inject
    ROStateService stateService;


    /**
     * Build a dArceo-understable messege.
     * 
     * @param feed
     *            given atom feed
     * @return the dArceo-understable message
     */
    public ChecklistMonitoringResult buildResult(SyndFeed feed) {
        /*
        for (Object ob : feed.getEntries()) {
            SyndEntry entry = (SyndEntry) ob;
            for (Object ln : entry.getLinks()) {
                System.out.println(ln.toString());
            }
        }
        */
        return new ChecklistMonitoringResult("title", "description");
    }
}
