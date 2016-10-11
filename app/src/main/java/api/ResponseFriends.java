package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseFriends {

    @JsonProperty("response")
    private ResponseFiendsVk mFrindsResponse;

    public ResponseFiendsVk getFrindsResponse() {
        return mFrindsResponse;
    }

    @Override
    public String toString() {
        return "ResponseFriends{" +
                "mFrindsResponse=" + mFrindsResponse +
                '}';
    }
}
