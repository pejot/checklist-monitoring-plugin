package pl.psnc.dl.wf4ever.monitoring.plugin;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import pl.psnc.dl.wf4ever.monitoring.BaseTest;
import pl.psnc.synat.wrdz.mdz.plugin.VerificationResult;

public class ChecklistMonitoringPluginTest extends BaseTest {

    private ChecklistMonitoringPlugin checklistMonitoringPlugin;


    @Before
    public void setUp()
            throws IOException {
        super.setUp();
        checklistMonitoringPlugin = new ChecklistMonitoringPlugin();
    }


    @Test
    public void testExecute() {
        VerificationResult result = checklistMonitoringPlugin.execute("http://www.example.org/rodl/ROs/simpleRO/");
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getData().size(), 2);
    }


    @Test
    public void testExecuteNoReport() {
        VerificationResult result = checklistMonitoringPlugin.execute("http://www.example.org/rodl/ROs/empty/");
        Assert.assertNull(result);
    }

}
