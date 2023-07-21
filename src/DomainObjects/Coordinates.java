package DomainObjects;
public class Coordinates{
    private int xCoord;
    private int yCoord;

    public Coordinates(){
        setX(0);
        setY(0);
    }

    public Coordinates(int x, int y){
        setX(x);
        setY(y);
    }

    public void setX(int x){
        this.xCoord = x;
    }

    public void setY(int y){
        this.yCoord = y;
    }

    public int getX(){
        return xCoord;
    }

    public int getY(){
        return yCoord;
    }
}
