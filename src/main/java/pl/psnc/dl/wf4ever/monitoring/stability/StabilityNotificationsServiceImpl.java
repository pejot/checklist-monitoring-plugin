package pl.psnc.dl.wf4ever.monitoring.stability;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
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
public final class StabilityNotificationsServiceImpl implements StabilityNotificationsService {

    /** Service uri. */
    @Inject
    @Named("serviceUri")
    private URI serviceUri;
    /** jersay client. */
    private Client client;
    /** Logger. */
    private static final Logger LOGGER = Logger.getLogger(StabilityNotificationsServiceImpl.class);


    /**
     * Constructor.
     * 
     */
    public StabilityNotificationsServiceImpl() {
        client = Client.create();
    }


    public URI getServiceUri() {
        return serviceUri;
    }


    @Override
    public SyndFeed getFeed(URI researchObject, DateTime from, DateTime to) {
        WebResource resource = client.resource(serviceUri);
        resource = (researchObject != null) ? resource.queryParam(StabilityNotificationsServiceDictionary.RO,
            researchObject.toString()) : resource;
        resource = (from != null) ? resource.queryParam(StabilityNotificationsServiceDictionary.FROM, ISODateTimeFormat
                .dateTime().print(from)) : resource;
        resource = (from != null) ? resource.queryParam(StabilityNotificationsServiceDictionary.TO, ISODateTimeFormat
                .dateTime().print(to)) : resource;
        InputStream feedInputStream = resource.get(InputStream.class);

        SyndFeedInput input = new SyndFeedInput();
        try {
            return input.build(new XmlReader(feedInputStream));
        } catch (IllegalArgumentException | FeedException | IOException e) {
            LOGGER.error("Can't get the feed " + resource.getURI().toString(), e);
            return null;
        }
    }
}
