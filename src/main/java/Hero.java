import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;


public class Hero{
    public Position position = new Position(0,0);

    public Hero(Position position){
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public Position moveUp(){
        return new Position(position.getX(),position.getY() - 1);
    }

    public Position moveDown(){
        return new Position(position.getX(),position.getY() + 1);
    }

    public Position moveLeft(){
        return new Position(position.getX() - 1,position.getY());
    }

    public Position moveRight(){
        return new Position(position.getX() + 1,position.getY());
    }

    public void draw(Screen screen){
        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('X')[0]);

    }

    public void setPosition(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }
}
