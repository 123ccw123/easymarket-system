package com.ccw.filter;

public class UrlFilter {
    private static final String noAuthorization = "/api/user/add,/api/user/load/**,/api/search/**";

    public static boolean hasAuthorize(String uri){
        String[] str = noAuthorization.split(",");
        for (String url : str) {
            if (uri.equals(url)){
                return true;
            }
        }
        return false;
    }
}
