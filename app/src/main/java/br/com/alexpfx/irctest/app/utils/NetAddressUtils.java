package br.com.alexpfx.irctest.app.utils;

import java.util.regex.Pattern;

/**
 * Created by alexandre on 29/06/15.
 */
public class NetAddressUtils {

    public static boolean isValidMACAddress(String line) {
        Pattern p = Pattern.compile("^([0-9A-F]{2}[:-]){5}([0-9A-F]{2})$");
        final boolean matches = p.matcher(line.toUpperCase()).matches();
        return matches;
    }
}
