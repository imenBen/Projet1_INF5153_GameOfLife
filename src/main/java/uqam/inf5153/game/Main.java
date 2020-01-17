package uqam.inf5153.game;

/**
 * Crée par Imen Benzarti le 13/01/2020
 */
public class Main {
	/*
	 * arg[0] : nombre de tours 
	 * arg[1] : nombre de lignes
	 * arg[2] : nombre de colonnes 
	 */
	public static void main(String[] args) {
		// définir le nombre de tours
		int nbTours = Integer.parseInt(args[0]);
		int rowCount =  Integer.parseInt(args[1]);
		int columnCount = Integer.parseInt(args[2]);
		// instancier un jeu
		GameOfLife game = new GameOfLife(rowCount, columnCount);
		// intialiser la grille avec des cellules mortes
		game.initializeGridWithDeadCells();
		// ajouter les cellules vivantes aléatoirement
		game.setLivingCell();
		// Jouer les trours
		int actualGeneration = 0;
		while (actualGeneration < nbTours) {
			
			System.out.println("Play tours nb : " + actualGeneration);
			game.displayGrid();
			//exécute toutes les règles possibles sur les cellules 
			game.computeNextGeneration();
			actualGeneration = actualGeneration + 1;

		}
	}

}
