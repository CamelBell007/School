package camlebell.com.model;

/**
 * Created by Administrator on 2015/11/10.
 */
public class WorkInfo {
    public String workId;
    public String jobName;//职务名称
    public String workName;//工人名字
    public String workStatus;//工作状态

    public String teamName;//归属团队
    public String boyTem;//体温
    public String earlyWorkTime;//打卡时间
    public String telePhone;//电话
    public String address;
    public String cardUsedData;//证件有效期


    public WorkInfo(String jobName,String workName,String workStatus){
        this.jobName = jobName;
        this.workName = workName;
        this.workStatus = workStatus;
    }
    public WorkInfo(String jobName,String workName,String workStatus,String teamName,String boyTem,String earlyWorkTime,
                    String address,String cardUsedData,String workId,String telePhone){
        this.jobName = jobName;
        this.workName = workName;
        this.workStatus = workStatus;
        this.teamName = teamName;
        this.boyTem = boyTem;
        this.earlyWorkTime = earlyWorkTime;
        this.address = address;
        this.cardUsedData = cardUsedData;
        this.workId = workId;
        this.telePhone = telePhone;
    }
}
