import java.util.Arrays;
import java.util.Random;

/**
 * Created by fabricejeannet on 16/09/2014. 
 * https://github.com/fabricejeannet/kataGameOfLife
 * Modifié par Imen Benzarti le
 * 13/01/2020
 */
public class GameOfLife {
	// Le nombre minimum de cellules vivantes dans la grille
	public static final int MIN_Living = 1;
	// nombre de colonnes et lignes
	private int rowCount;
	private int columnCount;
	// la grille représenté par un tableau
	public int[][] grid;
	// La représentation des cellules vivantes et mortes par une constante
	public final static int DEAD_CELL = 0;
	public final static int LIVING_CELL = 1;

	//constructeur 
	public GameOfLife(int rowCount, int columnCount) {
		grid = new int[rowCount][columnCount];
		this.rowCount = grid.length;
		this.columnCount = grid[0].length;
		initializeGridWithDeadCells();
	}
	//initialiser la grille avec des cellules mortes
	public void initializeGridWithDeadCells() {
		for (int y = 0; y < this.rowCount; y++) {
			Arrays.fill(grid[y], DEAD_CELL);
		}
	}
	//ajouter des cellules vivantes aléatoirement
	public void setLivingCell() {
		int nbLivingCells = MIN_Living + (int) (Math.random() * (((columnCount * rowCount) - MIN_Living) + 1));
		for (int i = 1; i <= nbLivingCells; i++) {
			int row = (int) (Math.random() * rowCount);
			int column = (int) (Math.random() * columnCount);
			grid[row][column] = LIVING_CELL;
		}
	}

	public int countlivingNeighbours(int row, int column) {

		int[][] cellsToCheck = { { row - 1, column - 1 }, { row - 1, column }, { row - 1, column + 1 },
				{ row, column + 1 }, { row + 1, column + 1 }, { row + 1, column }, { row + 1, column - 1 },
				{ row, column - 1 }, };

		int livingNeighbours = 0;

		for (int i = 0; i < cellsToCheck.length; i++) {

			int rowToCheck = cellsToCheck[i][0];
			int colTocheck = cellsToCheck[i][1];

			if (isInTheGrid(rowToCheck, colTocheck) && isAlive(rowToCheck, colTocheck)) {
				livingNeighbours++;
			}

		}

		return livingNeighbours;
	}

	private boolean isInTheGrid(int row, int col) {
		return row >= 0 && col >= 0 && row < rowCount && col < columnCount;
	}
	//jouer un tour
	public void computeNextGeneration() {

		int[][] nextGenerationGrid = new int[rowCount][columnCount];

		for (int y = 0; y < rowCount; y++) {

			for (int x = 0; x < columnCount; x++) {

				if (thisCellIsAliveAndHasLessThanTwoLivingNeighbours(y, x)) {
					nextGenerationGrid[y][x] = DEAD_CELL;
				} else if (thisCellIsAliveAndHasTwoOrThreeLivingNeighbours(y, x)) {
					nextGenerationGrid[y][x] = LIVING_CELL;
				} else if (thisCellIsAliveAndHasMoreThanThreeLivingNeighbours(y, x)) {
					nextGenerationGrid[y][x] = DEAD_CELL;
				} else if (thisCellIsDeadAndHasThreeLivingNeighbours(y, x)) {
					nextGenerationGrid[y][x] = LIVING_CELL;
				} else {
					nextGenerationGrid[y][x] = grid[y][x];
				}
			}

		}

		grid = nextGenerationGrid.clone();
	}
	/*
	 * Les règles 
	 */
	private boolean thisCellIsDeadAndHasThreeLivingNeighbours(int row, int column) {
		int livingNeighbours = countlivingNeighbours(row, column);
		return isDead(row, column) && livingNeighbours == 3;
	}

	private boolean thisCellIsAliveAndHasMoreThanThreeLivingNeighbours(int row, int column) {
		int livingNeighbours = countlivingNeighbours(row, column);
		return isAlive(row, column) && livingNeighbours > 3;
	}

	private boolean thisCellIsAliveAndHasTwoOrThreeLivingNeighbours(int row, int column) {
		int livingNeighbours = countlivingNeighbours(row, column);
		return isAlive(row, column) && (livingNeighbours == 2 || livingNeighbours == 3);
	}

	private boolean thisCellIsAliveAndHasLessThanTwoLivingNeighbours(int row, int column) {
		int neighboursCount = countlivingNeighbours(row, column);
		return isAlive(row, column) && neighboursCount < 2;
	}

	public boolean isAlive(int row, int column) {
		return grid[row][column] == LIVING_CELL;
	}

	public boolean isDead(int row, int column) {
		return grid[row][column] == DEAD_CELL;
	}
	
	//afficher grille
	public void displayGrid() {
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.print("\n");
		}
	}
}
