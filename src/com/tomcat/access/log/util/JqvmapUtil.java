package com.tomcat.access.log.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tomcat.access.log.AccessLogUtil;
import com.tomcat.access.log.GeoIpUtil;

public class JqvmapUtil {
    public static String parseSingleFile(String map, final String fileName) {
        Gson gson = new Gson();
        JsonElement element = gson.fromJson(map, JsonElement.class);
        JsonObject jsonMap = element.getAsJsonObject();

        Set<String> ipSet = AccessLogUtil.getUniqueIP(fileName);

        for (String ip : ipSet) {
            String countryCode = GeoIpUtil.getCountryCode(ip);
            try {
                JsonObject country = jsonMap.get(countryCode.toLowerCase()).getAsJsonObject();
                int count = country.get("count").getAsInt() + 1;
                country.addProperty("count", count);
            } catch (java.lang.NullPointerException e) {
                System.err.printf("Couldn't resolve: ip=%s, countryName=%s, countryCode=%s%n", ip,
                        GeoIpUtil.getCountryName(ip), GeoIpUtil.getCountryCode(ip));
                e.printStackTrace();
            }
        }

        return jsonMap.toString();
    }

    public static String parseDirectory(String map, final String directory) {
        File folder = new File(directory);
        List<String> fileNameList = new ArrayList<String>();
        listFilesForFolder(folder, fileNameList);

        for (String fileName : fileNameList) {
            map = parseSingleFile(map, fileName);
        }

        return map;
    }

    private static void listFilesForFolder(final File folder, List<String> fileNameList) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry, fileNameList);
            } else {
                fileNameList.add(fileEntry.getAbsolutePath());
            }
        }
    }
}
