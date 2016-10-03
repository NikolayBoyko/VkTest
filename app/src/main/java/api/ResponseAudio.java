package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseAudio {

    @JsonProperty("response")
    private ResponseAudioVk mResponse;


    public ResponseAudioVk getResponse() {
        return mResponse;
    }

    @Override
    public String toString() {
        return "ResponseAudio{" +
                "mResponse='" + mResponse + '\'' +
                '}';
    }
}
