package services;

import utils.TypeVilain;

public interface VilainService {

	//////---------------------------------------- OBSERVATORS
	// renvoie la ligne ou se trouve le vilain
	public int getLigne();
	
	// renvoie la colonne ou se trouve le vilain
	public int getColonne();
	
	// renvoie l'identifiant unique du vilain
	public int getNum();
	
	// renvoie le type du vilain
	public TypeVilain getType();
	
	//////---------------------------------------- CONSTUCTORS
	public void init(TypeVilain type, int num, int ligne, int colonne, TerrainService t);
	
	//////---------------------------------------- OPERATORS
	// modifie la ligne ou se trouve le vilain
	public void setLigne(int ligne, TerrainService terrain);
	
	// modifie la colonne ou se trouve le vilain
	public void setColonne(int colonne, TerrainService terrain);
}
