import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private TextGraphics graphics;

    public Arena(int width, int height, Hero hero, TextGraphics graphics) {
        this.width = width;
        this.height = height;
        this.hero = hero;
        this.graphics = graphics;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
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
        }
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position) {
        if ((position.getX() > 0 && position.getX() < width) && (position.getY() > 0 && position.getY() < height))
            return true;
        return false;
    }
}