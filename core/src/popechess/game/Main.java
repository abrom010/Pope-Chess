package popechess.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import popechess.engine.Board;
import popechess.engine.Piece;

public class Main extends ApplicationAdapter {
	private SpriteBatch spriteBatch;
	private ShapeRenderer shapeRenderer;
	private Board board;
	private int width;
	private int height;
	private float backgroundLength;
	private float squareLength;

	private Texture popeTexture;
	private Texture whiteRookTexture;
	private Texture whiteKnightTexture;
	private Texture whiteBishopTexture;
	private Texture whiteQueenTexture;
	private Texture whiteKingTexture;
	private Texture whitePawnTexture;
	private Texture blackRookTexture;
	private Texture blackKnightTexture;
	private Texture blackBishopTexture;
	private Texture blackQueenTexture;
	private Texture blackKingTexture;
	private Texture blackPawnTexture;
	
	private Sprite popeSprite;
	private Sprite whiteRookSprite;
	private Sprite whiteKnightSprite;
	private Sprite whiteBishopSprite;
	private Sprite whiteQueenSprite;
	private Sprite whiteKingSprite;
	private Sprite whitePawnSprite;
	private Sprite blackRookSprite;
	private Sprite blackKnightSprite;
	private Sprite blackBishopSprite;
	private Sprite blackQueenSprite;
	private Sprite blackKingSprite;
	private Sprite blackPawnSprite;

	@Override
	public void create() {
		// objects / measurements
		this.spriteBatch = new SpriteBatch();
		this.shapeRenderer = new ShapeRenderer();
		this.board = new Board();
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		this.backgroundLength = Math.min(width, height);
		this.squareLength = this.backgroundLength/8;
		
		// textures
		this.popeTexture = new Texture(Gdx.files.internal("pope.png"));
		this.whiteRookTexture = new Texture(Gdx.files.internal("whiterook.png"));
		this.whiteKnightTexture = new Texture(Gdx.files.internal("whiteknight.png"));
		this.whiteBishopTexture = new Texture(Gdx.files.internal("whitebishop.png"));
		this.whiteQueenTexture = new Texture(Gdx.files.internal("whitequeen.png"));
		this.whiteKingTexture = new Texture(Gdx.files.internal("whiteking.png"));
		this.whitePawnTexture = new Texture(Gdx.files.internal("whitepawn.png"));
		this.blackRookTexture = new Texture(Gdx.files.internal("blackrook.png"));
		this.blackKnightTexture = new Texture(Gdx.files.internal("blackknight.png"));
		this.blackBishopTexture = new Texture(Gdx.files.internal("blackbishop.png"));
		this.blackQueenTexture = new Texture(Gdx.files.internal("blackqueen.png"));
		this.blackKingTexture = new Texture(Gdx.files.internal("blackking.png"));
		this.blackPawnTexture = new Texture(Gdx.files.internal("blackpawn.png"));

		// sprites
		this.popeSprite = new Sprite(popeTexture);
		this.popeSprite.setOriginCenter();
		this.popeSprite.setSize(squareLength, squareLength);
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		shapeRenderer.dispose();
		popeTexture.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		drawBoard();
		drawPieces();
	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	private void drawBoard() {
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BROWN);
		for(int j=0; j<8; j++) {
			shapeRenderer = alternateColor(shapeRenderer, Color.BROWN, Color.TAN);
			for(int i=0; i<8; i++) {
				shapeRenderer = alternateColor(shapeRenderer, Color.BROWN, Color.TAN);
				shapeRenderer.rect(i*squareLength,j*squareLength, squareLength, squareLength);
			}
		}
		shapeRenderer.end();
	}

	private void drawPieces() {
		spriteBatch.begin();

//		popeSprite.setPosition((this.backgroundLength/2)-squareLength/2,(this.backgroundLength/2)-squareLength/2);
//		popeSprite.draw(spriteBatch);


		for(int j=0; j<board.state.length; j++) {
			for(int i=0; i<board.state[0].length; i++) {
				Texture pieceTexture;
				switch(board.state[j][i]) {
					case POPE:
						pieceTexture = popeTexture;
						break;
					case WHITE_ROOK:
						pieceTexture = whiteRookTexture;
						break;
					case WHITE_KNIGHT:
						pieceTexture = whiteKnightTexture;
						break;
					case WHITE_BISHOP:
						pieceTexture = whiteBishopTexture;
						break;
					case WHITE_QUEEN:
						pieceTexture = whiteQueenTexture;
						break;
					case WHITE_KING:
						pieceTexture = whiteKingTexture;
						break;
					case WHITE_PAWN:
						pieceTexture = whitePawnTexture;
						break;
					case BLACK_ROOK:
						pieceTexture = blackRookTexture;
						break;
					case BLACK_KNIGHT:
						pieceTexture = blackKnightTexture;
						break;
					case BLACK_BISHOP:
						pieceTexture = blackBishopTexture;
						break;
					case BLACK_QUEEN:
						pieceTexture = blackQueenTexture;
						break;
					case BLACK_KING:
						pieceTexture = blackKingTexture;
						break;
					case BLACK_PAWN:
						pieceTexture = blackPawnTexture;
						break;
					default:
						continue;
				}
				Sprite pieceSprite = new Sprite(pieceTexture);
				pieceSprite.setOriginCenter();
				pieceSprite.setSize(squareLength*.8f, squareLength*.8f);
				pieceSprite.setPosition(i*squareLength, j*squareLength);
				pieceSprite.draw(spriteBatch);
			}
		}
		

		spriteBatch.end();
	}

	private ShapeRenderer alternateColor(ShapeRenderer shapeRenderer, Color color1, Color color2) {
		if(shapeRenderer.getColor().equals(color1)) {
			shapeRenderer.setColor(color2);
		} else {
			shapeRenderer.setColor(color1);
		}
		return shapeRenderer;
	}

}
