package simplerpg.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import simplerpg.BaseObj;
import simplerpg.Config;

public class Map extends BaseObj
{
	private Config cf = Config.getInstance();
	private String ImgLink = cf.getLink();
	private String mapId;
	private ImageView map;

	private ImageView[] ImgEntity;
	private ArrayList<EntityInfo> Entity = new ArrayList<>();
	private ArrayList<EntityInfo> Room = new ArrayList<>();
	private int species = 0;
	private int EPR = 0;
	public Map(String Id)
	{
		///Name of Map
		this.mapId=Id;
		
		///Map
		map = new ImageView();
		map.setImage(new Image(ImgLink+"/map"+this.mapId+"/"+"map"+this.mapId+".png"));
		map.setX(0);
		map.setY(0);
		
		/// Input Map
		switch(Id)
		{
			case "01":
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/jar.input",1));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/brokenjar.input",2));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/stair_01.input",3));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/stair_02.input",4));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/brokenpillar_01.input",5));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/brokenpillar_02.input",6));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/brokenpillar_03.input",7));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/skeleton_01.input",8));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/skeleton_02.input",9));
				
				this.species  = 3;
				EPR = 3;
				
				Room.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/room.input",0));
				for(int i=0;i<Room.size();i++)
				{
					if(i<=6) Room.get(i).setTypeRoom(1); /// Fight Room
					if(i==4 || i==3) Room.get(i).setTypeRoom(2); /// Base
					if(i>6) Room.get(i).setTypeRoom(3); /// Lobby
				}
				break;
		}
		
		/// Image Entity
		ImgEntity = new ImageView[Entity.size()];
		for(int i=0;i<Entity.size();i++)
		{
			ImgEntity[i] = new ImageView();
			EntityInfo e = Entity.get(i);
			switch(e.getType())
			{
				case 1:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/jar.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 2:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/brokenjar.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 3:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/stair_01.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 4:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/stair_02.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 5:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/brokenpillar_01.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 6:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/brokenpillar_02.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 7:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/brokenpillar_03.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 8:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/skeleton_01.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 9:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/skeleton_02.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
			}
		}
		
	}
	@Override
	public void render(GraphicsContext gc) 
	{
		if(cf.root.getChildren().indexOf(map)<0) cf.root.getChildren().add(map);
		
		//for(int i=0;i<Entity.size();i++)if(cf.root.getChildren().indexOf(ImgEntity[i])<0 && Entity.get(i).getType()==1) cf.root.getChildren().add(ImgEntity[i]);
		
		
	}

	@Override
	public void update() 
	{
		
	}

	@Override
	public void erase() 
	{
		if(cf.root.getChildren().indexOf(map)>=0) cf.root.getChildren().remove(map);
		for(int i=0;i<Entity.size();i++)if(cf.root.getChildren().indexOf(ImgEntity[i])>=0) cf.root.getChildren().remove(ImgEntity[i]);
		
	}
	private ArrayList<EntityInfo> getCoordinate(String Link,int type)
	{
		ArrayList <EntityInfo> EI = new ArrayList<>();
		File file = new File(Link);
		try
		{
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine())
			{
				String input = scan.nextLine();
				EntityInfo entity = new EntityInfo(type);
				int x1=0,y1=0;
				int x2=0,y2=0;
				int x3=0,y3=0;
				int x4=0,y4=0;
				int num=0;
				for(int i=0;i<input.length();i++)
				{
					if(input.charAt(i)=='(' || input.charAt(i)==',') continue;
					if(input.charAt(i)==' ' || input.charAt(i)==')')
					{
						if(x1==0) x1=num;
						else if(y1==0) y1=num;
						else if(x2==0) x2=num;
						else if(y2==0) y2=num;
						else if(x3==0) x3=num;
						else if(y3==0) y3=num;
						else if(x4==0) x4=num;
						else if(y4==0) y4=num;
						num=0;
						continue;
					}
					num=num*10+(input.charAt(i)-'0');
				}
				entity.setMinX(Math.min(x1,Math.min(x2, Math.min(x3, x4))));
				entity.setMaxX(Math.max(x1,Math.max(x2, Math.max(x3, x4))));
				entity.setMinY(Math.min(y1,Math.min(y2, Math.min(y3, y4))));
				entity.setMaxY(Math.max(y1,Math.max(y2, Math.max(y3, y4))));
				EI.add(entity);
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return EI;
	}
	public ImageView getImageEntity(int i)
	{
		return ImgEntity[i];
	}
	public ArrayList<EntityInfo> getEntity()
	{
		return Entity;
	}
	public ArrayList<EntityInfo> getRoom()
	{
		return Room;
	}
	public EntityInfo isRoom(double X,double Y)
	{
		for(int i=0;i<Room.size();i++)
		{
			EntityInfo e= Room.get(i);
			if(e.getMinX()<=X && X<=e.getMaxX() && e.getMinY()<=Y && Y<=e.getMaxY()  && e.getState() == 1 && e.getTypeRoom()==1) return e;
		}
		return null;
	}
	public boolean isAvailable(double X, double Y)
	{
		boolean isRoom =false;
		for(int i=0;i<Room.size();i++)
		{
			EntityInfo e = Room.get(i);
			if(e.getMinX()<=X && X<=e.getMaxX() && e.getMinY()<=Y && Y<=e.getMaxY()) {isRoom=true;break;}
		}
		if(isRoom==false) return false;
		for(int i=0;i<Entity.size();i++)
		{
			EntityInfo e= Entity.get(i);
			double minY=0,maxY=0;
			double minX=0,maxX=0;
			switch(e.getType())
			{
				case 1:
					minY=e.getMaxY()-2*e.getHeight();
					maxY=e.getMaxY();
					minX=e.getMinX();
					maxX=e.getMaxX();
					break;
				case 2:
					minY=e.getMaxY()-2*e.getHeight();
					maxY=e.getMaxY();
					minX=e.getMinX();
					maxX=e.getMaxX();
				case 3:
					minY=e.getMaxY()-2*e.getHeight();
					maxY=e.getMaxY();
					minX=e.getMinX();
					maxX=e.getMaxX();
					break;
				case 4:
					minY=e.getMaxY()-2*e.getHeight();
					maxY=e.getMaxY();
					minX=e.getMinX();
					maxX=e.getMaxX();
					break;
				case 5:
					minY=e.getMaxY()-3*e.getHeight();
					maxY=e.getMaxY();
					minX=e.getMinX();
					maxX=e.getMaxX();
					break;
				case 6:
					minY=e.getMaxY()-2*e.getHeight();
					maxY=e.getMaxY();
					minX=e.getMinX();
					maxX=e.getMaxX();
					break;
				case 7:
					minY=e.getMaxY()-2*e.getHeight();
					maxY=e.getMaxY();
					minX=e.getMinX();
					maxX=e.getMaxX();
					break;
				case 8:
					minY=e.getMaxY()-3*e.getHeight();
					maxY=e.getMaxY();
					minX=e.getMinX();
					maxX=e.getMaxX();
					break;
				case 9:
					minY=e.getMaxY()-3*e.getHeight();
					maxY=e.getMaxY();
					minX=e.getMinX();
					maxX=e.getMaxX();
					break;
			}
			//System.out.println(minX+" "+maxX+" "+minY+" "+maxY);
			//System.out.println();
			if(minX<=X && X<=maxX && minY<=Y && Y<=maxY) return false;
		}
		return true;
	}
	public int getSpecies() {
		return species;
	}
	public void setSpecies(int species) {
		this.species = species;
	}
	public int getEPR() {
		return EPR;
	}
	public void setEPR(int ePR) {
		EPR = ePR;
	}
	
}
