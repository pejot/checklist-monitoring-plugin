package pl.psnc.dl.wf4ever.monitoring.dependencyinjection;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

import pl.psnc.dl.wf4ever.monitoring.service.exception.ChecklistMonitoringPluginException;
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
    private URI serviceUri;
    /** The minim uti. */
    private URI minimUri;


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
        serviceUri = URI.create(properties.getProperty("checklist_uri"));
        minimUri = URI.create(properties.getProperty("checklist_minim_uri"));
        bind(URI.class).annotatedWith(Names.named("minimUri")).toInstance(minimUri);
        bind(URI.class).annotatedWith(Names.named("serviceUri")).toInstance(serviceUri);
        bind(StabilityNotificationsService.class).to(StabilityNotificationsServiceImpl.class);
    }
}
