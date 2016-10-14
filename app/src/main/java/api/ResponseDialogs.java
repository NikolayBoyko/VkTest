package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDialogs {

    @JsonProperty("response")
    private ResponseDialogsVk mDialogsResponse;

    public ResponseDialogsVk getDialogsResponse() {
        return mDialogsResponse;
    }

    @Override
    public String toString() {
        return "ResponseDialogs{" +
                "mDialogsResponse=" + mDialogsResponse +
                '}';
    }
}
