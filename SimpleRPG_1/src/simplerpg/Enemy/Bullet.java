package simplerpg.Enemy;

import javafx.animation.Animation.Status;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import simplerpg.Config;
import simplerpg.SpriteAnimation;
import simplerpg.Adventurer.Player;
import simplerpg.Map.Map;

public class Bullet extends Enemy
{
	private BulletState State;
	private Config cf = Config.getInstance();
	private String ImgLink = cf.getLink();
	
	private double X;
	private double Y;
	private double toX;
	private double toY;
	private double vectorX;
	private double vectorY;
	
	private ImageView bullet = new ImageView();
	private ImageView bullethit = new ImageView();
	
	private SpriteAnimation bulletani ;
	private SpriteAnimation bullethitani;
	
	private boolean dead=false;
	public Bullet(int id,double X,double Y,double toX,double toY)
	{
		super();
		State = new BulletState(id);
		
		this.X= X;
		this.Y= Y;
		this.toX =  toX;
		this.toY = toY;
		this.vectorX =toX-X;
		this.vectorY =toY-Y;
		///Bullet
		bullet.setViewport(new Rectangle2D(0, 0, State.getBgwidth(),State.getBgheight()));
		bullet.setImage(new Image(ImgLink+State.getName()));
		bulletani = new SpriteAnimation(bullet, Duration.millis(800), State.getCount_ani_bullet(), State.getColumn_ani_bullet(), State.getBgwidth(), State.getBgheight(),State.getBgwidth(),State.getBgheight());
	
		///Bullet hit
		bullethit.setViewport(new Rectangle2D(0, 0, State.getBgwidth(),State.getBgheight()));
		bullethit.setImage(new Image(ImgLink+State.getNameHit()));
		bullethitani = new SpriteAnimation(bullethit, Duration.millis(800), State.getCount_ani_bullethit(), State.getColumn_ani_bullethit(), State.getBgwidth(), State.getBgheight(),State.getBgwidth(),State.getBgheight());
	}
	public void render()
	{
		if(cf.root.getChildren().indexOf(bullet)<0) cf.root.getChildren().add(bullet);
		if(cf.root.getChildren().indexOf(bullethit)<0)cf.root.getChildren().add(bullethit);
	}
	public void Run(Map map,Player player)
	{
		if((X-player.getX())*(X-player.getX())+(Y-player.getY())*(Y-player.getY()) <= State.getRange()*State.getRange())
		{
			if(cf.root.getChildren().indexOf(bullet)>=0) cf.root.getChildren().remove(bullet);
			
			bullethitani.setOnFinished(e->{if(cf.root.getChildren().indexOf(bullethit)>=0) cf.root.getChildren().remove(bullethit);});
			bullethit.setX(X-State.getBgwidth()/2);
			bullethit.setY(Y-State.getBgheight()/2);
			bullethitani.play();
			player.Hurted(State.getDamage());
			dead=true;
			return;
		}

		if(vectorX*(toX-X)<=0 && vectorY*(toY-Y)<=0)
		{
			if(cf.root.getChildren().indexOf(bullet)>=0) cf.root.getChildren().remove(bullet);
			bullethitani.setOnFinished(e->{if(cf.root.getChildren().indexOf(bullethit)>=0) cf.root.getChildren().remove(bullethit);});
			bullethit.setX(X-State.getBgwidth()/2);
			bullethit.setY(Y-State.getBgheight()/2);
			bullethitani.play();
			dead=true;
			return;
		}
		if(vectorX<0) {bulletani.setOffsetY(State.getBgheight());bullethitani.setOffsetY(State.getBgheight());}
		else {bulletani.setOffsetY(State.getBgheight()*2);bullethitani.setOffsetY(State.getBgheight()*2);}
		
		if(vectorX*(toX-X)>0)
			X+=((vectorX<0)?-1.0:1.0)*State.getSpeed();
		if(vectorY*(toY-Y)>0)
			Y+=((vectorY<0)?-1.0:1.0)*State.getSpeed();
		
		
		bullet.setX(X-State.getBgwidth()/2);
		bullet.setY(Y-State.getBgheight()/2);
		bulletani.play();
		if(checkStep(map,X,Y,State.getRange(),State.getRange())==true)
		{
			if(cf.root.getChildren().indexOf(bullet)>=0) cf.root.getChildren().remove(bullet);
			
			bullethitani.setOnFinished(e->{if(cf.root.getChildren().indexOf(bullethit)>=0) cf.root.getChildren().remove(bullethit);});
			bullethit.setX(X-State.getBgwidth()/2);
			bullethit.setY(Y-State.getBgheight()/2);
			bullethitani.play();
			dead=true;
		}

	}
	
	public boolean getDead()
	{
		return dead;
	}
	
}
