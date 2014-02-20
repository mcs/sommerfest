package configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class Conf {

    public String getServiceUrl() {
        return System.getProperty("sommerfest.serviceUrl");
    }
}
