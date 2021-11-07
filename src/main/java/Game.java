import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;


public class Game {
        public Screen screen;
        public Hero hero;
        public Arena arena;
        public Game() {
            int width = 40;
            int height = 20;
            try {
                 TerminalSize terminalSize = new TerminalSize(width, height);
                 DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
                 Terminal terminal = terminalFactory.createTerminal();
                 Screen screen = new TerminalScreen(terminal);
                 screen.setCursorPosition(null);
                 screen.startScreen();
                 screen.doResizeIfNecessary();
                 TextGraphics graphics = screen.newTextGraphics();
                 this.screen = screen;
                 hero = new Hero(new Position(10,10));
                 arena = new Arena(width,height,hero, screen.newTextGraphics());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }
    public void run() throws IOException {
        while(true) {
            draw();
            KeyStroke key = screen.readInput();
            if(key.getKeyType() == KeyType.EOF)
                break;
            if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                screen.close();
            arena.processKey(key);
        }
    }

    private void processKey(com.googlecode.lanterna.input.KeyStroke key) throws IOException {
        arena.processKey(key);
    }

}
