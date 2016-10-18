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
                                 @Query("access_token") String access_token,
                                 @Query("v") String version);

    @GET("friends.get")
    Call<ResponseFriends> getFriends(@Query("order") String order,
                                     @Query("fields") String fields,
                                     @Query("access_token") String access_token,
                                     @Query("v") String version);

    @GET("messages.getDialogs")
    Call<ResponseDialogs> getDialogs(@Query("access_token") String access_token,
                                     @Query("v") String version);

    @GET("messages.getHistory")
    Call<ResponseDialogHistory> getDialogHistory(@Query("user_id") String userId,
                                                 @Query("access_token") String access_token,
                                                 @Query("v") String version);

    @GET("messages.send")
    Call<ResponseSendMessage> sendMessage(@Query("user_id") String userId,
                                          @Query("message") String message,
                                          @Query("access_token") String token,
                                          @Query("v") String version);

    @GET("client_id=5610917&client_secret=so4s92L8QlsVSUqi841y&v=5.58&grant_type=client_credentials")
    Call getServiceToken();

}