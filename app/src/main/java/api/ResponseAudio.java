package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseAudio {

    @JsonProperty("response")
    private ResponseAudioVk mAudioResponse;


    public ResponseAudioVk getResponse() {
        return mAudioResponse;
    }

    @Override
    public String toString() {
        return "ResponseAudio{" +
                "mAudioResponse='" + mAudioResponse + '\'' +
                '}';
    }
}
