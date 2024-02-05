package montacer.elfazazi.examen2evalpmdmejerc1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import montacer.elfazazi.examen2evalpmdmejerc1.Constantes.Constantes;
import montacer.elfazazi.examen2evalpmdmejerc1.adapters.GameAdapter;
import montacer.elfazazi.examen2evalpmdmejerc1.conexiones.ApiConexiones;
import montacer.elfazazi.examen2evalpmdmejerc1.conexiones.RetrofitObject;
import montacer.elfazazi.examen2evalpmdmejerc1.databinding.ActivityMainBinding;
import montacer.elfazazi.examen2evalpmdmejerc1.modelos.Game;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private GameAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Game> listaGames;
    private SharedPreferences spMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        listaGames = new ArrayList<>();
        adapter = new GameAdapter(listaGames, R.layout.games_view_holder, this);
        
        layoutManager = new LinearLayoutManager(this);
        
        binding.contentMain.contenedorGames.setAdapter(adapter);
        binding.contentMain.contenedorGames.setLayoutManager(layoutManager);

        spMain = getSharedPreferences(Constantes.DATOS, MODE_PRIVATE);
        verUltimoAcceso();
        
        doGetGames();
        //aaaaaaaaaa
        
    }

    private void verUltimoAcceso() {
        String titulo = spMain.getString(Constantes.ULTIMO_TITULO, "");
        String developer = spMain.getString(Constantes.ULTIMO_DEVELOPER, "");

        showGameDetails(titulo, developer).show();

    }

    private AlertDialog showGameDetails(String titulo, String developer){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Ultimo juego guardado");
        builder.setCancelable(false);

        View gameModel = LayoutInflater.from(this).inflate(R.layout.game_view_model, null);

        TextView lbDeveloper = gameModel.findViewById(R.id.lbDeveloperGameViewModel);
        TextView lbTitulo = gameModel.findViewById(R.id.lbTituloGameViewModel);
        builder.setView(gameModel);

        lbDeveloper.setText(developer);
        lbTitulo.setText(titulo);


        builder.setNegativeButton("aceptar", null);

        return builder.create();
    }

    private void doGetGames() {
        Retrofit retrofit = RetrofitObject.getConexion();

        ApiConexiones api = retrofit.create(ApiConexiones.class);

        Call<ArrayList<Game>> getGames = api.getGames();

        getGames.enqueue(new Callback<ArrayList<Game>>() {
            @Override
            public void onResponse(Call<ArrayList<Game>> call, Response<ArrayList<Game>> response) {
                if (response.code() == HttpsURLConnection.HTTP_OK){
                    ArrayList<Game> temp = response.body();
                    listaGames.addAll(temp);
                    adapter.notifyItemRangeInserted(0, listaGames.size());
                }else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Game>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error cargando games", Toast.LENGTH_SHORT).show();
            }
        });
    }
}