package services;

/**
 * Service qui simule un Kidnappeur. Herite de JoueurService
 * @author Barbier & Deluze.
 *
 */
public interface KidnappeurService extends JoueurService {
	
	//////---------------------------------------- CONSTUCTORS
	public void init(int ligne, int colonne, TerrainService t);
}
