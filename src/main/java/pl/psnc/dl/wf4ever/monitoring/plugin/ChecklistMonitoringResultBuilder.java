package pl.psnc.dl.wf4ever.monitoring.plugin;

import pl.psnc.synat.wrdz.mdz.plugin.VerificationResult;

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
     * Build a dArceo-understable messege.
     * 
     * @param feed
     *            given atom feed
     * @return the dArceo-understable message
     */
    public VerificationResult buildResult(SyndFeed feed) {
        /*
        for (Object ob : feed.getEntries()) {
            SyndEntry entry = (SyndEntry) ob;
            for (Object ln : entry.getLinks()) {
                System.out.println(ln.toString());
            }
        }
        */
        return new VerificationResult();
    }
}
