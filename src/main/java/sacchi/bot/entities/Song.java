package sacchi.bot.entities;

public class Song {
	private String name;
	private String description;
	private String link;
	
	public Song(){
		
	}
	
	public Song(String name, String description, String link){
		this.name = name;
		this.description = description;
		this.link = link;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setDescription(String description){
		this.description=description;
	}
	
	public String getDescription(){
		return description;
		
	}public void setLink(String link){
		this.link=link;
	}
	
	public String getLink(){
		return link;
	}
	
	@Override
	public String toString(){
		return "Song: " + name + "\nDescription: " + description + "\nLink: " + link;
	}
	

}
