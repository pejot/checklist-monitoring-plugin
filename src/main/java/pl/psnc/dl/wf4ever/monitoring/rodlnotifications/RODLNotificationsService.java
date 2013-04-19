package pl.psnc.dl.wf4ever.monitoring.rodlnotifications;

import java.net.URI;

import com.sun.syndication.feed.synd.SyndFeed;

/**
 * RO State Service interface.
 * 
 * @author pejot
 * 
 */
public interface RODLNotificationsService {

    /**
     * Get the last quality changes notification about given RO.
     * 
     * @param researchObjectUri
     *            RO uri
     * @return the last notification about RO in the Atom Feed format
     */
    SyndFeed getLastFeed(URI researchObjectUri);
}
