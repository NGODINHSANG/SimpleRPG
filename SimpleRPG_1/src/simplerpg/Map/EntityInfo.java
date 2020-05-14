package simplerpg.Map;

public class EntityInfo 
{
	private int Width;
	private int Height;
	private int NumTileX;
	private int NumTileY;
	private int Type;
	private String Name;
	private String InputLink;
	private String ImgLink;
	private double MinX ;
	private double MaxX ; 
	private double MinY ;
	private double MaxY ;
	private int typeRoom ;
	private int state;
	
	public EntityInfo(int Id)
	{
		this.Type = Id;
		switch(Id)
		{
			case 0: ///Room
				this.InputLink ="src/simplerpg/data/map01/room.input";
				this.typeRoom = 0;
				this.state = 1;
				break;
			case 1: /// jar
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "jar";
				break;
			case 2: /// broken jar
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="brokenjar";
			case 3: /// stair_01
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="stair_01";
				break;
			case 4: /// stair_02
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="stair_02";
				break;
			case 5: /// brokenpillar_01
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="brokenpillar_01";
				break;
			case 6: /// brokenpillar_02
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="brokenpillar_02";
				break;
			case 7: /// brokenpillar_03
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="brokenpillar_03";
				break;
			case 8: /// skeleton_01
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="skeleton_01";
				break;
			case 9: /// skeleton_02
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="skeleton_02";
				break;
		}
	}
	public int getHeight() {
		return Height;
	}
	public void setHeight(int height) {
		Height = height;
	}
	public int getNumTileX() {
		return NumTileX;
	}
	public void setNumTileX(int numTileX) {
		NumTileX = numTileX;
	}
	public int getNumTileY() {
		return NumTileY;
	}
	public void setNumTileY(int numTileY) {
		NumTileY = numTileY;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public int getWidth() {
		return Width;
	}
	public void setWidth(int width) {
		Width = width;
	}
	public String getInputLink() {
		return InputLink;
	}
	public void setInputLink(String inputLink) {
		InputLink = inputLink;
	}
	public String getImgLink() {
		return ImgLink;
	}
	public void setImgLink(String imgLink) {
		ImgLink = imgLink;
	}
	public double getMinX() {
		return MinX;
	}
	public void setMinX(double minX) {
		MinX = minX;
	}
	public double getMaxX() {
		return MaxX;
	}
	public void setMaxX(double maxX) {
		MaxX = maxX;
	}
	public double getMinY() {
		return MinY;
	}
	public void setMinY(double minY) {
		MinY = minY;
	}
	public double getMaxY() {
		return MaxY;
	}
	public void setMaxY(double maxY) {
		MaxY = maxY;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getTypeRoom() {
		return typeRoom;
	}
	public void setTypeRoom(int typeRoom) {
		this.typeRoom = typeRoom;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
