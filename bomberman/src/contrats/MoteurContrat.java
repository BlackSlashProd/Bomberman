package contrats;

import java.util.Vector;

import services.BombeService;
import services.JoueurService;
import services.KidnappeurService;
import services.MoteurService;
import services.TerrainService;
import services.VilainService;
import utils.Action;
import utils.BlocType;
import utils.Contractor;
import utils.Gagnant;
import delegates.MoteurDecorator;

public class MoteurContrat extends MoteurDecorator {
	
	public MoteurContrat(MoteurService moteur) {
		super(moteur);
	}
	private void checkInvariant(){
		if( !(getStepRestants() >= 0) )
			Contractor.defaultContractor().invariantError("Moteur","getStepRestants() < 0");
		if( !(isGameOver() == ((getStepRestants() == 0) || !getJoueur().isVivant()) || !getKidnappeur().isVivant()) )
			Contractor.defaultContractor().invariantError("Moteur","isGameOver() != ((getStepRestants() == 0) || !getJoueur().isVivant() || !getKidnappeur().isVivant()))");
		if( !(getNbBombes() == getBombesNum().size()) )
			Contractor.defaultContractor().invariantError("Moteur","getNbBombes() != |getBombesNum(M)|");
		if( !(getNbVilains() == getVilainsNum().size()) )
			Contractor.defaultContractor().invariantError("Moteur","getNbVilains() != |getVilainsNum(M)|");
		for (int n : getBombesNum()) {
			if( !(bombeExiste(n) == true) )
			Contractor.defaultContractor().invariantError("Moteur","bombeExiste("+n+") n'appartient pas a getBombesNum()");
		}
		for (int n : getVilainsNum()) {
			if( !(vilainExiste(n) == true) )
			Contractor.defaultContractor().invariantError("Moteur","vilainExiste("+n+") n'appartient pas a getVilainsNum()");
		}
		if(isGameOver() == true){
			switch (getGagnant()) {
			case Joueur:
				if( !( getKidnappeur().isVivant() == false && getJoueur().isVivant() == true) )
					Contractor.defaultContractor().invariantError("Moteur","(getGagnant() == Gagnant.Joueur) != !getKidnappeur().isVivant() && getJoueur().isVivant()");
				break;
			case Kidnappeur:
				if( !( getKidnappeur().isVivant() == true && getJoueur().isVivant() == false ) )
				Contractor.defaultContractor().invariantError("Moteur","(getGagnant() == Gagnant.Kidnappeur) != getKidnappeur().isVivant() && !getJoueur().isVivant()");
				break;
			case Aucun:
				if( !( getJoueur().isVivant() == false && getKidnappeur().isVivant() == false) )
					Contractor.defaultContractor().invariantError("Moteur","(getGagnant() == Gagnant.Aucun) != !getJoueur().isVivant() && !getKidnappeur().isVivant()");
				break;
			default:
				break;
			}
		}
	}
	@Override
	public boolean isGameOver() {
		return super.isGameOver();
	}
	@Override
	public Gagnant getGagnant() {
		if( !(isGameOver() == true) )
			Contractor.defaultContractor().preconditionError("Moteur","getGagnant","isGameOver == false");
		return super.getGagnant();
	}
	@Override
	public JoueurService getJoueur() {
		return super.getJoueur();
	}
	@Override
	public KidnappeurService getKidnappeur() {
		return super.getKidnappeur();
	}
	@Override
	public int getStepRestants() {
		return super.getStepRestants();
	}
	@Override
	public TerrainService getTerrain() {
		return super.getTerrain();
	}
	@Override
	public boolean hasRemovedExplosions() {
		return super.hasRemovedExplosions();
	}
	@Override
	public boolean bombeExiste(int n) {
		return super.bombeExiste(n);
	}
	@Override
	public BombeService getBombe(int n) {
		if( !(bombeExiste(n) == true) )
			Contractor.defaultContractor().preconditionError("Moteur","getBombe","bombeExiste("+n+") == false");
		return super.getBombe(n);
	}
	@Override
	public Vector<Integer> getBombesNum() {
		return super.getBombesNum();
	}
	@Override
	public int getNbBombes() {
		return super.getNbBombes();
	}
	@Override
	public boolean vilainExiste(int n) {
		return super.vilainExiste(n);
	}
	@Override
	public VilainService getVilain(int n) {
		if( !(vilainExiste(n) == true) )
			Contractor.defaultContractor().preconditionError("Moteur","getVilain","vilainExiste("+n+") == false");
		return super.getVilain(n);
	}
	@Override
	public int getNbVilains() {
		return super.getNbVilains();
	}
	@Override
	public Vector<Integer> getVilainsNum() {
		return super.getVilainsNum();
	}
	@Override
	public boolean isInRange(int ligne, int colonne, int numBombe) {
		if( !(bombeExiste(numBombe) == true) )
			Contractor.defaultContractor().preconditionError("Moteur","isInRange","bombeExiste("+numBombe+") == false");
		return super.isInRange(ligne, colonne, numBombe);
	}
	@Override
	public void init(int nbSteps) {
		//PRE
		if( !(nbSteps > 0) )
			Contractor.defaultContractor().preconditionError("Moteur","init","nbSteps <= 0");
		
		//APPEL METHODE
		super.init(nbSteps);
		
		//POST
		if( !(getStepRestants() == nbSteps) )
			Contractor.defaultContractor().postconditionError("Moteur","init","getStepRestants() != nbSteps");
		if( !(getJoueur().getColonne() == 2) )
			Contractor.defaultContractor().postconditionError("Moteur","init","getJoueur().getColonne() != 2");
		if( !(getJoueur().getLigne() == 2) )
			Contractor.defaultContractor().postconditionError("Moteur","init","getJoueur().getLigne() != 2");
		if( !(getKidnappeur().getColonne() == getTerrain().getNbColonnes() - 1) )
			Contractor.defaultContractor().postconditionError("Moteur","init","getKidnappeur().getColonne() != getTerrain().getNbColonnes() - 1");
		if( !(getKidnappeur().getLigne() == getTerrain().getNbLignes() - 1) )
			Contractor.defaultContractor().postconditionError("Moteur","init","getKidnappeur().getLigne() != getTerrain().getNbLignes() - 1");
		if( !(getBombesNum().isEmpty()) )
			Contractor.defaultContractor().postconditionError("Moteur","init","getBombesNum() != isEmpty()");
		if( !(getVilainsNum().size() == 4) )
			Contractor.defaultContractor().postconditionError("Moteur","init","getVilainsNum().size() != 4");
		if( !(getTerrain().getNbLignes() == 13) )
			Contractor.defaultContractor().postconditionError("Moteur","init","getTerrain().getNbLignes() != 13");
		if( !(getTerrain().getNbColonnes() == 15) )
			Contractor.defaultContractor().postconditionError("Moteur","init","getTerrain().getNbColonnes() != 15");
		if( !(hasRemovedExplosions() == true) )
			Contractor.defaultContractor().postconditionError("Moteur","init","hasRemovedExplosions() != true");
		
		checkInvariant();
	}
	@Override
	public void setRemovedExplosions(boolean b) {
		//SAVE
		JoueurService joueur = getJoueur();
		KidnappeurService kidnappeur = getKidnappeur();
		Vector<Integer> nbVilains = getVilainsNum();
		Vector<VilainService> vilains = new Vector<VilainService>();
		for (Integer numVilain : getVilainsNum()) {
			vilains.add(getVilain(numVilain));
		}
		Vector<Integer> nbBombes = getBombesNum();
		Vector<BombeService> bombes = new Vector<BombeService>();
		for (Integer numBombe : getBombesNum()) {
			bombes.add(getBombe(numBombe));
		}
		TerrainService terrain = getTerrain();
		int steps = getStepRestants();
		
		//APPEL METHODE
		checkInvariant();
		super.setRemovedExplosions(b);
		checkInvariant();
		
		//POST
		if( !(getJoueur().equals(joueur)) )
			Contractor.defaultContractor().postconditionError("Moteur","setRemovedExplosions","getJoueur(setRemovedExplosions()) != getJoueur()");
		if( !(getKidnappeur().equals(kidnappeur)) )
			Contractor.defaultContractor().postconditionError("Moteur","setRemovedExplosions","getKidnappeur(setRemovedExplosions()) != getKidnappeur()");
		if( !(getVilainsNum().equals(nbVilains)) )
			Contractor.defaultContractor().postconditionError("Moteur","setRemovedExplosions","getJoueur(setRemovedExplosions()) != getJoueur()");
		for (Integer numVilain : getVilainsNum()) {
			if( !(getVilain(numVilain) == vilains.get(numVilain)) )
				Contractor.defaultContractor().postconditionError("Moteur","setRemovedExplosions","getVilain(setRemovedExplosions(), "+numVilain+") != getVilain("+numVilain+")");
		}
		if( !(getBombesNum().equals(nbBombes)) )
			Contractor.defaultContractor().postconditionError("Moteur","setRemovedExplosions","getBombesNum(setRemovedExplosions()) != getBombesNum()");
		for (Integer numBombe : getBombesNum()) {
			if( !(getBombe(numBombe) == bombes.get(numBombe)) )
				Contractor.defaultContractor().postconditionError("Moteur","setRemovedExplosions","getBombe(setRemovedExplosions(), "+numBombe+") != getBombe("+numBombe+")");
		}
		if( !(getTerrain().equals(terrain)) )
			Contractor.defaultContractor().postconditionError("Moteur","setRemovedExplosions","getTerrain(setRemovedExplosions()) != getTerrain()");
		if( !(getStepRestants() == steps) )
			Contractor.defaultContractor().postconditionError("Moteur","setRemovedExplosions","getStepRestants(setRemovedExplosions()) != getStepRestants()");
		if( !(hasRemovedExplosions() == b) )
			Contractor.defaultContractor().postconditionError("Moteur","setRemovedExplosions","hasRemovedExplosions(setRemovedExplosions()) != b");
	}
	@Override
	public void removeExplosions() {
		//SAVE
		JoueurService joueur = getJoueur();
		KidnappeurService kidnappeur = getKidnappeur();
		Vector<Integer> nbVilains = getVilainsNum();
		Vector<VilainService> vilains = new Vector<VilainService>();
		for (Integer numVilain : getVilainsNum()) {
			vilains.add(getVilain(numVilain));
		}
		Vector<Integer> nbBombes = getBombesNum();
		Vector<BombeService> bombes = new Vector<BombeService>();
		for (Integer numBombe : getBombesNum()) {
			bombes.add(getBombe(numBombe));
		}
		TerrainService terrain = getTerrain();
		int steps = getStepRestants();
		
		//APPEL METHODE
		checkInvariant();
		super.removeExplosions();
		checkInvariant();
		
		//POST
		if( !(getJoueur().equals(joueur)) )
			Contractor.defaultContractor().postconditionError("Moteur","removeExplosions","getJoueur(setRemovedExplosions()) != getJoueur()");
		if( !(getKidnappeur().equals(kidnappeur)) )
			Contractor.defaultContractor().postconditionError("Moteur","removeExplosions","getKidnappeur(setRemovedExplosions()) != getKidnappeur()");
		if( !(getVilainsNum().equals(nbVilains)) )
			Contractor.defaultContractor().postconditionError("Moteur","removeExplosions","getJoueur(setRemovedExplosions()) != getJoueur()");
		for (Integer numVilain : getVilainsNum()) {
			if( !(getVilain(numVilain) == vilains.get(numVilain)) )
				Contractor.defaultContractor().postconditionError("Moteur","removeExplosions","getVilain(setRemovedExplosions(), "+numVilain+") != getVilain("+numVilain+")");
		}
		if( !(getBombesNum().equals(nbBombes)) )
			Contractor.defaultContractor().postconditionError("Moteur","removeExplosions","getBombesNum(setRemovedExplosions()) != getBombesNum()");
		for (Integer numBombe : getBombesNum()) {
			if( !(getBombe(numBombe) == bombes.get(numBombe)) )
				Contractor.defaultContractor().postconditionError("Moteur","removeExplosions","getBombe(setRemovedExplosions(), "+numBombe+") != getBombe("+numBombe+")");
		}
		if( !(getTerrain().equals(terrain)) )
			Contractor.defaultContractor().postconditionError("Moteur","removeExplosions","getTerrain(setRemovedExplosions()) != getTerrain()");
		if( !(getStepRestants() == steps) )
			Contractor.defaultContractor().postconditionError("Moteur","removeExplosions","getStepRestants(setRemovedExplosions()) != getStepRestants()");
		if( !(hasRemovedExplosions() == true) )
			Contractor.defaultContractor().postconditionError("Moteur","removeExplosions","hasRemovedExplosions(setRemovedExplosions()) != true");
		for(int i = 1; i <= getTerrain().getNbLignes(); i++){
			for(int j = 1; i <= getTerrain().getNbColonnes(); j++){
				if( !(getTerrain().getBloc(i, j).getType() != BlocType.Explosion) )
					Contractor.defaultContractor().postconditionError("Moteur","removeExplosions","getTerrain().getBloc("+i+", "+j+") == BlocType.Explosion");
			}
		}
	}
	@Override
	public void step(Action a) {
		//PRE
		if( !(a != null) )
			Contractor.defaultContractor().preconditionError("Moteur","step", "a == null");
		
		//SAVE
		JoueurService joueur = getJoueur();
		
		int jl = joueur.getLigne();
		int jc = joueur.getColonne();
		int jinv = joueur.getInvulnerableStep();
		KidnappeurService kidnappeur = getKidnappeur();
		int kl = kidnappeur.getLigne();
		int kc = kidnappeur.getColonne();
		int kinv = kidnappeur.getInvulnerableStep();
		Vector<Integer> vilainsNums = getVilainsNum();
		Vector<VilainService> vilains = new Vector<VilainService>();
		for (Integer numVilain : getVilainsNum()) {
			vilains.add(getVilain(numVilain));
		}
		//int nbBombesPosables = getJoueur().getNbBombePosable();
		//Vector<Integer> bombesNums = getBombesNum();
		Vector<BombeService> bombes = new Vector<BombeService>();
		for (Integer numBombe : getBombesNum()) {
			bombes.add(getBombe(numBombe));
		}
		TerrainService terrain = getTerrain();
		int steps = getStepRestants();
		boolean removed = hasRemovedExplosions();
		
		Vector<BombeService> explose = new Vector<BombeService>();
		Vector<BombeService> explosePas = new Vector<BombeService>();
		Vector<Integer> timers = new Vector<Integer>();
		boolean[] jInRange = {false, false};
		boolean[] kInRange = {false, false};
		boolean[] vInRange = {false, false, false, false, false, false, false, false};
		int nb = 0;
		int i = 0;
		for (BombeService bombe : bombes) {
			if(bombe.getTimer() == 1){
				explose.add(bombe);
				jInRange[nb] = isInRange(jl, jc, bombe.getNum());
				kInRange[nb] = isInRange(kl, kc, bombe.getNum());
				for (VilainService vilain : vilains) {
					vInRange[i] = isInRange(vilain.getLigne(), vilain.getColonne(), bombe.getNum());
					i++;
				}
				nb++;
			}else{
				explosePas.add(bombe);
				timers.add(bombe.getTimer());
			}
				
		}
		
		//APPEL METHODE
		checkInvariant();
		super.step(a);
		checkInvariant();
		
		//POST
		
		// observateurs
		if( !(getJoueur().equals(joueur)) )
			Contractor.defaultContractor().postconditionError("Moteur","step","getJoueur(step("+a.toString()+")) != getJoueur()");
		if( !(getKidnappeur().equals(kidnappeur)) )
			Contractor.defaultContractor().postconditionError("Moteur","step","getKidnappeur(step("+a.toString()+")) != getKidnappeur()");
		if( !(getTerrain().equals(terrain)) )
			Contractor.defaultContractor().postconditionError("Moteur","step","getTerrain(step("+a.toString()+")) != getTerrain()");
		if( !(getStepRestants() == steps-1) )
			Contractor.defaultContractor().postconditionError("Moteur","step","getStepRestants(step("+a.toString()+")) != getStepRestants()-1");
		if( !(hasRemovedExplosions() == removed) )
			Contractor.defaultContractor().postconditionError("Moteur","step","hasRemovedExplosions(step("+a.toString()+")) != hasRemovedExplosions()");
		
		// Gestion des explosions (gestion des morts)
		if(explose.size() > 0){
			int nk = 0;
			int ni = 1;
			for (BombeService bombe : explose) {
				if(bombeExiste(bombe.getNum()))
					Contractor.defaultContractor().postconditionError("Moteur","step","la bombe "+bombe.getNum()+" ne devrait plus exister");
				if(jinv == 0 && jInRange[nk])
					if( joueur.isVivant() )
						Contractor.defaultContractor().postconditionError("Moteur","step","le joueur devrait etre mort dans l'explosion d'une bombe");
				if(kinv == 0 && kInRange[nk])
					if( kidnappeur.isVivant() )
						Contractor.defaultContractor().postconditionError("Moteur","step","le kidnappeur devrait etre mort dans l'explosion d'une bombe");
				for(int k = 0; k < vInRange.length; k++){
					if(vInRange[ni]){
						if( vilainExiste(ni) )
							Contractor.defaultContractor().postconditionError("Moteur","step","le vilain "+(ni)+" devrait etre mort dans l'explosion d'une bombe");
						ni++;
					}
				}
				nk++;
			}
		}else{
			// si il n'y a pas d'explosions a ce tour, le nombre de vilains ne varie pas
			if( !(getVilainsNum().equals(vilainsNums)) )
				Contractor.defaultContractor().postconditionError("Moteur","step","getVilainsNum(step("+a.toString()+")) != getVilainsNum()");
		}
		
		// Decrementation des bombes
		int it = 0;
		for (BombeService b : bombes) {
			if(bombeExiste(b.getNum())){
				if( !(timers.get(it)-1 == getBombe(b.getNum()).getTimer()) )
					Contractor.defaultContractor().postconditionError("Moteur","step","le timer de la bombe "+b.getNum()+" n'a pas ete decrementee "+(timers.get(it)-1)+"/"+getBombe(b.getNum()).getTimer());
				it++;
			}
		}
		
		// Gestion du joueur (deplacement/bombe) :
		switch (a) {
		case Rien:
			// le joueur ne bouge pas
			if( !(getJoueur().getLigne() == jl) )
				Contractor.defaultContractor().postconditionError("Moteur","step","getLigne(getJoueur(step("+a.toString()+"))) != getLigne(getJoueur())");
			if( !(getJoueur().getColonne() == jc) )
				Contractor.defaultContractor().postconditionError("Moteur","step","getColonne(getJoueur(step("+a.toString()+"))) != getColonne(getJoueur())");
			break;
		case Bombe:
			// le joueur ne bouge pas
			if( !(getJoueur().getLigne() == jl) )
				Contractor.defaultContractor().postconditionError("Moteur","step","getLigne(getJoueur(step("+a.toString()+"))) != getLigne(getJoueur())");
			if( !(getJoueur().getColonne() == jc) )
				Contractor.defaultContractor().postconditionError("Moteur","step","getColonne(getJoueur(step("+a.toString()+"))) != getColonne(getJoueur())");
			// (au moins) une nouvelle bombe est cree (si le joueur pouvait en poser une)
			// TODO: il faut tester en plus si il y a deja une bombe a cet endroit, ou un mur
			// if(nbBombesPosables > 0 )//&& terrain.)
			//	  if( !(bombesNums.size() < getNbBombes()) )
			//		 Contractor.defaultContractor().postconditionError("Moteur","step","getNbBombes(getJoueur(step("+a.toString()+"))) != getNbBombes(getJoueur())");
			break;
		case Haut:
				switch (terrain.getBloc(jl-1, jc).getType()) {
				case MurMetal:
					if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc) )
						Contractor.defaultContractor().postconditionError("Moteur","step","le joueur n'aurait pas du pouvoir aller en haut a ce tour, les murs en metal ne sont pas franchissables");
					break;
				case MurBrique:
					if(getJoueur().passeMuraille()){
						if( !(getJoueur().getLigne() == jl-1 && getJoueur().getColonne() == jc) )
							Contractor.defaultContractor().postconditionError("Moteur","step","le joueur aurait du pouvoir aller en haut a ce tour, il a le bonus pour passer a travers les murs");
					}else{
						if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc) )
							Contractor.defaultContractor().postconditionError("Moteur","step","le joueur n'aurait pas du pouvoir aller en haut a ce tour, il n'a pas le bonus pour passer a travers les murs");
					}
					break;
				case Explosion:
				case Vide:
					if(explosePas.size() > 0){
						for (BombeService b : explosePas) {
							if(b.getLigne() == jl-1 && b.getColonne() == jc){
								if(getJoueur().passeBombe()){
									if( !(getJoueur().getLigne() == jl-1 && getJoueur().getColonne() == jc) )
										Contractor.defaultContractor().postconditionError("Moteur","step","le joueur aurait du pouvoir aller en haut a ce tour, il a le bonus pour passer a travers les bombes");
								}else{
									if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc) )
										Contractor.defaultContractor().postconditionError("Moteur","step","le joueur n'aurait pas du pouvoir aller en haut a ce tour, il n'a pas le bonus pour passer a travers les bombes");
								}
							}
						}
					}else{
						if( !(getJoueur().getLigne() == jl-1 && getJoueur().getColonne() == jc) )
							Contractor.defaultContractor().postconditionError("Moteur","step","le joueur aurait du pouvoir aller en haut a ce tour, la case est vide !");
					}
					break;
				default:
					break;
				}
			break;
		case Bas:
			switch (terrain.getBloc(jl+1, jc).getType()) {
			case MurMetal:
				if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc) )
					Contractor.defaultContractor().postconditionError("Moteur","step","le joueur n'aurait pas du pouvoir aller en bas a ce tour, les murs en metal ne sont pas franchissables");
				break;
			case MurBrique:
				if(getJoueur().passeMuraille()){
					if( !(getJoueur().getLigne() == jl+1 && getJoueur().getColonne() == jc) )
						Contractor.defaultContractor().postconditionError("Moteur","step","le joueur aurait du pouvoir aller en bas a ce tour, il a le bonus pour passer a travers les murs");
				}else{
					if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc) )
						Contractor.defaultContractor().postconditionError("Moteur","step","le joueur n'aurait pas du pouvoir aller en bas a ce tour, il n'a pas le bonus pour passer a travers les murs");
				}
				break;
			case Explosion:
			case Vide:
				if(explosePas.size() > 0){
					for (BombeService b : explosePas) {
						if(b.getLigne() == jl+1 && b.getColonne() == jc){
							if(getJoueur().passeBombe()){
								if( !(getJoueur().getLigne() == jl+1 && getJoueur().getColonne() == jc) )
									Contractor.defaultContractor().postconditionError("Moteur","step","le joueur aurait du pouvoir aller en bas a ce tour, il a le bonus pour passer a travers les bombes");
							}else{
								if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc) )
									Contractor.defaultContractor().postconditionError("Moteur","step","le joueur n'aurait pas du pouvoir aller en bas a ce tour, il n'a pas le bonus pour passer a travers les bombes");
							}
						}
					}
				}else{
					if( !(getJoueur().getLigne() == jl+1 && getJoueur().getColonne() == jc) )
						Contractor.defaultContractor().postconditionError("Moteur","step","le joueur aurait du pouvoir aller en bas a ce tour, la case est vide !");
				}
				break;
			default:
				break;
			}
			break;
		case Droite:
			switch (terrain.getBloc(jl, jc+1).getType()) {
			case MurMetal:
				if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc) )
					Contractor.defaultContractor().postconditionError("Moteur","step","le joueur n'aurait pas du pouvoir aller a droite a ce tour, les murs en metal ne sont pas franchissables");
				break;
			case MurBrique:
				if(getJoueur().passeMuraille()){
					if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc+1) )
						Contractor.defaultContractor().postconditionError("Moteur","step","le joueur aurait du pouvoir aller a droite a ce tour, il a le bonus pour passer a travers les murs");
				}else{
					if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc) )
						Contractor.defaultContractor().postconditionError("Moteur","step","le joueur n'aurait pas du pouvoir aller a droite a ce tour, il n'a pas le bonus pour passer a travers les murs");
				}
				break;
			case Explosion:
			case Vide:
				if(explosePas.size() > 0){
					for (BombeService b : explosePas) {
						if(b.getLigne() == jl && b.getColonne() == jc+1){
							if(getJoueur().passeBombe()){
								if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc+1) )
									Contractor.defaultContractor().postconditionError("Moteur","step","le joueur aurait du pouvoir aller a droite a ce tour, il a le bonus pour passer a travers les bombes");
							}else{
								if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc) )
									Contractor.defaultContractor().postconditionError("Moteur","step","le joueur n'aurait pas du pouvoir aller a droite a ce tour, il n'a pas le bonus pour passer a travers les bombes");
							}
						}
					}
				}else{
					if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc+1) )
						Contractor.defaultContractor().postconditionError("Moteur","step","le joueur aurait du pouvoir aller a droite a ce tour, la case est vide !");
				}
			}
			break;
		case Gauche:
			switch (terrain.getBloc(jl, jc-1).getType()) {
			case MurMetal:
				if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc) )
					Contractor.defaultContractor().postconditionError("Moteur","step","le joueur n'aurait pas du pouvoir aller a gauche a ce tour, les murs en metal ne sont pas franchissables");
				break;
			case MurBrique:
				if(getJoueur().passeMuraille()){
					if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc-1) )
						Contractor.defaultContractor().postconditionError("Moteur","step","le joueur aurait du pouvoir aller a gauche a ce tour, il a le bonus pour passer a travers les murs");
				}else{
					if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc) )
						Contractor.defaultContractor().postconditionError("Moteur","step","le joueur n'aurait pas du pouvoir aller a gauche a ce tour, il n'a pas le bonus pour passer a travers les murs");
				}
				break;
			case Explosion:
			case Vide:
				if(explosePas.size() > 0){
					for (BombeService b : explosePas) {
						if(b.getLigne() == jl && b.getColonne() == jc-1){
							if(getJoueur().passeBombe()){
								if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc-1) )
									Contractor.defaultContractor().postconditionError("Moteur","step","le joueur aurait du pouvoir aller a gauche a ce tour, il a le bonus pour passer a travers les bombes");
							}else{
								if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc) )
									Contractor.defaultContractor().postconditionError("Moteur","step","le joueur n'aurait pas du pouvoir aller a gauche a ce tour, il n'a pas le bonus pour passer a travers les bombes");
							}
						}
					}
				}else{
					if( !(getJoueur().getLigne() == jl && getJoueur().getColonne() == jc-1) )
						Contractor.defaultContractor().postconditionError("Moteur","step","le joueur aurait du pouvoir aller a gauche a ce tour, la case est vide !");
				}
			}
			break;
		default:
			break;
		}
		
		
		// Gestion du kidnappeur (deplacement/bombe) :
		//    il est difficile de tester correctement les actions du kidnappeur car nous n'avons pas de moyen de connaitre l'action
		//    qu'il a entreprise a ce tour ! Nous avons choisi de ne pas inclure ces tests dans le contrat (en sachant que notre programme
		//    sera potentiellement moins fiable sans ces tests).
		
		
		// Gestion des vilains (qui mangent les joueurs) :
		//    pour les memes raisons que ci dessus (voir Gestion du kidnappeur), nous ne traitons pas les deplacements des vilains.
		for (VilainService vilain : vilains) {
			if(getJoueur().getLigne() == vilain.getLigne() && getJoueur().getColonne() == vilain.getColonne()){
				if( getJoueur().isVivant() )
					Contractor.defaultContractor().postconditionError("Moteur","step","le joueur devrait etre mort mange par un vilain");
			}
			if(getKidnappeur().getLigne() == vilain.getLigne() && getKidnappeur().getColonne() == vilain.getColonne()){
				if( getKidnappeur().isVivant() )
					Contractor.defaultContractor().postconditionError("Moteur","step","le kidnappeur devrait etre mort mange par un vilain");
			}	
		}
	}
}
