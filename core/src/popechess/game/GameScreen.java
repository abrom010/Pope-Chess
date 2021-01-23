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
		    float x = Gdx.input.getX();
		    float y = Gdx.input.getY();
		    if(main.positionOfPieceBeingMoved == null) { // Not moving regular piece
		        if(main.getPopeBeingMoved()) { // if moving pope
                    if(!main.clickedOnPope(x,y)) {
                        float xDiff = x - main.popeSprite.getX()-main.popeSprite.getWidth()/2;
                        float yDiff = y - main.getPopeSpriteY()-main.popeSprite.getHeight()/2;
                        if(Math.abs(xDiff)>Math.abs(yDiff)) {
                            if(xDiff<0) {
                                main.board.pope.moveLeft();
                            } else {
                                main.board.pope.moveRight();
                            }
                        } else {
                            if(yDiff<0) {
                                main.board.pope.moveDown();
                            } else {
                                main.board.pope.moveUp();
                            }
                        }
                        main.isWhiteTurn = !main.isWhiteTurn;
                    }
                    main.setPopeBeingMoved(false);
                } else if(main.clickedOnPope(x,y)) { // clicked on pope
                    main.setPopeBeingMoved(true);
                } else { // did not click on pope
                    Position position = main.getPositionFromCoordinates(x, y);
                    if(position != null) {
                        Tile tile = main.board.getTileAtPosition(position);
                        Piece piece = tile.getPiece();
                        if(!(tile.isPieceWhite() && !main.isWhiteTurn)) {
                            if(!(!tile.isPieceWhite() && main.isWhiteTurn)) {
                                if(piece != Piece.EMPTY && piece != null) {
                                    main.positionOfPieceBeingMoved = position;
                                    main.possiblePositions = main.board.getPiecePossiblePositions(main.positionOfPieceBeingMoved);
                                    if(main.possiblePositions == null) main.positionOfPieceBeingMoved = null;
                                }
                            }
                        }
                    }
                }
            } else { // Placing piece
                Position position = main.getPositionFromCoordinates(x, y);
                if(position != null) {
                    boolean contains = false;
                    for(Position p : main.possiblePositions) {
                        if(p.i == position.i && p.j == position.j) contains = true;
                    }
                    if(contains) {
                        Tile tile = main.board.getTileAtPosition(position);
                        tile.setPiece(main.board.getPieceAtPosition(main.positionOfPieceBeingMoved));
                        Tile originalTile = main.board.getTileAtPosition(main.positionOfPieceBeingMoved);
                        originalTile.setPiece(Piece.EMPTY);
                        main.isWhiteTurn = !main.isWhiteTurn;
                    }
                }
		        main.positionOfPieceBeingMoved = null;
		        main.possiblePositions = null;
            }

        }

		main.drawBoard();
		main.drawHighlights();
        main.drawPopeHighlights();
		main.drawPieces();

        if(main.getPopeBeingMoved()) {
            main.drawRedDots();
        }
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
