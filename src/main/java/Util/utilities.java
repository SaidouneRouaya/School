package Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class utilities {



    public static Date formatDate (String date){
        Map<Pattern, DateFormat> dateFormatPatterns = new HashMap<Pattern, DateFormat>();
        dateFormatPatterns.put(Pattern.compile("^((0|1)\\d{1})-((0|1|2)\\d{1})-((20)\\d{2})"), new SimpleDateFormat("MM-dd-yyyy"));
        dateFormatPatterns.put(Pattern.compile("^((0|1)\\d{1})/((0|1|2)\\d{1})/((20)\\d{2})"), new SimpleDateFormat("MM/dd/yyyy"));

        dateFormatPatterns.put(Pattern.compile("^((0|1|2)\\d{1})-((0|1)\\d{1})-((20)\\d{2})"), new SimpleDateFormat("dd-MM-yyyy"));
        dateFormatPatterns.put(Pattern.compile("^((0|1|2)\\d{1})/((0|1)\\d{1})/((20)\\d{2})"), new SimpleDateFormat("dd/MM/yyyy"));

        dateFormatPatterns.put(Pattern.compile("^((20)\\d{2})-((0|1)\\d{1})-((0|1|2)\\d{1})"), new SimpleDateFormat("yyyy-MM-dd"));
        dateFormatPatterns.put(Pattern.compile("^((20)\\d{2})/((0|1)\\d{1})/((0|1|2)\\d{1})"), new SimpleDateFormat("yyyy/MM/dd"));

        Date finalDate=null;
        try{
            DateFormat finalFormat = new SimpleDateFormat("MM/dd/yyyy");

            for (Pattern pattern : dateFormatPatterns.keySet()) {
                if (pattern.matcher(date).matches()) {
                    Date dateFormatted = dateFormatPatterns.get(pattern).parse(date);

                    finalDate  = finalFormat.parse(finalFormat.format(dateFormatted));

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return finalDate;

    }
     public static Date formatTime (String time){
        Map<Pattern, DateFormat> timeFormatPatterns = new HashMap<Pattern, DateFormat>();
         timeFormatPatterns.put(Pattern.compile("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"), new SimpleDateFormat("HH:mm"));

         timeFormatPatterns.put(Pattern.compile("^([0-1][0-9]|[2][0-3]):([0-5][0-9])$"), new SimpleDateFormat("HH:mm"));

         timeFormatPatterns.put(Pattern.compile("^((([0]?[1-9]|1[0-2])(:|\\.)[0-5][0-9]((:|\\.)[0-5][0-9])?( )?(AM|am|aM|Am|PM|pm|pM|Pm))|(([0]?[0-9]|1[0-9]|2[0-3])(:|\\.)[0-5][0-9]((:|\\.)[0-5][0-9])?))$^((0|1|2)\\d{1})-((0|1)\\d{1})-((20)\\d{2})"),
                 new SimpleDateFormat("hh:mm:ss"));

         timeFormatPatterns.put(Pattern.compile("((1[0-2]|0?[1-9]):([0-5][0-9]) ?([AaPp][Mm]))"), new SimpleDateFormat("hh:mm"));
         timeFormatPatterns.put(Pattern.compile("^(0?[1-9]|1[0-2]):[0-5][0-9]$"), new SimpleDateFormat("hh:mm"));

         timeFormatPatterns.put(Pattern.compile("(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)"), new SimpleDateFormat("hh:mm:ss"));



        Date finalTime=null;
        try{
            DateFormat finalFormat = new SimpleDateFormat("HH:mm:ss");

            for (Pattern pattern : timeFormatPatterns.keySet()) {
                if (pattern.matcher(time).matches()) {
                    Date timeFormatted = timeFormatPatterns.get(pattern).parse(time);

                    finalTime = finalFormat.parse(finalFormat.format(timeFormatted));

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return finalTime;

    }






}
