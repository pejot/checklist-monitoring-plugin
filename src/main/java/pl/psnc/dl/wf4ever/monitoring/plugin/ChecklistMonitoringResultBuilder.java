package pl.psnc.dl.wf4ever.monitoring.plugin;

import java.util.HashMap;
import java.util.Map;

import pl.psnc.synat.wrdz.mdz.plugin.VerificationResult;

import com.google.inject.Singleton;
import com.sun.syndication.feed.synd.SyndEntry;
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
        VerificationResult result = new VerificationResult();
        if (feed.getEntries() == null || feed.getEntries().size() == 0) {
            return null;
        }

        result.setMessage("There are fallowing changes in the RO history quality");
        Map<String, Object> resultMap = new HashMap<>();
        for (Object ob : feed.getEntries()) {
            SyndEntry entry = (SyndEntry) ob;
            resultMap.put(entry.getUri(), entry.toString());
        }
        result.setData(resultMap);
        return result;
    }
}
