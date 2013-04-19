package pl.psnc.dl.wf4ever.monitoring;

import java.io.IOException;

import org.junit.Test;

import pl.psnc.dl.wf4ever.monitoring.plugin.ChecklistMonitoringResultBuilder;
import pl.psnc.dl.wf4ever.monitoring.plugin.GuiceModule;

import com.google.inject.Guice;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class ChecklistMonitoringResultBuilderTest {

    @Test
    public void testBuildResult()
            throws IllegalArgumentException, FeedException, IOException {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(getClass().getClassLoader().getResource("atom.xml")));
        Guice.createInjector(new GuiceModule()).getInstance(ChecklistMonitoringResultBuilder.class).buildResult(feed);
    }
}
