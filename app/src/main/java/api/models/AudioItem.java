package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AudioItem {

    @JsonProperty("artist")
    private String mArtist;

    @JsonProperty("title")
    private String mTitle;

    @JsonProperty("url")
    private String mUrl;

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
                "mArtist='" + mArtist + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mUrl='" + mUrl + '\'' +
                '}';
    }
}