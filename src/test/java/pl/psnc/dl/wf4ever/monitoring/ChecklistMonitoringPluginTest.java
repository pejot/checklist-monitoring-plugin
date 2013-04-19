package pl.psnc.dl.wf4ever.monitoring;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.hibernate.engine.loading.internal.LoadContexts;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import pl.psnc.dl.wf4ever.monitoring.plugin.ChecklistMonitoringPlugin;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class ChecklistMonitoringPluginTest extends BaseTest {

    private ChecklistMonitoringPlugin checklistMonitoringPlugin;
    private URI stabilityServiceUri;
    private URI rosStateUris;


    @Before
    public void setUp()
            throws IOException {
        super.setUp();
        checklistMonitoringPlugin = new ChecklistMonitoringPlugin();
    }


    @Test
    public void exampleTest() {

    }

}
