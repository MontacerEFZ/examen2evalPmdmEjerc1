package montacer.elfazazi.examen2evalpmdmejerc1.conexiones;

import java.util.ArrayList;

import montacer.elfazazi.examen2evalpmdmejerc1.modelos.Game;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiConexiones {
    @GET("/api/games")
    Call<ArrayList<Game>> getGames();
}
