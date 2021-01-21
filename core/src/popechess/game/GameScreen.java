package popechess.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import java.util.List;

import popechess.engine.Piece;
import popechess.engine.Position;
import popechess.engine.Tile;

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

		if(Gdx.input.justTouched()) { // if click
		    if(main.positionOfPieceBeingMoved == null) { // Trying to pick up piece
                Position position = main.getPositionFromCoordinates(Gdx.input.getX(), Gdx.input.getY());
                Tile tile = main.board.getTileAtPosition(position);
                Piece piece = tile.getPiece();
                if(piece != Piece.EMPTY && piece != null) {
                    main.positionOfPieceBeingMoved = position;
                    main.possiblePositions = main.board.getPiecePossiblePositions(main.positionOfPieceBeingMoved);
                }
            } else { // Placing piece
                Position position = main.getPositionFromCoordinates(Gdx.input.getX(), Gdx.input.getY());

                boolean contains = false;
                for(Position p : main.possiblePositions) {
                    if(p.i == position.i && p.j == position.j) contains = true;
                }
                if(contains) {
                    Tile tile = main.board.getTileAtPosition(position);
                    tile.setPiece(main.board.getPieceAtPosition(main.positionOfPieceBeingMoved));
                    Tile originalTile = main.board.getTileAtPosition(main.positionOfPieceBeingMoved);
                    originalTile.setPiece(Piece.EMPTY);
                }

		        main.positionOfPieceBeingMoved = null;
		        main.possiblePositions = null;
            }

        }

		main.drawBoard();
		main.drawHighlights();
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
