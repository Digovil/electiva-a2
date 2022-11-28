package Utils;

import java.util.List;

import Model.Revista;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface RevistaServices {

    @GET("mostrar/revistas")
    Call<List<Revista>> getRevista();

    @POST("crear/revista")
    Call<Revista>addRevista(@Body Revista revista);

    @PUT("update/revista/{id}")
    Call<Revista>updateRevista(@Body Revista revista, @Path("id") int id);

    @DELETE("delete/revista/{id}")
    Call<Revista>deleteRevista(@Path("id")int id);


}




