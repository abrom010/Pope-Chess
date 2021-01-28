package popechess.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import popechess.engine.Piece;

public class Utils {
    Texture popeTexture = new Texture(Gdx.files.internal("pope.png"));
    Texture whiteRookTexture = new Texture(Gdx.files.internal("whiterook.png"));
    Texture whiteKnightTexture = new Texture(Gdx.files.internal("whiteknight.png"));
    Texture whiteBishopTexture = new Texture(Gdx.files.internal("whitebishop.png"));
    Texture whiteQueenTexture = new Texture(Gdx.files.internal("whitequeen.png"));
    Texture whiteKingTexture = new Texture(Gdx.files.internal("whiteking.png"));
    Texture whitePawnTexture = new Texture(Gdx.files.internal("whitepawn.png"));
    Texture blackRookTexture = new Texture(Gdx.files.internal("blackrook.png"));
    Texture blackKnightTexture = new Texture(Gdx.files.internal("blackknight.png"));
    Texture blackBishopTexture = new Texture(Gdx.files.internal("blackbishop.png"));
    Texture blackQueenTexture = new Texture(Gdx.files.internal("blackqueen.png"));
    Texture blackKingTexture = new Texture(Gdx.files.internal("blackking.png"));
    Texture blackPawnTexture = new Texture(Gdx.files.internal("blackpawn.png"));

    public Texture getTextureFromPiece(Piece piece) {
        switch(piece) {
            case WHITE_ROOK:
                return whiteRookTexture;
            case WHITE_KNIGHT:
                return whiteKnightTexture;
            case WHITE_BISHOP:
                return whiteBishopTexture;
            case WHITE_QUEEN:
                return whiteQueenTexture;
            case WHITE_KING:
                return whiteKingTexture;
            case WHITE_PAWN:
                return whitePawnTexture;
            case BLACK_ROOK:
                return blackRookTexture;
            case BLACK_KNIGHT:
                return blackKnightTexture;
            case BLACK_BISHOP:
                return blackBishopTexture;
            case BLACK_QUEEN:
                return blackQueenTexture;
            case BLACK_KING:
                return blackKingTexture;
            case BLACK_PAWN:
                return blackPawnTexture;
            default:
                return null;
        }
    }

    public int getRankFromPiece(Piece piece) {
        switch(piece) {
            case WHITE_ROOK:
                return 4;
            case WHITE_KNIGHT:
                return 2;
            case WHITE_BISHOP:
                return 3;
            case WHITE_QUEEN:
                return 5;
            case WHITE_PAWN:
                return 1;
            case BLACK_ROOK:
                return 4;
            case BLACK_KNIGHT:
                return 2;
            case BLACK_BISHOP:
                return 3;
            case BLACK_QUEEN:
                return 5;
            case BLACK_PAWN:
                return 1;
            default:
                return 6;
        }
    }

    public ShapeRenderer alternateColor(ShapeRenderer shapeRenderer, Color color1, Color color2) {
        if(shapeRenderer.getColor().equals(color1)) {
            shapeRenderer.setColor(color2);
        } else {
            shapeRenderer.setColor(color1);
        }
        return shapeRenderer;
    }
}
