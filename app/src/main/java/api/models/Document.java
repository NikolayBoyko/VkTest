package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Document {

    @JsonProperty("id")
    private int mId;

    @JsonProperty("owner_id")
    private long mOwner_id;

    @JsonProperty("title")
    private String mTitle;

    @JsonProperty("size")
    private long mSize;

    @JsonProperty("ext")
    private long mExt;

    @JsonProperty("url")
    private String mUrl;

    @JsonProperty("date")
    private long mDate;

    @JsonProperty("type")
    private int mType;

    @JsonProperty("access_key")
    private String mAccess_key;

    public int getId() {
        return mId;
    }

    public long getOwner_id() {
        return mOwner_id;
    }

    public String getTitle() {
        return mTitle;
    }

    public long getSize() {
        return mSize;
    }

    public long getExt() {
        return mExt;
    }

    public String getUrl() {
        return mUrl;
    }

    public long getDate() {
        return mDate;
    }

    public int getType() {
        return mType;
    }

    public String getAccess_key() {
        return mAccess_key;
    }

    @Override
    public String toString() {
        return "Document{" +
                "mId=" + mId +
                ", mOwner_id=" + mOwner_id +
                ", mTitle='" + mTitle + '\'' +
                ", mSize=" + mSize +
                ", mExt=" + mExt +
                ", mUrl='" + mUrl + '\'' +
                ", mDate=" + mDate +
                ", mType=" + mType +
                ", mAccess_key='" + mAccess_key + '\'' +
                '}';
    }

}