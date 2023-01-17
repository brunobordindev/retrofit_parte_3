package br.com.retrofithttpparte3.api;

import java.util.List;

import br.com.retrofithttpparte3.model.Foto;
import br.com.retrofithttpparte3.model.Postagem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataService {

    @GET("/photos")
    Call<List<Foto>> recuperarFotos();

    @GET("/posts")
    Call<List<Postagem>> recuperarPostagens();

    @POST("/posts")
    Call<Postagem> salvarPostagem(@Body Postagem postagem);

    //mesma coisa, mas de forma diferente
    @FormUrlEncoded
    @POST("/posts")
    Call<Postagem> salvarPostagem(
            @Field("userId") String userId,
            @Field("title") String title,
            @Field("body") String body
    );

    //os de baixo usado para atuaizacao
    //atualiza todo o objeto
    @PUT("/posts/{id}")
    Call<Postagem> atualizarPostagem(@Path("id") int id, @Body Postagem postagem);

    //atualiza somente os campos desejados do objeto
    @PATCH("/posts/{id}")
    Call<Postagem> atualizarPostagemPatch(@Path("id") int id, @Body Postagem postagem);

    @DELETE("/posts/{id}")
    Call<Void> removerPostagem(@Path("id") int id);


}
