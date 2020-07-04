package com.tomcat.access.log;

import java.io.IOException;

import com.maxmind.geoip.Country;
import com.maxmind.geoip.LookupService;

public class GeoIpUtil {

    private static final String COUNTRY_DATA_PATH = "./data/Country_GeoIP.dat";

    private static LookupService lookupService;

    public static String getCountryName(String ip) {
        if (lookupService == null) {
            loadCountry();
        }

        if (lookupService == null) {
            return null;
        }

        Country country = lookupService.getCountry(ip);
        if (country == null || country.getName().isEmpty()) {
            return null;
        }

        return country.getName();
    }

    public static String getCountryCode(String ip) {
        if (lookupService == null) {
            loadCountry();
        }

        if (lookupService == null) {
            return null;
        }

        Country country = lookupService.getCountry(ip);
        if (country == null || country.getCode().isEmpty()) {
            return null;
        }

        return country.getCode();
    }

    // 加载数据文件
    private static synchronized void loadCountry() {
        if (lookupService != null) {
            return;
        }

        try {
            lookupService = new LookupService(COUNTRY_DATA_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}