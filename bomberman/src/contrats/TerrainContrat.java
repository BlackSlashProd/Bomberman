package contrats;

import services.BlocService;
import services.TerrainService;
import delegates.TerrainDecorator;
import utils.BlocReward;
import utils.BlocType;
import utils.Contractor;


public class TerrainContrat extends TerrainDecorator{

	public TerrainContrat(TerrainService terrain) {
		super(terrain);
	}
	
	private void checkInvariant(){
		for(int i = 1; i <= getNbLignes(); i++){
			for(int j = 1; j <= getNbColonnes(); j++){
				if( !(getBloc(i, j) != null) )
					Contractor.defaultContractor().invariantError("Terrain","getBloc("+i+", "+j+") == null");
				if(i == 1){
					if( !(getBloc(i, j).getType() == BlocType.MurMetal) )
						Contractor.defaultContractor().invariantError("Terrain","getType(getBloc("+i+", "+j+")) != BlocType.MurMetal");
				}
				if(i == getNbLignes()){
					if( !(getBloc(i, j).getType() == BlocType.MurMetal) )
						Contractor.defaultContractor().invariantError("Terrain","getType(getBloc("+i+", "+j+")) != BlocType.MurMetal");
				}
				if(j == 1){
					if( !(getBloc(i, j).getType() == BlocType.MurMetal) )
						Contractor.defaultContractor().invariantError("Terrain","getType(getBloc("+i+", "+j+")) != BlocType.MurMetal");
				}
				if(j == getNbColonnes()){
					if( !(getBloc(i, j).getType() == BlocType.MurMetal) )
						Contractor.defaultContractor().invariantError("Terrain","getType(getBloc("+i+", "+j+")) != BlocType.MurMetal");
				}
				if(i%2 == 1 && j%2 == 1){
					if( !(getBloc(i, j).getType() == BlocType.MurMetal) )
						Contractor.defaultContractor().invariantError("Terrain","getType(getBloc("+i+", "+j+")) != BlocType.MurMetal");
				}
				if(getBloc(i, j).getType() == BlocType.MurMetal){
					if( !(getBloc(i, j).getReward() == BlocReward.Rien) )
						Contractor.defaultContractor().invariantError("Terrain","getReward(getBloc("+i+", "+j+")) != BlocReward.Rien");
				}
			}
		}
	}
	
	@Override
	public void init(int lignes, int colonnes){
		//PRE
		if( !(lignes >= 6) )
			Contractor.defaultContractor().preconditionError("Terrain","init : lignes < 6");
		if( !(colonnes >= 6) )
			Contractor.defaultContractor().preconditionError("Terrain","init : colonnes < 6");
		
		//APPEL METHODE
		super.init(lignes, colonnes);
		
		//POST
		if( !(getNbLignes()==lignes) )
			Contractor.defaultContractor().postconditionError("Terrain","init : getNbLignes(init(lignes, colonnes)) != lignes");
		if( !(getNbColonnes()==colonnes) )
			Contractor.defaultContractor().postconditionError("Terrain","init : getNbColonnes(init(lignes, colonnes)) != colonnes");
		for(int i = 1; i <= lignes; i++){
			for(int j = 1; j <= colonnes; j++){
				if( !(getBloc(i, j) != null) )
					Contractor.defaultContractor().postconditionError("Terrain","init : getBloc("+i+", "+j+") == null");
				if(i == 1){
					if( !(getBloc(i, j).getType() == BlocType.MurMetal) )
						Contractor.defaultContractor().postconditionError("Terrain","init : getType(getBloc("+i+", "+j+")) != BlocType.MurMetal");
				}
				if(i == lignes){
					if( !(getBloc(i, j).getType() == BlocType.MurMetal) )
						Contractor.defaultContractor().postconditionError("Terrain","init : getType(getBloc("+i+", "+j+")) != BlocType.MurMetal");
				}
				if(j == 1){
					if( !(getBloc(i, j).getType() == BlocType.MurMetal) )
						Contractor.defaultContractor().postconditionError("Terrain","init : getType(getBloc("+i+", "+j+")) != BlocType.MurMetal");
				}
				if(j == colonnes){
					if( !(getBloc(i, j).getType() == BlocType.MurMetal) )
						Contractor.defaultContractor().postconditionError("Terrain","init : getType(getBloc("+i+", "+j+")) != BlocType.MurMetal");
				}
				if(i%2 == 1 && j%2 == 1){
					if( !(getBloc(i, j).getType() == BlocType.MurMetal) )
						Contractor.defaultContractor().postconditionError("Terrain","init : getType(getBloc("+i+", "+j+")) != BlocType.MurMetal");
				}
				if(getBloc(i, j).getType() == BlocType.MurMetal){
					if( !(getBloc(i, j).getReward() == BlocReward.Rien) )
						Contractor.defaultContractor().postconditionError("Terrain","init : getReward(getBloc("+i+", "+j+")) != BlocReward.Rien");
				}
			}
		}
		checkInvariant();
	}
	
	@Override
	public int getNbLignes(){
		return super.getNbLignes();
	}
	
	@Override
	public int getNbColonnes(){
		return super.getNbColonnes();
	}
	
	@Override
	public BlocService getBloc(int ligne, int colonne) {
		//PRE
		if( !(ligne >= 1) )
			Contractor.defaultContractor().preconditionError("Terrain","getBloc : ligne < 1");
		if( !(ligne <= getNbLignes()) )
			Contractor.defaultContractor().preconditionError("Terrain","getBloc : ligne > getNbLignes()");
		if( !(colonne >= 1) )
			Contractor.defaultContractor().preconditionError("Terrain","getBloc : colonne < 1");
		if( !(colonne <= getNbColonnes()) )
			Contractor.defaultContractor().preconditionError("Terrain","getBloc : colonne > getNbColonnes()");
		
		//APPEL METHODE
		return super.getBloc(ligne, colonne);
	}
}
