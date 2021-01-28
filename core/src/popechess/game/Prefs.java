package popechess.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

import popechess.engine.Piece;
import popechess.engine.Tile;

public class Prefs {
    private Preferences pref ;

    public Prefs(){
        pref = Gdx.app.getPreferences("My Preferences");
    }

    public void setBoardState(Tile[][] state) {
        Map<String,String> map = new HashMap<>();
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                map.put(i+","+j, state[i][j].getPieceName());
            }
        }
        pref.put(map);
    }

    public Tile[][] getBoardState() {
        Map<String,String> map = new HashMap<>();
        map = (Map<String, String>) pref.get();
        Tile[][] state = new Tile[8][8];
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                if(map.containsKey(i+","+j)) {
                    String pieceName = map.get(i+","+j);
                    state[i][j] = new Tile(getPieceByName(pieceName));
                }
            }
        }
        return state;
    }

    private Piece getPieceByName(String name) {
        switch(name) {
            case "WHITE_ROOK":
                return Piece.WHITE_ROOK;
            case "WHITE_KNIGHT":
                return Piece.WHITE_KNIGHT;
            case "WHITE_BISHOP":
                return Piece.WHITE_BISHOP;
            case "WHITE_QUEEN":
                return Piece.WHITE_QUEEN;
            case "WHITE_KING":
                return Piece.WHITE_KING;
            case "WHITE_PAWN":
                return Piece.WHITE_PAWN;
            case "BLACK_ROOK":
                return Piece.BLACK_ROOK;
            case "BLACK_KNIGHT":
                return Piece.BLACK_KNIGHT;
            case "BLACK_BISHOP":
                return Piece.BLACK_BISHOP;
            case "BLACK_QUEEN":
                return Piece.BLACK_QUEEN;
            case "BLACK_KING":
                return Piece.BLACK_KING;
            case "BLACK_PAWN":
                return Piece.BLACK_PAWN;
            default:
                return Piece.EMPTY;
        }
    }
}
