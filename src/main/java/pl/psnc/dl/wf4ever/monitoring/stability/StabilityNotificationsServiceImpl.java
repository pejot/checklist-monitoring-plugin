package pl.psnc.dl.wf4ever.monitoring.stability;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * Connect with the stability service to obtain notifications.
 * 
 * @author pejot
 * 
 */
@Singleton
public final class StabilityNotificationsServiceImpl implements StabilityNotificationsService {

    /** Service uri. */
    @Inject
    @Named("checklistNotificationUri")
    private URI serviceUri;
    /** Logger. */
    private static final Logger LOGGER = Logger.getLogger(StabilityNotificationsServiceImpl.class);


    public URI getServiceUri() {
        return serviceUri;
    }


    @Override
    public SyndFeed getFeed(URI researchObject, DateTime from, DateTime to) {
        //@TODO establish with ISOCO the way of  building the URI or basically wait for their specification.
        URI resultUri = serviceUri;
        resultUri = (researchObject != null) ? UriBuilder.fromUri(resultUri)
                .queryParam(StabilityNotificationsServiceDictionary.RO, researchObject.toString()).build() : resultUri;
        resultUri = (researchObject != null) ? UriBuilder.fromUri(resultUri)
                .queryParam(StabilityNotificationsServiceDictionary.RO, researchObject.toString()).build() : resultUri;
        resultUri = (researchObject != null) ? UriBuilder.fromUri(resultUri)
                .queryParam(StabilityNotificationsServiceDictionary.RO, researchObject.toString()).build() : resultUri;
        SyndFeedInput input = new SyndFeedInput();
        try {
            return input.build(new XmlReader(resultUri.toURL()));
        } catch (IllegalArgumentException | FeedException | IOException e) {
            LOGGER.error("Can't get the feed " + resultUri.toString(), e);
            return null;
        }

    }
}
