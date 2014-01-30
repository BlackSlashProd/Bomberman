package services;

/**
 * Service qui simule un terrain de jeu
 * @author Barbier & Deluze.
 *
 */
public interface TerrainService {

	//////---------------------------------------- OBSERVATORS 
	// Renvoie le nombre de colonnes du terrain.
	public int getNbColonnes();
	
	// Renvoie le nombre de lignes du terrain.
	public int getNbLignes();
	
	// Renvoie le bloc aux coordonnes ligne,colonne
	public BlocService getBloc(int ligne, int colonne);
	
	//////---------------------------------------- CONSTUCTORS
	public void init(int lignes, int colonnes);
}
