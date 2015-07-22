package br.com.alexpfx.irctest.app.utils;

/**
 * Created by alexandre on 22/07/15.
 */
public class TagUtils {

    public static String tag(Class clazz) {
        return clazz.getSimpleName();
    }

    public static String tag(Object o) {
        return o.getClass().getSimpleName();
    }

    public static String method() {
        try {
            return Thread.currentThread().getStackTrace()[3].getMethodName();
        } catch (RuntimeException e) {
            return "error_method";
        }
    }

    public static String method(String addInfo) {
        try {
            return Thread.currentThread().getStackTrace()[3].getMethodName() + " " + addInfo;
        } catch (RuntimeException e) {
            return "error_method";
        }
    }


    public static String tag() {
        try {
            return Thread.currentThread().getStackTrace()[3].getFileName();
        } catch (RuntimeException e) {
            return "error_tag";
        }
    }

}
