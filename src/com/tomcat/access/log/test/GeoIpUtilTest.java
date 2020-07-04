package com.tomcat.access.log.test;

import org.junit.Test;

import com.tomcat.access.log.GeoIpUtil;

public class GeoIpUtilTest {
    @Test
    public void getCountryName() {
        String[] ips = { "117.136.58.118", "94.102.51.100", "50.117.123.235", "128.106.236.94" };

        for (String ip : ips) {
            System.out.println(GeoIpUtil.getCountryName(ip));
        }
    }

    @Test
    public void getCountryCode() {
        String[] ips = { "117.136.58.118", "94.102.51.100", "128.106.236.94" };

        for (String ip : ips) {
            System.out.println(GeoIpUtil.getCountryCode(ip));
        }
    }
}
