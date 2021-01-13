package sun.net.www.protocol.az;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * @author az
 * @since 08/03/20
 */
public class SimpleHandler extends URLStreamHandler {

    @Override
    protected URLConnection openConnection(URL u) {
        return new AzUrlConnection(u);
    }
}
