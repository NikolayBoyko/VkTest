package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import api.models.AudioItem;
import api.models.FriendItem;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseFiendsVk {

    @JsonProperty("count")
    private int mCount;

    @JsonProperty("items")
    private List<FriendItem> mFriendList;

    public int getCount() {
        return mCount;
    }

    public List<FriendItem> getFriendList() {
        return mFriendList;
    }

    @Override
    public String toString() {
        return "ResponseFiendsVk{" +
                "mCount=" + mCount +
                ", mFriendList=" + mFriendList +
                '}';
    }
}
