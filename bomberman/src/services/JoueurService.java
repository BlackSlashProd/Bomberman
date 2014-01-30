package services;

import utils.BlocReward;

/**
 * Service qui simule un Joueur
 * @author Barbier & Deluze.
 *
 */
public interface JoueurService {

	//////---------------------------------------- OBSERVATORS
	// renvoie la ligne ou se trouve le joueur
	public int getLigne();
	
	// renvoie la colonne ou se trouve le joueur
	public int getColonne();
	
	// renvoie la force du joueur (= amplitude des bombes)
	public int getForce();
	
	// le joueur peut il passer a travers les murs ?
	public boolean passeMuraille();
	
	// le joueur peut il passer a travers les bombes ?
	public boolean passeBombe();
	
	// renvoie le nombre de pas de jeu restant durant lesquels le joueur est insensible aux explosions
	public int getInvulnerableStep();
	
	// decremente de 1 le nombre de tours restant durant lesquels le joueur est insensible aux explosions
	public void decrementeInvulnerabilite();
	
	// renvoie le nombre de bombes que le joueur peut poser
	public int getNbBombePosable();
	
	// le joueur est il en vie ?
	public boolean isVivant();
	
	//////---------------------------------------- CONSTUCTORS
	public void init(int ligne, int colonne, TerrainService t);
	
	//////---------------------------------------- OPERATORS
	// modifie la ligne ou se trouve le joueur
	public void setLigne(int ligne, TerrainService t);
	
	// modifie la colonne ou se trouve le joueur
	public void setColonne(int colonne, TerrainService t);
	
	// modifie la force du joueur
	public void setForce(int force);
	
	// modifie la capacite du joueur a passer a travers les murs
	public void setPasseMuraille(boolean b);
	
	// modifie la capacite du joueur a passer a travers les bombes
	public void setPasseBombe(boolean b);
	
	// rend le joueur insensible aux bombes pendant 100 tours de jeu
	public void setInvulnerable();
	
	// modifie le nombre de bombes que le joueur peut poser
	public void setNbBombePosable(int n);
	
	// modifie l'etat de sante du joueur
	public void setVivant(boolean b);
	
	// ajoute un PowerUp au joueur
	public void addReward(BlocReward reward);
}
