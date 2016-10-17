package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DialogHistoryMessage {

    @JsonProperty("id")
    private String mId;

    @JsonProperty("body")
    private String mBody;

    @JsonProperty("user_id")
    private String mUser_id;

    @JsonProperty("from_id")
    private String mFrom_id;

    @JsonProperty("date")
    private String mDate;

    @JsonProperty("read_state")
    private String mRead_state;

    @JsonProperty("out")
    private String mOut;

    public String getmId() {
        return mId;
    }

    public String getmBody() {
        return mBody;
    }

    public String getmUser_id() {
        return mUser_id;
    }

    public String getmFrom_id() {
        return mFrom_id;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmRead_state() {
        return mRead_state;
    }

    public String getmOut() {
        return mOut;
    }

    @Override
    public String toString() {
        return "DialogHistoryMessage{" +
                "mId='" + mId + '\'' +
                ", mBody='" + mBody + '\'' +
                ", mUser_id='" + mUser_id + '\'' +
                ", mFrom_id='" + mFrom_id + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mRead_state='" + mRead_state + '\'' +
                ", mOut='" + mOut + '\'' +
                '}';
    }

}