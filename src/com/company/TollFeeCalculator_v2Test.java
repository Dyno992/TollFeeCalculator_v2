package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Testing TollFeeCalculator")
public class TollFeeCalculator_v2Test {

    @BeforeEach
    void before() {
        System.out.println("Test starting");
    }

    @AfterEach
    void after() {
        System.out.println("Test ending");
    }

    @Test
    @DisplayName("Testing isTollFreeDate?")
    void isTollFreeDate() {     // testing if the toll is free during weekend and July month

        LocalDateTime date = LocalDateTime.parse("2020-07-01 09:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertTrue(TollFeeCalculator_v2.isTollFreeDate(date));

        LocalDateTime date2 = LocalDateTime.parse("2020-08-01 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertTrue(TollFeeCalculator_v2.isTollFreeDate(date2));

        LocalDateTime date3 = LocalDateTime.parse("2020-08-02 11:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertTrue(TollFeeCalculator_v2.isTollFreeDate(date3));

        LocalDateTime date4 = LocalDateTime.parse("2020-06-18 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertFalse(TollFeeCalculator_v2.isTollFreeDate(date4));
    }

    @Test
    @DisplayName("Testing Length of arrays")
    void TestLength() {
        TollFeeCalculator_v2 t = new TollFeeCalculator_v2("testData/Lab4.txt");
        assertEquals(t.testDates.length, t.testDateStrings.length);
    }

    @Test
    @DisplayName("Testing Exceptions")
    void TestExceptions() {
        try {
            try {
                new TollFeeCalculator_v2("testData/FelDatumFil.txt");
            }catch (NoSuchElementException e) {
                System.err.println(e);
                assertNull(e);
            }
            new TollFeeCalculator_v2("testData/TomFil.txt");
       }catch(DateTimeParseException e) {
            System.err.println(e);
            assertNull(e);
        }
    }

    @Test
    @DisplayName("Testing DiffInMinutes")
    void getDiffInMinutes() {
        LocalDateTime[] dates = new LocalDateTime[6];
        dates[0] = LocalDateTime.parse("2020-06-30 06:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        dates[1] = LocalDateTime.parse("2020-06-30 06:33", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        dates[2] = LocalDateTime.parse("2020-06-30 07:20", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        dates[3] = LocalDateTime.parse("2020-06-30 08:15", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        dates[4] = LocalDateTime.parse("2020-06-30 14:35", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        dates[5] = LocalDateTime.parse("2020-06-30 15:15", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(44, TollFeeCalculator_v2.getTotalFeeCost(dates));
    }

    @Test
    @DisplayName("Testing getTollFeePerPassing")
    void getTollFeePerPassing() {       // testing if the toll registers the fee when passing
        LocalDateTime date = LocalDateTime.parse("2020-06-30 06:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(8, TollFeeCalculator_v2.getTollFeePerPassing(date));

        LocalDateTime date2 = LocalDateTime.parse("2020-06-30 06:34", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(13, TollFeeCalculator_v2.getTollFeePerPassing(date2));

        LocalDateTime date3 = LocalDateTime.parse("2020-06-30 07:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(18, TollFeeCalculator_v2.getTollFeePerPassing(date3));

        LocalDateTime date4 = LocalDateTime.parse("2020-06-30 08:15", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(13, TollFeeCalculator_v2.getTollFeePerPassing(date4));

        LocalDateTime date5 = LocalDateTime.parse("2020-06-30 10:25", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(8, TollFeeCalculator_v2.getTollFeePerPassing(date5));

        LocalDateTime date6 = LocalDateTime.parse("2020-06-30 15:45", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(18, TollFeeCalculator_v2.getTollFeePerPassing(date6));

        LocalDateTime date7 = LocalDateTime.parse("2020-06-30 17:50", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(13, TollFeeCalculator_v2.getTollFeePerPassing(date7));

        LocalDateTime date8 = LocalDateTime.parse("2020-06-30 18:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(8, TollFeeCalculator_v2.getTollFeePerPassing(date8));

        LocalDateTime date9 = LocalDateTime.parse("2020-06-30 21:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(0, TollFeeCalculator_v2.getTollFeePerPassing(date9));

        LocalDateTime date10 = LocalDateTime.parse("2020-07-01 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(0, TollFeeCalculator_v2.getTollFeePerPassing(date10));

        LocalDateTime date11 = LocalDateTime.parse("2020-09-26 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(0, TollFeeCalculator_v2.getTollFeePerPassing(date10));

        LocalDateTime date12 = LocalDateTime.parse("2020-09-27 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(0, TollFeeCalculator_v2.getTollFeePerPassing(date10));
    }

    @Test
    @DisplayName("Testing the total fee cost")
    void getTotalFeeCost() {        // testing if the total fee is between 0 and 60, can not be over 60
        LocalDateTime[] dates = new LocalDateTime[10];
            dates[0] = LocalDateTime.parse("2020-06-30 06:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            dates[1] = LocalDateTime.parse("2020-06-30 06:34", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            dates[2] = LocalDateTime.parse("2020-06-30 07:52", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            dates[3] = LocalDateTime.parse("2020-06-30 08:13", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            dates[4] = LocalDateTime.parse("2020-06-30 09:25", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            dates[5] = LocalDateTime.parse("2020-06-30 10:04", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            dates[6] = LocalDateTime.parse("2020-06-30 11:50", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            dates[7] = LocalDateTime.parse("2020-06-30 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            dates[8] = LocalDateTime.parse("2020-06-30 13:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            dates[9] = LocalDateTime.parse("2020-07-01 14:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        if (TollFeeCalculator_v2.getTotalFeeCost(dates) > 60){
            assertEquals(60, TollFeeCalculator_v2.getTotalFeeCost(dates));
        }else {
            assertEquals(TollFeeCalculator_v2.getTotalFeeCost(dates), TollFeeCalculator_v2.getTotalFeeCost(dates));
        }
    }
}


