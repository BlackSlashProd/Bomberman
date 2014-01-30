package delegates;

import services.BlocService;
import services.TerrainService;
/**
 * 
 * @author Barbier & Deluze.
 *
 */
public class TerrainDecorator implements TerrainService {
	
	private TerrainService delegate;
	
	public TerrainDecorator(TerrainService terrain){
		delegate = terrain;
	}
	
	@Override
	public void init(int lignes, int colonnes) {
		delegate.init(lignes, colonnes);
	}
	
	@Override
	public int getNbLignes() {
		return delegate.getNbLignes();
	}
	
	@Override
	public int getNbColonnes() {
		return delegate.getNbColonnes();
	}

	@Override
	public BlocService getBloc(int ligne, int colonne) {
		return delegate.getBloc(ligne, colonne);
	}
}
