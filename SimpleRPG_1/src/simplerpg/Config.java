package simplerpg;

import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;

public class Config 
{
	private static Config instance = null;
	
	private final String ImgLink = "file:src/simplerpg/data/";
	private final int width = 1024;
	private final int height = 720;
	
	public final Group root = new Group();
	public static final Camera camera = new PerspectiveCamera(true);
	///button menu
	private final int Panel = 0;
	private final int Playing = 1;
	private final int Options = 2;
	private final int Quit = 3;
	private final int isPlay = 1;
	
	///Difficulty
	private final int Easy = 1;
	private final int Medium = 2;
	private final int Hard = 3;
	
	///Animated
	private final int running = 12;
	
	///Operation
	public Config()
	{
		
	}
	public static Config getInstance()
	{
		if(instance == null) instance = new Config();
		return instance;
	}
	public int getWidth()
	{
		return this.width;
	}
	public int getHeight()
	{
		return this.height;
	}	
	public String getLink()
	{
		return this.ImgLink;
	}
	public int getPlaying()
	{
		return this.Playing;
	}
	public int getOptions()
	{
		return this.Options;
	}
	public int getQuit()
	{
		return this.Quit;
	}
	public int getPanel()
	{
		return this.Panel;
	}
	public int getEasy()
	{
		return this.Easy;
	}
	public int getMedium()
	{
		return this.Medium;
	}
	public int getHard()
	{
		return this.Hard;
	}
	public int getIsPlay()
	{
		return this.isPlay;
	}
	
	public int getAnimatedRunning()
	{
		return this.running;
	}
}
