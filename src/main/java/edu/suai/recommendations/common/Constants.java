package edu.suai.recommendations.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String LOGIN = "login";
    public static final String STR_NULL = "null";
    public static final String DEFAULT_SEARCH_VALUE = "";

    public static final String USER = "/user";
    public static final String MODER = "/control";
    public static final String SHOP = "/shop";
    public static final String CATEGORY = "/category";
    public static final String SUBCATEGORY = "/subcategory";
    public static final String CRITERIA = "/criteria";
    public static final String PRODUCT = "/product";
    public static final String OPTION = "/option";
    public static final String ADD = "/add";
    public static final String REDIRECT = "redirect:";
    public static final String CONTROL = "/control";
    public static final String ME = "/me";
    public static final String MAIN = "/";

    public static final double SIGNIFICANCE = 0.8;
    public static final double MULTI = 1+(1-SIGNIFICANCE);




    public static final String TOKEN_HEADER = "X-token";
    public static final String TOKEN_AUTH_TIME = "auth_time";
    public static final String TOKEN_EXPIRED_TIME = "exp";

    public static final String CITEXT = "citext";
}
