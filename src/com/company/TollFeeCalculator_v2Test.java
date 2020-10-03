package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

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
    @DisplayName("Testing getTollFeePerPassing")
    void getTollFeePerPassing() {
        LocalDateTime date = LocalDateTime.parse("2020-06-30 00:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(0, TollFeeCalculator_v2.getTollFeePerPassing(date));
        LocalDateTime date2 = LocalDateTime.parse("2020-06-30 06:34", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(13, TollFeeCalculator_v2.getTollFeePerPassing(date2));
        LocalDateTime date3 = LocalDateTime.parse("2020-06-30 08:52", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(8, TollFeeCalculator_v2.getTollFeePerPassing(date3));
        LocalDateTime date4 = LocalDateTime.parse("2020-06-30 10:13", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(8, TollFeeCalculator_v2.getTollFeePerPassing(date4));
        LocalDateTime date5 = LocalDateTime.parse("2020-06-30 10:25", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(8, TollFeeCalculator_v2.getTollFeePerPassing(date5));
        LocalDateTime date6 = LocalDateTime.parse("2020-06-30 11:04", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(8, TollFeeCalculator_v2.getTollFeePerPassing(date6));
        LocalDateTime date7 = LocalDateTime.parse("2020-06-30 16:50", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(18, TollFeeCalculator_v2.getTollFeePerPassing(date7));
        LocalDateTime date8 = LocalDateTime.parse("2020-06-30 18:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(8, TollFeeCalculator_v2.getTollFeePerPassing(date8));
        LocalDateTime date9 = LocalDateTime.parse("2020-06-30 21:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(0, TollFeeCalculator_v2.getTollFeePerPassing(date9));
        LocalDateTime date10 = LocalDateTime.parse("2020-07-01 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));


    }
}

