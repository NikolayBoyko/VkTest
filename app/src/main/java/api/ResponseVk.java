package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import api.models.UserItem;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseVk {

    @JsonProperty("response")
    private List<UserItem> mListUserItem;

    public List<UserItem> getListUser() {
        return mListUserItem;
    }

    @Override
    public String toString() {
        return "ResponseVk{" +
                "mListUserItem=" + mListUserItem +
                '}';
    }

}