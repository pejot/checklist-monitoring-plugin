package pl.psnc.dl.wf4ever.monitoring;

import org.junit.Test;

import pl.psnc.dl.wf4ever.monitoring.service.exception.ChecklistMonitoringPluginException;

public class ChecklistMonitoringPluginTest {

    public void testConstructor() {

    }


    @Test
    public void testExecute()
            throws ChecklistMonitoringPluginException {
        ChecklistMonitoringPlugin plugin = new ChecklistMonitoringPlugin();
        plugin.execute("http://sandbox.wf4ever-project.org/rodl/ROs/Pack387/");

    }
}
