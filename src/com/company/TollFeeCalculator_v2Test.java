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
    private final TollFeeCalculator_v2 tollfeeTest = new TollFeeCalculator_v2("testData/Lab4.txt");
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
    void isTollFreeDate() {
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
        assertEquals(0, );
        LocalDateTime date2 = LocalDateTime.parse("2020-06-30 06:34", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date3 = LocalDateTime.parse("2020-06-30 08:52", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date4 = LocalDateTime.parse("2020-06-30 10:13", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date5 = LocalDateTime.parse("2020-06-30 10:25", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date6 = LocalDateTime.parse("2020-06-30 11:04", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date7 = LocalDateTime.parse("2020-06-30 16:50", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date8 = LocalDateTime.parse("2020-06-30 18:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date9 = LocalDateTime.parse("2020-06-30 21:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date10 = LocalDateTime.parse("2020-07-01 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));


    }
}

