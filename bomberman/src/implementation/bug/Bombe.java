package implementation.bug;

import services.BombeService;
import services.JoueurService;
import services.TerrainService;

/**
 * Implementation du Service Bombe
 * @author Barbier & Deluze.
 *
 */
public class Bombe implements BombeService {

	private int timer;
	private int amplitude;
	private int ligne;
	private int colonne;
	private int numero;
	private JoueurService proprio;

	public Bombe(){}
	
	@Override
	public void init(JoueurService joueur, int numero, int ligne, int colonne, int amplitude, TerrainService terrain) {
		this.timer = 5;
		this.amplitude = amplitude+1;
		this.ligne = ligne;
		this.colonne = colonne;
		this.numero = numero;
		this.proprio = joueur;
		if(ligne < 2 || colonne < 2 || ligne > terrain.getNbLignes()-1 || colonne > terrain.getNbColonnes()-1 || amplitude < 3 || amplitude > 11 || joueur == null){
			throw new IllegalArgumentException();
		}
	}
	@Override
	public int getTimer() {
		return timer;
	}
	@Override
	public void setTimer(int timer) {
		if(timer < 0 || timer > 10){
			throw new IllegalArgumentException();
		}
		this.timer = timer;
	}
	@Override
	public int getAmplitude() {
		return amplitude;
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
	public JoueurService getProprietaire() {
		return proprio;
	}
	
}
