package popechess.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import popechess.engine.Piece;
import popechess.engine.Position;

public class GameScreen implements Screen {
    private Main main;

    public GameScreen(Main main) {
        this.main = main;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
		Gdx.gl.glClearColor(.5f, .6f, .9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(Gdx.input.justTouched()) {
		    if(main.pieceBeingCarried == null) {
                Position position = main.getPositionFromCoordinates(Gdx.input.getX(), Gdx.input.getY());
                Piece piece = main.board.getTileAtPosition(position).getPiece();
                System.out.println(main.board.getPieceName(piece));
//		        Position position = main.getPositionFromCoordinates(Gdx.input.getX(), Gdx.input.getY());
//		        Piece piece = main.board.getTileAtPosition(position).getPiece();
//                if(piece != Piece.EMPTY && piece != null) {
//                    main.pieceBeingCarried = piece;
//                }
            } else {
//                Position position = main.getPositionFromCoordinates(Gdx.input.getX(), Gdx.input.getY());
//                Piece piece = main.board.getTileAtPosition(position).getPiece();
//		        if(piece == Piece.EMPTY && piece != null) {
//		            main.pieceBeingCarried = null;
//                }
            }

        }

		main.drawBoard();
		main.drawPieces();
    }

    @Override
    public void resize(int width, int height) {
        main.width = width;
        main.height = height;
        main.backgroundLength = Math.min(width, height);
        main.squareLength = main.backgroundLength/8;
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
        this.main.dispose();
    }
}
