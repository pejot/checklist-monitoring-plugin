package pl.psnc.dl.wf4ever.monitoring.utils.feed;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;

import com.sun.syndication.feed.synd.SyndEntry;

/**
 * Feed procesor.
 * 
 * @author pejot
 * 
 */
public final class FeedUtils {

    /**
     * Hidden constructor.
     */
    private FeedUtils() {
        //nope;
    }


    /**
     * Get the freshest created date.
     * 
     * @param entries
     *            list of processed entries
     * @return the freshest created date in the list.
     */
    public static Date getTheFreshestDate(List entries) {
        DateTime result = null;
        Iterator itEntries = entries.iterator();
        while (itEntries.hasNext()) {
            SyndEntry entry = (SyndEntry) itEntries.next();
            if (entry.getPublishedDate() != null) {
                DateTime tmp = new DateTime(entry.getPublishedDate());
                if (result == null) {
                    result = tmp;
                }

                else if (result.compareTo(tmp) < 0) {
                    result = tmp;
                }
            }
        }
        return (result != null) ? result.toDate() : null;
    }
}
