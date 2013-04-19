package pl.psnc.dl.wf4ever.monitoring.rostate;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Before;

import com.google.inject.Guice;

import pl.psnc.dl.wf4ever.monitoring.BaseTest;
import pl.psnc.dl.wf4ever.monitoring.ChecklistMonitoringResultBuilder;
import pl.psnc.dl.wf4ever.monitoring.GuiceModule;

public class ROStateServiceImplTest extends BaseTest {

    @Override
    @Before
    public void setUp()
            throws IOException {
        super.setUp();
    }
}
