import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private Screen screen;
    public Arena(int width, int height, Hero hero,Screen screen){
        this.width = width;
        this.height = height;
        this.hero = hero;
        this.screen = screen;
    }

    public void draw(Screen screen) {
        screen.setCharacter(hero.position.getX(), hero.position.getY(), TextCharacter.fromCharacter('X')[0]);
    }

    public void processKey(KeyStroke key) throws IOException {
        switch (key.getKeyType()) {
            case ArrowDown: {
                moveHero(hero.moveDown());
                break;
            }
            case ArrowUp: {
                moveHero(hero.moveUp());
                break;
            }
            case ArrowLeft: {
                moveHero(hero.moveLeft());
                break;
            }
            case ArrowRight: {
                moveHero(hero.moveRight());
                break;
            }
            case Character:
            {
                if(key.getCharacter() == 'q')
                    screen.close();
                break;
            }
        }
    }
    public void moveHero(Position position) {
        if(canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position) {
        if((position.getX() > 0 && position.getX() < width) && (position.getY() > 0 && position.getY() < height))
            return true;
        return false;
    }
}
