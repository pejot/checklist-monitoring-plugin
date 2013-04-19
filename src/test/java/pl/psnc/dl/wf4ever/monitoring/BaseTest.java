package pl.psnc.dl.wf4ever.monitoring;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;

import pl.psnc.dl.wf4ever.monitoring.plugin.ChecklistMonitoringPluginException;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.sun.jersey.api.client.Client;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class BaseTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    protected static URI checklistNotificationUri;
    protected static URI roStateUri;
    protected static URI rodlUri;
    protected static final String HOST_STRING = "http://127.0.0.1:8089";


    @BeforeClass
    public static void setUpAll() {
        loadConfiguration();
    }


    @Before
    public void setUp()
            throws IOException {
        generalMocksBuild();
    }


    protected static void loadConfiguration() {
        Properties properties = new Properties();
        try {
            properties.load(BaseTest.class.getClassLoader().getResourceAsStream("connection.properties"));
        } catch (IOException e) {
            throw new ChecklistMonitoringPluginException("Configuration couldn't be loaded", e);
        }
        checklistNotificationUri = URI.create(properties.getProperty("checklist_notifications_uri"));
        roStateUri = URI.create(properties.getProperty("ro_state_uri"));
        rodlUri = URI.create(properties.getProperty("rodl_uri"));
    }


    protected void generalMocksBuild()
            throws IOException {
        InputStream stabilityServiceInput = BaseTest.class.getClassLoader().getResourceAsStream(
            "stability_service_notification.xml");
        InputStream lastNotificationInput = BaseTest.class.getClassLoader()
                .getResourceAsStream("last_notification.xml");
        InputStream emptyNotificationInput = BaseTest.class.getClassLoader().getResourceAsStream(
            "last_notification_case_empty.xml");
        InputStream checklistResultId1Input = BaseTest.class.getClassLoader().getResourceAsStream(
            "checklist_result_id_1.json");
        InputStream checklistResultId2Input = BaseTest.class.getClassLoader().getResourceAsStream(
            "checklist_result_id_2.json");
        //stabiltyservice
        stubFor(get(urlMatching((stabilityServiceInput.toString() + "*").replace(HOST_STRING, ""))).willReturn(
            aResponse().withStatus(200).withBody(IOUtils.toString(stabilityServiceInput))));

        //rodl get last notification
        stubFor(get(urlMatching((rodlUri.toString() + ".*notifications.*").replace(HOST_STRING, ""))).willReturn(
            aResponse().withStatus(200).withBody(IOUtils.toString(lastNotificationInput))));
        //rodl get last empty notification
        stubFor(get(urlMatching((rodlUri.toString() + "notifications.*empty.*").replace(HOST_STRING, ""))).willReturn(
            aResponse().withStatus(200).withBody(IOUtils.toString(emptyNotificationInput))));

        //checklist_result_id1
        stubFor(get(urlMatching((roStateUri.toString() + "*" + "id1" + "*").replace(HOST_STRING, ""))).willReturn(
            aResponse().withStatus(200).withBody(IOUtils.toString(checklistResultId1Input))));

        //checklist_result_id2
        stubFor(get(urlMatching((roStateUri.toString() + "*" + "id2" + "*").replace(HOST_STRING, ""))).willReturn(
            aResponse().withStatus(200).withBody(IOUtils.toString(checklistResultId2Input))));
        InputStream notificationSerrviceDescription = BaseTest.class.getClassLoader().getResourceAsStream(
            "rodl_notifications_description.rdf");

        //notification service description
        stubFor(get(urlEqualTo(rodlUri.toString().replace(HOST_STRING, ""))).withHeader("Content-Type",
            matching("*rdf*")).willReturn(
            aResponse().withStatus(200).withHeader("Content-Type", "application/rdf+xml")
                    .withBody(IOUtils.toString(notificationSerrviceDescription))));

        Client client = Client.create();
        System.out.println((rodlUri.toString() + "notifications").replace(HOST_STRING, ""));
        System.out.println(client.resource("http://127.0.0.1:8089/rodl/notifications/").get(String.class));

    }
}
