
public class LinkedGrid<T> {

	private int width;
	private int height;
	private LinearNode<T>[] grid;
	
	public LinkedGrid(int width, int height) {

		
		this.grid = (LinearNode<T>[])(new LinearNode[width]);
		this.width = width;
		this.height = height;
		
		
		
		for(int i = 0; i < width; i++) {
			
			//this.grid[i] = new LinearNode<T>();
			
			//LinearNode temp = new LinearNode<T>();
			//this.grid[i].setNext(temp);
			
			// make a new node 
			LinearNode<T> newNode = new LinearNode<T>();
			
			// then set newNode as grid[i]
			this.grid[i] = newNode;
			
			LinearNode<T> temp = this.grid[i];
			
			//this.grid[i] = new LinearNode<T>();
			//LinearNode temp = this.grid[i];
			
			for(int j = 1; j < height; j++) {
				LinearNode<T> t = new LinearNode<T>();
				temp.setNext(t);
				temp = t;
			}
		}
		
	}
	public void displayMyGrid() {
		String s = "GRID: \n";
		LinearNode<T>[] tempGrid = this.grid;
		System.out.println("width: " + width);
		for (int i=0;i<width;i++) {
			s += i + "i ";
			LinearNode temp = tempGrid[i];
			while(temp!=null) {
				s+=temp.getElement() +" ";
				temp = temp.getNext();
			}
			s+= "\n";
		}
		System.out.println(s);
	}

	public void setElement(int col, int row, T data) throws LinkedListException{
		
		if(col >= this.width || col < 0 || row >= this.height || row < 0) {
			throw new LinkedListException ("Column/row out of bound");
		}
		
		LinearNode<T> head = this.grid[col];
		//System.out.println("Column: " + col + " Row: " + row);
		for(int i = 0; i < row; i++) {
			head = head.getNext();
			
		}
		head.setElement(data);
		//System.out.println(head.getElement());
		
	}
	
	public T getElement(int col, int row) {
		if(col >= this.width || col < 0 || row >= this.height || row < 0) {
			throw new LinkedListException ("Column/row out of bound");
		}
		
		LinearNode<T> head = this.grid[col];
		//System.out.println("Column: " + col + " Row: " + row);
		for(int i = 0; i < row; i++) {
			head = head.getNext();
			
		}
		return head.getElement();
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public String toString1() {
		String s = "";
		// include 2 spaces and new line
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				s += getElement(j,i) + "  ";
			}
			s += "\n";
		}
		
		
		return s;
	}
	
	public String toStringFailed() {
		String s = "";
		
		System.out.println("height: " + height + " width: " + width);
		for(int j = 0; j < height; j++) {
			for(int i = 0; i < width; i++) {
				
			s += this.grid[i].getElement() + "  ";
			this.grid[i] = this.grid[i].getNext();
			
			}
			
			s+= "\n";
		}
		
		return s;
	}
	public String toStringNotWorking() {
		LinearNode<T>[]tempGrid = new LinearNode[width];
		tempGrid = this.grid;
		String s = "";
		for(int j = 0; j < height; j++) {
			
			for(int i = 0; i < width; i++) {
				LinearNode<T> temp = tempGrid[i];
				for (int f = 0; f < j; f++) {
					if(temp != null) {
						temp = temp.getNext();
					}
					
				}
				if(temp != null) {
					s+=temp.getElement() + "  ";
				}
				else {
				}
			}
			//s+="\n";
			
			
			
			
			
			s+= "\n";
		}
		return s;
	}
	//chat.openai.com
	public String toString() {
	    String s = "";
	    for (int j = 0; j < height; j++) {
	        for (int i = 0; i < width; i++) {
	            LinearNode<T> temp = grid[i];
	            for (int k = 0; k < j; k++) {
	                if (temp != null) {
	                    temp = temp.getNext();
	                }
	            }
	            if (temp != null) {
	                s += temp.getElement() + "  ";
	            } else {
	                s += "null  ";
	            }
	        }
	        s += "\n";
	    }
	    return s;
	}
	
	
	/*
	 * notes to self:
	 * col and width starts at 0
	 * 
	 * displayMyGrid is flipping the output
	 */
	public static void main(String[] args) {
		LinkedGrid<Integer> intGrid = new LinkedGrid<Integer>(5, 3);
		intGrid.setElement(0, 0, 21);
		intGrid.setElement(1, 0, 34);
		intGrid.setElement(2, 0, 68);
		intGrid.setElement(3, 0, 37);
		intGrid.setElement(4, 0, 17);
		intGrid.setElement(0, 1, 56);
		intGrid.setElement(1, 1, 88);
		intGrid.setElement(2, 1, 30);
		intGrid.setElement(3, 1, 12);
		intGrid.setElement(4, 1, 72);
		intGrid.setElement(0, 2, 19);
		intGrid.setElement(1, 2, 33);
		intGrid.setElement(2, 2, 16);
		intGrid.setElement(3, 2, 92);
		intGrid.setElement(4, 2, 24);
		
		System.out.println(intGrid.toString1());
		System.out.println("--------------------");
		//System.out.println(intGrid.toStringFailed());
		//System.out.println("--------------------");
		System.out.println(intGrid.toStringNotWorking());
		System.out.println(intGrid.toStringNotWorking());
		//System.out.println(intGrid.toString1());
		//System.out.println(intGrid);
		//System.out.println(intGrid);
		
	}

}


