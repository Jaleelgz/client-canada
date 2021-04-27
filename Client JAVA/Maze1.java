import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * Creates a random maze, then solves it by finding a path from the
 * upper left corner to the lower right corner.
 */
 
 class node {
	public int x, y;

	node() {
	}

	node(int a, int b) {
		x = a;
		y = b;
	}

	void set(int a, int b) {
		x = a;
		y = b;
	}
}

class CreateMaze {
	// Initialize a map, all roads are blocked by default
	// The row row represents the number of rows of the blank grid at the beginning, and there are walls between the grids, so the final two-dimensional array size is actually (2row+1) * (2colum+1)

	private int row;
	private int column;

	public int[][] map;// Array to store the maze
	// private Vector[] Pos;
	private int r;
	private int c;

	CreateMaze(int r0, int c0) {
		row = r0;
		column = c0;
		r = 2 * row + 1;
		c = 2 * column + 1;

		map = new int[r][c];
	}

	public void Init() {

		for (int i = 0; i < r; i++) // Set all grids as walls
			for (int j = 0; j < c; j++)
				map[i][j] = 0;// 0 is the wall 1 is the road

		// The middle grid is set to 1
		for (int i = 0; i < row; i++)
			for (int j = 0; j < column; j++)
				map[2 * i + 1][2 * j + 1] = 1;// 0 is the wall 1 is the road

		// Primm algorithm
		accLabPrime();
	}

	// Process the array through Prim algorithm to generate the final maze
	// Idea:
	// Randomly find the nearest point to visit (each point is only visited once, until all the roads are visited),
	//A way to visit all points (unordered) will be generated. When the next point is randomly found, the wall between the two adjacent grids will be opened

	public void accLabPrime() {
		// acc stores the visited queue, noacc stores no access queue
		int[] acc, noacc;
		int count = row * column;
		int accsize = 0;// Record the number of points visited

		acc = new int[count];
		noacc = new int[count];

		// Offset in each direction on row Offset in each direction of column 0 left 1 right 3 up 2 down

		int[] offR = { -1, 1, 0, 0 };
		int[] offC = { 0, 0, 1, -1 };

		// Offset in four directions, left, right, up, down
		int[] offS = { -1, 1, row, -row }; // Move up and down is to change one line
		// In the initialization acc, 0 means not visited, and 0 in noacc means not visited
		for (int i = 0; i < count; i++) {
			acc[i] = 0;
			noacc[i] = 0;
		}

		// starting point
		Random rd = new Random();
		acc[0] = rd.nextInt(count);// starting point

		int pos = acc[0];
		// Deposit the first point
		noacc[pos] = 1;
		while (accsize < count) {
			// Take out the current point
			int x = pos % row;
			int y = pos / row;// the coordinates of the point
			int offpos = -1;// Used to record the offset
			int w = 0;
			// try in all four directions until you get it through
			while (++w < 5) {
				// Random access to the nearest point
				int point = rd.nextInt(4); // 0-3
				int repos;
				int move_x, move_y;
				// Calculate the moving direction
				repos = pos + offS[point];// Subscript after moving
				move_x = x + offR[point];// Position after moving
				move_y = y + offC[point];
				// Determine whether the movement is legal
				if (move_y >= 0 && move_x >= 0 && move_x < row && move_y < column && repos >= 0 && repos < count
						&& noacc[repos] != 1) {
					noacc[repos] = 1;// Mark the point as visited
					acc[++accsize] = repos;// ++accsize represents the number of visited points, and repos represents the subscript of the point
					pos = repos;// Use this point as the starting point
					offpos = point;
					// Put 1 in the middle of the adjacent grid

					map[2 * x + 1 + offR[point]][2 * y + 1 + offC[point]] = 1;
					break;
				} else {
					if (accsize == count - 1)
						return;
					continue;
				}
			}
			if (offpos < 0) {// I can’t find a way around, find a new starting point from the path I walked
				pos = acc[rd.nextInt(accsize + 1)];}
		}
	}
}

 
 
public class Maze1 extends Application implements Runnable {

    public static void main(String[] args) {
        launch(args);
    }
    //------------------------------------------------------------------------

    int[][] maze;   // Description of state of maze.  The value of maze[i][j]
    // is one of the constants wallCode, emptyCode,
    // or visitedCode.  (Value can also be negative, temporarily,
    // inside createMaze().)
    //    A maze is made up of walls and empty space.  maze[i][j]
    // is either part of a wall or part of empty space .
    // cell which is not wall is represented in white.
    //path: if it is part of the current path through the maze,
    // visited: if it has already been explored without finding a solution,
    //  and by emptyCode if it has not yet been explored.

