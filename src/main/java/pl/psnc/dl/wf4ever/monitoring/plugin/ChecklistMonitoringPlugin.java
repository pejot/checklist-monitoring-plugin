package pl.psnc.dl.wf4ever.monitoring.plugin;

import java.net.URI;
import java.util.Date;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import pl.psnc.dl.wf4ever.monitoring.rodlnotifications.RODLNotificationsService;
import pl.psnc.dl.wf4ever.monitoring.rodlnotifications.RODLNotificationsServiceImpl;
import pl.psnc.dl.wf4ever.monitoring.stability.StabilityNotificationsService;
import pl.psnc.dl.wf4ever.monitoring.stability.StabilityNotificationsServiceImpl;
import pl.psnc.dl.wf4ever.monitoring.utils.feed.FeedUtils;
import pl.psnc.synat.wrdz.mdz.plugin.VerificationPlugin;
import pl.psnc.synat.wrdz.mdz.plugin.VerificationResult;

import com.google.inject.Guice;
import com.sun.syndication.feed.synd.SyndFeed;

/**
 * Monitoring Plugin implementation.
 * 
 * @author pejot
 * 
 */
public class ChecklistMonitoringPlugin implements VerificationPlugin {

    /** Stability service. */
    private StabilityNotificationsService stabilityNotificationsService;
    /** RODL Notifications service. */
    private RODLNotificationsService rodlNotifcationService;
    /** Logger. */
    private static final Logger LOGGER = Logger.getLogger(ChecklistMonitoringPlugin.class);
    /** Result builder. */
    private ChecklistMonitoringResultBuilder resultBuilder;


    /**
     * Default constructor.
     */
    public ChecklistMonitoringPlugin() {
        stabilityNotificationsService = Guice.createInjector(new GuiceModule()).getInstance(
            StabilityNotificationsServiceImpl.class);
        rodlNotifcationService = Guice.createInjector(new GuiceModule())
                .getInstance(RODLNotificationsServiceImpl.class);
        resultBuilder = Guice.createInjector(new GuiceModule()).getInstance(ChecklistMonitoringResultBuilder.class);
    }


    /**
     * Plugin execute function.
     * 
     * @param objectId
     *            examined research object id
     * @return the result of examination in the dArceo readable form
     */
    public VerificationResult execute(String objectId) {
        SyndFeed feed = rodlNotifcationService.getLastFeed(URI.create(objectId));
        Date from = FeedUtils.getTheFreshestDate(feed.getEntries());
        DateTime dateTimefrom = (from != null) ? new DateTime(from) : null;
        feed = stabilityNotificationsService.getFeed(URI.create(objectId), dateTimefrom, null);
        return resultBuilder.buildResult(feed);
    }
}
