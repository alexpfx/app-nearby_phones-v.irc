package br.com.alexpfx.irctest.app;

/**
 * Created by alexandre on 28/06/15.
 */
public class TextStringUtils {


    public static final String logConcat(String sep, String... msgs) {
        StringBuilder sb = new StringBuilder();
        final int length = msgs.length;
        for (int i = 0; i < length; i++) {
            sb.append(msgs[i]);
            if (i < length - 1) {
                sb.append(sep);
            }
        }
        return sb.toString();
    }

}
