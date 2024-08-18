package com.neighbor.gether.ing;

import java.sql.Timestamp;

public class Announcements {
    private int announcementID;
    private  String title;
    private  String content;
    private Timestamp publishdate;
    private String attachment;
    private int userid;
    public Announcements(){}

    public Announcements(int announcementID, String title, String content, Timestamp publishdate, String attachment, int userid) {
        this.announcementID = announcementID;
        this.title = title;
        this.content = content;
        this.publishdate = publishdate;
        this.attachment = attachment;
        this.userid = userid;
    }

    public int getAnnouncementID() {
        return announcementID;
    }

    public void setAnnouncementID(int announcementID) {
        this.announcementID = announcementID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Timestamp publishdate) {
        this.publishdate = publishdate;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Announcements{" +
                "announcementID=" + announcementID +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publishdate=" + publishdate +
                ", attachment='" + attachment + '\'' +
                ", userid=" + userid +
                '}';
    }
}
