
public class Game {

	private LinkedGrid<Character> board;
	private LinkedGrid<GUICell> cells;
	public static int width;
	public static int height;
	private boolean isPlaying;
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
		System.out.println(cells);
		
		BombRandomizer.placeBombs(board, fixedRandom, seed);
		
		gui = new GUI(this,cells);
		
		
		
		
		System.out.println(board);
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				//System.out.println(board.getElement(i, j));
				if (board.getElement(i, j) == 'x') {
					GUICell cell = cells.getElement(i, j);
					cell.setNumber(9);
					this.cells.setElement(i, j, cell);
				} else {
					GUICell cell = cells.getElement(i, j);
					cell.setNumber(7);
					this.cells.setElement(i, j, cell);
				}
				
				
			}	
		}
		
		System.out.println(this.cells);
		
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
		return 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Game game1 = new Game(4,4,true,2);
		
	}

}
