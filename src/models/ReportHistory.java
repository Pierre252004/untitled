package src.models;

import java.util.Date;

public class ReportHistory {
    private int reportNumber;
    private int ssn;
    private Date date;
    private String details;

    public ReportHistory(int reportNumber, int ssn, Date date, String details) {
        this.reportNumber = reportNumber;
        this.ssn = ssn;
        this.date = date;
        this.details = details;
    }

    public int getReportNumber() { return reportNumber; }
    public int getSsn() { return ssn; }
    public Date getDate() { return date; }
    public String getDetails() { return details; }
}
