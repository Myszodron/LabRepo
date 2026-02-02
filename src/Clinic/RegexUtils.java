package Clinic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegexUtils {

    public static String find(String regex, String text) {
        Matcher m = Pattern.compile(regex).matcher(text);
        return m.find() ? m.group() : "not found";
    }

    public static String findWithGroup(String regex, String text) {
        Matcher m = Pattern.compile(regex).matcher(text);
        if (!m.find()) return "not found";
        return m.groupCount() >= 1 ? m.group(1) : m.group();
    }

    public static int safeParseInt(String text) {
        if (text == null || text.equals("not found")) return 0;
        return Integer.parseInt(text);
    }
}
