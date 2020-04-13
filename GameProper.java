public class GameProper{
	public final String UP = "UP";
	public final String DOWN = "DOWN";
	public final String LEFT = "LEFT";
	public final String RIGHT = "RIGHT";

	private GameArea gameArea;
	private int keeperXpos;
	private int keeperYpos;
	private char[][] layout;

	static final char BOX = 'b';
	static final char BOS = 'B';
	static final char KEEPER = 'k';
	static final char KOS = 'K';
	static final char FLOOR = 'e';
	static final char STORAGE = 's';
	static final char WALL = 'w';
	static final char NONE = 'x';

	public GameProper(GameArea gameArea){
		this.gameArea = gameArea;
		this.keeperXpos = this.gameArea.getKeeperXpos();
		this.keeperYpos = this.gameArea.getKeeperYpos();
		this.layout = this.gameArea.getlayout();
	}

	public void getKey(String move){
		switch(move){
			case UP:
				move(-1,0);
				break;
			case DOWN:
				move(1,0);
				break;
			case LEFT:
				move(0,-1);
				break;
			case RIGHT:
				move(0,+1);
				break;
		}
		this.gameArea.repaint();
		if (isWinner()){
			System.out.println("YOU WIN");
			this.gameArea.win();
		}
	}

	private void move(int stepX,int stepY){
		if (isWall(this.keeperXpos+stepX,this.keeperYpos+stepY)){
			System.out.println("Cannot move to the wall");
		}
		else{
			if (isBox(this.keeperXpos+stepX,this.keeperYpos+stepY)){
				int boxXpos = this.keeperXpos+stepX;
				int boxYpos = this.keeperYpos+stepY;
				if (isWall(boxXpos +stepX,boxYpos+stepY) || isBox(boxXpos +stepX,boxYpos+stepY))
					System.out.println("Cannot push the box to wall or another box");
				else{
					switchTiles(boxXpos, boxYpos, boxXpos+stepX, boxYpos+stepY);
					switchTiles(this.keeperXpos,this.keeperYpos,this.keeperXpos+stepX,this.keeperYpos+stepY);

				}
			}
			else switchTiles(this.keeperXpos,this.keeperYpos,this.keeperXpos+stepX,this.keeperYpos+stepY);

		}
	}
	private void switchTiles(int prevX,int prevY,int nextX, int nextY){
		char prevTile = this.layout[prevX][prevY];
		char nextTile = this.layout[nextX][nextY];

		switch (nextTile){
			case FLOOR:
					nextTile = Character.toLowerCase(prevTile);
				break;
			case STORAGE:
					nextTile = Character.toUpperCase(prevTile);
				break;
			
		}
		if (Character.isLowerCase(prevTile)) prevTile = FLOOR;
		else prevTile = STORAGE;

		this.layout[prevX][prevY] = prevTile;
		this.layout[nextX][nextY] = nextTile;

		findKeeper();
	}
	private boolean isBox(int isBoxX,int isBoxY){
		if(this.layout[isBoxX][isBoxY] == BOX || this.layout[isBoxX][isBoxY] == BOS){
			return true;
		}
		else
			return false;		
	}


	private boolean isWall(int isWallX,int isWallY){

		if(this.layout[isWallX][isWallY] == WALL || this.layout[isWallX][isWallY] == NONE)
			return true;
		return false;
	}

	private void findKeeper(){
		for(int i = 0; i<10; i++){
			for(int j = 0; j<10; j++){
				if (this.layout[i][j] == KEEPER || this.layout[i][j] == KOS){					
					this.keeperXpos = i;
					this.keeperYpos = j;
				break;
				}
			}
		}
	}

	private boolean isWinner(){
		for(int i = 0; i<10; i++){
			for(int j = 0; j<10; j++){
				if (this.layout[i][j] == BOX)
					return false;
			}
		}
		return true;
	}

	public GameArea getGameArea(){
		return this.gameArea;
	}

}