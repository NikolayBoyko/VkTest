package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDialogHistory {

    @JsonProperty("response")
    private ResponseDialogHIstoryVk mDialogHistoryResponse;

    public ResponseDialogHIstoryVk getDialogHistoryResponse() {
        return mDialogHistoryResponse;
    }

    @Override
    public String toString() {
        return "ResponseDialogHistory{" +
                "mDialogHistoryResponse=" + mDialogHistoryResponse +
                '}';
    }
}
