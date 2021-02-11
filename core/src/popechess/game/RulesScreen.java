package popechess.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class RulesScreen implements Screen {
    Main main;
    BitmapFont font;
    String rulesText = "Pope Chess Rules\n" +
        "\n" +
        "1. The rules of Pope Chess are the same as regular chess,\nexcept that there is a Pope piece.\n" +
        "\n" +
        "2. The Pope starts in the very center of the board,\nat the intersection of the middle four squares.\n" +
        "\n" +
        "3. The Pope protects these four squares.\nA player's piece cannot be captured if it is on one of these four squares.\n" +
        "\n" +
        "4. Either player can use one of their turns to move the Pope.\nThe Pope can move up, down, left, or right, one square at a time.\n The Pope may not move to the edge of the board,\nbut always stays one square away from the edge of the board.\n" +
        "\n" +
        "5. The Pope may not be moved backwards to the space that it just came from.\nThis means that the Pope has a maximum of three places it may be moved\nat any given time, except for its first move\n when four spots may be chosen.\n" +
        "\n" +
        "6. The Pope does not prevent pieces from passing diagonally through it,\nor block other pieces in any way by itself. If there is a piece on one of the\nfour squares being protected by the Pope, then they will block other pieces\nthat attempt to move through that piece's square.\n" +
        "\n" +
        "7. The Pope cannot be captured, nor can it capture other pieces.\n" +
        "\n" +
        "8. The Pope may not be moved on white's first turn.\nThe Pope may not be moved until black's first turn.\n" +
        "\n" +
        "9. The Pope cannot protect the king." ;

    public RulesScreen(Main main) {
        this.main = main;
        this.font = new BitmapFont();
        this.font.setColor(new Color(1f, 1f, 0f, 1));
        this.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        float scale = Gdx.graphics.getWidth()*3.5f/1800;
        this.font.getData().setScale(scale);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.2f, .2f, .4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            main.setScreen(new MenuScreen(main));
        }

        main.spriteBatch.begin();
        float padding = Gdx.graphics.getWidth()*20f/1800;
        font.draw(main.spriteBatch, rulesText, padding, main.height-padding);
        main.spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
