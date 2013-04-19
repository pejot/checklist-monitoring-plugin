package pl.psnc.dl.wf4ever.monitoring.plugin;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

import pl.psnc.dl.wf4ever.monitoring.rodlnotifications.RODLNotificationsService;
import pl.psnc.dl.wf4ever.monitoring.rodlnotifications.RODLNotificationsServiceImpl;
import pl.psnc.dl.wf4ever.monitoring.stability.StabilityNotificationsService;
import pl.psnc.dl.wf4ever.monitoring.stability.StabilityNotificationsServiceImpl;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * Configure dependency injection.
 * 
 * @author pejot
 * 
 */
public class GuiceModule extends AbstractModule {

    /** The service uri. */
    private URI checlistNotificationsUri;
    /** The minim uri. */
    private URI roStateUri;
    /** The rodl uri. */
    private URI rodlUri;


    @Override
    protected void configure() {
        loadConfigurationProperties();
    }


    /**
     * Load configuration to bindf classes.
     * 
     * @throws ChecklistMonitoringPluginException
     */
    private void loadConfigurationProperties() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("connection.properties"));

        } catch (IOException e) {
            throw new ChecklistMonitoringPluginException("Configuration couldn't be loaded", e);
        }
        checlistNotificationsUri = URI.create(properties.getProperty("checklist_notifications_uri"));
        roStateUri = URI.create(properties.getProperty("ro_state_uri"));
        rodlUri = URI.create(properties.getProperty("rodl_uri"));
        bind(URI.class).annotatedWith(Names.named("checklistNotificationUri")).toInstance(checlistNotificationsUri);
        bind(URI.class).annotatedWith(Names.named("roStateUri")).toInstance(roStateUri);
        bind(URI.class).annotatedWith(Names.named("rodlUri")).toInstance(rodlUri);
        bind(StabilityNotificationsService.class).to(StabilityNotificationsServiceImpl.class);
        bind(RODLNotificationsService.class).to(RODLNotificationsServiceImpl.class);
        bind(ChecklistMonitoringResultBuilder.class);
    }
}
