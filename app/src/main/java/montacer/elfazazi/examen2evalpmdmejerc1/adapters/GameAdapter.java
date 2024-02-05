package montacer.elfazazi.examen2evalpmdmejerc1.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import montacer.elfazazi.examen2evalpmdmejerc1.Constantes.Constantes;
import montacer.elfazazi.examen2evalpmdmejerc1.R;
import montacer.elfazazi.examen2evalpmdmejerc1.modelos.Game;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameVH> {
    private List<Game> objects;
    private int resource;
    private Context context;

    SharedPreferences sp;

    public GameAdapter(List<Game> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;

        sp = context.getSharedPreferences(Constantes.DATOS, context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public GameVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View gameView = LayoutInflater.from(context).inflate(resource, null);
        gameView.setLayoutParams(
                new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );

        return new GameVH(gameView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameVH holder, int position) {
        Game g = objects.get(position);

        holder.lbTitulo.setText(g.getTitle());
        holder.lbGenre.setText(g.getGenre());
        holder.lbPlatform.setText(g.getPlatform());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGameDetails(g).show();
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(Constantes.ULTIMO_TITULO, g.getTitle());
                editor.putString(Constantes.ULTIMO_DEVELOPER, g.getDeveloper());
                editor.apply();
            }
        });

    }

    private AlertDialog showGameDetails(Game g){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Game Details");
        builder.setCancelable(false);

        View gameModel = LayoutInflater.from(context).inflate(R.layout.game_view_model, null);

        TextView lbDeveloper = gameModel.findViewById(R.id.lbDeveloperGameViewModel);
        ImageView imgThumbnail = gameModel.findViewById(R.id.imgGameViewModel);
        builder.setView(gameModel);

        lbDeveloper.setText(g.getDeveloper());
        Picasso.get()
                .load(g.getThumbnail())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(imgThumbnail);

        builder.setNegativeButton("aceptar", null);

        return builder.create();
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class GameVH extends RecyclerView.ViewHolder{
        TextView lbTitulo, lbGenre, lbPlatform;

        public GameVH(@NonNull View itemView) {
            super(itemView);
            lbTitulo = itemView.findViewById(R.id.lbTitleGamesViewHolder);
            lbGenre = itemView.findViewById(R.id.lbGenreGamesViewHolder);
            lbPlatform = itemView.findViewById(R.id.lbPlatformGamesViewHolder);
        }
    }
}
