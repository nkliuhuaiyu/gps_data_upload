package temp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class justfortest {
    public static void test(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Date date = simpleDateFormat.parse("10-03-2018 00:00:00");

        System.out.println(date.toString());
    }
}
