package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogHelper {
    /**
     * The log object used for debugging.
     */
    private static final Logger LOG = LoggerFactory.getLogger(LogHelper.class);

    public static void main(String[] args) {
        try {
            int i = 3 / 0;
        } catch (Throwable t) {

            LOG.error("mod 0!", t);
            LOG.warn("throwable is {}", t.toString());

        }
    }
}
