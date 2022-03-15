package za.co.ezmed.qa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
class TestDates_Format {
    public static void main(String args[]) {
        Date objDate = new Date(); // Current System Date and time is assigned to objDate
        String strDateFormat = "dd-MMM-yyyy hh:mm"; //Date format is Specified  :ss a
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); //Date format string is passed as an argument to the Date format object
        System.out.println(objSDF.format(objDate)); //Date formatting is applied to the current date
    }
}