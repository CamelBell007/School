package camlebell.com.model;


import camlebell.com.base.BaseBean;

/**
 */

public class LoginBean extends BaseBean {
    public Detail detail;

    public static class Detail {
        public String userCode;
        public String name;
        public String userId;
        public String className;
        public String teacherName;
        public String teacherPhone;

    }
}
