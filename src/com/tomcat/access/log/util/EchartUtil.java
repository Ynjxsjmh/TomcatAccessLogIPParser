package com.tomcat.access.log.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tomcat.access.log.AccessLogUtil;
import com.tomcat.access.log.GeoIpUtil;

public class EchartUtil extends BaseUtil {
    @Override
    public String parseSingleFile(String map, final String fileName) {
        Gson gson = new Gson();
        JsonArray countriesArray = gson.fromJson(map, JsonArray.class);

        Set<String> ipSet = AccessLogUtil.getUniqueIP(fileName);

        for (String ip : ipSet) {
            String countryCode = GeoIpUtil.getCountryCode(ip);
            int i = 0;

            for (; i < countriesArray.size(); i++) {
                JsonElement country = countriesArray.get(i);
                JsonObject countryObj = country.getAsJsonObject();
                String code = countryObj.get("code").getAsString();

                if (code.equals(countryCode)) {
                    int count = countryObj.get("value").getAsInt() + 1;
                    countryObj.addProperty("value", count);
                    break;
                }
            }

            if (i >= countriesArray.size()) {
                System.err.printf("Couldn't resolve: ip=%s, countryName=%s, countryCode=%s%n",
                        ip, GeoIpUtil.getCountryName(ip), GeoIpUtil.getCountryCode(ip));
            }
        }

        return countriesArray.toString();
    }

    @Override
    public String parseDirectory(String map, final String directory) {
        File folder = new File(directory);
        List<String> fileNameList = new ArrayList<String>();
        this.listFilesForFolder(folder, fileNameList);

        for (String fileName : fileNameList) {
            map = this.parseSingleFile(map, fileName);
        }

        return map;
    }

}
