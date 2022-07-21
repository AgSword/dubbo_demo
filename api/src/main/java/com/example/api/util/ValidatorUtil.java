package com.example.api.util;

import java.util.regex.Pattern;

/**
 * @projectName: dubbo_demo
 * @package: com.example.provider.util
 * @className: ValidatorUtil
 * @author: LiYinjian
 * @date: 2022/7/20 14:30
 * @version: 1.0
 */

public class ValidatorUtil {
    public final static Pattern mobilePattern = Pattern.compile("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$");

    public static boolean isMobile(String mobilePhone) {
        return mobilePattern.matcher(mobilePhone).matches();
    }
}