    final static int background = 0;
    final static int wall = 1;
    final static int path = 2;
    final static int emptyCode = 3;
    final static int visited = 4;
    final static int Point = 5;

    Canvas canvas;      // the canvas where the maze is drawn and which fills the whole window
    GraphicsContext g;  // graphics context for drawing on the canvas

    Color[] color;          // colors associated with the preceding 5 constants;
    /*have to put an option to change size of maze
    currently i kept it fixed.
     */
    int rows = 31;          // number of rows of cells in maze, including a wall around edges(has to remain odd number)
    int column = 31;       // number of columns of cells in maze, including a wall around edges(has to remain odd number)
    int blockSize = 15;     // size of each cell
    int SolveSpeed = 1;    // short delay between steps in making and solving maze


    public void start(Stage stage) {
        color = new Color[] {
                Color.rgb(150,0,0),//walls colour
                Color.rgb(255,255,255),
                Color.rgb(255,255,255),//pathCode colour
                Color.WHITE,
                Color.rgb(255,255,255),//already visited code
                Color.rgb(100,200,100),//End Point
        };
        maze = new int[rows][column];
        canvas = new Canvas(column*blockSize, rows*blockSize);
        g = canvas.getGraphicsContext2D();
        g.setFill(color[background]);
        g.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Maze Runner");
        stage.show();
        Thread runner = new Thread(this);
        runner.setDaemon(true);  // so thread won't stop program from ending
        runner.start();
        
        
        scene.setOnKeyPressed(k -> {
			KeyCode code = k.getCode();
			int tx = recX, ty = recY;
			if (code.equals(KeyCode.L) && autoPath == false) { // L button pressed
				tx -= Range;
			} else if (code.equals(KeyCode.R) && autoPath == false) {// R button pressed
				tx += Range;
			} else if (code.equals(KeyCode.U) && autoPath == false) {// U button pressed
				ty -= Range;
			} else if (code.equals(KeyCode.D) && autoPath == false) {// D button pressed
				ty += Range;
			} else if (code.equals(KeyCode.SPACE)) {
				if (autoPath == false) {
					autoPath = true;
					node e = new node();
					e.set(recX, recY);
					autoMove(e);
				}
			}
			if (inside(tx, ty) && maze[tx][ty] == 1 && autoPath == false) {
				// System.out.println(recX+" "+recY+" "+tx + " " + ty);
				move(tx, ty);
				recX = tx;
				recY = ty;
			} else if (recX == VSize - Range * 2 && recY == VSize - Range * 2) {// Determine whether out of bounds and hit the wall
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.titleProperty().set("information");
				alert.headerTextProperty().set("You WIN!!!!");
				alert.showAndWait();
				try {
					start(stage);
				} catch (Exception e) {
					// TODO automatically generated catch block
					e.printStackTrace();
				}
				move(Range, Range);
				recX = Range;
				recY = Range;
			}
		});
    }

    void drawSquare( int row, int column, int colorCode ) {
        // Fill specified square of the grid with the
        // color specified by colorCode, which has to be
        // one of the constants emptyCode, wallCode, etc.
        Platform.runLater( () -> {
            g.setFill( color[colorCode] );
            int x = blockSize * column;
            int y = blockSize * row;
            g.fillRect(x,y,blockSize,blockSize);
        });
    }

    public void run() {
        // Run method for thread  makes a maze and then solves it and saves the path used to solve maze.
        /*
        we can always put a do-while loop to ask player if he wants to play again or exit.
         */

            try { Thread.sleep(1000); } // wait a bit before starting
            catch (InterruptedException e) { }
            makeMaze();
            solveMaze(1,1);
        drawSquare(rows-2,column-2,Point);
        drawSquare(1,1,Point);
            if(solveMaze(1,1)){//need to make another class that makes player run the game and give hints as well
                //player turn
            }


        }


