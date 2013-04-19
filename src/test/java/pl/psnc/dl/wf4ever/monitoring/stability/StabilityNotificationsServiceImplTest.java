package pl.psnc.dl.wf4ever.monitoring.stability;

import java.io.IOException;
import java.net.URI;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import pl.psnc.dl.wf4ever.monitoring.BaseTest;
import pl.psnc.dl.wf4ever.monitoring.plugin.GuiceModule;

import com.google.inject.Guice;

public class StabilityNotificationsServiceImplTest extends BaseTest {

    StabilityNotificationsServiceImpl stabilityNotificationsService;


    @Override
    @Before
    public void setUp()
            throws IOException {
        super.setUp();
        stabilityNotificationsService = Guice.createInjector(new GuiceModule()).getInstance(
            StabilityNotificationsServiceImpl.class);
    }


    @Test
    public void testGetFeed() {
        DateTime now = DateTime.now();
        URI researchObjectUri = URI.create("http://www.example.org/rodl/ROs/simpleRO");
        Assert.assertNotNull((stabilityNotificationsService.getFeed(researchObjectUri, now, null)));
    }
}
