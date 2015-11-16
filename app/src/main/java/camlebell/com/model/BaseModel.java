package camlebell.com.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/11.
 */
public class BaseModel implements Serializable {
    public int result;
    public int totalRecordNum;
    public int pages;
    public int pageNo;
    public String resultNote;
    public String detail;
}
