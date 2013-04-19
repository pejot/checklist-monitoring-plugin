package pl.psnc.dl.wf4ever.monitoring.stability;

import java.io.IOException;

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
        stabilityNotificationsService.getFeed(null, null, null);
    }

}
