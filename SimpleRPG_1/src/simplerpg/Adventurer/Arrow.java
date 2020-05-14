package simplerpg.Adventurer;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import simplerpg.Config;
import simplerpg.Launcher;
import simplerpg.SpriteAnimation;
import simplerpg.Enemy.Enemy;
import simplerpg.Map.Map;

public class Arrow
{
	private Config cf = Config.getInstance();
	private String ImgLink = cf.getLink();
	
	private ImageView arrow = new ImageView();
	private SpriteAnimation arrowani ;
	private double X;
	private double Y;
	private double toX;
	private double toY;
	private double vectorX;
	private double vectorY;
	private double Damage;
	private boolean dead;
	
	public Arrow(int type ,double X,double Y,double toX,double toY,double Damage, String playerId)
	{
		this.X=X;
		this.Y=Y;
		this.toX=toX;
		this.toY=toY;
		this.Damage= Damage;
		this.dead=false;
		vectorX = this.toX-this.X;
		vectorY = this.toY-this.Y;
		arrow.setViewport(new Rectangle2D(0, 0, 64, 64));
		arrow.setImage(new Image(ImgLink+"arrow"+"_"+"player"+"_"+playerId+".png"));
		arrowani = new SpriteAnimation(arrow, Duration.millis(800), 1, 1, 64, 64,64,64);
		arrowani.setOffsetY(64*type);
	}
	public void render()
	{
		if(cf.root.getChildren().indexOf(arrow)<0) cf.root.getChildren().add(arrow);
	}
	public void Run(Map map,Enemy enemy)
	{
		if((X-enemy.getX())*(X-enemy.getX())+(Y-enemy.getY())*(Y-enemy.getY()) <= 12*12)
		{
			if(cf.root.getChildren().indexOf(arrow)>=0) cf.root.getChildren().remove(arrow);
			enemy.Hurted(Damage);
			dead=true;
			return;
		}
		if(vectorX*(toX-X)<=0 && vectorY*(toY-Y)<=0)
		{
			if(cf.root.getChildren().indexOf(arrow)>=0) cf.root.getChildren().remove(arrow);
			dead=true;
			return;
		}
		if(vectorX*(toX-X)>0)
			X+=((vectorX<0)?-1.0:1.0)*1;
		if(vectorY*(toY-Y)>0)
			Y+=((vectorY<0)?-1.0:1.0)*1;
		
		arrow.setX(X-64/2);
		arrow.setY(Y-64/2);
		arrowani.play();
		if(map.isAvailable(X, Y)==false)
		{
			if(cf.root.getChildren().indexOf(arrow)>=0) cf.root.getChildren().remove(arrow);
			dead=true;
		}
	}
	
	public boolean isDead()
	{
		return this.dead;
	}
}
