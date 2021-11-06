import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.ArrowDown;

public class Game {
        public Screen screen;
        private Hero hero;
        public Game() {
            int columns = 40;
            int rows = 20;
            try {
                 TerminalSize terminalSize = new TerminalSize(columns, rows);
                 DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
                 Terminal terminal = terminalFactory.createTerminal();
                 Screen screen = new TerminalScreen(terminal);
                 screen.setCursorPosition(null);
                 screen.startScreen();
                 screen.doResizeIfNecessary();
                 this.screen = screen;
                 hero = new Hero(10, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }
    public void run() throws IOException {
        while(true) {
            draw();
            KeyStroke key = screen.readInput();
            if(key.getKeyType() == KeyType.EOF)
                break;
            processKey(key);
        }
    }

    private void processKey(com.googlecode.lanterna.input.KeyStroke key) throws IOException {
        switch (key.getKeyType()) {
            case ArrowDown: {
                hero.moveDown();
                break;
            }
            case ArrowUp: {
                hero.moveUp();
                break;
            }
            case ArrowLeft: {
                hero.moveLeft();
                break;
            }
            case ArrowRight: {
                hero.moveRight();
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
}
