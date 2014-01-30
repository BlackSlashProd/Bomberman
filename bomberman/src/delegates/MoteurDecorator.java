package delegates;

import java.util.Vector;

import services.BombeService;
import services.JoueurService;
import services.KidnappeurService;
import services.MoteurService;
import services.TerrainService;
import services.VilainService;
import utils.Action;
import utils.Gagnant;

public class MoteurDecorator implements MoteurService {

	private MoteurService delegate;
	
	public MoteurDecorator(MoteurService moteur){
		delegate = moteur;
	}
	
	@Override
	public JoueurService getJoueur() {
		return delegate.getJoueur();
	}

	@Override
	public KidnappeurService getKidnappeur() {
		return delegate.getKidnappeur();
	}

	@Override
	public int getNbBombes() {
		return delegate.getNbBombes();
	}

	@Override
	public Vector<Integer> getBombesNum() {
		return delegate.getBombesNum();
	}

	@Override
	public boolean bombeExiste(int n) {
		return delegate.bombeExiste(n);
	}

	@Override
	public BombeService getBombe(int n) {
		return delegate.getBombe(n);
	}

	@Override
	public int getNbVilains() {
		return delegate.getNbVilains();
	}

	@Override
	public Vector<Integer> getVilainsNum() {
		return delegate.getVilainsNum();
	}

	@Override
	public boolean vilainExiste(int n) {
		return delegate.vilainExiste(n);
	}

	@Override
	public VilainService getVilain(int n) {
		return delegate.getVilain(n);
	}

	@Override
	public TerrainService getTerrain() {
		return delegate.getTerrain();
	}

	@Override
	public int getStepRestants() {
		return delegate.getStepRestants();
	}

	@Override
	public boolean isGameOver() {
		return delegate.isGameOver();
	}

	@Override
	public Gagnant getGagnant() {
		return delegate.getGagnant();
	}

	@Override
	public boolean isInRange(int ligne, int colonne, int numBombe) {
		return delegate.isInRange(ligne, colonne, numBombe);
	}

	@Override
	public boolean hasRemovedExplosions() {
		return delegate.hasRemovedExplosions();
	}

	@Override
	public void init(int nbSteps) {
		delegate.init(nbSteps);
	}

	@Override
	public void step(Action a) {
		delegate.step(a);
	}

	@Override
	public void removeExplosions() {
		delegate.removeExplosions();
	}

	@Override
	public void setRemovedExplosions(boolean b) {
		delegate.setRemovedExplosions(b);
	}

}
