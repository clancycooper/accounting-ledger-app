package com.pluralsight;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import static com.pluralsight.AccountingLedgerApp.reportsScreen;
import static com.pluralsight.AccountingLedgerApp.scanner;
import static com.pluralsight.Reader.transactionList;

/*This is my ReportsFunctions class
In this class you'll find the following methods:
monthToDate();
previousMonth();
yearToDate();
previousYear();
searchByVendor(); -Should have an additional "return home" option
 */
public class ReportsFunctions {

    public static void monthToDate() throws IOException {
        //[0] - Year, [1] - Month, [2] - Day
        System.out.println("Here are your Month to Date statements:");
        for (Map.Entry<String, Transaction> dateSet : transactionList.entrySet()) {
            String[] splitDates = dateSet.getValue().getDate().split("-");
            LocalDate today = LocalDate.now();
            if((Integer.parseInt(splitDates[1]) == today.getMonthValue()) && (Integer.parseInt(splitDates[2]) <= today.getDayOfMonth())) {
                System.out.printf("Date: %S | Time: %S | Description: %S | Vendor: %S | Amount $%.2f\n", dateSet.getValue().getDate(), dateSet.getValue().getTime(), dateSet.getValue().getDesc(), dateSet.getValue().getVendor(), dateSet.getValue().getAmount());
            }
        }
        System.out.println("Press 'X' when you're ready to return to the Reports menu");
        String exit = scanner.nextLine().toUpperCase().trim();
        switch(exit) {
            case "X":
                System.out.println("Redirecting to Reports menu...");
                reportsScreen();
            default:
                System.out.println("Sorry, didn't catch that. Press 'X' to return to the Reports Menu");
                monthToDate();
        }
    }
}