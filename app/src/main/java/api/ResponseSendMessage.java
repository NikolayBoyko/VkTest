package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseSendMessage {

    @JsonProperty("response")
    private String mResponse;

    public String getResponse() {
        return mResponse;
    }

    @Override
    public String toString() {
        return "ResponseSendMessage{" +
                "mResponse='" + mResponse + '\'' +
                '}';
    }

}