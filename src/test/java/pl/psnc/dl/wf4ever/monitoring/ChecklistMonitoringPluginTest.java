package pl.psnc.dl.wf4ever.monitoring;

import org.junit.Test;

import pl.psnc.dl.wf4ever.monitoring.dependencyinjection.GuiceModule;
import pl.psnc.dl.wf4ever.monitoring.service.exception.ChecklistMonitoringPluginException;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ChecklistMonitoringPluginTest {

    public void testConstructor() {

    }


    @Test
    public void testExecute()
            throws ChecklistMonitoringPluginException {
        Injector injector = Guice.createInjector(new GuiceModule());
        ChecklistMonitoringPlugin plugin = new ChecklistMonitoringPlugin();
        plugin.execute("http://www.example.com/some-id/");
    }
}
