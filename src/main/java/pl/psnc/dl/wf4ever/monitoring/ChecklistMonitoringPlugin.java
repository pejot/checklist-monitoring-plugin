package pl.psnc.dl.wf4ever.monitoring;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

import org.purl.wf4ever.checklist.client.ChecklistEvaluationService;
import org.purl.wf4ever.checklist.client.EvaluationResult;

import pl.psnc.dl.darceo.monitoring.MonitoringPlugin;
import pl.psnc.dl.darceo.monitoring.MonitoringResult;
import pl.psnc.dl.wf4ever.monitoring.service.exception.ChecklistMonitoringPluginException;

public class ChecklistMonitoringPlugin implements MonitoringPlugin {

    /** The service uri. */
    private URI serviceUri;
    /** The minim definition uri. */
    private URI minimUri;

    private static final String purpouse = "repatable";


    /**
     * Constructor.
     * 
     * @throws ChecklistMonitoringPluginException
     *             in case the plugin can be created because the configuration can't be loaded
     */
    public ChecklistMonitoringPlugin()
            throws ChecklistMonitoringPluginException {
        try {
            loadProperties();
        } catch (IOException e) {
            throw new ChecklistMonitoringPluginException("Configuration can't be loaded", e);
        }
    }


    public MonitoringResult execute(String objectId) {
        ChecklistEvaluationService service = new ChecklistEvaluationService(serviceUri, minimUri);
        EvaluationResult evaluationResult = service.evaluate(URI.create(objectId), "ready-to-release");

        return null;
    }


    /**
     * Load connection properties.
     * 
     * @throws IOException
     *             in case properties canb't be loaded
     */
    private void loadProperties()
            throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("connection.properties"));
        serviceUri = URI.create(properties.getProperty("checklist_uri"));
        minimUri = URI.create(properties.getProperty("checklist_minim_uri"));
    }
}
