package simplerpg.Map;

public class Pair implements Comparable<Pair>
{
	private double X;
	private double Y;
	public Pair()
	{
		
	}
	public Pair(double X,double Y) 
	{
		this.X=X;
		this.Y=Y;
	}
	public double getX()
	{
		return this.X;
	}
	public double getY()
	{
		return this.Y;
	}
	public void setX(double x)
	{
		this.X=x;
	}
	public void setY(double y)
	{
		this.Y=y;
	}
	@Override
	public int compareTo(Pair o) 
	{
		if(this.X>o.X) return 1;
		if(this.X==o.X)
		{
			if(this.Y>o.Y) return 1;
			if(this.Y==o.Y) return 0;
		}
		return -1;
	}
}
