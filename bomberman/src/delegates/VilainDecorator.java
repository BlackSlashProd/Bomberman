package delegates;

import services.TerrainService;
import services.VilainService;
import utils.TypeVilain;

public class VilainDecorator implements VilainService {

	private VilainService delegate;
	
	public VilainDecorator(VilainService vilain) {
		delegate = vilain;
	}
	
	@Override
	public void init(TypeVilain type, int num, int ligne, int colonne, TerrainService t) {
		delegate.init(type, num, ligne, colonne, t);

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
	public int getNum() {
		return delegate.getNum();
	}
	
	@Override
	public TypeVilain getType() {
		return delegate.getType();
	}	

	@Override
	public void setLigne(int ligne, TerrainService terrain) {
		delegate.setLigne(ligne, terrain);
	}

	@Override
	public void setColonne(int colonne, TerrainService terrain) {
		delegate.setColonne(colonne, terrain);
	}
}
