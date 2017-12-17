package org.comlev.factograph.common;

import java.util.HashMap;
import java.util.Map;

/**
 * .
 *
 * @author <a href="mailto:AlexKomlev@gmail.com">Aleksey Komlev</a>
 * @version 18.11.2017
 */
public class CharsetHelper {
    static Map<Integer, String> chars = new HashMap<>();
    static {
        chars.put(-96, "а");
        chars.put(-95, "б");
        chars.put(-94, "в");
        chars.put(-93, "г");
        chars.put(-92, "д");
        chars.put(-91, "е");
        chars.put(-15, "ё");
        chars.put(-90, "ж");
        chars.put(-89, "з");
        chars.put(-88, "и");
        chars.put(-87, "й");
        chars.put(-86, "к");
        chars.put(-85, "л");
        chars.put(-84, "м");
        chars.put(-83, "н");
        chars.put(-82, "о");
        chars.put(-81, "п");
        chars.put(-32, "р");
        chars.put(-31, "с");
        chars.put(-30, "т");
        chars.put(-29, "у");
        chars.put(-28, "ф");
        chars.put(-27, "х");
        chars.put(-26, "ц");
        chars.put(-25, "ч");
        chars.put(-24, "ш");
        chars.put(-23, "щ");
        chars.put(-22, "ъ");
        chars.put(-21, "ы");
        chars.put(-20, "ь");
        chars.put(-19, "э");
        chars.put(-18, "ю");
        chars.put(-17, "я");

        chars.put(-128, "А");
        chars.put(-127, "Б");
        chars.put(-126, "В");
        chars.put(-125, "Г");
        chars.put(-124, "Д");
        chars.put(-123, "Е");
        chars.put(-16, "Ё");
        chars.put(-122, "Ж");
        chars.put(-121, "З");
        chars.put(-120, "И");
        chars.put(-119, "Й");
        chars.put(-118, "К");
        chars.put(-117, "Л");
        chars.put(-116, "М");
        chars.put(-115, "Н");
        chars.put(-114, "О");
        chars.put(-113, "П");
        chars.put(-112, "Р");
        chars.put(-111, "С");
        chars.put(-110, "Т");
        chars.put(-109, "У");
        chars.put(-108, "Ф");
        chars.put(-107, "Х");
        chars.put(-106, "Ц");
        chars.put(-105, "Ч");
        chars.put(-104, "Ш");
        chars.put(-103, "Щ");
        chars.put(-102, "Ъ");
        chars.put(-101, "Ы");
        chars.put(-100, "Ь");
        chars.put(-99, "Э");
        chars.put(-98, "Ю");
        chars.put(-97, "Я");
    }

    public static String byteToStr(byte[] bytes){
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            if (chars.containsKey((int)aByte)) {
                result.append(chars.get((int)aByte));
            } else {
                result.append(new String(new byte[]{aByte}));
            }
        }
        return result.toString();
    }

}
