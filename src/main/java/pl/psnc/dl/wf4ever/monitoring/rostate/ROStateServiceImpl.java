package pl.psnc.dl.wf4ever.monitoring.rostate;

import java.net.URI;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

/**
 * Implementation of ROStateService.
 * 
 * @author pejot
 * 
 */
@Singleton
public class ROStateServiceImpl implements ROStateService {

    /** Service uri. */
    @Inject
    @Named("roStateUri")
    private URI serviceUri;

}
