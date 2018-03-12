package home.pb.springwebmvc;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateJsonValueProcessor implements JsonValueProcessor {

    private String dateFormat = "yyyy-MM-dd HH:mm:ss";

    private SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return null;
    }

    //需要处理日期的相关格式
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {

        if(o == null){
            return null;
        }else if(o instanceof Date){
            return sdf.format((Date) o);
        }else {
            return o.toString();
        }
    }
}
