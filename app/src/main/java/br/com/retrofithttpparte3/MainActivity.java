package br.com.retrofithttpparte3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.retrofithttpparte3.api.DataService;
import br.com.retrofithttpparte3.databinding.ActivityMainBinding;
import br.com.retrofithttpparte3.model.Foto;
import br.com.retrofithttpparte3.model.Postagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Retrofit retrofitFotos;
    private Retrofit retrofitPostagens;
    private List<Foto> listasFotos = new ArrayList<>();
    private List<Postagem> listasPostagens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //mexer no manisfet permissao
        retrofitFotos = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitPostagens = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        binding.btn.setOnClickListener(view -> {
            //recupeta
//            recuperarListaFotosRetrofit();
//            recuperarListaPostagensRetrofit();

            //executa
//            salvarPostagem();
            
            atualizarPostagem();

//            removerPostagem();
        });
    }

    private void removerPostagem() {

        DataService service = retrofitPostagens.create(DataService.class);
        Call<Void> call = service.removerPostagem(2);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    binding.textResult.setText("Status: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private void atualizarPostagem() {

        //configurando objeto postagem
//        Postagem postagem = new Postagem("1234", null, "corpo Postagem!");
        Postagem postagem = new Postagem();
        postagem.setBody("Mudando somenete aqui!");

        DataService service = retrofitPostagens.create(DataService.class);
        Call<Postagem> call = service.atualizarPostagem(2, postagem);
//        Call<Postagem> call = service.atualizarPostagemPatch(2, postagem);

        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if (response.isSuccessful()){
                    Postagem postagemResposta = response.body();
                    binding.textResult.setText(
                            "Código status: " + response.code() + " "+
                                    "id: " + postagemResposta.getId() + " "+
                                    "userId: " + postagemResposta.getUserId() + " "+
                                    "title: " + postagemResposta.getTitle() + " "+
                                    "body: " + postagemResposta.getBody());

                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    private void salvarPostagem() {

        //configurando objeto postagem
//        Postagem postagem = new Postagem("1234", "ítulo postagem!", "corpo Postagem!");

        //recupera o servico e salva postagem
        DataService service = retrofitPostagens.create(DataService.class);
//        Call<Postagem> call = service.salvarPostagem(postagem);
        Call<Postagem> call = service.salvarPostagem("1234", "ítulo postagem!", "corpo Postagem!");

        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if (response.isSuccessful()){
                    Postagem postagemResposta = response.body();
                    binding.textResult.setText(
                            "Código: " + response.code() + " "+
                            "id: " + postagemResposta.getId() + " "+
                            "title: " + postagemResposta.getTitle() + " "+
                            "body: " + postagemResposta.getBody());

                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    private void recuperarListaFotosRetrofit(){

        DataService service = retrofitFotos.create(DataService.class);
        Call<List<Foto>> call = service.recuperarFotos();

        call.enqueue(new Callback<List<Foto>>() {
            @Override
            public void onResponse(Call<List<Foto>> call, Response<List<Foto>> response) {

                if (response.isSuccessful()){
                    listasFotos = response.body();

                    //vou fazer  somente log
                    for (int i = 0; i < listasFotos.size(); i++){
                        Foto foto = listasFotos.get(i);
                        Log.d("resultado", "resultado: " + foto.getId() + " / " + foto.getTitle());

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Foto>> call, Throwable t) {

            }
        });
    }

    private void recuperarListaPostagensRetrofit(){

        DataService service = retrofitPostagens.create(DataService.class);
        Call<List<Postagem>> call = service.recuperarPostagens();

        call.enqueue(new Callback<List<Postagem>>() {
            @Override
            public void onResponse(Call<List<Postagem>> call, Response<List<Postagem>> response) {
                if (response.isSuccessful()){
                    listasPostagens = response.body();

                    //vou fazer  somente log
                    for (int i = 0; i < listasPostagens.size(); i++){
                       Postagem postagem = listasPostagens.get(i);
                        Log.d("resultado", "resultado: " + postagem.getId() + " / " + postagem.getTitle());

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Postagem>> call, Throwable t) {

            }
        });
    }
}