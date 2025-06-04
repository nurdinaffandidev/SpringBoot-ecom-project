package com.nurdinaffandidev.SpringBoot_ecom_project.utilities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
    public static Date convertToDate(String isoDate) {
        LocalDate localDate = LocalDate.parse(isoDate);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
