package api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VkService {

    @GET("users.get")
    Call<ResponseVk> getUser(@Query("user_ids") String userId,
                             @Query("fields") String fields,
                             @Query("v") String version);

    @GET("audio.get")
    Call<ResponseAudio> getAudio(@Query("owner_id") String owner_id,
                                 @Query("count") String count,
                                 @Query("access_token") String access_token,
                                 @Query("v") String version);

    @GET("friends.get")
    Call<ResponseFriends> getFriends(@Query("order") String order,
                                     @Query("fields") String fields,
                                     @Query("access_token") String access_token,
                                     @Query("v") String version);
}