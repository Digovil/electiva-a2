package Utils;

import java.util.List;

import Model.Autor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;



public interface AutorServices {

    @GET("mostrar/autores")
    Call<List<Autor>> getAutor();

    @POST("crear/autor")
    Call<Autor>addAutor(@Body Autor autor);

    @PUT("update/autor/{id}")
    Call<Autor>updateAutor(@Body Autor autor, @Path("id") int id);

    @DELETE("delete/autor/{id}")
    Call<Autor>deleteAutor(@Path("id")int id);


}




