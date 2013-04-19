package pl.psnc.dl.wf4ever.monitoring.rodlnotifications;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;
import com.damnhandy.uri.template.UriTemplate;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.shared.JenaException;
import com.hp.hpl.jena.util.FileManager;
import com.sun.jersey.api.client.Client;
import com.sun.syndication.feed.synd.SyndFeed;

/**
 * Implementation of ROStateService.
 * 
 * @author pejot
 * 
 */
@Singleton
public class RODLNotificationsServiceImpl implements RODLNotificationsService {

    /** Service uri. */
    @Inject
    @Named("rodlUri")
    private URI serviceUri;
    /** rodl notifications uri. */
    @Inject
    @Named("checklistNotificationUri")
    private URI checklistNotificationUri;
    /** Notifications resource URI template. */
    private String notificationsUriTemplateString;
    /** Logger. */
    private static final Logger LOGGER = Logger.getLogger(RODLNotificationsServiceImpl.class);


    /**
     * Default Constructor.
     */
    public RODLNotificationsServiceImpl() {
        //    init();
    }


    @Override
    public SyndFeed getLastFeed(URI researchObjectUri) {
        URI requestedUri = buildUri(researchObjectUri);
        return null;
    }


    /**
     * Load the notification service description.
     */
    void init() {
        try {
            Model model = FileManager.get().loadModel(serviceUri.toString());
            Resource serviceResource = model.getResource(serviceUri.toString());
            this.notificationsUriTemplateString = serviceResource
                    .listProperties(pl.psnc.dl.wf4ever.vocabulary.NotificationService.notifications).next().getObject()
                    .asLiteral().getString();
        } catch (JenaException e) {
            LOGGER.warn("Could not initialize the notification service: " + e.getLocalizedMessage());
        }
    }


    /**
     * Build Uri to get the last notification about changes in the ro quality.
     * 
     * @param researchObjectUri
     *            the uri of research object, if is null then uri will point to the last notification in general
     * @return the last notification, null if it doesn' exist
     */
    public URI buildUri(URI researchObjectUri) {
        if (notificationsUriTemplateString == null) {
            init();
        }
        UriTemplate uriTemplate = UriTemplate.fromTemplate(notificationsUriTemplateString);
        if (researchObjectUri != null) {
            uriTemplate = uriTemplate.set("ro", researchObjectUri.toString());
        }
        uriTemplate = uriTemplate.set("limit", "1");
        uriTemplate = uriTemplate.set("source", checklistNotificationUri.toString());
        return serviceUri.resolve(UriBuilder.fromUri(uriTemplate.expand()).build());
    }
}
