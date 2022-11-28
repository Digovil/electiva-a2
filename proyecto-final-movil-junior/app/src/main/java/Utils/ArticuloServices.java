package Utils;

import java.util.List;

import Model.Articulo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;



public interface ArticuloServices {

    @GET("mostrar/articulos")
    Call<List<Articulo>> getArticulo();

    @POST("crear/articulo")
    Call<Articulo>addArticulo(@Body Articulo articulo);

    @PUT("update/articulo/{id}")
    Call<Articulo>updateArticulo(@Body Articulo articulo, @Path("id") int id);

    @DELETE("delete/articulo/{id}")
    Call<Articulo>deleteArticulo(@Path("id")int id);


}




