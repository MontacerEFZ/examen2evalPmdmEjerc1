package montacer.elfazazi.examen2evalpmdmejerc1.modelos;

import com.google.gson.annotations.SerializedName;

public class Game {
	@SerializedName(value = "short_description")
	private String shortDescription;
	private String thumbnail;
	@SerializedName(value = "game_url")

	private String gameUrl;
	@SerializedName(value = "release_date")

	private String releaseDate;
	@SerializedName(value = "freetogame_profile_url")

	private String freetogameProfileUrl;
	private String genre;
	private String publisher;
	private String developer;
	private int id;
	private String title;
	private String platform;

	public void setShortDescription(String shortDescription){
		this.shortDescription = shortDescription;
	}

	public String getShortDescription(){
		return shortDescription;
	}

	public void setThumbnail(String thumbnail){
		this.thumbnail = thumbnail;
	}

	public String getThumbnail(){
		return thumbnail;
	}

	public void setGameUrl(String gameUrl){
		this.gameUrl = gameUrl;
	}

	public String getGameUrl(){
		return gameUrl;
	}

	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public void setFreetogameProfileUrl(String freetogameProfileUrl){
		this.freetogameProfileUrl = freetogameProfileUrl;
	}

	public String getFreetogameProfileUrl(){
		return freetogameProfileUrl;
	}

	public void setGenre(String genre){
		this.genre = genre;
	}

	public String getGenre(){
		return genre;
	}

	public void setPublisher(String publisher){
		this.publisher = publisher;
	}

	public String getPublisher(){
		return publisher;
	}

	public void setDeveloper(String developer){
		this.developer = developer;
	}

	public String getDeveloper(){
		return developer;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setPlatform(String platform){
		this.platform = platform;
	}

	public String getPlatform(){
		return platform;
	}

	@Override
 	public String toString(){
		return 
			"GamesItem{" + 
			"short_description = '" + shortDescription + '\'' + 
			",thumbnail = '" + thumbnail + '\'' + 
			",game_url = '" + gameUrl + '\'' + 
			",release_date = '" + releaseDate + '\'' + 
			",freetogame_profile_url = '" + freetogameProfileUrl + '\'' + 
			",genre = '" + genre + '\'' + 
			",publisher = '" + publisher + '\'' + 
			",developer = '" + developer + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",platform = '" + platform + '\'' + 
			"}";
		}
}
