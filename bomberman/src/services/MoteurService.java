package services;

import java.util.Vector;

import utils.Action;
import utils.Gagnant;

/**
 * Service qui simule le Moteur de jeu
 * @author Barbier & Deluze.
 *
 */
public interface MoteurService {
	
	//////---------------------------------------- OBSERVATORS
	// renvoie le Joueur
	public JoueurService getJoueur();
	
	// renvoie le Kidnappeur
	public KidnappeurService getKidnappeur();
	
	// renvoie le nombre de bombes posees sur le terrain
	public int getNbBombes();
	
	// renvoie la liste des identifiants uniques des bombes posees sur le terrain
	public Vector<Integer> getBombesNum();
	
	// indique si la bombe d'identifiant n existe sur le terrain
	public boolean bombeExiste(int n);
	
	// renvoie la bombe d'identifiant n
	public BombeService getBombe(int n);
	
	// renvoie le nombre de vilains sur le terrain
	public int getNbVilains();
	
	// renvoie la liste des identifiants uniques des vilains sur le terrain
	public Vector<Integer> getVilainsNum();
	
	// indique si le vilain d'identifiant n existe sur le terrain
	public boolean vilainExiste(int n);
	
	// renvoie le vilain d'identifiant n
	public VilainService getVilain(int n);
	
	// renvoie le terrain
	public TerrainService getTerrain();
	
	// renvoie le nombre de tours de jeu restants
	public int getStepRestants();
	
	// indique si la partie est finie
	public boolean isGameOver();
	
	// renvoie le gagant de la partie
	public Gagnant getGagnant();
	
	// indique si la bombe d'identifiant numBombe va exploser le contenu du bloc aux coordonnees ligne;colonne
	public boolean isInRange(int ligne, int colonne, int numBombe);
	
	// indique si le terrain a ete nettoye des explosions du tour precedent
	public boolean hasRemovedExplosions();
	
	//////---------------------------------------- CONSTUCTORS
	public void init(int nbSteps);
	
	//////---------------------------------------- OPERATORS
	// fait avancer le jeu d'un tour. la fonction tente de realiser l'action a sur le Joueur
	public void step(Action a);
	
	// retire les explosions du terrain qui ont eu lieu au tour precedent
	public void removeExplosions();
	
	// modifie l'etat de la variable qui indique si le nettoyage des explosions a ete fait
	public void setRemovedExplosions(boolean b);
}
