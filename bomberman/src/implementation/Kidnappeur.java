package implementation;

import services.KidnappeurService;
import services.TerrainService;
import utils.BlocReward;

public class Kidnappeur implements KidnappeurService {

	private int force;
	private int ligne;
	private int colonne;
	private int nbBombes;
	private boolean alive;
	private int invuln;
	private boolean passeM;
	private boolean passeB;
	
	public Kidnappeur() {}
	
	@Override
	public int getForce() {
		return force;
	}
	@Override
	public void setForce(int force) {
		if(force < 3 || force > 11){
			throw new IllegalArgumentException();
		}
		this.force = force;
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
	public void addReward(BlocReward reward) {
		switch (reward) {
		case FireUp:
			if(force < 11){
				setForce(getForce()+2);
			}
			break;
		case BombUp: setNbBombePosable(getNbBombePosable()+1);
			break;
		case BombPass: setPasseBombe(true);
			break;
		case FireSuit: setInvulnerable();
			break;
		case WallPass: setPasseMuraille(true);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean passeMuraille() {
		return passeM;
	}

	@Override
	public boolean passeBombe() {
		return passeB;
	}

	@Override
	public int getInvulnerableStep() {
		return invuln;
	}

	@Override
	public int getNbBombePosable() {
		return nbBombes;
	}

	@Override
	public boolean isVivant() {
		return alive;
	}

	@Override
	public void init(int ligne, int colonne, TerrainService terrain) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.nbBombes = 1;
		alive = true;
		invuln = 0;
		passeM = false;
		passeB = false;
		force = 3;
		if(ligne < 2 || colonne < 2 || ligne > terrain.getNbLignes()-1 || colonne > terrain.getNbColonnes()-1){
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setLigne(int ligne, TerrainService t) {
		if(ligne < 2 || ligne > t.getNbLignes()-1){
			throw new IllegalArgumentException();
		}
		this.ligne = ligne;
	}

	@Override
	public void setColonne(int colonne, TerrainService t) {
		if(colonne < 2 || colonne > t.getNbColonnes()-1){
			throw new IllegalArgumentException();
		}
		this.colonne = colonne;
	}

	@Override
	public void setPasseMuraille(boolean b) {
		passeM = b;
	}

	@Override
	public void setPasseBombe(boolean b) {
		passeB = b;
	}

	@Override
	public void setInvulnerable() {
		invuln = 100;
	}

	@Override
	public void setNbBombePosable(int n) {
		if(n < 0){
			throw new IllegalArgumentException();
		}
		nbBombes = n;
	}

	@Override
	public void setVivant(boolean b) {
		alive = b;
	}

	@Override
	public void decrementeInvulnerabilite() {
		invuln--;
	}
}
