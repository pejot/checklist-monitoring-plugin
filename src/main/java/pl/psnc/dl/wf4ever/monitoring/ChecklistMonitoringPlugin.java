package pl.psnc.dl.wf4ever.monitoring;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

import pl.psnc.dl.darceo.monitoring.MonitoringPlugin;
import pl.psnc.dl.darceo.monitoring.MonitoringResult;
import pl.psnc.dl.wf4ever.monitoring.service.exception.ChecklistMonitoringPluginException;

public class ChecklistMonitoringPlugin implements MonitoringPlugin {

    /** The service uri. */
    private URI serviceUri;
    /** The minim definition uri. */
    private URI minimUri;
    /** Default checklist request purpose. */
    private static final String PURPOUSE = "ready-to-release";


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


    /**
     * Plugin execute function.
     * 
     * @param objectId
     *            examined research object id
     * @return the result of examination in the dArceo readable form
     */
    public MonitoringResult execute(String objectId) {
        //ChecklistEvaluationService service = new ChecklistEvaluationService(serviceUri, minimUri);
        //EvaluationResult result = service.evaluate(URI.create(objectId), PURPOUSE);
        //buil new data
        //save new data
        //ChecklistMonitoringData data = new ChecklistMonitoringData(result);
        //return ChecklistMonitoringResultBuilder.builResult(result);
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
