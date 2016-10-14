package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import api.models.Dialog;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDialogsVk {

    @JsonProperty("count")
    private long mCount;

    @JsonProperty("items")
    private List<Dialog> mDialogsList;

    public long getCount() {
        return mCount;
    }

    public List<Dialog> getDialogsList() {
        return mDialogsList;
    }

    @Override
    public String toString() {
        return "ResponseDialogsVk{" +
                "mCount=" + mCount +
                ", mDialogsList=" + mDialogsList +
                '}';
    }

}
