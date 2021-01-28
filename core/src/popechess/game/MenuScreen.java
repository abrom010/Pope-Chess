package popechess.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MenuScreen implements Screen {
    Main main;
    Texture playButtonInactive;
    Texture playButtonActive;
    BitmapFont font;

    public MenuScreen(Main main) {
        this.main = main;
        this.playButtonInactive = new Texture("play_button_inactive.png");
        this.playButtonActive = new Texture("play_button_active.png");
        this.font = new BitmapFont();
        this.font.getData().setScale(5,5);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.5f, .6f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        main.spriteBatch.begin();
        main.spriteBatch.draw(playButtonActive,playButtonActive.getWidth()/4.0f,(main.height/2.0f)-playButtonActive.getHeight()/2.0f);
        font.draw(main.spriteBatch, "Hello, World", 100, 100);
        main.spriteBatch.end();

        if(Gdx.input.isTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();

            // Play button
            float playButtonStartX = playButtonActive.getWidth()/4.0f;
            float playButtonEndX = playButtonActive.getWidth()/4.0f+playButtonActive.getWidth();
            float playButtonStartY = (main.height/2.0f)-playButtonActive.getHeight()/2.0f;
            float playButtonEndY = (main.height/2.0f)-playButtonActive.getHeight()/2.0f+playButtonActive.getHeight();

            if(x>playButtonStartX && x<playButtonEndX) {
                if(y>playButtonStartY && y<playButtonEndY) {
                    main.setScreen(new GameScreen(main));
                }
            }

        }
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
