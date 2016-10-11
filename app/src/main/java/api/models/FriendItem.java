package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FriendItem {

    @JsonProperty("id")
    private String mId;

    @JsonProperty("first_name")
    private String mFirstName;

    @JsonProperty("last_name")
    private String mLastName;

    @JsonProperty("photo_100")
    private String mPhoto;

    @JsonProperty("online")
    private String mOnline;

    public String getmId() {
        return mId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public String getOnline() {
        return mOnline;
    }

    @Override
    public String toString() {
        return "FriendItem{" +
                "mId='" + mId + '\'' +
                ", mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mPhoto='" + mPhoto + '\'' +
                ", mOnline='" + mOnline + '\'' +
                '}';
    }
}
