package implementation;

import services.TerrainService;
import services.VilainService;
import utils.TypeVilain;

public class Vilain implements VilainService{
	private int ligne;
	private int colonne;
	private TypeVilain type;
	private int numero;
	
	public Vilain (){}
	
	@Override
	public TypeVilain getType() {
		return type;
	}
	@Override
	public int getLigne() {
		return ligne;
	}
	@Override
	public int getColonne() {
		return colonne;
	}
	@Override
	public int getNum() {
		return numero;
	}
	@Override
	public void init(TypeVilain type, int num, int ligne, int colonne, TerrainService terrain) {
		if(terrain == null || ligne < 2 || colonne < 2 || ligne > terrain.getNbLignes()-1 || colonne > terrain.getNbColonnes()-1 || type == null){
			throw new IllegalArgumentException();
		}
		this.ligne = ligne;
		this.colonne = colonne;
		this.type = type;
		this.numero = num;
	}
	@Override
	public void setLigne(int ligne, TerrainService terrain) {
		if(ligne < 2 || ligne > terrain.getNbLignes()-1){
			throw new IllegalArgumentException();
		}
		this.ligne = ligne;
	}
	@Override
	public void setColonne(int colonne, TerrainService terrain) {
		if(colonne < 2 || colonne > terrain.getNbColonnes()-1){
			throw new IllegalArgumentException();
		}
		this.colonne = colonne;
	}
}
