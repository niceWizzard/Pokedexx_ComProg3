package org.nice;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Utils {
    public static URL getResource(String resourcePath) {
        try {
            return Objects.requireNonNull(
                    Utils.class.getResource(URLDecoder.decode(resourcePath, StandardCharsets.UTF_8))
            );
        }catch(Exception e) {
            throw  new RuntimeException("The file with path: " + resourcePath + " does not exists.");
        }
    }
}
