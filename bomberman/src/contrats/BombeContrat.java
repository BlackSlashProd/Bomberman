package contrats;

import services.BombeService;
import services.JoueurService;
import services.TerrainService;
import utils.Contractor;
import delegates.BombeDecorator;

public class BombeContrat extends BombeDecorator{

	public BombeContrat(BombeService bombe) {
		super(bombe);
	}
	
	private void checkInvariant(){
		if( !(getTimer() >= 0) )
			Contractor.defaultContractor().invariantError("Bombe", "getTimer < 0");
		if( !(getTimer() <= 10) )
			Contractor.defaultContractor().invariantError("Bombe", "getTimer > 10");
		if( !(getAmplitude() >= 3) )
			Contractor.defaultContractor().invariantError("Bombe", "getAmplitude < 3");
		if( !(getAmplitude() <= 11) )
			Contractor.defaultContractor().invariantError("Bombe", "getAmplitude > 11");
	}
	
	@Override
	public void init(JoueurService joueur, int numero, int ligne, int colonne, int amplitude, TerrainService terrain) {
		//PRE
		if( !(amplitude >= 3))
			Contractor.defaultContractor().preconditionError("Bombe", "init", "amplitude < 3");
		if( !(amplitude <= 11))
			Contractor.defaultContractor().preconditionError("Bombe", "init", "amplitude > 11");
		if( !(ligne >= 2) )
			Contractor.defaultContractor().preconditionError("Bombe", "init", "ligne < 2");
		if( !(ligne < terrain.getNbLignes()) )
			Contractor.defaultContractor().preconditionError("Bombe", "init", "ligne >= terrain.getNbLignes()");
		if( !(colonne >= 2) )
			Contractor.defaultContractor().preconditionError("Bombe", "init", "colonne < 2");
		if( !(colonne < terrain.getNbColonnes()) )
			Contractor.defaultContractor().preconditionError("Bombe", "init", "colonne >= terrain.getNbColonnes()");
		
		//APPEL METHODE
		super.init(joueur, numero, ligne, colonne, amplitude, terrain);
		
		//POST
		if( !(getTimer() == 10) )
			Contractor.defaultContractor().postconditionError("Bombe", "init", "getTimer() != 10");
		if( !(getLigne() == ligne) )
			Contractor.defaultContractor().postconditionError("Bombe", "init", "getLigne() != ligne");
		if( !(getColonne() == colonne) )
			Contractor.defaultContractor().postconditionError("Bombe", "init", "getColonne() != colonne");
		if( !(getAmplitude() == amplitude) )
			Contractor.defaultContractor().postconditionError("Bombe", "init", "getAmplitude() != amplitude");
		if( !(getNum() == numero) )
			Contractor.defaultContractor().postconditionError("Bombe", "init", "getNum() != numero");
		if( !(getProprietaire() == joueur) )
			Contractor.defaultContractor().postconditionError("Bombe", "init", "getProprietaire() != joueur");
		checkInvariant();
	}
	
	@Override
	public int getTimer(){
		return super.getTimer();
	}

	@Override
	public int getLigne(){
		return super.getLigne();
	}
	
	@Override
	public int getColonne(){
		return super.getColonne();
	}
	
	@Override
	public int getAmplitude(){
		return super.getAmplitude();
	}
	
	@Override
	public int getNum() {
		return super.getNum();
	}
	
	@Override
	public JoueurService getProprietaire() {
		return super.getProprietaire();
	}
	
	@Override
	public void setTimer(int timer){
		//PRE
		if( !(timer >= 0) )
			Contractor.defaultContractor().preconditionError("Bombe", "setTimer", "timer < 0");
		if( !(timer <= 10) )
			Contractor.defaultContractor().preconditionError("Bombe", "setTimer", "timer > 10");
		
		//SAVE
		int num = getNum();
		JoueurService prop = getProprietaire();
		int amp = getAmplitude();
		int lig = getLigne();
		int col = getColonne();
		
		//APPEL METHODE
		checkInvariant();
		super.setTimer(timer);
		checkInvariant();
		
		//POST
		if( !(getTimer()==timer) )
			Contractor.defaultContractor().preconditionError("Bombe", "setTimer", "getTimer(setTimer(t,B)) != t");
		if( !(getNum()==num) )
			Contractor.defaultContractor().preconditionError("Bombe", "setTimer", "getNum(setTimer(t,B)) != getNum(B)");
		if( !(getProprietaire()==prop) )
			Contractor.defaultContractor().preconditionError("Bombe", "setTimer", "getProprietaire(setTimer(t,B)) != getProprietaire(B)");
		if( !(getAmplitude()==amp) )
			Contractor.defaultContractor().preconditionError("Bombe", "setTimer", "getAmplitude(setTimer(t,B)) != getAmplitude(B)");
		if( !(getLigne()==lig) )
			Contractor.defaultContractor().preconditionError("Bombe", "setTimer", "getLigne(setTimer(t,B)) != getLigne(B)");
		if( !(getColonne()==col) )
			Contractor.defaultContractor().preconditionError("Bombe", "setTimer", "getColonne(setTimer(t,B)) != getColonne(B)");
	}
}
