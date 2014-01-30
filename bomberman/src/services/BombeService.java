package services;

/**
 * Service qui simule une bombe sur le terrain de jeu.
 * @author Barbier & Deluze.
 *
 */
public interface BombeService {

	//////---------------------------------------- OBSERVATORS 
	// Renvoie le temps restant du timer.
	public int getTimer();
	
	// Renvoie la ligne sur laquelle se trouve la bombe.
	public int getLigne();
	
	// Renvoie la colonne sur laquelle se trouve la bombe.
	public int getColonne();
	
	// Renvoie l'amplitude de la bombe.
	public int getAmplitude();
	
	// Renvoie le numero de la bombe
	public int getNum();
	
	// Renvoie le proprietaire de la bombe
	public JoueurService getProprietaire();
	
	//////---------------------------------------- CONSTUCTORS
	public void init(JoueurService joueur, int numero, int ligne, int colonne, int amplitude, TerrainService terrain);
	
	//////---------------------------------------- OPERATORS
	// Fixe le nombre de tours de jeu restants avant l'explosion de la bombe.
	public void setTimer(int timer);
}
