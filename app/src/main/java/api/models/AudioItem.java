package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AudioItem {

    @JsonProperty("id")
    private long mId;

    @JsonProperty("owner_id")
    private long mOwner_id;

    @JsonProperty("artist")
    private String mArtist;

    @JsonProperty("title")
    private String mTitle;

    @JsonProperty("url")
    private String mUrl;

    public long getId() {
        return mId;
    }

    public long getOwner_id() {
        return mOwner_id;
    }

    public String getArtist() {
        return mArtist;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    @Override
    public String toString() {
        return "AudioItem{" +
                "mId='" + mId + '\'' +
                ", mOwner_id='" + mOwner_id + '\'' +
                ", mArtist='" + mArtist + '\'' +
                ", mTitle='" + mTitle + '\'' +
                '}';
    }

}