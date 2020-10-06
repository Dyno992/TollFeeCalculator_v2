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
    public TollFeeCalculator_v2(String inputFile)  {
        String[] dateStrings;
        try {
            dateStrings = getDateStrings(inputFile);
            LocalDateTime[] dates = getDates(dateStrings);
            System.out.println("The total fee for the inputfile is " + getTotalFeeCost(dates));
        } catch(FileNotFoundException | NoSuchElementException | DateTimeParseException e)  {
            System.err.println("Could not read file: " + inputFile); // ToDo bug 1-2, add specific exceptions
        }
    }

    public static String[] getDateStrings(String inputFile) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(inputFile));
        return sc.nextLine().split(", ");
    }

    public static LocalDateTime[] getDates(String[] dateStrings) {
        LocalDateTime[] dates = new LocalDateTime[dateStrings.length]; // Todo bug 3, need to change length to correct length.
        for(int i = 0; i < dates.length; i++) {
            dates[i] = LocalDateTime.parse(dateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
        return dates;
    }

    public static int getTotalFeeCost(LocalDateTime[] dates) {
        int totalFee = 0;
        int tempFeeWithinAnHour = 0;   // toDo bug 4, need also new variables to store prices in
        int lastFee = 0;
        int actualFee = 0;
        long diffInMinutes = 0;
        LocalDateTime intervalStart = dates[0];
        for (LocalDateTime date : dates) {
            System.out.println(date.toString());
            diffInMinutes = intervalStart.until(date, ChronoUnit.MINUTES);
            if (diffInMinutes <= 60) {
                lastFee = getTollFeePerPassing(intervalStart);
                actualFee = getTollFeePerPassing(date);
                tempFeeWithinAnHour = Math.max(getTollFeePerPassing(date), tempFeeWithinAnHour);
                if (tempFeeWithinAnHour > totalFee){       // ToDo bug 5, added if to store price in totalFee
                    totalFee = tempFeeWithinAnHour;
                }else if (lastFee > actualFee){           // ToDo bug 10, added else if (lastFee > actualFee)
                    totalFee -= actualFee;
                    totalFee += lastFee;
                }
            } else {                     // ToDo bug 6, changed comparison between interValFee and dateFee
                if (tempFeeWithinAnHour > getTollFeePerPassing(date)){
                    tempFeeWithinAnHour = Math.min(getTollFeePerPassing(date), tempFeeWithinAnHour);
                }
                tempFeeWithinAnHour = Math.max(getTollFeePerPassing(date), tempFeeWithinAnHour);
                totalFee += tempFeeWithinAnHour;
                intervalStart = date;
                tempFeeWithinAnHour = getTollFeePerPassing(intervalStart);
            }

        }
       // if(diffInMinutes > 60) totalFee +=tempFeeWithinAnHour;
        return Math.min(totalFee, 60);       // ToDo bug 7, need to change max to min.
    }

    public static int getTollFeePerPassing(LocalDateTime date) {
        if (isTollFreeDate(date)) return 0;
        int hour = date.getHour();
        int minute = date.getMinute();
        if (hour == 6 && minute <= 29) return 8;
        else if (hour == 6 ) return 13;
        else if (hour == 7 ) return 18;
        else if (hour == 8 && minute <= 29) return 13;
        else if (hour >= 8 && hour <= 14 && minute < 59 ) return 8;// Todo bug 8, need to change if to 8:00-14:59
        else if (hour == 15 && minute <= 29) return 13;
        else if (hour == 15  || hour == 16) return 18;      // ToDo bug 9, Unnecessary Code, cleaned up variable minute.
        else if (hour == 17) return 13;
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
