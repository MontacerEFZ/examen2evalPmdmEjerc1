package montacer.elfazazi.examen2evalpmdmejerc1.modelos;

import java.util.List;

public class Games{
	private List<Game> games;

	public void setGames(List<Game> games){
		this.games = games;
	}

	public List<Game> getGames(){
		return games;
	}

	@Override
 	public String toString(){
		return 
			"Games{" + 
			"games = '" + games + '\'' + 
			"}";
		}
}