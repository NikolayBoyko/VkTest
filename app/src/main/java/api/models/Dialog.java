package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import api.models.Message;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dialog {

    @JsonProperty("message")
    private Message mMessage;

    @JsonProperty("in_read")
    private int mInRead;

    @JsonProperty("out_read")
    private int mOutRead;

    public Message getMessage() {
        return mMessage;
    }

    public int getInRead() {
        return mInRead;
    }

    public int getOutRead() {
        return mOutRead;
    }

    @Override
    public String toString() {
        return "Dialog{" +
                "mMessage=" + mMessage +
                ", mInRead=" + mInRead +
                ", mOutRead=" + mOutRead +
                '}';
    }
}
