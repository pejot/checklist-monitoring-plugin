package pl.psnc.dl.wf4ever.monitoring.utils.feed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Test;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;

public class FeedUtilsTest {

    @Test
    public void testGetTheFeshestDate() {
        DateTime now = DateTime.now();
        List list = new ArrayList<>();
        SyndEntry entry = new SyndEntryImpl();
        entry.setPublishedDate(now.minusDays(4).toDate());
        list.add(entry);
        entry = new SyndEntryImpl();
        entry.setPublishedDate(now.minusSeconds(1).toDate());
        list.add(entry);
        entry = new SyndEntryImpl();
        entry.setPublishedDate(now.toDate());
        list.add(entry);
        entry = new SyndEntryImpl();
        Date expectedDate = now.plusHours(2).toDate();
        entry.setPublishedDate(expectedDate);
        list.add(entry);
        Assert.assertEquals(expectedDate, FeedUtils.getTheFreshestDate(list));

    }
}
