package popechess.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import java.util.Comparator;
import java.util.List;

import popechess.engine.Piece;
import popechess.engine.Position;
import popechess.engine.Tile;
import popechess.util.Utils;

import static popechess.engine.Piece.BLACK_BISHOP;
import static popechess.engine.Piece.BLACK_KING;
import static popechess.engine.Piece.BLACK_KNIGHT;
import static popechess.engine.Piece.BLACK_PAWN;
import static popechess.engine.Piece.BLACK_QUEEN;
import static popechess.engine.Piece.BLACK_ROOK;
import static popechess.engine.Piece.EMPTY;
import static popechess.engine.Piece.WHITE_BISHOP;
import static popechess.engine.Piece.WHITE_KING;
import static popechess.engine.Piece.WHITE_KNIGHT;
import static popechess.engine.Piece.WHITE_PAWN;
import static popechess.engine.Piece.WHITE_QUEEN;
import static popechess.engine.Piece.WHITE_ROOK;

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

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            main.setScreen(new MenuScreen(main));
        }

        if(main.board.isWhiteCheckmated() || main.board.isBlackCheckmated()) {
            main.isCheckmate = true;
        }

        boolean justTouched = Gdx.input.justTouched();

        if(main.isCheckmate && justTouched) {
            main.board.state = new Tile[][] {
                {new Tile(WHITE_ROOK), new Tile(WHITE_KNIGHT), new Tile(WHITE_BISHOP), new Tile(WHITE_QUEEN), new Tile(WHITE_KING), new Tile(WHITE_BISHOP), new Tile(WHITE_KNIGHT), new Tile(WHITE_ROOK)},
                {new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN)},
                {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
                {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
                {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
                {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
                {new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN)},
                {new Tile(BLACK_ROOK), new Tile(BLACK_KNIGHT), new Tile(BLACK_BISHOP), new Tile(BLACK_QUEEN), new Tile(BLACK_KING), new Tile(BLACK_BISHOP), new Tile(BLACK_KNIGHT), new Tile(BLACK_ROOK)}
            };
            Gdx.gl.glClearColor(.5f, .6f, .9f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            main.isWhiteTurn = true;
            main.board.capturedBlackPieces.clear();
            main.board.capturedWhitePieces.clear();
            main.board.pope.reset();
            main.isCheckmate = false;
        }

        else if(main.whitePawnBeingPromoted && justTouched) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            if(y > main.verticalOffset+main.squareLength*3 && y < main.verticalOffset+main.squareLength*3+main.squareLength*2) {
                float boxWidth = main.squareLength*2;
                // figure out piece, promote pawn
                Tile tile = main.board.getTileAtPosition(main.positionOfPawnBeingPromoted);
                if(x<boxWidth) {
                    tile.setPiece(Piece.WHITE_QUEEN);
                } else if(x<boxWidth*2) {
                    tile.setPiece(Piece.WHITE_ROOK);
                } else if(x<boxWidth*3) {
                    tile.setPiece(Piece.WHITE_BISHOP);
                } else {
                    tile.setPiece(Piece.WHITE_KNIGHT);
                }
                main.whitePawnBeingPromoted = false;
            }
        } else if(main.blackPawnBeingPromoted && justTouched){
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            if(y > main.verticalOffset+main.squareLength*3 && y < main.verticalOffset+main.squareLength*3+main.squareLength*2) {
                float boxWidth = main.squareLength*2;
                // figure out piece, promote pawn
                Tile tile = main.board.getTileAtPosition(main.positionOfPawnBeingPromoted);
                if(x<boxWidth) {
                    tile.setPiece(Piece.BLACK_QUEEN);
                } else if(x<boxWidth*2) {
                    tile.setPiece(Piece.BLACK_ROOK);
                } else if(x<boxWidth*3) {
                    tile.setPiece(Piece.BLACK_BISHOP);
                } else {
                    tile.setPiece(Piece.BLACK_KNIGHT);
                }
                main.blackPawnBeingPromoted = false;
            }
        }

        else if(justTouched) { // if click
		    float x = Gdx.input.getX();
		    float y = Gdx.input.getY();
		    if(main.positionOfPieceBeingMoved == null) { // Not moving regular piece
		        if(main.getPopeBeingMoved()) { // if moving pope
                    if(!main.clickedOnPope(x,y)) {
                        float xDiff = x - main.popeSprite.getX()-main.popeSprite.getWidth()/2;
                        float yDiff = y - main.getPopeSpriteY()-main.popeSprite.getHeight()/2;
                        boolean popeMoved = false;
                        if(Math.abs(xDiff)>Math.abs(yDiff)) {
                            if(xDiff<0) {
                                popeMoved = main.board.pope.moveLeft();
                            } else {
                                popeMoved = main.board.pope.moveRight();
                            }
                        } else {
                            if(yDiff<0) {
                                popeMoved = main.board.pope.moveDown();
                            } else {
                                popeMoved = main.board.pope.moveUp();
                            }
                        }
                        if(popeMoved) {
                            main.isWhiteTurn = !main.isWhiteTurn;
                            if(main.board.firstMove) main.board.firstMove = false;
                        }
                    }
                    main.setPopeBeingMoved(false);
                } else if(main.clickedOnPope(x,y)) { // clicked on pope
                    if(!(main.board.isWhiteKingInCheck() || main.board.isBlackKingInCheck()))
		                if(!main.board.firstMove) main.setPopeBeingMoved(true);
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
                                    if(main.possiblePositions == null || main.possiblePositions.isEmpty()) main.positionOfPieceBeingMoved = null;
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
                        // castle?
                        Tile tileOfPieceBeingMoved = main.board.getTileAtPosition(main.positionOfPieceBeingMoved);
                        if((tileOfPieceBeingMoved.getPiece()==Piece.WHITE_KING || tileOfPieceBeingMoved.getPiece()==Piece.BLACK_KING) && Math.abs(position.i-main.positionOfPieceBeingMoved.i)>1) {
                            Position rookPosition;
                            Position newRookPosition;
                            if(position.i<main.positionOfPieceBeingMoved.i) { // left
                                rookPosition = new Position(position.i - 2, position.j);
                                newRookPosition = new Position(position.i + 1, position.j);
                            } else { // right
                                rookPosition = new Position(position.i+1,position.j);
                                newRookPosition = new Position(position.i-1, position.j);
                            }
                            main.board.setPieceAtPosition(rookPosition, Piece.EMPTY);
                            if(tileOfPieceBeingMoved.isPieceWhite()) { // white
                                if(!main.board.isWhiteKingInCheck()) {
                                    main.board.setPieceAtPosition(newRookPosition, Piece.WHITE_ROOK);
                                }
                            } else { // black
                                if(!main.board.isBlackKingInCheck()) {
                                    main.board.setPieceAtPosition(newRookPosition, Piece.BLACK_ROOK);
                                }
                            }
                        }
                        Tile tile = main.board.getTileAtPosition(position);

                        // add to captures
                        if(!tile.isEmpty() && tile.isPieceWhite()) {
                            main.board.capturedWhitePieces.add(tile.getPiece());
                            main.board.capturedWhitePieces.sort(new Comparator<Piece> (){
                                @Override
                                public int compare(Piece piece1, Piece piece2) {
                                    int rank1 = main.utils.getRankFromPiece(piece1);
                                    int rank2 = main.utils.getRankFromPiece(piece2);
                                    return Integer.compare(rank1, rank2);
                                }
                            }.reversed());
                        }
                        else if(!tile.isEmpty() && !tile.isPieceWhite()) {
                            main.board.capturedBlackPieces.add(tile.getPiece());
                            main.board.capturedBlackPieces.sort(new Comparator<Piece> (){
                                @Override
                                public int compare(Piece piece1, Piece piece2) {
                                    int rank1 = main.utils.getRankFromPiece(piece1);
                                    int rank2 = main.utils.getRankFromPiece(piece2);
                                    return Integer.compare(rank1, rank2);
                                }
                            }.reversed());
                        }

                        tile.setPiece(main.board.getPieceAtPosition(main.positionOfPieceBeingMoved));
                        Tile originalTile = main.board.getTileAtPosition(main.positionOfPieceBeingMoved);
                        originalTile.setPiece(Piece.EMPTY);
                        main.isWhiteTurn = !main.isWhiteTurn;

                        // pawn promotion logic
                        if(tile.getPiece() == Piece.WHITE_PAWN && position.j == 7) {
                            main.positionOfPawnBeingPromoted = position;
                            main.whitePawnBeingPromoted = true;
                        } else if(tile.getPiece() == Piece.BLACK_PAWN && position.j == 0) {
                            main.positionOfPawnBeingPromoted = position;
                            main.blackPawnBeingPromoted = true;
                        }
                    }
                }

                if(main.board.firstMove) main.board.firstMove = false;

                if(!main.board.whiteKingHasMoved && main.board.getPieceAtPosition(position)==Piece.WHITE_KING && position.i!=4) {
                    main.board.whiteKingHasMoved = true;
                }
                if(!main.board.blackKingHasMoved && main.board.getPieceAtPosition(position)==Piece.BLACK_KING && position.i!=4) {
                    main.board.blackKingHasMoved = true;
                }

                main.positionOfPieceBeingMoved = null;
                main.possiblePositions = null;
            }

        }

		main.drawBoard();
		main.drawHighlights();
        main.drawPopeHighlights();
		main.drawPieces();
		main.drawIndicator();

		main.drawCapturedPieces();
		if(main.whitePawnBeingPromoted) {
            main.drawPawnPromotion(true);
        } else if(main.blackPawnBeingPromoted) {
		    main.drawPawnPromotion(false);
        }

        if(main.getPopeBeingMoved()) {
            main.drawRedDots();
        }

        if(main.isCheckmate) {
            main.drawCheckmateBanner();
        }

    }

    @Override
    public void resize(int width, int height) {
        main.width = width;
        main.height = height;
        main.backgroundLength = width;//Math.min(width, height);
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
