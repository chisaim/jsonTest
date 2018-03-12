package home.pb.springwebmvc;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateJsonValueProcessor implements JsonValueProcessor {

    private String dateFormat = "yyyyMMddHHmmss";

    private SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return null;
    }

    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {

        if (o == null)
            return null;
        else if (o instanceof Date) {
            return sdf.format((Date) o);
        } else {
            return o.toString();
        }

    }
}
