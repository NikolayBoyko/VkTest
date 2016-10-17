package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import api.models.DialogHistoryMessage;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDialogHIstoryVk {

    @JsonProperty("count")
    private long mCount;

    @JsonProperty("items")
    private List<DialogHistoryMessage> mDialogHIstoryList;

    @JsonProperty("in_read")
    private long mIn_read;

    @JsonProperty("out_read")
    private long mOut_read;

    public long getCount() {
        return mCount;
    }

    public List<DialogHistoryMessage> getDialogHIstoryList() {
        return mDialogHIstoryList;
    }

    public long getIn_read() {
        return mIn_read;
    }

    public long getOut_read() {
        return mOut_read;
    }

    @Override
    public String toString() {
        return "ResponseDialogHIstoryVk{" +
                "mCount=" + mCount +
                ", mDialogHIstoryList=" + mDialogHIstoryList +
                ", mIn_read=" + mIn_read +
                ", mOut_read=" + mOut_read +
                '}';
    }
}
