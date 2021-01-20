package popechess.engine;

import java.util.ArrayList;
import java.util.List;

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

    public Board(Tile[][] state) {
        this.state = state;
    }

    public Tile getTileAtPosition(Position position) {
        return  state[position.j][position.i];
    }

    public Piece getPieceAtPosition(Position position) {
        return getTileAtPosition(position).getPiece();
    }

    public List<Position> getPiecePossiblePositions(Position piecePosition) {
        Piece piece = getPieceAtPosition(piecePosition);
        switch(piece) {
            case POPE:
                return getPopePossiblePositions(piecePosition);
            case WHITE_ROOK:
                return getWhiteRookPossiblePositions(piecePosition);
            case WHITE_KNIGHT:
                return getWhiteKnightPossiblePositions(piecePosition);
            case WHITE_BISHOP:
                return getWhiteBishopPossiblePositions(piecePosition);
            case WHITE_QUEEN:
                return getWhiteQueenPossiblePositions(piecePosition);
            case WHITE_KING:
                return getWhiteKingPossiblePositions(piecePosition);
            case WHITE_PAWN:
                return getWhitePawnPossiblePositions(piecePosition);
            case BLACK_ROOK:
                return getBlackRookPossiblePositions(piecePosition);
            case BLACK_KNIGHT:
                return getBlackKnightPossiblePositions(piecePosition);
            case BLACK_BISHOP:
                return getBlackBishopPossiblePositions(piecePosition);
            case BLACK_QUEEN:
                return getBlackQueenPossiblePositions(piecePosition);
            case BLACK_KING:
                return getBlackKingPossiblePositions(piecePosition);
            case BLACK_PAWN:
                return getBlackPawnPossiblePositions(piecePosition);
            default:
                return new ArrayList<Position>();
        }
    }

    private List<Position> getBlackPawnPossiblePositions(Position piecePosition) {
        return new ArrayList<Position>();
    }

    private List<Position> getBlackKingPossiblePositions(Position piecePosition) {
        return new ArrayList<Position>();
    }

    private List<Position> getBlackQueenPossiblePositions(Position piecePosition) {
        return new ArrayList<Position>();
    }

    private List<Position> getBlackBishopPossiblePositions(Position piecePosition) {
        return new ArrayList<Position>();
    }

    private List<Position> getBlackKnightPossiblePositions(Position piecePosition) {
        return new ArrayList<Position>();
    }

    private List<Position> getBlackRookPossiblePositions(Position piecePosition) {
        return new ArrayList<Position>();
    }

    private List<Position> getWhitePawnPossiblePositions(Position piecePosition) {
        return new ArrayList<Position>();
    }

    private List<Position> getWhiteKingPossiblePositions(Position piecePosition) {
        return new ArrayList<Position>();
    }

    private List<Position> getWhiteQueenPossiblePositions(Position piecePosition) {
        return new ArrayList<Position>();
    }

    private List<Position> getWhiteBishopPossiblePositions(Position piecePosition) {
        return new ArrayList<Position>();
    }

    private List<Position> getWhiteKnightPossiblePositions(Position piecePosition) {
        return new ArrayList<Position>();
    }


    private List<Position> getWhiteRookPossiblePositions(Position piecePosition) {
        return new ArrayList<Position>();
    }

    private List<Position> getPopePossiblePositions(Position piecePosition) {
        return new ArrayList<Position>();
    }

    public Board getCopyOfBoard() {
        return new Board(this.state);
    }
}