    void makeMaze() {
        // Create a random maze.  The strategy is to start with
        // a grid of disconnected "rooms" separated by walls,
        // then look at each of the separating walls, in a random
        // order.  If tearing down a wall would not create a loop
        // in the maze, then tear it down.  Otherwise, leave it in place.
        int i,j;
        int emptyRoom = 0; // number of rooms
        int walls = 0;  // number of walls
        int[] wallRow = new int[(rows*column)/2];  // position of walls between rooms
        int[] wallCol = new int[(rows*column)/2];
        for (i = 0; i<rows; i++)  // start with everything being a wall
            for (j = 0; j < column; j++)
                maze[i][j] = wall;
        for (i = 1; i<rows-1; i += 2)  { // make a grid of empty rooms
            for (j = 1; j<column-1; j += 2) {
                emptyRoom++;
                maze[i][j] = -emptyRoom;  // each room is represented by a different negative number
                if (i < rows-2) {  // record info about wall below this room
                    wallRow[walls] = i+1;
                    wallCol[walls] = j;
                    walls++;
                }
                if (j < column-2) {  // record info about wall to right of this room
                    wallRow[walls] = i;
                    wallCol[walls] = j+1;
                    walls++;
                }
            }
        }
        Platform.runLater( () -> {
            g.setFill( color[emptyCode] );
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < column; c++) {
                    if (maze[r][c] < 0)
                        g.fillRect( c*blockSize, r*blockSize, blockSize, blockSize );
                }
            }
        });
        synchronized(this) {
            try { wait(1000); }
            catch (InterruptedException e) { }
        }
        int r;
        for (i=walls-1; i>0; i--) {
            r = (int)(Math.random() * i);  // choose a wall randomly and maybe tear it down
            tearDown(wallRow[r],wallCol[r]);
            wallRow[r] = wallRow[i];
            wallCol[r] = wallCol[i];
        }
        for (i=1; i<rows-1; i++)  // replace negative values in maze[][] with emptyCode
            for (j=1; j<column-1; j++)
                if (maze[i][j] < 0)
                    maze[i][j] = emptyCode;
        synchronized(this) {
            try { wait(1000); }
            catch (InterruptedException ignored) { }
        }
    }

    void tearDown(int row, int col) {
        // Tear down a wall, unless doing so will form a loop.  Tearing down a wall
        // joins two "rooms" into one "room".  (Rooms begin to look like corridors
        // as they grow.)  When a wall is torn down, the room codes on one side are
        // converted to match those on the other side, so all the cells in a room
        // have the same code.  Note that if the room codes on both sides of a
        // wall already have the same code, then tearing down that wall would
        // create a loop, so the wall is left in place.
        if (row % 2 == 1 && maze[row][col-1] != maze[row][col+1]) {
            // row is odd; wall separates rooms horizontally
            fill(row, col-1, maze[row][col-1], maze[row][col+1]);
            maze[row][col] = maze[row][col+1];
            drawSquare(row,col,emptyCode);
            synchronized(this) {
                try { wait(SolveSpeed); }
                catch (InterruptedException e) { }
            }
        }
        else if (row % 2 == 0 && maze[row-1][col] != maze[row+1][col]) {
            // row is even; wall separates rooms vertically
            fill(row-1, col, maze[row-1][col], maze[row+1][col]);
            maze[row][col] = maze[row+1][col];
            drawSquare(row,col,emptyCode);
            synchronized(this) {
                try { wait(SolveSpeed); }
                catch (InterruptedException e) { }
            }
        }
    }

    void fill(int row, int col, int replace, int replaceWith) {
        // called by tearDown() to change "room codes".
        if (maze[row][col] == replace) {
            maze[row][col] = replaceWith;
            fill(row+1,col,replace,replaceWith);
            fill(row-1,col,replace,replaceWith);
            fill(row,col+1,replace,replaceWith);
            fill(row,col-1,replace,replaceWith);
        }
    }

    boolean solveMaze(int row, int col) {
        // Try to solve the maze by continuing current path from position
        // (row,col).  Return true if a solution is found.  The maze is
        // considered to be solved if the path reaches the lower right cell.
        if (maze[row][col] == emptyCode) {
            maze[row][col] = path;      // add this cell to the path
            drawSquare(row,col,path);
            if (row == rows-2 && col == column-2)
                return true;  // path has reached goal

            try { Thread.sleep(SolveSpeed); }
            catch (InterruptedException ignored) { }
            if ( solveMaze(row-1,col)  ||     // try to solve maze by extending path
                    solveMaze(row,col-1)  ||     //    in each possible direction
                    solveMaze(row+1,col)  ||
                    solveMaze(row,col+1) )
                return true;
            // maze can't be solved from this cell, so backtrack out of the cell
            maze[row][col] = visited;   // mark cell as having been visited
            drawSquare(row,col,visited);
            synchronized(this) {
                try { wait(SolveSpeed); }
                catch (InterruptedException ignored) { }
            }

        }

        return false;
    }

}
