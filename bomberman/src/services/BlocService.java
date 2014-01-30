package services;

import utils.BlocType;
import utils.BlocReward;

/**
 * Service qui simule une case du terrain de jeu
 * @author Barbier & Deluze.
 *
 */
public interface BlocService {

	//////---------------------------------------- OBSERVATORS
	// Renvoie le type du bloc
	public BlocType getType();
	
	// Renvoie le reward du bloc
	public BlocReward getReward();
	
	// Indique si le bloc peut etre detruit par une bombe
	public boolean isDestructible();
	
	//////---------------------------------------- CONSTUCTORS
	public void init(BlocType type, BlocReward reward);	
	
	//////---------------------------------------- OPERATORS
	// Change le type du bloc.
	public void setType(BlocType type);
	
	// Change le reward du bloc.
	public void setReward(BlocReward reward);
}
