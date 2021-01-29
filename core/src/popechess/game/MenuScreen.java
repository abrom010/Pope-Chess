package popechess.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class MenuScreen implements Screen {
    Main main;
    Texture playButtonInactive;
    Texture playButtonActive;

    GlyphLayout layout;

    Rectangle recRulesButton;
    String rulesButtonText;

    Rectangle recPlayButton;
    String playButtonText;

    BitmapFont font;

    float screenHeight;
    float screenWidth;

    public MenuScreen(Main main) {
        this.font = new BitmapFont();
        this.font.setColor(new Color(.9f, .9f, 0f, 1));
        this.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.font.getData().setScale(10);

        this.main = main;
        this.playButtonInactive = new Texture("play_button_inactive.png");
        this.playButtonActive = new Texture("play_button_active.png");
        this.layout = new GlyphLayout();
        this.recPlayButton = new Rectangle();
        this.playButtonText = "Play";
        this.recRulesButton = new Rectangle();
        this.rulesButtonText = "Rules";

        this.screenHeight = Gdx.graphics.getHeight();
        this.screenWidth = Gdx.graphics.getWidth();

        this.layout.setText(this.font, rulesButtonText);
        this.recRulesButton.set(screenWidth / 3, screenHeight/2+layout.height*.9f, layout.width, layout.height);

        this.layout.setText(this.font, playButtonText);
        this.recPlayButton.set(screenWidth/3, screenHeight/2-layout.height*.9f, layout.width, layout.height);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.5f, .6f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        main.spriteBatch.begin();
        this.font.draw(main.spriteBatch,playButtonText, screenWidth/3, screenHeight/2+layout.height*.9f);
        this.font.draw(main.spriteBatch,rulesButtonText,screenWidth/3,screenHeight/2-layout.height*.9f);
        main.spriteBatch.end();

        if(Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();

            if (recPlayButton.contains(x, y)) {
                main.setScreen(new GameScreen(main));
            }
            else if(recRulesButton.contains(x,y)) {
                main.setScreen(new RulesScreen(main));
            }
        }


//        main.spriteBatch.begin();
//        main.spriteBatch.draw(playButtonActive,playButtonActive.getWidth()/4.0f,(main.height/2.0f)-playButtonActive.getHeight()/2.0f);
//        main.spriteBatch.end();

//        if(Gdx.input.isTouched()) {
//            float x = Gdx.input.getX();
//            float y = Gdx.input.getY();
//
//            // Play button
//            float playButtonStartX = playButtonActive.getWidth()/4.0f;
//            float playButtonEndX = playButtonActive.getWidth()/4.0f+playButtonActive.getWidth();
//            float playButtonStartY = (main.height/2.0f)-playButtonActive.getHeight()/2.0f;
//            float playButtonEndY = (main.height/2.0f)-playButtonActive.getHeight()/2.0f+playButtonActive.getHeight();
//
//            if(x>playButtonStartX && x<playButtonEndX) {
//                if(y>playButtonStartY && y<playButtonEndY) {
////                    main.setScreen(new GameScreen(main));
//                    main.setScreen(new RulesScreen(main));
//                }
//            }
//
//        }
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
