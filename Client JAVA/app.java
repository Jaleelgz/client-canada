import java.util.Random;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

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

class CreateMap {
	// Initialize a map, all roads are blocked by default
	// The row row represents the number of rows of the blank grid at the beginning, and there are walls between the grids, so the final two-dimensional array size is actually (2row+1) * (2colum+1)

	private int row;
	private int column;

	public int[][] map;// Array to store the maze
	// private Vector[] Pos;
	private int r;
	private int c;

	CreateMap(int r0, int c0) {
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



public class Maze extends Application {
	public int Size = 15;// Effective map size, used for Prim algorithm to generate map
	public static final int Range = 30;// cell side length
	public int VSize = (Size * 2 + 1) * Range;// Actual map size
	public int maze[][] = new int[VSize][VSize];// map
	public int vis[][] = new int[VSize][VSize];// Visited path
	public node f[][] = new node[VSize][VSize];
	public int[][] dir = { { -Range, 0 }, { Range, 0 }, { 0, -Range }, { 0, Range } };// moving direction
	public CreateMap c = new CreateMap(Size, Size);
	Rectangle rec = new Rectangle(Range, Range, Range, Range);
	private int recX = 30, recY = 30;
	private boolean autoPath = false;// Whether to enable automatic solution

	public void start(Stage stage) throws Exception {

		CreateMap();
		Pane pane = Init();// Generate maze platform
		Scene scene = new Scene(pane, VSize, VSize);

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
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.setTitle("maze");
		stage.show();

	}

	public void move(int tx, int ty) {
		SequentialTransition link = new SequentialTransition();// Animation list
		link.setNode(rec);
		TranslateTransition tt = new TranslateTransition();

		tt.setFromX(recX - 30);
		tt.setToX(tx - 30);
		tt.setFromY(recY - 30);
		tt.setToY(ty - 30);

		// System.out.println(recX+" "+recY+" "+tx+" "+ty);
		link.getChildren().add(tt);
		link.play();
	}

	public void CreateMap() {
		c.Init();// Generate maze
		for (int i = 0; i < VSize; i += Range) {
			for (int j = 0; j < VSize; j += Range) {
				maze[i][j] = c.map[i / Range][j / Range];
			}
		} // Maze mapping
	}

	public Pane Init() {
		
		Pane pane = new Pane();
		for (int i = 0; i < VSize; i += Range) {
			for (int j = 0; j < VSize; j += Range) {
				Rectangle r = new Rectangle(i,j, Range, Range);
				if (maze[i][j] == 0) {
					r.setFill(Color.RED);
				} else if (maze[i][j] == 1) {
					r.setFill(Color.WHITE);
				}
				if (i == VSize - Range && j == VSize - Range * 2) {
					r.setFill(Color.RED);
				}
				pane.getChildren().add(r);
			}
		}
		rec.setFill(Color.BLACK);
		pane.getChildren().add(rec);// Show target block
		return pane;
	}

	public void autoMove(node e) {
		SequentialTransition link = new SequentialTransition();// Animation list
		link.setNode(rec);
		Queue<node> queue = new ArrayBlockingQueue<node>(1000);
		int flag = 0;
		System.out.println(e.x + " " + e.y);
		queue.add(e);
		vis[e.x][e.y] = 1;// visited
		while (flag == 0) {// Breadth first traversal, find the shortest path
			node now = queue.remove();
			for (int i = 0; i < 4; i++) {
				int fx = now.x + dir[i][0];
				int fy = now.y + dir[i][1];
				if ((inside(fx, fy) && (vis[fx][fy] == 0) && maze[fx][fy] == 1)) {
					vis[fx][fy] = 1;
					f[fx][fy] = new node(now.x, now.y);
					queue.add(new node(fx, fy));
				}
				if (fx == VSize - Range * 2 && fy == VSize - Range * 2) {// start backtracking when one way reaches the end

					node ans[] = new node[1000];
					int cnt = 0;
					int t1, t2;
					ans[cnt] = new node(fx, fy);
					while (f[fx][fy].x != e.x || f[fx][fy].y != e.y) {// Trace back according to the coordinates of the previous point recorded by the point to get the shortest path to the point.
						t1 = fx;
						t2 = fy;
						cnt++;
						ans[cnt] = new node(f[fx][fy].x, f[fx][fy].y);
						fx = f[t1][t2].x;
						fy = f[t1][t2].y;
					}

					ans[++cnt] = new node(0, 0);

					for (int l = cnt - 1; l > 0; l--) {
						// move(ans[l].x, ans[l].y);
						// System.out.println(recX + " " + recY + " " + ans[l].x + " " + ans[l].y);
						// recX = ans[l].x;
						// recY = ans[l].y;
						TranslateTransition tt = new TranslateTransition();
						tt.setFromX(ans[l].x - 30);
						tt.setToX(ans[l - 1].x - 30);
						tt.setFromY(ans[l].y - 30);
						tt.setToY(ans[l - 1].y - 30);
						link.getChildren().add(tt);
					}
					flag = 1;
					break;

				}
			}
		}
		link.play();

	}

	boolean inside(int fx, int fy) {
		return (fx >= Range && fx <= VSize - Range && fy >= Range && fy <= VSize - Range);
	}
	public static void main(String[] args){
		  Application.launch();
		 }
}

