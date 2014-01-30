package delegates;

import services.BombeService;
import services.JoueurService;
import services.TerrainService;

/**
 * 
 * @author Barbier & Deluze.
 *
 */
public class BombeDecorator implements BombeService {

	private BombeService delegate;

	public BombeDecorator(BombeService bombe) {
		delegate = bombe;
	}
	
	@Override
	public void init(JoueurService joueur, int numero, int ligne, int colonne, int amplitude, TerrainService terrain) {
		delegate.init(joueur, numero, ligne, colonne, amplitude, terrain);
	}

	@Override
	public int getTimer() {
		return delegate.getTimer();
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
	public int getAmplitude() {
		return delegate.getAmplitude();
	}

	@Override
	public void setTimer(int timer) {
		delegate.setTimer(timer);		
	}

	@Override
	public int getNum() {
		return delegate.getNum();
	}

	@Override
	public JoueurService getProprietaire() {
		return delegate.getProprietaire();
	}
	
}
