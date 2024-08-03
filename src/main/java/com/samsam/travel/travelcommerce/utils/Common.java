package com.samsam.travel.travelcommerce.utils;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class Common {
    private static String OS = System.getProperty("os.name").toLowerCase();

    public String getTargetUuid(String targerName) {
        return (targerName + "-" + UUID.randomUUID()).toString().toUpperCase();
    }

    public String getUpperUuid() {
        return UUID.randomUUID().toString().toUpperCase();
    }

}
