package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

    @JsonProperty("id")
    private int mId;

    @JsonProperty("date")
    private String mDate;

    @JsonProperty("out")
    private int mOut;

    @JsonProperty("user_id")
    private String mUserId;

    @JsonProperty("read_state")
    private int mRead_state;

    @JsonProperty("title")
    private String mTitle;

    @JsonProperty("body")
    private String mBody;

    @JsonProperty("random_id")
    private String mRandom_id;

    @JsonProperty("attachments")
    private List<Attachment> mAttachments;

    public int getId() {
        return mId;
    }

    public String getDate() {
        return mDate;
    }

    public int getOut() {
        return mOut;
    }

    public String getUserId() {
        return mUserId;
    }

    public int getRead_state() {
        return mRead_state;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getBody() {
        return mBody;
    }

    public String getRandom_id() {
        return mRandom_id;
    }

    public List<Attachment> getAttachments() {
        return mAttachments;
    }

    @Override
    public String toString() {
        return "Message{" +
                "mId=" + mId +
                ", mDate='" + mDate + '\'' +
                ", mOut=" + mOut +
                ", mUserId=" + mUserId +
                ", mRead_state=" + mRead_state +
                ", mTitle='" + mTitle + '\'' +
                ", mBody='" + mBody + '\'' +
                ", mRandom_id='" + mRandom_id + '\'' +
                ", mAttachments=" + mAttachments +
                '}';

    }

}