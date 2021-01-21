package popechess.engine;

import java.util.ArrayList;
import java.util.List;

import static popechess.engine.Piece.EMPTY;
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
            {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
            {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
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
        List<Position> possiblePositions = new ArrayList<>();
        int i1 = piecePosition.i;
        int j1 = piecePosition.j - 1;
        int j2 = j1 - 1;

        Position moveForwardOnce = new Position(i1,j1);
        Piece piece1 = this.getPieceAtPosition(moveForwardOnce);
        if(piece1 == EMPTY) {
            possiblePositions.add(moveForwardOnce);
        }

        if(piecePosition.j == 6) {
            Position moveForwardTwice = new Position(i1,j2);
            Piece piece2 = this.getPieceAtPosition(moveForwardTwice);
            if(piece2 == EMPTY) {
                possiblePositions.add(moveForwardTwice);
            }
        }

        int i2 = i1 - 1;
        int i3 = i1 + 1;
        Position attackLeft = new Position(i2, j1);
        Tile tile3 = this.getTileAtPosition(attackLeft);
        Piece piece3 = tile3.getPiece();
        if(piece3 != EMPTY && piece3 != WHITE_KING && tile3.isPieceWhite() && !tile3.isPopeProtected()) {
            possiblePositions.add(attackLeft);
        }

        Position attackRight = new Position(i3, j1);
        Tile tile4 = this.getTileAtPosition(attackRight);
        Piece piece4 = tile4.getPiece();
        if(piece4 != EMPTY && piece4 != WHITE_KING && tile4.isPieceWhite() && !tile4.isPopeProtected()) {
            possiblePositions.add(attackRight);
        }

        return possiblePositions;
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

    public Board getCopyOfBoard() {
        return new Board(this.state);
    }
}
