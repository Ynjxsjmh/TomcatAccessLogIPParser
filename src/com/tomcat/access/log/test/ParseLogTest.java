package com.tomcat.access.log.test;

import org.junit.Test;

import com.tomcat.access.log.ParseLog;

import picocli.CommandLine;

public class ParseLogTest {
    @Test
    public void testParseLog() {
        new CommandLine(new ParseLog()).execute("-f", "C:/test/logs/rsdb_access_log.2020-07-02.txt");
    }
}
