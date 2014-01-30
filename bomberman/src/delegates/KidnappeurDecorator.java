package delegates;

import services.KidnappeurService;
import services.TerrainService;
import utils.BlocReward;

public class KidnappeurDecorator implements KidnappeurService {

	private KidnappeurService delegate;
	
	public KidnappeurDecorator(KidnappeurService kidnappeur){
		delegate = kidnappeur;
	}
	
	@Override
	public int getLigne() {
		return delegate.getLigne();
	}

	@Override
	public int getColonne() {
		return delegate.getColonne();
	}

	@Override
	public int getForce() {
		return delegate.getForce();
	}

	@Override
	public boolean passeMuraille() {
		return delegate.passeMuraille();
	}

	@Override
	public boolean passeBombe() {
		return delegate.passeBombe();
	}

	@Override
	public int getInvulnerableStep() {
		return delegate.getInvulnerableStep();
	}

	@Override
	public int getNbBombePosable() {
		return delegate.getNbBombePosable();
	}

	@Override
	public boolean isVivant() {
		return delegate.isVivant();
	}

	@Override
	public void init(int ligne, int colonne, TerrainService t) {
		delegate.init(ligne, colonne, t);
	}

	@Override
	public void setLigne(int ligne, TerrainService t) {
		delegate.setLigne(ligne, t);
	}

	@Override
	public void setColonne(int colonne, TerrainService t) {
		delegate.setColonne(colonne, t);
	}

	@Override
	public void setForce(int force) {
		delegate.setForce(force);
	}

	@Override
	public void setPasseMuraille(boolean b) {
		delegate.setPasseMuraille(b);
	}

	@Override
	public void setPasseBombe(boolean b) {
		delegate.setPasseBombe(b);
	}

	@Override
	public void setInvulnerable() {
		delegate.setInvulnerable();
	}
	
	@Override
	public void decrementeInvulnerabilite() {
		delegate.decrementeInvulnerabilite();
	}	

	@Override
	public void setNbBombePosable(int n) {
		delegate.setNbBombePosable(n);
	}

	@Override
	public void setVivant(boolean b) {
		delegate.setVivant(b);
	}

	@Override
	public void addReward(BlocReward reward) {
		delegate.addReward(reward);
	}

}
