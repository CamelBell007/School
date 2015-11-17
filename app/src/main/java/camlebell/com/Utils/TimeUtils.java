package camlebell.com.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/17.
 */
public class TimeUtils {
    /**
     * 获取当前时间
     * @return
     */
    public static String getNowDate(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        return  dateNowStr;
    }
}
