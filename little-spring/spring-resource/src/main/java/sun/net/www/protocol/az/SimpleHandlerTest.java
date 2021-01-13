package sun.net.www.protocol.az;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author az
 * @since 08/03/20
 */
public class SimpleHandlerTest {

    public static void main(String[] args) throws IOException {
        // 类似于 classpath:/META-INF/default.properties
        URL url = new URL("az:///META-INF/default.properties");
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8));
    }
}
