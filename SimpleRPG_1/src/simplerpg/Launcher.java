package simplerpg;

import java.io.FileNotFoundException;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Camera;
import javafx.scene.ImageCursor;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import simplerpg.Button.Options;
import simplerpg.Button.PlayGame;
import simplerpg.Adventurer.Board;
import simplerpg.Adventurer.PlayerState;
public class Launcher extends Application
{
	private GraphicsContext gc;
	private final String Name = "SimpleRPG";
	
	private Config cf = Config.getInstance();
	public static Scene scene;
	
	private int WIDTH = cf.getWidth();
	private int HEIGHT = cf.getHeight();
	public static int choose;
	public static int difficulty=0;
	public static int joined =0;
	public static int MapId = 1;
	public static int PlayerId = 1;
	public static int dead = 0;
	///Operation
	@Override
	public void start(Stage stage) throws FileNotFoundException 
	{
		stage.setTitle(Name);
		stage.getIcons().add(new Image(cf.getLink() + "icon.png"));
		
		
		///Create Canvas
		Canvas canvas = new Canvas(WIDTH,HEIGHT);
		gc = canvas.getGraphicsContext2D();
		cf.root.getChildren().addAll(canvas);
		
		///Create Scene + Camera
		scene = new Scene(cf.root);
		scene.setFill(Color.BLACK);
		scene.setCamera(cf.camera);
		
		cf.camera.translateXProperty().set(WIDTH/2);
		cf.camera.translateYProperty().set(HEIGHT/2);
		cf.camera.translateZProperty().set(-1350);
		cf.camera.setNearClip(1);
		cf.camera.setFarClip(1350);
		
		///Mouse
		Image mouse = new Image(cf.getLink()+"mouse.png");
		scene.setCursor(new ImageCursor(mouse));
		
		///Css
		scene.getStylesheets().add(cf.getLink()+"style.css");
		
		///Push Scene into Stage
		stage.setScene(scene);
		stage.show();
		
		///Sound
		
		
		///Init
		GamePanel Panel = new GamePanel();
		PlayGame Play = new PlayGame();
		Options Option = new Options();
		Board _Board = new Board();

		//AnimationTimer <-> GameLoop
		AnimationTimer timer = new AnimationTimer()
		{
			@Override
			public void handle(long now) 
			{
				gc.clearRect(0, 0, WIDTH, HEIGHT);
				
				///Panel
				if(choose == 0) 
				{
					Panel.render(gc);
					Panel.update();
				}
				
				/// PlayGame
				else if(choose == 1 ) 
				{
					if(joined ==1) ///Playing
					{
						GameField.getInstance().render(gc);
						if(joined==1) GameField.getInstance().update();
					}
					else
					if(difficulty==0) /// Choose Difficulty
					{
						Play.render(gc);
						Play.update(); 
					}
					else /// Edit Sate
					{
						_Board.render(gc);
						_Board.update();
					}
				}
				
				/// Options
				else if(choose == 2 ) 
				{
					Option.render(gc);
					Option.update();
				}
				
				/// QuitGame
				else if(choose == 3) 
				{
					Platform.exit();
				}
			}
			
		};
		timer.start();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}

}
