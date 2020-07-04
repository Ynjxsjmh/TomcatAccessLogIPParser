package com.tomcat.access.log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AccessLogUtil {
    public static Set<String> getUniqueIP(String fileName) {
        Set<String> ipSet = new HashSet<String>();

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for(String line; (line = br.readLine()) != null; ) {
                if (!line.isEmpty()) {
                    String ip = line.split(" -")[0];
                    ipSet.add(ip);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ipSet;
    }
}
