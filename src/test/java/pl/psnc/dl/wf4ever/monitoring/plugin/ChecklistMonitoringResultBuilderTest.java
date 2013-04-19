package pl.psnc.dl.wf4ever.monitoring.plugin;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import pl.psnc.dl.wf4ever.monitoring.BaseTest;
import pl.psnc.synat.wrdz.mdz.plugin.VerificationResult;

import com.google.inject.Guice;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class ChecklistMonitoringResultBuilderTest extends BaseTest {

    ChecklistMonitoringResultBuilder builder;


    @Override
    @Before
    public void setUp()
            throws IOException {
        super.setUp();
        builder = Guice.createInjector(new GuiceModule()).getInstance(ChecklistMonitoringResultBuilder.class);
    }


    @Test
    public void testBuildResult()
            throws IllegalArgumentException, FeedException, IOException {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(getClass().getClassLoader().getResource(
            "stability_service_notification.xml")));
        VerificationResult result = builder.buildResult(feed);
        Assert.assertNotNull(result.getMessage());
        Assert.assertEquals(2, result.getData().size());
    }
}
