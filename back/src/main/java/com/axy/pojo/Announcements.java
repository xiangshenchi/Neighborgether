package com.axy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName announcements
 */
@TableName(value ="announcements")
public class Announcements implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer announcementid;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private Date publishdate;

    /**
     * 
     */
    private String attachment;

    /**
     * 
     */
    private Integer userid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getAnnouncementid() {
        return announcementid;
    }

    /**
     * 
     */
    public void setAnnouncementid(Integer announcementid) {
        this.announcementid = announcementid;
    }

    /**
     * 
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     */
    public Date getPublishdate() {
        return publishdate;
    }

    /**
     * 
     */
    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    /**
     * 
     */
    public String getAttachment() {
        return attachment;
    }

    /**
     * 
     */
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    /**
     * 
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Announcements other = (Announcements) that;
        return (this.getAnnouncementid() == null ? other.getAnnouncementid() == null : this.getAnnouncementid().equals(other.getAnnouncementid()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getPublishdate() == null ? other.getPublishdate() == null : this.getPublishdate().equals(other.getPublishdate()))
            && (this.getAttachment() == null ? other.getAttachment() == null : this.getAttachment().equals(other.getAttachment()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAnnouncementid() == null) ? 0 : getAnnouncementid().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getPublishdate() == null) ? 0 : getPublishdate().hashCode());
        result = prime * result + ((getAttachment() == null) ? 0 : getAttachment().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", announcementid=").append(announcementid);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", publishdate=").append(publishdate);
        sb.append(", attachment=").append(attachment);
        sb.append(", userid=").append(userid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}