package pl.psnc.dl.wf4ever.monitoring.rodlnotifications;

import java.io.IOException;
import java.net.URI;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;

import pl.psnc.dl.wf4ever.monitoring.BaseTest;
import pl.psnc.dl.wf4ever.monitoring.GuiceModule;

public class RODLNotificationsServiceImplTest extends BaseTest {

    RODLNotificationsServiceImpl rodlNotificationsService;
    URI researchObjectUri = URI.create("http://www.example.com/ROs/research-object/");


    @Override
    @Before
    public void setUp()
            throws IOException {
        super.setUp();
        rodlNotificationsService = Guice.createInjector(new GuiceModule()).getInstance(
            RODLNotificationsServiceImpl.class);
    }


    @Test
    public void testBuildUri() {
        String resultUri = rodlNotificationsService.buildUri(researchObjectUri).toString();
        //result uri should contain three parameters
        Assert.assertTrue(resultUri.contains("ro"));
        Assert.assertTrue(resultUri.contains("source"));
        Assert.assertTrue(resultUri.contains("limit"));
        Assert.assertFalse(resultUri.contains("from"));
        Assert.assertFalse(resultUri.contains("to"));
    }


    @Test
    public void testGetLastFeed() {
        Assert.assertNotNull(rodlNotificationsService.getLastFeed(researchObjectUri).getEntries().size());
    }
}
