import java.awt.Color;

public class Game {

	private LinkedGrid<Character> board;
	private LinkedGrid<GUICell> cells;
	public static int width;
	public static int height;
	protected boolean isPlaying;
	private GUI gui;
	
	public Game(int width, int height, boolean fixedRandom, int seed) {
		this.width = width;
		this.height = height;
		this.board = new LinkedGrid<Character>(width, height);
		this.cells = new LinkedGrid<GUICell>(width,height);
		for(int i = 0; i < width; i++) {
			for(int j = 0; j <height; j++) {
				board.setElement(i, j, '_');
				GUICell newCell = new GUICell(i,j);
				this.cells.setElement(i, j, newCell);
			}
		}
		
		
		BombRandomizer.placeBombs(board, fixedRandom, seed);
		determineNumbers();
		gui = new GUI(this,cells);
		this.isPlaying = true;
		//System.out.println(" Here... " + countBombs(0,3));
		//System.out.println("-> " + this.cells.getElement(0, 0).getNumber());
		//System.out.println(board.getElement(0, 0).getClass().getName());
	}
	
	public Game(LinkedGrid<Character> board) {
		
		this.width = board.getWidth();
		this.height = board.getHeight();
		this.board = board;
		this.cells = new LinkedGrid<GUICell>(width, height);
		
		//System.out.println(board);
		determineNumbers();
		this.gui = new GUI(this, cells);
		this.isPlaying = true;
	}
	
	
	public void determineNumbers() {
		//System.out.println("width " + width);
		//System.out.println("height " + height);
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				
				//cells that contain bombs must have a number of -1
				if (board.getElement(i, j) == 'x') {
					GUICell newCell = new GUICell(i,j);
					newCell.setNumber(-1);
					this.cells.setElement(i, j, newCell); 
					
					//GUICell cell = cells.getElement(i, j);
					
					//cell.setNumber(-1);
					//this.cells.setElement(i, j, cell);
					
				} 
				// all other cells must have number from 0 to 8,
				// number of bombs around the cell
				else {
					GUICell cell = new GUICell(i,j);
					
					cell.setNumber(countBombs(i, j));
					this.cells.setElement(i, j, cell);
				}
				//System.out.println(cells.getElement(i, j) + " ***** " + i + " " + j);
				
			}	
		}
		
		
	}

	// 0 0
	public int countBombs(int x, int y) {
		// call getElement from LinkedGrid
		int counter = 0;
		//System.out.println("w " + width + " h " + height);
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				int tempX = x + i;
				int tempY = y + j;
				//System.out.println("X: " + tempX + " Y: " + tempY);
				if (tempX < 0 || tempY < 0 || tempX >= width || tempY >= height) {
					//System.out.println("Finding: " + tempX + " findingY: " + tempY);
					continue;
				}
				else {
					Character temp = board.getElement(tempX, tempY);
					if (temp == 'x') {
						//System.out.println("found");
						counter += 1;
					}
				}
				

			}
		}
		
		return counter;
	}
	
	
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public LinkedGrid<GUICell> getCells() {
		return this.cells;
	}
	public int processClick(int col, int row) {
		if (this.isPlaying == false) {
			return -10;
		}
		
		
		if (cells.getElement(col, row).getNumber() == -1) {
			cells.getElement(col, row).setBackground(Color.red);
			cells.getElement(col, row).reveal();
			isPlaying = false;
			return -1;
		}
		if ((cells.getElement(col, row).getNumber() == -1) && isPlaying == false) {
			return -10;
		}
		if (cells.getElement(col, row).getNumber() == 0) {
			return recClear(col,row);
		}
		if (cells.getElement(col, row).getNumber() <= 8 ){
			if (cells.getElement(col,row).isRevealed() == true){
				return 0;
			}
			else {
				cells.getElement(col,row).reveal();
				cells.getElement(col, row).setBackground(Color.white);
				return 1;
			}
				
		}
		return 0;
	}
	private int recClear(int col, int row) {
		//System.out.println(cells);
		//System.out.println(col + " " + row);
		//System.out.println("numba " + cells.getElement(col, row).getNumber());
		
		if(col >= this.width || col < 0 || row >= this.height || row < 0) {
			return 0;
		}
		if (cells.getElement(col,row).isRevealed() == true){
			return 0;
		}
		if (cells.getElement(col, row).getNumber() == -1) {
			return 0;
		}
		else if (cells.getElement(col, row).getNumber() > 0) {
			cells.getElement(col, row).reveal();
			
			cells.getElement(col,row).setBackground(Color.white);
			
			return 1;
		}
		else {
			cells.getElement(col, row).reveal();
			cells.getElement(col,row).setBackground(Color.white);
			int result = 1;
			result += recClear(col-1,row+1);
			result += recClear(col-1,row);
			result += recClear(col-1,row-1);
			result += recClear(col,row-1);
			result += recClear(col,row+1);
			result += recClear(col+1,row-1);
			result += recClear(col+1,row);
			result += recClear(col+1,row+1);
			return result;
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Game game1 = new Game(5,4,false, 999);
		//Game game1 = new Game(GameLayout());
		//System.out.println(game1.cells);
		
	}
	
	public static LinkedGrid<Character> GameLayout () {
		LinkedGrid<Character> board = new LinkedGrid<Character>(7, 7);

		// Default board.
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				board.setElement(i, j, '_');
			}
		}
		// Add bombs.
		board.setElement(3, 5, 'x');
		board.setElement(3, 6, 'x');
		board.setElement(2, 5, 'x');
		board.setElement(1, 4, 'x');
		board.setElement(6, 3, 'x');
		board.setElement(6, 4, 'x');
		
		board.setElement(3, 1, 'x');
		board.setElement(4, 1, 'x');
		board.setElement(5, 1, 'x');
		board.setElement(5, 2, 'x');
		board.setElement(5, 3, 'x');
		board.setElement(4, 3, 'x');
		board.setElement(3, 3, 'x');
		board.setElement(3, 2, 'x');
		
		return board;
	}

}
