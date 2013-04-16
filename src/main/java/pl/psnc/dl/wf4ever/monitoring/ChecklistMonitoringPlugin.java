package pl.psnc.dl.wf4ever.monitoring;

import java.net.URI;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import pl.psnc.dl.darceo.monitoring.MonitoringPlugin;
import pl.psnc.dl.darceo.monitoring.MonitoringResult;
import pl.psnc.dl.wf4ever.monitoring.dependencyinjection.GuiceModule;
import pl.psnc.dl.wf4ever.monitoring.stability.StabilityNotificationsService;
import pl.psnc.dl.wf4ever.monitoring.stability.StabilityNotificationsServiceImpl;

import com.google.inject.Guice;
import com.sun.syndication.feed.synd.SyndFeed;

/**
 * Monitoring Plugin implementation.
 * 
 * @author pejot
 * 
 */
public class ChecklistMonitoringPlugin implements MonitoringPlugin {

    /** The service uri. */
    private URI serviceUri;
    /** The minim definition uri. */
    private URI minimUri;
    /** Stability service. */
    private StabilityNotificationsService stabilityNotifiationsService;
    /** Default checklist request purpose. */
    private static final String PURPOUSE = "ready-to-release";
    /** Logger. */
    private static final Logger LOGGER = Logger.getLogger(ChecklistMonitoringPlugin.class);


    /**
     * Default constructor.
     */
    public ChecklistMonitoringPlugin() {
        stabilityNotifiationsService = Guice.createInjector(new GuiceModule()).getInstance(
            StabilityNotificationsServiceImpl.class);
    }


    /**
     * Plugin execute function.
     * 
     * @param objectId
     *            examined research object id
     * @return the result of examination in the dArceo readable form
     */
    public MonitoringResult execute(String objectId) {
        //take a from parameter from rodl notification for now set unlimited
        DateTime from = null;
        DateTime to = DateTime.now();
        SyndFeed feed = stabilityNotifiationsService.getFeed(URI.create(objectId), from, to);
        return null;
    }
}
