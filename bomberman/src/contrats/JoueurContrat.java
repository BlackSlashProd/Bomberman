package contrats;

import delegates.JoueurDecorator;
import services.JoueurService;
import services.TerrainService;
import utils.BlocReward;
import utils.Contractor;

public class JoueurContrat extends JoueurDecorator {
	
	public JoueurContrat(JoueurService joueur) {
		super(joueur);
	}
	private void checkInvariant(){
		if( !(getInvulnerableStep() >= 0) )
			Contractor.defaultContractor().invariantError("Joueur","getInvulnerableStep() < 0");
		if( !(getInvulnerableStep() <= 100) )
			Contractor.defaultContractor().invariantError("Joueur","getInvulnerableStep() > 100");
		if( !(getForce() >= 3) )
			Contractor.defaultContractor().invariantError("Joueur","getForce() < 3");
		if( !(getForce() <= 11) )
			Contractor.defaultContractor().invariantError("Joueur","getForce() > 11");
		if( !(getNbBombePosable() >= 0) )
			Contractor.defaultContractor().invariantError("Joueur","getNbBombesPosable() < 0");
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
	public int getForce() {
		return super.getForce();
	}
	@Override
	public int getInvulnerableStep() {
		return super.getInvulnerableStep();
	}
	@Override
	public int getNbBombePosable() {
		return super.getNbBombePosable();
	}
	@Override
	public boolean passeBombe() {
		return super.passeBombe();
	}
	@Override
	public boolean passeMuraille() {
		return super.passeMuraille();
	}
	@Override
	public boolean isVivant() {
		return super.isVivant();
	}
	
	@Override
	public void init(int ligne, int colonne, TerrainService t) {
		//PRE
		if( !(ligne >= 2) )
			Contractor.defaultContractor().preconditionError("Joueur","init","ligne < 2");
		if( !(ligne < t.getNbLignes()) )
			Contractor.defaultContractor().preconditionError("Joueur","init","ligne >= terrain.getNbLignes()");
		if( !(colonne >= 2) )
			Contractor.defaultContractor().preconditionError("Joueur","init","colonne < 2");
		if( !(colonne < t.getNbColonnes()) )
			Contractor.defaultContractor().preconditionError("Joueur","init","colonne >= terrain.getNbColonnes()");
		
		//APPEL METHODE
		super.init(ligne, colonne, t);
		
		//POST
		if( !(getLigne() == ligne) )
			Contractor.defaultContractor().postconditionError("Joueur","init","getLigne() != ligne");
		if( !(getColonne() == colonne) )
			Contractor.defaultContractor().postconditionError("Joueur","init","getColonne() != colonne");
		if( !(getForce() == 3) )
			Contractor.defaultContractor().postconditionError("Joueur","init","getForce() != 3");
		if( !(passeMuraille() == false) )
			Contractor.defaultContractor().postconditionError("Joueur","init","passeMuraille() != false");
		if( !(passeBombe() == false) )
			Contractor.defaultContractor().postconditionError("Joueur","init","passeBombe() != false");
		if( !(getNbBombePosable() == 1) )
			Contractor.defaultContractor().postconditionError("Joueur","init","getNbBombePosable() != 1");
		if( !(getInvulnerableStep() == 0) )
			Contractor.defaultContractor().postconditionError("Joueur","init","getInvulnerableStep() != 0");
		if( !(isVivant() == true) )
			Contractor.defaultContractor().postconditionError("Joueur","init","isVivant() != true");
		
		checkInvariant();
	}
	
	@Override
	public void setForce(int force) {
		//PRE
		if( !(force >= 3) )
			Contractor.defaultContractor().preconditionError("Joueur","setForce","force < 3");
		if( !(force <= 11) )
			Contractor.defaultContractor().preconditionError("Joueur","setForce","force > 11");
		
		//SAVE
		int ligne = getLigne();
		int colonne = getColonne();
		boolean passeM = passeMuraille();
		boolean passeB = passeBombe();
		int nbBP = getNbBombePosable();
		int invul = getInvulnerableStep();
		boolean alive = isVivant();
		
		//APPEL METHODE
		checkInvariant();
		super.setForce(force);
		checkInvariant();
		
		//POST
		if( !(getLigne() == ligne) )
			Contractor.defaultContractor().postconditionError("Joueur","setForce","getLigne(setForce(f)) != getLigne()");
		if( !(getColonne() == colonne) )
			Contractor.defaultContractor().postconditionError("Joueur","setForce","getColonne(setForce(f)) != getColonne()");
		if( !(getForce() == force) )
			Contractor.defaultContractor().postconditionError("Joueur","setForce","getForce(setForce(f)) != force");
		if( !(passeMuraille() == passeM) )
			Contractor.defaultContractor().postconditionError("Joueur","setForce","passeMuraille(setForce(f)) != passeMuraille()");
		if( !(passeBombe() == passeB) )
			Contractor.defaultContractor().postconditionError("Joueur","setForce","passeBombe(setForce(f)) != passeBombe()");
		if( !(getNbBombePosable() == nbBP) )
			Contractor.defaultContractor().postconditionError("Joueur","setForce","getNbBombePosable(setForce(f)) != getNbBombePosable()");
		if( !(getInvulnerableStep() == invul) )
			Contractor.defaultContractor().postconditionError("Joueur","setForce","getInvulnerableStep(setForce(f)) != getInvulnerableStep()");
		if( !(isVivant() == alive) )
			Contractor.defaultContractor().postconditionError("Joueur","setForce","isVivant(setForce(f)) != isVivant()");
	}
	@Override
	public void setInvulnerable() {
		//SAVE
		int ligne = getLigne();
		int colonne = getColonne();
		boolean passeM = passeMuraille();
		boolean passeB = passeBombe();
		int nbBP = getNbBombePosable();
		int force = getForce();
		boolean alive = isVivant();
		
		//APPEL METHODE
		checkInvariant();
		super.setInvulnerable();
		checkInvariant();
		
		//POST
		if( !(getLigne() == ligne) )
			Contractor.defaultContractor().postconditionError("Joueur","setInvulnerable","getLigne(setInvulnerable()) != getLigne()");
		if( !(getColonne() == colonne) )
			Contractor.defaultContractor().postconditionError("Joueur","setInvulnerable","getColonne(setInvulnerable()) != getColonne()");
		if( !(getForce() == force) )
			Contractor.defaultContractor().postconditionError("Joueur","setInvulnerable","getForce(setInvulnerable()) != getForce()");
		if( !(passeMuraille() == passeM) )
			Contractor.defaultContractor().postconditionError("Joueur","setInvulnerable","passeMuraille(setInvulnerable()) != passeMuraille()");
		if( !(passeBombe() == passeB) )
			Contractor.defaultContractor().postconditionError("Joueur","setInvulnerable","passeBombe(setInvulnerable()) != passeBombe()");
		if( !(getNbBombePosable() == nbBP) )
			Contractor.defaultContractor().postconditionError("Joueur","setInvulnerable","getNbBombePosable(setInvulnerable()) != getNbBombePosable()");
		if( !(getInvulnerableStep() == 100) )
			Contractor.defaultContractor().postconditionError("Joueur","setInvulnerable","getInvulnerableStep(setInvulnerable()) != 100");
		if( !(isVivant() == alive) )
			Contractor.defaultContractor().postconditionError("Joueur","setInvulnerable","isVivant(setInvulnerable()) != isVivant()");
	}
	@Override
	public void decrementeInvulnerabilite() {
		//SAVE
		int ligne = getLigne();
		int colonne = getColonne();
		boolean passeM = passeMuraille();
		boolean passeB = passeBombe();
		int nbBP = getNbBombePosable();
		int force = getForce();
		boolean alive = isVivant();
		int inv = getInvulnerableStep();
		
		//APPEL METHODE
		checkInvariant();
		super.decrementeInvulnerabilite();
		checkInvariant();
		
		//POST
		if( !(getLigne() == ligne) )
			Contractor.defaultContractor().postconditionError("Joueur","decrementeInvulnerabilite","getLigne(setInvulnerable()) != getLigne()");
		if( !(getColonne() == colonne) )
			Contractor.defaultContractor().postconditionError("Joueur","decrementeInvulnerabilite","getColonne(setInvulnerable()) != getColonne()");
		if( !(getForce() == force) )
			Contractor.defaultContractor().postconditionError("Joueur","decrementeInvulnerabilite","getForce(setInvulnerable()) != getForce()");
		if( !(passeMuraille() == passeM) )
			Contractor.defaultContractor().postconditionError("Joueur","decrementeInvulnerabilite","passeMuraille(setInvulnerable()) != passeMuraille()");
		if( !(passeBombe() == passeB) )
			Contractor.defaultContractor().postconditionError("Joueur","decrementeInvulnerabilite","passeBombe(setInvulnerable()) != passeBombe()");
		if( !(getNbBombePosable() == nbBP) )
			Contractor.defaultContractor().postconditionError("Joueur","decrementeInvulnerabilite","getNbBombePosable(setInvulnerable()) != getNbBombePosable()");
		if( !(getInvulnerableStep() == inv-1) )
			Contractor.defaultContractor().postconditionError("Joueur","decrementeInvulnerabilite","getInvulnerableStep(setInvulnerable()) != getInvulnerableStep() - 1");
		if( !(isVivant() == alive) )
			Contractor.defaultContractor().postconditionError("Joueur","decrementeInvulnerabilite","isVivant(setInvulnerable()) != isVivant()");
	}
	@Override
	public void setNbBombePosable(int n) {
		//PRE
		if( !(n >= 0) )
			Contractor.defaultContractor().preconditionError("Joueur","setNbBombePosable","n < 0");
		
		//SAVE
		int ligne = getLigne();
		int colonne = getColonne();
		boolean passeM = passeMuraille();
		boolean passeB = passeBombe();
		int force = getForce();
		int invul = getInvulnerableStep();
		boolean alive = isVivant();
		
		//APPEL METHODE
		checkInvariant();
		super.setNbBombePosable(n);
		checkInvariant();
		
		//POST
		if( !(getLigne() == ligne) )
			Contractor.defaultContractor().postconditionError("Joueur","setNbBombePosable","getLigne(setNbBombePosable(n)) != getLigne()");
		if( !(getColonne() == colonne) )
			Contractor.defaultContractor().postconditionError("Joueur","setNbBombePosable","getColonne(setNbBombePosable(n)) != getColonne()");
		if( !(getForce() == force) )
			Contractor.defaultContractor().postconditionError("Joueur","setNbBombePosable","getForce(setNbBombePosable(n)) != getForce()");
		if( !(passeMuraille() == passeM) )
			Contractor.defaultContractor().postconditionError("Joueur","setNbBombePosable","passeMuraille(setNbBombePosable(n)) != passeMuraille()");
		if( !(passeBombe() == passeB) )
			Contractor.defaultContractor().postconditionError("Joueur","setNbBombePosable","passeBombe(setNbBombePosable(n)) != passeBombe()");
		if( !(getNbBombePosable() == n) )
			Contractor.defaultContractor().postconditionError("Joueur","setNbBombePosable","getNbBombePosable(setNbBombePosable(n)) != n");
		if( !(getInvulnerableStep() == invul) )
			Contractor.defaultContractor().postconditionError("Joueur","setNbBombePosable","getInvulnerableStep(setNbBombePosable(n)) != getInvulnerableStep()");
		if( !(isVivant() == alive) )
			Contractor.defaultContractor().postconditionError("Joueur","setNbBombePosable","isVivant(setNbBombePosable(n)) != isVivant()");
	}
	@Override
	public void setPasseBombe(boolean b) {
		//SAVE
		int ligne = getLigne();
		int colonne = getColonne();
		boolean passeM = passeMuraille();
		int nbBP = getNbBombePosable();
		int force = getForce();
		int invul = getInvulnerableStep();
		boolean alive = isVivant();
		
		//APPEL METHODE
		checkInvariant();
		super.setPasseBombe(b);
		checkInvariant();
		
		//POST
		if( !(getLigne() == ligne) )
			Contractor.defaultContractor().postconditionError("Joueur","setPasseBombe","getLigne(setPasseBombe(b)) != getLigne()");
		if( !(getColonne() == colonne) )
			Contractor.defaultContractor().postconditionError("Joueur","setPasseBombe","getColonne(setPasseBombe(b)) != getColonne()");
		if( !(getForce() == force) )
			Contractor.defaultContractor().postconditionError("Joueur","setPasseBombe","getForce(setPasseBombe(b)) != getForce()");
		if( !(passeMuraille() == passeM) )
			Contractor.defaultContractor().postconditionError("Joueur","setPasseBombe","passeMuraille(setPasseBombe(b)) != passeMuraille()");
		if( !(passeBombe() == b) )
			Contractor.defaultContractor().postconditionError("Joueur","setPasseBombe","passeBombe(setPasseBombe(b)) != b");
		if( !(getNbBombePosable() == nbBP) )
			Contractor.defaultContractor().postconditionError("Joueur","setPasseBombe","getNbBombePosable(setPasseBombe(b)) != getNbBombePosable()");
		if( !(getInvulnerableStep() == invul) )
			Contractor.defaultContractor().postconditionError("Joueur","setPasseBombe","getInvulnerableStep(setPasseBombe(b)) != getInvulnerableStep()");
		if( !(isVivant() == alive) )
			Contractor.defaultContractor().postconditionError("Joueur","setPasseBombe","isVivant(setPasseBombe(b)) != isVivant()");
	}
	@Override
	public void setPasseMuraille(boolean b) {
		//SAVE
		int ligne = getLigne();
		int colonne = getColonne();
		boolean passeB = passeBombe();
		int nbBP = getNbBombePosable();
		int force = getForce();
		int invul = getInvulnerableStep();
		boolean alive = isVivant();
		
		//APPEL METHODE
		checkInvariant();
		super.setPasseMuraille(b);
		checkInvariant();
		
		//POST
		if( !(getLigne() == ligne) )
			Contractor.defaultContractor().postconditionError("Joueur","setPasseMuraille","getLigne(setPasseMuraille(b)) != getLigne()");
		if( !(getColonne() == colonne) )
			Contractor.defaultContractor().postconditionError("Joueur","setPasseMuraille","getColonne(setPasseMuraille(b)) != getColonne()");
		if( !(getForce() == force) )
			Contractor.defaultContractor().postconditionError("Joueur","setPasseMuraille","getForce(setPasseMuraille(b)) != getForce()");
		if( !(passeMuraille() == b) )
			Contractor.defaultContractor().postconditionError("Joueur","setPasseMuraille","passeMuraille(setPasseMuraille(b)) != b");
		if( !(passeBombe() == passeB) )
			Contractor.defaultContractor().postconditionError("Joueur","setPasseMuraille","passeBombe(setPasseMuraille(b)) != passeBombe()");
		if( !(getNbBombePosable() == nbBP) )
			Contractor.defaultContractor().postconditionError("Joueur","setPasseMuraille","getNbBombePosable(setPasseMuraille(b)) != getNbBombePosable()");
		if( !(getInvulnerableStep() == invul) )
			Contractor.defaultContractor().postconditionError("Joueur","setPasseMuraille","getInvulnerableStep(setPasseMuraille(b)) != getInvulnerableStep()");
		if( !(isVivant() == alive) )
			Contractor.defaultContractor().postconditionError("Joueur","setPasseMuraille","isVivant(setPasseMuraille(b)) != isVivant()");
	}
	@Override
	public void setVivant(boolean b) {
		//SAVE
		int ligne = getLigne();
		int colonne = getColonne();
		boolean passeB = passeBombe();
		boolean passeM = passeMuraille();
		int nbBP = getNbBombePosable();
		int force = getForce();
		int invul = getInvulnerableStep();
		
		//APPEL METHODE
		checkInvariant();
		super.setVivant(b);
		checkInvariant();
		
		//POST
		if( !(getLigne() == ligne) )
			Contractor.defaultContractor().postconditionError("Joueur","setVivant","getLigne(setVivant(b)) != getLigne()");
		if( !(getColonne() == colonne) )
			Contractor.defaultContractor().postconditionError("Joueur","setVivant","getColonne(setVivant(b)) != getColonne()");
		if( !(getForce() == force) )
			Contractor.defaultContractor().postconditionError("Joueur","setVivant","getForce(setVivant(b)) != getForce()");
		if( !(passeMuraille() == passeM) )
			Contractor.defaultContractor().postconditionError("Joueur","setVivant","passeMuraille(setVivant(b)) != passeMuraille()");
		if( !(passeBombe() == passeB) )
			Contractor.defaultContractor().postconditionError("Joueur","setVivant","passeBombe(setVivant(b)) != passeBombe()");
		if( !(getNbBombePosable() == nbBP) )
			Contractor.defaultContractor().postconditionError("Joueur","setVivant","getNbBombePosable(setVivant(b)) != getNbBombePosable()");
		if( !(getInvulnerableStep() == invul) )
			Contractor.defaultContractor().postconditionError("Joueur","setVivant","getInvulnerableStep(setVivant(b)) != getInvulnerableStep()");
		if( !(isVivant() == b) )
			Contractor.defaultContractor().postconditionError("Joueur","setVivant","isVivant(setVivant(b)) != b");
	}
	@Override
	public void addReward(BlocReward reward) {
		//SAVE
		int ligne = getLigne();
		int colonne = getColonne();
		boolean passeB = passeBombe();
		boolean passeM = passeMuraille();
		int nbBP = getNbBombePosable();
		int force = getForce();
		int invul = getInvulnerableStep();
		boolean alive = isVivant();
		
		//APPEL METHODE
		checkInvariant();
		super.addReward(reward);
		checkInvariant();
		
		//POST
		if( !(getLigne() == ligne) )
			Contractor.defaultContractor().postconditionError("Joueur","addReward","getLigne(addReward(reward)) != getLigne()");
		if( !(getColonne() == colonne) )
			Contractor.defaultContractor().postconditionError("Joueur","addReward","getColonne(addReward(reward)) != getColonne()");
		if( !(isVivant() == alive) )
			Contractor.defaultContractor().postconditionError("Joueur","addReward","isVivant(addReward(reward)) != isVivant()");
		if(reward == BlocReward.FireUp){
			if(force < 11){
				if( !(getForce() == force + 2) )
					Contractor.defaultContractor().postconditionError("Joueur","addReward","getForce(addReward(reward)) != getForce() + 2");
			}else{
				if( !(getForce() == force) )
					Contractor.defaultContractor().postconditionError("Joueur","addReward","getForce(addReward(reward)) != getForce()");
			}
		}else{
			if( !(getForce() == force) )
				Contractor.defaultContractor().postconditionError("Joueur","addReward","getForce(addReward(reward)) != getForce()");
		}
		if(reward == BlocReward.WallPass){
			if( !(passeMuraille() == true) )
				Contractor.defaultContractor().postconditionError("Joueur","addReward","passeMuraille(addReward(reward)) != true");
		}else{
			if( !(passeMuraille() == passeM) )
				Contractor.defaultContractor().postconditionError("Joueur","addReward","passeMuraille(addReward(reward)) != passeMuraille()");
		}
		if(reward == BlocReward.BombPass){
			if( !(passeBombe() == true) )
				Contractor.defaultContractor().postconditionError("Joueur","addReward","passeBombe(addReward(reward)) != true");
		}else{
			if( !(passeBombe() == passeB) )
				Contractor.defaultContractor().postconditionError("Joueur","addReward","passeBombe(addReward(reward)) != passeBombe()");
		}
		if(reward == BlocReward.BombUp){
			if( !(getNbBombePosable() == nbBP + 1) )
				Contractor.defaultContractor().postconditionError("Joueur","addReward","getNbBombePosable(addReward(reward)) != getNbBombePosable() + 1");
		}else{
			if( !(getNbBombePosable() == nbBP) )
				Contractor.defaultContractor().postconditionError("Joueur","addReward","getNbBombePosable(addReward(reward)) != getNbBombePosable()");
		}
		if(reward == BlocReward.FireSuit){
			if( !(getInvulnerableStep() == 100) )
				Contractor.defaultContractor().postconditionError("Joueur","addReward","getInvulnerableStep(addReward(reward)) != 100");
		}else{
			if( !(getInvulnerableStep() == invul) )
				Contractor.defaultContractor().postconditionError("Joueur","addReward","getInvulnerableStep(addReward(reward)) != getInvulnerableStep()");
		}
	}
	@Override
	public void setColonne(int colonne, TerrainService terrain) {
		//PRE
		if( !(colonne >= 2) )
			Contractor.defaultContractor().preconditionError("Joueur","setColonne","colonne < 2");
		if( !(colonne < terrain.getNbColonnes()) )
			Contractor.defaultContractor().preconditionError("Joueur","setColonne","colonne >= terrain.getNbColonnes()");
		
		//SAVE
		int ligne = getLigne();
		boolean passeB = passeBombe();
		boolean passeM = passeMuraille();
		int nbBP = getNbBombePosable();
		int force = getForce();
		int invul = getInvulnerableStep();
		boolean isVivant = isVivant();
		
		//APPEL METHODE
		checkInvariant();
		super.setColonne(colonne, terrain);
		checkInvariant();
		
		//POST
		if( !(getColonne() == colonne) )
			Contractor.defaultContractor().postconditionError("Joueur","setColonne","getColonne(setColonne(c)) != c");
		if( !(getLigne() == ligne) )
			Contractor.defaultContractor().postconditionError("Joueur","setColonne","getLigne(setColonne(c)) != getLigne()");
		if( !(getForce() == force) )
			Contractor.defaultContractor().postconditionError("Joueur","setColonne","getForce(setVivant(b)) != getForce()");
		if( !(passeMuraille() == passeM) )
			Contractor.defaultContractor().postconditionError("Joueur","setColonne","passeMuraille(setVivant(b)) != passeMuraille()");
		if( !(passeBombe() == passeB) )
			Contractor.defaultContractor().postconditionError("Joueur","setColonne","passeBombe(setVivant(b)) != passeBombe()");
		if( !(getNbBombePosable() == nbBP) )
			Contractor.defaultContractor().postconditionError("Joueur","setColonne","getNbBombePosable(setVivant(b)) != getNbBombePosable()");
		if( !(getInvulnerableStep() == invul) )
			Contractor.defaultContractor().postconditionError("Joueur","setColonne","getInvulnerableStep(setVivant(b)) != getInvulnerableStep()");
		if( !(isVivant() == isVivant) )
			Contractor.defaultContractor().postconditionError("Joueur","setColonne","isVivant(setVivant(b)) != isVivant()");
	}
	@Override
	public void setLigne(int ligne, TerrainService terrain) {
		//PRE
		if( !(ligne >= 2) )
			Contractor.defaultContractor().preconditionError("Joueur","setLigne","ligne < 2");
		if( !(ligne < terrain.getNbLignes()) )
			Contractor.defaultContractor().preconditionError("Joueur","setLigne","ligne >= terrain.getNbLignes()");
		
		//SAVE
		int colonne = getColonne();
		boolean passeB = passeBombe();
		boolean passeM = passeMuraille();
		int nbBP = getNbBombePosable();
		int force = getForce();
		int invul = getInvulnerableStep();
		boolean isVivant = isVivant();
		
		//APPEL METHODE
		checkInvariant();
		super.setLigne(ligne, terrain);
		checkInvariant();
		
		//POST
		if( !(getLigne() == ligne) )
			Contractor.defaultContractor().postconditionError("Joueur","setLigne","getLigne(setLigne(l)) != l");
		if( !(getColonne() == colonne) )
			Contractor.defaultContractor().postconditionError("Joueur","setLigne","getColonne(setLigne(l)) != getColonne()");
		if( !(getForce() == force) )
			Contractor.defaultContractor().postconditionError("Joueur","setLigne","getForce(setVivant(b)) != getForce()");
		if( !(passeMuraille() == passeM) )
			Contractor.defaultContractor().postconditionError("Joueur","setLigne","passeMuraille(setVivant(b)) != passeMuraille()");
		if( !(passeBombe() == passeB) )
			Contractor.defaultContractor().postconditionError("Joueur","setLigne","passeBombe(setVivant(b)) != passeBombe()");
		if( !(getNbBombePosable() == nbBP) )
			Contractor.defaultContractor().postconditionError("Joueur","setLigne","getNbBombePosable(setVivant(b)) != getNbBombePosable()");
		if( !(getInvulnerableStep() == invul) )
			Contractor.defaultContractor().postconditionError("Joueur","setLigne","getInvulnerableStep(setVivant(b)) != getInvulnerableStep()");
		if( !(isVivant() == isVivant) )
			Contractor.defaultContractor().postconditionError("Joueur","setLigne","isVivant(setVivant(b)) != isVivant()");
	}
}
