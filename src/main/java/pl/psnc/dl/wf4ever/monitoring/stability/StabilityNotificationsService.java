package pl.psnc.dl.wf4ever.monitoring.stability;

import java.net.URI;

import org.joda.time.DateTime;

import com.sun.syndication.feed.synd.SyndFeed;

/**
 * The stability Notification Service interface.
 * 
 * @author pejot
 * 
 */
public interface StabilityNotificationsService {

    /**
     * Get Registered in service ro states i nthe Atom Feed format within the given range.
     * 
     * @param researchObject
     *            RO (if null unlimited - all ROs)
     * @param from
     *            range - from (if null upper range unlimited)
     * @param to
     *            range - to (if null bottom range unlimited)
     * @return feed
     */
    SyndFeed getFeed(URI researchObject, DateTime from, DateTime to);
}
