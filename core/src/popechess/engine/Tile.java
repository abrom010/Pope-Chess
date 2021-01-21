package popechess.engine;

public class Tile {
    private Piece piece;

    Tile(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isPieceWhite() {
        switch(piece) {
            case WHITE_ROOK:
                return true;
            case WHITE_KNIGHT:
                return true;
            case WHITE_BISHOP:
                return true;
            case WHITE_QUEEN:
                return true;
            case WHITE_KING:
                return true;
            case WHITE_PAWN:
                return true;
            case BLACK_ROOK:
                return false;
            case BLACK_KNIGHT:
                return false;
            case BLACK_BISHOP:
                return false;
            case BLACK_QUEEN:
                return false;
            case BLACK_KING:
                return false;
            case BLACK_PAWN:
                return false;
            case EMPTY:
                return false;
            default:
                return false;
        }
    }

    public String getPieceName() {
        switch(piece) {
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

    public boolean isPopeProtected() {
        return false;
    }
}
