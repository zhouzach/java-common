package string;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StringEncode {

    public static void main(String[] args) {
        try {
            String tags = URLEncoder.encode("\"tags\" : \"内测\"", "UTF-8");
            System.out.println("tags: " + tags);
            String rawTags = URLDecoder.decode(tags, "UTF-8");
            System.out.println("rawTags: " + rawTags);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
