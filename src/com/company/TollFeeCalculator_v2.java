package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TollFeeCalculator_v2 {
    public String[] testDateStrings;
    public LocalDateTime[] testDates;
    public TollFeeCalculator_v2(String inputFile)  {
        try {
            Scanner sc = new Scanner(new File(inputFile));
            String[] dateStrings = sc.nextLine().split(", ");
            LocalDateTime[] dates = new LocalDateTime[dateStrings.length-1];// Todo bug 2, need to change length to correct length.
            testDateStrings = dateStrings;
            testDates = dates;
            for(int i = 0; i < dates.length; i++) {
                dates[i] = LocalDateTime.parse(dateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }
            System.out.println("The total fee for the inputfile is " + getTotalFeeCost(dates));
        } catch(IOException e)  {           // ToDo bug 5-6, add specific exceptions
            System.err.println("Could not read file: " + inputFile);
        }
    }

    public static int getTotalFeeCost(LocalDateTime[] dates) {
        int totalFee = 0;
        int tempFeeWithinAnHour = 0;                    // toDo bug 8, need also new variables to store prices in
        int feePrice = 0;
        try {
            LocalDateTime intervalStart = dates[0];
            for (LocalDateTime date : dates) {
                System.out.println(date.toString());
                System.out.println("Total, feeprice, temp : " + totalFee +"," + feePrice + "," + tempFeeWithinAnHour);
                long diffInMinutes = intervalStart.until(date, ChronoUnit.MINUTES);
                if (diffInMinutes >= 60) {              //ToDo bug 9,
                    feePrice = getTollFeePerPassing(date);
                    totalFee += feePrice;
                    intervalStart = date;
                } else {
                    tempFeeWithinAnHour = Math.max(feePrice, tempFeeWithinAnHour);
                }                         // ToDo bug 4, need to change operator for totalFee from "+=" to "=".
            }
        } catch (Exception e){             // ToDo bug 7, dont need TryCatch here (överflödig kod???)
            System.out.println("Something went wrong: " + e);
        }
        return Math.min(totalFee + tempFeeWithinAnHour, 60);       // ToDo bug 3, need to change max to min.
    }

    public static int getTollFeePerPassing(LocalDateTime date) {
        if (isTollFreeDate(date)) return 0;
        int hour = date.getHour();
        int minute = date.getMinute();                        // ToDo bug 8, remove minutes in the if and elseif
        if (hour == 6 && minute <= 29) return 8;
        else if (hour == 6 ) return 13;
        else if (hour == 7 ) return 18;
        else if (hour == 8 && minute <= 29) return 13;
        else if (hour >= 8 && hour <= 14 && minute < 59 ) return 8;// Todo bug 1, need to change if to 8:00-14:59
        else if (hour == 15 && minute <= 29) return 13;
        else if (hour == 15  || hour == 16 ) return 18;
        else if (hour == 17 && minute >= 0 && minute <= 59) return 13;
        else if (hour == 18 && minute <= 29) return 8;
        else return 0;
    }

    public static boolean isTollFreeDate(LocalDateTime date) {
        return date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7 || date.getMonth().getValue() == 7;
    }

    public static void main(String[] args) {
        new TollFeeCalculator_v2("testData/Lab4.txt");
    }
}
