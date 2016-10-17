package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserItem {

    @JsonProperty("id")
    private String mId;

    @JsonProperty("first_name")
    private String mFirstName;

    @JsonProperty("last_name")
    private String mLastName;

    @JsonProperty("bdate")
    private String bDate;

    @JsonProperty("photo_100")
    private String mPhoto_100;

    @JsonProperty("photo_200")
    private String mPhoto_200;

    public String getPhoto_100() {
        return mPhoto_100;
    }

    public String getPhoto_200() {
        return mPhoto_200;
    }

    public String getId() {
        return mId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getBdate() {
        return bDate;
    }

    @Override
    public String toString() {
        return "UserItem{" +
                "mId='" + mId + '\'' +
                ", mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", bDate='" + bDate + '\'' +
                ", mPhoto_100='" + mPhoto_100 + '\'' +
                ", mPhoto_200='" + mPhoto_200 + '\'' +
                '}';
    }
}
