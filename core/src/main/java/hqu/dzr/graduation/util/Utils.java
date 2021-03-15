package hqu.dzr.graduation.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

public class Utils {

    /**
     * 判断是否是一个数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否为空串
     * @param str
     * @return
     */
    public static boolean isEmptyOrHasSpaceString(String str) {
        if (str == null || str.length() == 0) return true;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPhoneNumberAndPasswordLegal(String phoneNumber, String password) {
        if (phoneNumber == null || password == null) {
            return false;
        }

        phoneNumber.trim();
        password.trim();

        if (StringUtils.isBlank(phoneNumber)
                || phoneNumber.length() != 11
                || password.length() <= 0
                || !Utils.isNumeric(phoneNumber)) {
            return false;
        }

        return true;
    }
}
