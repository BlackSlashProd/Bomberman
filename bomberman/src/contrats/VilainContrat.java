package contrats;

import services.TerrainService;
import services.VilainService;
import utils.Contractor;
import utils.TypeVilain;
import delegates.VilainDecorator;

public class VilainContrat extends VilainDecorator{

	public VilainContrat(VilainService vilain) {
		super(vilain);
	}

	private void checkInvariant(){
		//Aucun
	}
	
	@Override
	public int getColonne() {
		return super.getColonne();
	}
	@Override
	public int getLigne() {
		return super.getLigne();
	}
	@Override
	public int getNum() {
		return super.getNum();
	}
	@Override
	public TypeVilain getType() {
		return super.getType();
	}
	@Override
	public void init(TypeVilain type, int num, int ligne, int colonne, TerrainService terrain) {
		//PRE
		if( !(ligne >= 2) )
			Contractor.defaultContractor().preconditionError("Vilain","init : ligne < 2");
		if( !(ligne < terrain.getNbLignes()) )
			Contractor.defaultContractor().preconditionError("Vilain","init : ligne >= terrain.getNbLignes()");
		if( !(colonne >= 2) )
			Contractor.defaultContractor().preconditionError("Vilain","init : colonne < 2");
		if( !(colonne < terrain.getNbColonnes()) )
			Contractor.defaultContractor().preconditionError("Vilain","init : colonne >= terrain.getNbColonnes()");
		
		//APPEL METHODE
		super.init(type, num, ligne, colonne, terrain);
		
		//POST
		if( !(getLigne()==ligne) )
			Contractor.defaultContractor().postconditionError("Vilain","init : getLigne() != ligne");
		if( !(getColonne()==colonne) )
			Contractor.defaultContractor().postconditionError("Vilain","init : getColonne() != colonne");
		if( !(getNum()== num) )
			Contractor.defaultContractor().postconditionError("Vilain","init : getNum() != num");
		checkInvariant();
		
	}
	@Override
	public void setColonne(int colonne, TerrainService terrain) {
		//PRE
		if( !(colonne >= 2) )
			Contractor.defaultContractor().preconditionError("Vilain","setColonne : ligne < 2");
		if( !(colonne < terrain.getNbColonnes()) )
			Contractor.defaultContractor().preconditionError("Vilain","setColonne : ligne >= terrain.getNbColonnes()");
		
		//SAVE
		int ligne = getLigne();
		
		//APPEL METHODE
		checkInvariant();
		super.setColonne(colonne, terrain);
		checkInvariant();
		
		//POST
		if( !(getColonne() == colonne) )
			Contractor.defaultContractor().postconditionError("Vilain","setLigne : getColonne(setColonne(c)) != c");
		if( !(getLigne() == ligne) )
			Contractor.defaultContractor().postconditionError("Vilain","setLigne : getLigne(setColonne(c)) != getLigne()");
	}
	@Override
	public void setLigne(int ligne, TerrainService terrain) {
		//PRE
		if( !(ligne >= 2) )
			Contractor.defaultContractor().preconditionError("Vilain","setLigne : ligne < 2");
		if( !(ligne < terrain.getNbLignes()) )
			Contractor.defaultContractor().preconditionError("Vilain","setLigne : ligne >= terrain.getNbLignes()");
		
		//SAVE
		int colonne = getColonne();
		
		//APPEL METHODE
		checkInvariant();
		super.setLigne(ligne, terrain);
		checkInvariant();
		
		//POST
		if( !(getLigne() == ligne) )
			Contractor.defaultContractor().postconditionError("Vilain","setLigne : getLigne(setLigne(l)) != l");
		if( !(getColonne() == colonne) )
			Contractor.defaultContractor().postconditionError("Vilain","setLigne : getColonne(setLigne(l)) != getColonne()");
	}
}
