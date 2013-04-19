package pl.psnc.dl.wf4ever.monitoring;

import java.net.URI;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import pl.psnc.dl.darceo.monitoring.MonitoringPlugin;
import pl.psnc.dl.darceo.monitoring.MonitoringResult;
import pl.psnc.dl.wf4ever.monitoring.rodlnotifications.RODLNotificationsService;
import pl.psnc.dl.wf4ever.monitoring.rodlnotifications.RODLNotificationsServiceImpl;
import pl.psnc.dl.wf4ever.monitoring.rostate.ROStateService;
import pl.psnc.dl.wf4ever.monitoring.rostate.ROStateServiceImpl;
import pl.psnc.dl.wf4ever.monitoring.stability.StabilityNotificationsService;
import pl.psnc.dl.wf4ever.monitoring.stability.StabilityNotificationsServiceImpl;

import com.google.inject.Guice;

/**
 * Monitoring Plugin implementation.
 * 
 * @author pejot
 * 
 */
public class ChecklistMonitoringPlugin implements MonitoringPlugin {

    /** Stability service. */
    private StabilityNotificationsService stabilityNotificationsService;
    /** RO states service. */
    private ROStateService roStateService;
    /** RODL Notifications service. */
    private RODLNotificationsService rodlNotifcationService;

    /** Default checklist request purpose. */
    private static final String PURPOUSE = "ready-to-release";
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
        roStateService = Guice.createInjector(new GuiceModule()).getInstance(ROStateServiceImpl.class);
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
    public MonitoringResult execute(String objectId) {
        //take a from parameter from rodl notification for now set unlimited
        DateTime from = null;
        DateTime to = DateTime.now();
        stabilityNotificationsService.getFeed(URI.create(objectId), from, to);
        return resultBuilder.buildResult(stabilityNotificationsService.getFeed(URI.create(objectId), from, to));
    }
}
