package popechess.engine;

import static popechess.engine.Piece.EMPTY;
import static popechess.engine.Piece.POPE;
import static popechess.engine.Piece.BLACK_ROOK;
import static popechess.engine.Piece.BLACK_KNIGHT;
import static popechess.engine.Piece.BLACK_BISHOP;
import static popechess.engine.Piece.BLACK_QUEEN;
import static popechess.engine.Piece.BLACK_KING;
import static popechess.engine.Piece.BLACK_PAWN;
import static popechess.engine.Piece.WHITE_ROOK;
import static popechess.engine.Piece.WHITE_KNIGHT;
import static popechess.engine.Piece.WHITE_BISHOP;
import static popechess.engine.Piece.WHITE_QUEEN;
import static popechess.engine.Piece.WHITE_KING;
import static popechess.engine.Piece.WHITE_PAWN;


public class Board {
    public Tile[][] state;

    public Board() {
        this.state = new Tile[][] {
            {new Tile(WHITE_ROOK), new Tile(WHITE_KNIGHT), new Tile(WHITE_BISHOP), new Tile(WHITE_QUEEN), new Tile(WHITE_KING), new Tile(WHITE_BISHOP), new Tile(WHITE_KNIGHT), new Tile(WHITE_ROOK)},
            {new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN)},
            {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
            {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(POPE), new Tile(POPE), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
            {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(POPE), new Tile(POPE), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
            {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
            {new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN)},
            {new Tile(BLACK_ROOK), new Tile(BLACK_KNIGHT), new Tile(BLACK_BISHOP), new Tile(BLACK_QUEEN), new Tile(BLACK_KING), new Tile(BLACK_BISHOP), new Tile(BLACK_KNIGHT), new Tile(BLACK_ROOK)}
        };
    }

    public Tile getTileAtPosition(Position position) {
        return  state[position.j][position.i];
    }

    public Piece getPieceAtPosition(Position position) {
        return getTileAtPosition(position).getPiece();
    }

    public String getPieceName(Piece piece) {
        switch(piece) {
            case POPE:
                return "POPE";
            case WHITE_ROOK:
                return "WHITE_ROOK";
            case WHITE_KNIGHT:
                return "WHITE_KNIGHT";
            case WHITE_BISHOP:
                return "WHITE_BISHOP";
            case WHITE_QUEEN:
                return "WHITE_QUEEN";
            case WHITE_KING:
                return "WHITE_KING";
            case WHITE_PAWN:
                return "WHITE_PAWN";
            case BLACK_ROOK:
                return "BLACK_ROOK";
            case BLACK_KNIGHT:
                return "BLACK_KNIGHT";
            case BLACK_BISHOP:
                return "BLACK_BISHOP";
            case BLACK_QUEEN:
                return "BLACK_QUEEN";
            case BLACK_KING:
                return "BLACK_KING";
            case BLACK_PAWN:
                return "BLACK_PAWN";
            case EMPTY:
                return "EMPTY";
            default:
                return "null";
        }
    }


}
