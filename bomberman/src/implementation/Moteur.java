package implementation;

import implementation.Bombe;

import java.util.Random;
import java.util.Vector;

import contrats.BombeContrat;
import contrats.JoueurContrat;
import contrats.KidnappeurContrat;
import contrats.TerrainContrat;
import contrats.VilainContrat;
import services.BombeService;
import services.JoueurService;
import services.KidnappeurService;
import services.MoteurService;
import services.TerrainService;
import services.VilainService;
import utils.Action;
import utils.BlocType;
import utils.BlocReward;
import utils.Gagnant;
import utils.TypeVilain;


public class Moteur implements MoteurService{
	private TerrainService terrain;
	private JoueurService joueur;
	private KidnappeurService kidnappeur;
	private Vector<VilainService> vilains;
	private Vector<BombeService> bombes;
	private int nbSteps;
	private Random rand;
	private boolean removedExplosions;

	public Moteur(){}
	
	/*
	 * affichage du terrain dans la console.
	 */
	public void affichageTerrainConsole(){
		for(int ligne = 1; ligne<=terrain.getNbLignes(); ligne++){
			for(int colonne = 1; colonne<=terrain.getNbColonnes(); colonne++){
				String print = "";
				switch (terrain.getBloc(ligne,colonne).getType()) {
				case Explosion:
					print = " * ";
					break;
				case MurMetal:
					print = "[#]";
					break;
				case MurBrique:
					print = "[ ]";
					break;
				case Vide:
					print = "   ";
					break;
				default:
					break;
				}
				switch (terrain.getBloc(ligne,colonne).getReward()) {
				case FireUp:
					if(terrain.getBloc(ligne,colonne).getType() == BlocType.MurBrique)
						print = "[f]";
					else
						print = " f ";
					break;
				case BombUp:
					if(terrain.getBloc(ligne,colonne).getType() == BlocType.MurBrique)
						print = "[b]";
					else
						print = " b ";
					break;
				case FireSuit:
					if(terrain.getBloc(ligne,colonne).getType() == BlocType.MurBrique)
						print = "[F]";
					else
						print = " F ";
					break;
				case BombPass:
					if(terrain.getBloc(ligne,colonne).getType() == BlocType.MurBrique)
						print = "[B]";
					else
						print = " B ";
					break;
				case WallPass:
					if(terrain.getBloc(ligne,colonne).getType() == BlocType.MurBrique)
						print = "[W]";
					else
						print = " W ";
					break;
				default:
					break;
				}
				for (VilainService v : vilains) {
					if(v.getLigne()==ligne && v.getColonne()==colonne){
						if(terrain.getBloc(ligne,colonne).getType() == BlocType.MurBrique)
							print = "[V]";
						else
							print = " V ";
					}
				}
				for (BombeService b : bombes) {
					if(b.getLigne()==ligne && b.getColonne()==colonne)
						print = "("+b.getTimer()+")";
				}
				if(joueur.getLigne()==ligne && joueur.getColonne()==colonne)
					print = "{J}";
				if(kidnappeur.getLigne()==ligne && kidnappeur.getColonne()==colonne)
					print = "{K}";
				System.out.print(print);
			}
			System.out.println();
		}
	}
	
	/*
	 * explosion des bombes dont le timer est a 0 et decrementation du timer
	 * des autres bombes. l'explosion se fait en partant du centre et est
	 * stopee si isDestructible renvoie false sur le bloc en question.
	 * Les joueurs ou vilains qui sont dans le champ de l'explosion sont
	 * retires de la partie. (fonction isInRange)
	 */
	private void gestionBombes(){
		Vector<BombeService> toRemove = new Vector<BombeService>();
		for (BombeService bombe: bombes) {
			bombe.setTimer(bombe.getTimer()-1);
			if(bombe.getTimer()<=0){
				System.out.println("Une bombe de force "+bombe.getAmplitude()+" explose en ["+bombe.getLigne()+";"+bombe.getColonne()+"].");
				toRemove.add(bombe);
				bombe.getProprietaire().setNbBombePosable(bombe.getProprietaire().getNbBombePosable() + 1);
				/*
				 *  gestion de l'explosion en partant des coodonnees de la bombe et en allant vers l'exterieur
				 */
				Vector<VilainService> deadVilains = new Vector<VilainService>();
				// ligne, vers le haut
				for(int ligne = 0; ligne>=-bombe.getAmplitude(); ligne--){
					if(bombe.getLigne()+ligne>=0 && bombe.getLigne()+ligne<terrain.getNbLignes() && bombe.getColonne() >=0 && bombe.getColonne()<terrain.getNbColonnes()){
						if(terrain.getBloc(bombe.getLigne()+ligne, bombe.getColonne()).isDestructible() || terrain.getBloc(bombe.getLigne()+ligne, bombe.getColonne()).getType() == BlocType.Vide && terrain.getBloc(bombe.getLigne()+ligne, bombe.getColonne()).getReward() != BlocReward.Rien){
							for (VilainService vilain : vilains) {
								if(isInRange(vilain.getLigne(), vilain.getColonne(), bombe.getNum())){
									if(!deadVilains.contains(vilain))
										deadVilains.add(vilain);
								}
							}
							if(isInRange(joueur.getLigne(), joueur.getColonne(), bombe.getNum()) && joueur.getInvulnerableStep() == 0)
								joueur.setVivant(false);
							if(isInRange(kidnappeur.getLigne(), kidnappeur.getColonne(), bombe.getNum()) && kidnappeur.getInvulnerableStep() == 0)
								kidnappeur.setVivant(false);
							boolean stop = terrain.getBloc(bombe.getLigne()+ligne,bombe.getColonne()).getType() == BlocType.MurBrique|| terrain.getBloc(bombe.getLigne()+ligne, bombe.getColonne()).getType() == BlocType.Vide && terrain.getBloc(bombe.getLigne()+ligne, bombe.getColonne()).getReward() != BlocReward.Rien;
							if(terrain.getBloc(bombe.getLigne()+ligne,bombe.getColonne()).getType() == BlocType.Vide){
								terrain.getBloc(bombe.getLigne()+ligne,bombe.getColonne()).setReward(BlocReward.Rien);
							}
							terrain.getBloc(bombe.getLigne()+ligne, bombe.getColonne()).setType(BlocType.Explosion);
							if(stop)
								break;
						}else{
							// on rencontre un objet indestructible, la ligne d'explosion est stopee.
							break;
						}
					}
					else
						break;
				}
				// ligne, vers la bas
				for(int ligne = 0; ligne<=bombe.getAmplitude(); ligne++){
					if(bombe.getLigne()+ligne>=0 && bombe.getLigne()+ligne<terrain.getNbLignes() && bombe.getColonne() >=0 && bombe.getColonne()<terrain.getNbColonnes()){
						if(terrain.getBloc(bombe.getLigne()+ligne,bombe.getColonne()).isDestructible() || terrain.getBloc(bombe.getLigne()+ligne,bombe.getColonne()).getType() == BlocType.Vide && terrain.getBloc(bombe.getLigne()+ligne,bombe.getColonne()).getReward() != BlocReward.Rien){
							for (VilainService vilain : vilains) {
								if(isInRange(vilain.getLigne(), vilain.getColonne(), bombe.getNum())){
									if(!deadVilains.contains(vilain))
										deadVilains.add(vilain);
								}
							}
							if(isInRange(joueur.getLigne(), joueur.getColonne(), bombe.getNum()) && joueur.getInvulnerableStep() == 0)
								joueur.setVivant(false);
							if(isInRange(kidnappeur.getLigne(), kidnappeur.getColonne(), bombe.getNum()) && kidnappeur.getInvulnerableStep() == 0)
								kidnappeur.setVivant(false);
							boolean stop = terrain.getBloc(bombe.getLigne()+ligne,bombe.getColonne()).getType() == BlocType.MurBrique || terrain.getBloc(bombe.getLigne()+ligne,bombe.getColonne()).getType() == BlocType.Vide && terrain.getBloc(bombe.getLigne()+ligne,bombe.getColonne()).getReward() != BlocReward.Rien;
							if(terrain.getBloc(bombe.getLigne()+ligne,bombe.getColonne()).getType() == BlocType.Vide){
								terrain.getBloc(bombe.getLigne()+ligne,bombe.getColonne()).setReward(BlocReward.Rien);
							}
							terrain.getBloc(bombe.getLigne()+ligne,bombe.getColonne()).setType(BlocType.Explosion);
							if(stop)
								break;
						}else{
							// on rencontre un objet indestructible, la ligne d'explosion est stopee.
							break;
						}
					}
					else
						break;
				}
				// colonne, vers la gauche
				for(int colonne = 0; colonne>=-bombe.getAmplitude(); colonne--){
					if(bombe.getLigne()>=0 && bombe.getLigne()<terrain.getNbLignes() && bombe.getColonne()+colonne>=0 && bombe.getColonne()+colonne<terrain.getNbColonnes()){
						if(terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).isDestructible() || terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).getType() == BlocType.Vide && terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).getReward() != BlocReward.Rien){
							for (VilainService vilain : vilains) {
								if(isInRange(vilain.getLigne(), vilain.getColonne(), bombe.getNum())){
									if(!deadVilains.contains(vilain))
										deadVilains.add(vilain);
								}
							}
							if(isInRange(joueur.getLigne(), joueur.getColonne(), bombe.getNum()) && joueur.getInvulnerableStep() == 0)
								joueur.setVivant(false);
							if(isInRange(kidnappeur.getLigne(), kidnappeur.getColonne(), bombe.getNum()) && kidnappeur.getInvulnerableStep() == 0)
								kidnappeur.setVivant(false);
							boolean stop = terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).getType() == BlocType.MurBrique || terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).getType() == BlocType.Vide && terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).getReward() != BlocReward.Rien;
							if(terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).getType() == BlocType.Vide){
								terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).setReward(BlocReward.Rien);
							}
							terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).setType(BlocType.Explosion);
							if(stop)
								break;
						}else{
							// on rencontre un objet indestructible, la ligne d'explosion est stopee.
							break;
						}
					}
					else
						break;
				}
				// colonne, vers la droite
				for(int colonne = 0; colonne<=bombe.getAmplitude(); colonne++){
					if(bombe.getLigne()>=0 && bombe.getLigne()<terrain.getNbLignes() && bombe.getColonne()+colonne>=0 && bombe.getColonne()+colonne<terrain.getNbColonnes()){
						if(terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).isDestructible() || terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).getType() == BlocType.Vide && terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).getReward() != BlocReward.Rien){
							for (VilainService vilain : vilains) {
								if(isInRange(vilain.getLigne(), vilain.getColonne(), bombe.getNum())){
									if(!deadVilains.contains(vilain))
										deadVilains.add(vilain);
								}
							}
							if(isInRange(joueur.getLigne(), joueur.getColonne(), bombe.getNum()) && joueur.getInvulnerableStep() == 0)
								joueur.setVivant(false);
							if(isInRange(kidnappeur.getLigne(), kidnappeur.getColonne(), bombe.getNum()) && kidnappeur.getInvulnerableStep() == 0)
								kidnappeur.setVivant(false);
							boolean stop = terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).getType() == BlocType.MurBrique || terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).getType() == BlocType.Vide && terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).getReward() != BlocReward.Rien;
							if(terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).getType() == BlocType.Vide){
								terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).setReward(BlocReward.Rien);
							}
							terrain.getBloc(bombe.getLigne(),bombe.getColonne()+colonne).setType(BlocType.Explosion);
							if(stop)
								break;
						}else{
							// on rencontre un objet indestructible, la ligne d'explosion est stopee.
							break;
						}
					}
					else
						break;
				}
				if(deadVilains.size()>0) System.out.println(deadVilains.size()+" vilain(s) mort(s) dans l'explosion !");
				if(!joueur.isVivant()) System.out.println("Le joueur est mort dans l'explosion !");
				if(!kidnappeur.isVivant()) System.out.println("Le kidnappeur est mort dans l'explosion !");
				// retrait des vilains morts
				vilains.removeAll(deadVilains);
			}
		}
		bombes.removeAll(toRemove);
	}
	
	/*
	 * deplacement aleatoire des vilains et gestion de la mort des joueurs
	 * (si un vilain se retrouve sur la meme case qu'un joueur)
	 */
	private void gestionVilains(){
		boolean bombePresente = false;
		for (VilainService vilain : vilains) {
			switch (rand.nextInt(4)) {
			case 0:
				if(vilain.getColonne()-1 <= 1) break;
				vilain.setColonne(vilain.getColonne()-1, terrain);
				if(terrain.getBloc(vilain.getLigne(),vilain.getColonne()).getType() == BlocType.MurMetal){
					// obstacle infranchissable
					vilain.setColonne(vilain.getColonne()+1, terrain);
				}
				if(terrain.getBloc(vilain.getLigne(),vilain.getColonne()).getType() == BlocType.MurBrique){
					// obstacle franchissable par les Fantomes
					if(vilain.getType() == TypeVilain.BallonOrange){
						vilain.setColonne(vilain.getColonne()+1, terrain);
					}
				}
				bombePresente = false;
				for (BombeService bombe : bombes) {
					if(bombe.getLigne() == vilain.getLigne() && bombe.getColonne() == vilain.getColonne()){
						bombePresente = true;
						break;
					}
				}
				if(bombePresente){
					if(vilain.getType() == TypeVilain.BallonOrange){
						vilain.setColonne(vilain.getColonne()+1, terrain);
					}
				}
				break;
			case 1:
				if(vilain.getColonne()+1 >= terrain.getNbColonnes()) break;
				vilain.setColonne(vilain.getColonne()+1, terrain);
				if(terrain.getBloc(vilain.getLigne(),vilain.getColonne()).getType() == BlocType.MurMetal){
					// obstacle infranchissable
					vilain.setColonne(vilain.getColonne()-1, terrain);
				}
				if(terrain.getBloc(vilain.getLigne(),vilain.getColonne()).getType() == BlocType.MurBrique){
					// obstacle franchissable par les Fantomes
					if(vilain.getType() == TypeVilain.BallonOrange){
						vilain.setColonne(vilain.getColonne()-1, terrain);
					}
				}
				bombePresente = false;
				for (BombeService bombe : bombes) {
					if(bombe.getLigne() == vilain.getLigne() && bombe.getColonne() == vilain.getColonne()){
						bombePresente = true;
						break;
					}
				}
				if(bombePresente){
					if(vilain.getType() == TypeVilain.BallonOrange){
						vilain.setColonne(vilain.getColonne()-1, terrain);
					}
				}
				break;
			case 2:
				if(vilain.getLigne()+1 >= terrain.getNbLignes()) break;
				vilain.setLigne(vilain.getLigne()+1, terrain);
				if(terrain.getBloc(vilain.getLigne(),vilain.getColonne()).getType() == BlocType.MurMetal){
					// obstacle infranchissable
					vilain.setLigne(vilain.getLigne()-1, terrain);
				}
				if(terrain.getBloc(vilain.getLigne(),vilain.getColonne()).getType() == BlocType.MurBrique){
					// obstacle franchissable par les Fantomes
					if(vilain.getType() == TypeVilain.BallonOrange){
						vilain.setLigne(vilain.getLigne()-1, terrain);
					}
				}
				bombePresente = false;
				for (BombeService bombe : bombes) {
					if(bombe.getLigne() == vilain.getLigne() && bombe.getColonne() == vilain.getColonne()){
						bombePresente = true;
						break;
					}
				}
				if(bombePresente){
					if(vilain.getType() == TypeVilain.BallonOrange){
						vilain.setLigne(vilain.getLigne()-1, terrain);
					}
				}
				break;
			case 3:
				if(vilain.getLigne()-1 <= 1) break;
				vilain.setLigne(vilain.getLigne()-1, terrain);
				if(terrain.getBloc(vilain.getLigne(),vilain.getColonne()).getType() == BlocType.MurMetal){
					// obstacle infranchissable
					vilain.setLigne(vilain.getLigne()+1, terrain);
				}
				if(terrain.getBloc(vilain.getLigne(),vilain.getColonne()).getType() == BlocType.MurBrique){
					// obstacle franchissable par les Fantomes
					if(vilain.getType() == TypeVilain.BallonOrange){
						vilain.setLigne(vilain.getLigne()+1, terrain);
					}
				}
				bombePresente = false;
				for (BombeService bombe : bombes) {
					if(bombe.getLigne() == vilain.getLigne() && bombe.getColonne() == vilain.getColonne()){
						bombePresente = true;
						break;
					}
				}
				if(bombePresente){
					if(vilain.getType() == TypeVilain.BallonOrange){
						vilain.setLigne(vilain.getLigne()+1, terrain);
					}
				}
				break;
			default:
				System.out.println("Un vilain a fait une action inconnue.");
				break;
			}
			// le vilain mange les joueurs qui sont sur la meme case que lui OM NOM NOM NOM...
			if(joueur.getLigne() == vilain.getLigne() && joueur.getColonne() == vilain.getColonne()){
				joueur.setVivant(false);
				System.out.println("Le joueur a ete devore par un vilain !");
			}
			if(kidnappeur.getLigne() == vilain.getLigne() && kidnappeur.getColonne() == vilain.getColonne()){
				kidnappeur.setVivant(false);
				System.out.println("Le kidnappeur a ete devore par un vilain !");
			}
		}
	}
	
	/*
	 * deplacement des joueurs et gestion du ramassage des PowerUps
	 *  - si le joueur est un bot, il fait appel a la fonction "choisirAction"
	 *    pour determiner la meilleure action a faire a ce tour
	 *  - si c'est un humain, on se base sur l'action passee en parametre, qui provient de l'IHM
	 */
	private void gestionJoueurs(Action action){
		Vector<JoueurService> joueurs = new Vector<JoueurService>();
		joueurs.add(getJoueur());
		joueurs.add(getKidnappeur());
		String joueurNom;
		boolean bombePresente = false;
		for (JoueurService joueur : joueurs) {
			if(joueur.isVivant()){
				if(joueur instanceof KidnappeurService){
					action = choisirAction(joueur);
					System.out.println("Le kidnappeur a choisi l'action : "+action);
					joueurNom = "kidnappeur";
				}else{
					System.out.println("Le joueur a choisi l'action : "+action);
					joueurNom = "joueur";
				}
				if(joueur.getInvulnerableStep() > 0)
					joueur.decrementeInvulnerabilite();
				try{
					switch (action) {
					case Rien:
						System.out.println("Le "+joueurNom+" ne fait rien.");
						break;
					case Bombe:
						bombePresente = false;
						for (BombeService bombe : bombes) {
							if(bombe.getLigne() == joueur.getLigne() && bombe.getColonne() == joueur.getColonne()){
								bombePresente = true;
								break;
							}
						}
						if(joueur.getNbBombePosable() > 0 && terrain.getBloc(joueur.getLigne(), joueur.getColonne()).getType() == BlocType.Vide && !bombePresente){
							BombeService b = new BombeContrat(new Bombe());
							int numeroBombe = 0; // identifiant unique
							if(joueur instanceof KidnappeurService)
								numeroBombe = -getStepRestants();
							else
								numeroBombe = getStepRestants();
							b.init(joueur, numeroBombe, joueur.getLigne(), joueur.getColonne(), joueur.getForce(), terrain);
							bombes.add(b);
							System.out.println("Le "+joueurNom+" pose une bombe de force "+b.getAmplitude()+" en ["+joueur.getLigne()+";"+joueur.getColonne()+"] et d'id "+numeroBombe+".");
							joueur.setNbBombePosable(joueur.getNbBombePosable()-1);
						}else{
							System.out.println("Le "+joueurNom+" veut poser une bombe en ["+joueur.getLigne()+";"+joueur.getColonne()+"] mais echoue !");
						}
						break;
					case Gauche:
						if(joueur.getColonne()-1 < 2) break;
						joueur.setColonne(joueur.getColonne()-1, terrain);
						if(terrain.getBloc(joueur.getLigne(),joueur.getColonne()).getType() == BlocType.MurBrique && !joueur.passeMuraille() ||
								terrain.getBloc(joueur.getLigne(),joueur.getColonne()).getType() == BlocType.MurMetal){
							joueur.setColonne(joueur.getColonne()+1, terrain);
							System.out.println("Le "+joueurNom+" butte contre un mur en voulant aller a gauche");
							break;
						}
						bombePresente = false;
						for (BombeService bombe : bombes) {
							if(bombe.getLigne() == joueur.getLigne() && bombe.getColonne() == joueur.getColonne()){
								bombePresente = true;
								break;
							}
						}
						if(bombePresente){
							if(joueur.passeBombe()){
								System.out.println("Le "+joueurNom+" va a gauche en ["+joueur.getLigne()+";"+joueur.getColonne()+"].");
							}else{
								joueur.setColonne(joueur.getColonne()+1, terrain);
								System.out.println("Le "+joueurNom+" butte contre une bombe en voulant aller a gauche");
							}
							break;
						}
						System.out.println("Le "+joueurNom+" butte contre une bombe en voulant aller a gauche");
						break;
					case Droite:
						if(joueur.getColonne()+1 >= terrain.getNbColonnes()) break;
						joueur.setColonne(joueur.getColonne()+1, terrain);
						if(terrain.getBloc(joueur.getLigne(),joueur.getColonne()).getType() == BlocType.MurBrique && !joueur.passeMuraille() ||
								terrain.getBloc(joueur.getLigne(),joueur.getColonne()).getType() == BlocType.MurMetal){
							joueur.setColonne(joueur.getColonne()-1, terrain);
							System.out.println("Le "+joueurNom+" butte contre un mur en voulant aller a droite");
							break;
						}
						bombePresente = false;
						for (BombeService bombe : bombes) {
							if(bombe.getLigne() == joueur.getLigne() && bombe.getColonne() == joueur.getColonne()){
								bombePresente = true;
								break;
							}
						}
						if(bombePresente){
							if(joueur.passeBombe()){
								System.out.println("Le "+joueurNom+" va a droite en ["+joueur.getLigne()+";"+joueur.getColonne()+"].");
							}else{
								joueur.setColonne(joueur.getColonne()-1, terrain);
								System.out.println("Le "+joueurNom+" butte contre une bombe en voulant aller a droite");
							}
							break;
						}
						System.out.println("Le "+joueurNom+" va a droite en ["+joueur.getLigne()+";"+joueur.getColonne()+"].");
						break;
					case Bas:
						if(joueur.getLigne()+1 >= terrain.getNbLignes()) break;
						joueur.setLigne(joueur.getLigne()+1, terrain);
						if(terrain.getBloc(joueur.getLigne(),joueur.getColonne()).getType() == BlocType.MurBrique && !joueur.passeMuraille() ||
								terrain.getBloc(joueur.getLigne(),joueur.getColonne()).getType() == BlocType.MurMetal){
							joueur.setLigne(joueur.getLigne()-1, terrain);
							System.out.println("Le "+joueurNom+" butte contre un mur en voulant aller en bas");
							break;
						}
						bombePresente = false;
						for (BombeService bombe : bombes) {
							if(bombe.getLigne() == joueur.getLigne() && bombe.getColonne() == joueur.getColonne()){
								bombePresente = true;
								break;
							}
						}
						if(bombePresente){
							if(joueur.passeBombe()){
								System.out.println("Le "+joueurNom+" va en bas en ["+joueur.getLigne()+";"+joueur.getColonne()+"].");
							}else{
								joueur.setLigne(joueur.getLigne()-1, terrain);
								System.out.println("Le "+joueurNom+" butte contre une bombe en voulant aller en bas");
							}
							break;
						}
						System.out.println("Le "+joueurNom+" va en bas en ["+joueur.getLigne()+";"+joueur.getColonne()+"].");
						break;
					case Haut:
						if(joueur.getLigne()-1 < 2) break;
						joueur.setLigne(joueur.getLigne()-1, terrain);
						if(terrain.getBloc(joueur.getLigne(),joueur.getColonne()).getType() == BlocType.MurBrique && !joueur.passeMuraille() ||
								terrain.getBloc(joueur.getLigne(),joueur.getColonne()).getType() == BlocType.MurMetal){
							joueur.setLigne(joueur.getLigne()+1, terrain);
							System.out.println("Le "+joueurNom+" butte contre un mur en voulant aller en haut");
							break;
						}
						bombePresente = false;
						for (BombeService bombe : bombes) {
							if(bombe.getLigne() == joueur.getLigne() && bombe.getColonne() == joueur.getColonne()){
								bombePresente = true;
								break;
							}
						}
						if(bombePresente){
							if(joueur.passeBombe()){
								System.out.println("Le "+joueurNom+" va en haut en ["+joueur.getLigne()+";"+joueur.getColonne()+"].");
							}else{
								joueur.setLigne(joueur.getLigne()+1, terrain);
								System.out.println("Le "+joueurNom+" butte contre une bombe en voulant aller en haut");
							}
							break;
						}
						System.out.println("Le "+joueurNom+" va en haut en ["+joueur.getLigne()+";"+joueur.getColonne()+"].");
						break;
					default:
						System.out.println("Le "+joueurNom+" a fait une action inconnue : "+action.toString());
						break;
					}
				}catch (IllegalArgumentException e){
					System.out.println("Action interdite : "+action);
				}
				// ramassage des powerup
				if(terrain.getBloc(joueur.getLigne(),joueur.getColonne()).getReward() != BlocReward.Rien && terrain.getBloc(joueur.getLigne(), joueur.getColonne()).getType() == BlocType.Vide){
					joueur.addReward(terrain.getBloc(joueur.getLigne(),joueur.getColonne()).getReward());
					System.out.println("Le "+joueurNom+" a pris le bonus : "+terrain.getBloc(joueur.getLigne(),joueur.getColonne()).getReward());
					terrain.getBloc(joueur.getLigne(),joueur.getColonne()).setReward(BlocReward.Rien);
				}
			}
		}
	}

	/*
	 * "intelligence" artificielle du kidnappeur
	 * pour le moment :
	 * 	- le bot ne reste pas a cote d'une bombe
	 *  - le bot ne va pas vers une bombe (a 2 cases de lui)
	 *  - le bot ne fonce pas betement dans un mur
	 *  - le bot ramasse les PowerUp1 quand il en voit un a une case de portee
	 * TODO: utiliser une ponderation des actions possibles pour avoir un systeme de selection de l'action plus intelligent
	 */
	private Action choisirAction(JoueurService joueur) {
		Vector<Action> actionsPossibles = new Vector<Action>();
		for (Action a : Action.values()) {
			actionsPossibles.add(a);
		}
		// le joueur n'essaie pas de poser de bombes si il n'en a plus en stock
		if(joueur.getNbBombePosable() == 0)
			actionsPossibles.remove(Action.Bombe);
		// eviter que le joueur reste a cote d'une bombe, ou n'aille vers une bombe a 2 cases de lui (si la case adjacente au joueur est un bloc Vide)
		if(bombes.size()>0){
			for (BombeService bombe : bombes) {
				if(bombe.getLigne() == joueur.getLigne()-1 && bombe.getColonne() == joueur.getColonne() ||
						joueur.getLigne()-2 >= 0 &&
						bombe.getLigne() == joueur.getLigne()-2 && bombe.getColonne() == joueur.getColonne() &&
						terrain.getBloc(joueur.getLigne()-1,joueur.getColonne()).getType() == BlocType.Vide){
					actionsPossibles.remove(Action.Haut);
					actionsPossibles.remove(Action.Rien);
				}
				if(bombe.getLigne() == joueur.getLigne() && bombe.getColonne() == joueur.getColonne()-1 ||
						joueur.getColonne()-2 >= 0 &&
						bombe.getLigne() == joueur.getLigne() && bombe.getColonne() == joueur.getColonne()-2 &&
						terrain.getBloc(joueur.getLigne(),joueur.getColonne()-1).getType() == BlocType.Vide){
					actionsPossibles.remove(Action.Gauche);
					actionsPossibles.remove(Action.Rien);
				}
				if(bombe.getLigne() == joueur.getLigne()+1 && bombe.getColonne() == joueur.getColonne() ||
						joueur.getLigne()+2 <= terrain.getNbLignes() &&
						bombe.getLigne() == joueur.getLigne()+2 && bombe.getColonne() == joueur.getColonne() &&
						terrain.getBloc(joueur.getLigne()+1,joueur.getColonne()).getType() == BlocType.Vide){
					actionsPossibles.remove(Action.Bas);
					actionsPossibles.remove(Action.Rien);
				}
				if(bombe.getLigne() == joueur.getLigne() && bombe.getColonne() == joueur.getColonne()+1 ||
						joueur.getColonne()+2 <= terrain.getNbColonnes() &&
						bombe.getLigne() == joueur.getLigne() && bombe.getColonne() == joueur.getColonne()+2 &&
						terrain.getBloc(joueur.getLigne(),joueur.getColonne()+1).getType() == BlocType.Vide){
					actionsPossibles.remove(Action.Droite);
					actionsPossibles.remove(Action.Rien);
				}
				if(bombe.getLigne() == joueur.getLigne() && bombe.getColonne() == joueur.getColonne()){
					actionsPossibles.remove(Action.Bombe); // il y a deja une bombe la ou est le joueur
					actionsPossibles.remove(Action.Rien);
				}
			}
		}
		// eviter que le joueur se tape la tete dans un mur
		if(terrain.getBloc(joueur.getLigne()-1,joueur.getColonne()).getType() == BlocType.MurMetal ||
				terrain.getBloc(joueur.getLigne()-1,joueur.getColonne()).getType() == BlocType.MurBrique)
			actionsPossibles.remove(Action.Haut);
		if(terrain.getBloc(joueur.getLigne(),joueur.getColonne()-1).getType() == BlocType.MurMetal ||
				terrain.getBloc(joueur.getLigne(),joueur.getColonne()-1).getType() == BlocType.MurBrique)
			actionsPossibles.remove(Action.Gauche);
		if(terrain.getBloc(joueur.getLigne()+1,joueur.getColonne()).getType() == BlocType.MurMetal ||
				terrain.getBloc(joueur.getLigne()+1,joueur.getColonne()).getType() == BlocType.MurBrique)
			actionsPossibles.remove(Action.Bas);
		if(terrain.getBloc(joueur.getLigne(),joueur.getColonne()+1).getType() == BlocType.MurMetal ||
				terrain.getBloc(joueur.getLigne(),joueur.getColonne()+1).getType() == BlocType.MurBrique)
			actionsPossibles.remove(Action.Droite);
		
		// le joueur est attire par le Reward si il en voit un a portee (le return est un peu violent, il faudrait ponderer les Actions)
		if(terrain.getBloc(joueur.getLigne()-1,joueur.getColonne()).getType() == BlocType.Vide && 
				terrain.getBloc(joueur.getLigne()-1,joueur.getColonne()).getReward() != BlocReward.Rien)
			return Action.Haut;
		if(terrain.getBloc(joueur.getLigne(),joueur.getColonne()-1).getType() == BlocType.Vide &&
				terrain.getBloc(joueur.getLigne(),joueur.getColonne()-1).getReward() != BlocReward.Rien)
			return Action.Gauche;
		if(terrain.getBloc(joueur.getLigne()+1,joueur.getColonne()).getType() == BlocType.Vide &&
				terrain.getBloc(joueur.getLigne()+1,joueur.getColonne()).getReward() != BlocReward.Rien)
			return Action.Bas;
		if(terrain.getBloc(joueur.getLigne(),joueur.getColonne()+1).getType() == BlocType.Vide &&
				terrain.getBloc(joueur.getLigne(),joueur.getColonne()+1).getReward() != BlocReward.Rien)
			return Action.Droite;
		
		if(actionsPossibles.size() < 1) actionsPossibles.add(Action.Rien); // joueur bloque, oblige de ne rien faire
		if(joueur instanceof KidnappeurService){
			System.out.println("Le kidnappeur a le choix parmis : "+actionsPossibles.toString());
		}else{
			System.out.println("Le joueur a le choix parmis : "+actionsPossibles.toString());
		}
		return actionsPossibles.get(rand.nextInt(actionsPossibles.size()));
	}

	@Override
	public JoueurService getJoueur() {
		return joueur;
	}

	@Override
	public KidnappeurService getKidnappeur() {
		return kidnappeur;
	}

	@Override
	public int getNbBombes() {
		return bombes.size();
	}

	@Override
	public Vector<Integer> getBombesNum() {
		Vector<Integer> res = new Vector<Integer>();
		for (BombeService b : bombes) {
			res.add(b.getNum());
		}
		return res;
	}

	@Override
	public boolean bombeExiste(int n) {
		for (BombeService b : bombes) {
			if(b.getNum() == n)
				return true;
		}
		return false;
	}

	@Override
	public BombeService getBombe(int n) {
		for (BombeService b : bombes) {
			if(b.getNum() == n)
				return b;
		}
		return null;
	}

	@Override
	public int getNbVilains() {
		return vilains.size();
	}

	@Override
	public Vector<Integer> getVilainsNum() {
		Vector<Integer> res = new Vector<Integer>();
		for (VilainService v : vilains) {
			res.add(v.getNum());
		}
		return res;
	}

	@Override
	public boolean vilainExiste(int n) {
		for (VilainService v : vilains) {
			if(v.getNum() == n)
				return true;
		}
		return false;
	}

	@Override
	public VilainService getVilain(int n) {
		for (VilainService v : vilains) {
			if(v.getNum() == n)
				return v;
		}
		return null;
	}

	@Override
	public int getStepRestants() {
		return nbSteps;
	}

	@Override
	public boolean isGameOver() {
		return (!joueur.isVivant() || !kidnappeur.isVivant() || getStepRestants() == 0);
	}

	@Override
	public Gagnant getGagnant() {
		if(!joueur.isVivant() && !kidnappeur.isVivant())
			return Gagnant.Aucun;
		if(!kidnappeur.isVivant() && joueur.isVivant())
			return Gagnant.Joueur;
		if(!joueur.isVivant() && kidnappeur.isVivant())
			return Gagnant.Kidnappeur;
		return Gagnant.Aucun;
	}

	@Override
	public boolean isInRange(int ligneCible, int colonneCible, int numBombe) {
		for (BombeService bombe: bombes) {
			if(bombe.getNum() == numBombe){
				// hors de la croix (sans verifier l'amplitude)
				if(bombe.getLigne() != ligneCible && bombe.getColonne() != colonneCible){
					return false;
				}
				// ligne, vers le haut
				for(int ligne = 0; ligne>=-bombe.getAmplitude(); ligne--){
					if(bombe.getLigne()+ligne > 0 && bombe.getLigne()+ligne <= terrain.getNbLignes() && bombe.getColonne() > 0 && bombe.getColonne() <= terrain.getNbColonnes()){
						if(ligneCible == bombe.getLigne()+ligne && colonneCible == bombe.getColonne())
							return true;
						if(terrain.getBloc(bombe.getLigne()+ligne, bombe.getColonne()).getType() != BlocType.Vide ||
							terrain.getBloc(bombe.getLigne()+ligne, bombe.getColonne()).getType() == BlocType.Vide &&
							terrain.getBloc(bombe.getLigne()+ligne, bombe.getColonne()).getReward() != BlocReward.Rien)
							break;
					}else{
						break;
					}
				}
				// ligne, vers la bas
				for(int ligne = 0; ligne<=bombe.getAmplitude(); ligne++){
					if(bombe.getLigne()+ligne > 0 && bombe.getLigne()+ligne <= terrain.getNbLignes() && bombe.getColonne() > 0 && bombe.getColonne() <= terrain.getNbColonnes()){
						if(ligneCible == bombe.getLigne()+ligne && colonneCible == bombe.getColonne())
							return true;
						if(terrain.getBloc(bombe.getLigne()+ligne,bombe.getColonne()).getType() != BlocType.Vide ||
							terrain.getBloc(bombe.getLigne()+ligne, bombe.getColonne()).getType() == BlocType.Vide &&
							terrain.getBloc(bombe.getLigne()+ligne, bombe.getColonne()).getReward() != BlocReward.Rien)
							break;
					}else{
						break;
					}
				}
				// colonne, vers la gauche
				for(int colonne = 0; colonne>=-bombe.getAmplitude(); colonne--){
					if(bombe.getLigne() > 0 && bombe.getLigne() <= terrain.getNbLignes() && bombe.getColonne()+colonne > 0 && bombe.getColonne()+colonne <= terrain.getNbColonnes()){
						if(ligneCible == bombe.getLigne() && colonneCible == bombe.getColonne()+colonne)
							return true;
						if(terrain.getBloc(bombe.getLigne(), bombe.getColonne()+colonne).getType() != BlocType.Vide ||
							terrain.getBloc(bombe.getLigne(), bombe.getColonne()+colonne).getType() == BlocType.Vide &&
							terrain.getBloc(bombe.getLigne(), bombe.getColonne()+colonne).getReward() != BlocReward.Rien)
							break;
					}else{
						break;
					}
				}
				// colonne, vers la droite
				for(int colonne = 0; colonne<=bombe.getAmplitude(); colonne++){
					if(bombe.getLigne() > 0 && bombe.getLigne() <= terrain.getNbLignes() && bombe.getColonne()+colonne > 0 && bombe.getColonne()+colonne <= terrain.getNbColonnes()){
						if(ligneCible == bombe.getLigne() && colonneCible == bombe.getColonne()+colonne)
							return true;
						if(terrain.getBloc(bombe.getLigne(), bombe.getColonne()+colonne).getType() != BlocType.Vide ||
							terrain.getBloc(bombe.getLigne(), bombe.getColonne()+colonne).getType() == BlocType.Vide &&
							terrain.getBloc(bombe.getLigne(), bombe.getColonne()+colonne).getReward() != BlocReward.Rien)
							break;
					}else{
						break;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public TerrainService getTerrain() {
		return terrain;
	}

	@Override
	public boolean hasRemovedExplosions() {
		return removedExplosions;
	}

	@Override
	public void init(int nbSteps) {
		if(nbSteps <= 0){
			throw new IllegalArgumentException();
		}
		
		rand = new Random(System.currentTimeMillis());
		removedExplosions = true;
		
		terrain = new TerrainContrat(new Terrain());
		terrain.init(13,15);
		
		this.nbSteps = nbSteps;
		
		bombes = new Vector<BombeService>();
		
		vilains = new Vector<VilainService>();
		
		int i = 0, j = 0;
		
		joueur = new JoueurContrat(new Joueur());
		joueur.init(2, 2, terrain);

		kidnappeur = new KidnappeurContrat(new Kidnappeur());
		kidnappeur.init(terrain.getNbLignes()-1, terrain.getNbColonnes()-1, terrain);
		
		terrain.getBloc(terrain.getNbLignes()-1, terrain.getNbColonnes()-2).setType(BlocType.Vide);
		terrain.getBloc(terrain.getNbLignes()-1, terrain.getNbColonnes()-2).setReward(BlocReward.Rien);
		terrain.getBloc(terrain.getNbLignes()-2, terrain.getNbColonnes()-1).setType(BlocType.Vide);
		terrain.getBloc(terrain.getNbLignes()-2, terrain.getNbColonnes()-1).setReward(BlocReward.Rien);
		terrain.getBloc(terrain.getNbLignes()-1, terrain.getNbColonnes()-1).setType(BlocType.Vide);
		terrain.getBloc(terrain.getNbLignes()-1, terrain.getNbColonnes()-1).setReward(BlocReward.Rien);

		terrain.getBloc(2, 3).setType(BlocType.Vide);
		terrain.getBloc(2, 3).setReward(BlocReward.Rien);
		terrain.getBloc(3, 2).setType(BlocType.Vide);
		terrain.getBloc(3, 2).setReward(BlocReward.Rien);
		terrain.getBloc(2, 2).setType(BlocType.Vide);
		terrain.getBloc(2, 2).setReward(BlocReward.Rien);

		// placement des vilains de facon aleatoire
		for(int nbv = 0; nbv < 4; nbv++){
			i = 1; j = 1;
			while(true){
				if(terrain.getBloc(i,j).getType() == BlocType.Vide && 
					getJoueur().getLigne() != i && getJoueur().getColonne() != j && 
					getJoueur().getLigne()+1 != i && getJoueur().getColonne() != j && 
					getJoueur().getLigne() != i && getJoueur().getColonne()+1 != j &&
					getKidnappeur().getLigne() != i && getKidnappeur().getColonne() != j &&
					getKidnappeur().getLigne()-1 != i && getKidnappeur().getColonne() != j &&
					getKidnappeur().getLigne() != i && getKidnappeur().getColonne()-1 != j){
					break;
				}else{
					i = rand.nextInt(terrain.getNbLignes()-1)+1;
					j = rand.nextInt(terrain.getNbColonnes()-1)+1;
				}
			}
			VilainService v;
			if(nbv >= 2){
				v = new VilainContrat(new Vilain());
				v.init(TypeVilain.BallonOrange, nbv, i, j, terrain);
			}else{
				v = new VilainContrat(new Vilain());
				v.init(TypeVilain.FantomeBleu, nbv, i, j, terrain);
			}
			vilains.add(v);
		}
	}

	@Override
	public void step(Action a) {
		if(a == null){
			throw new IllegalArgumentException();
		}
		
		System.out.println();
		System.out.println("Tours de jeu restants : "+nbSteps);
		removeExplosions();
		gestionBombes();
		gestionJoueurs(a);
		gestionVilains();
		nbSteps--;
		
		
		// TODO: a retirer avant de lancer les Tests MBT !
		//getJoueur().setVivant(true);
		//getKidnappeur().setVivant(true);
		// TODO: a retirer avant de tester les Tests MBT !
		
		
		// TEMPS MORT ENTRE DEUX TOURS DE JEU, pour laisser le temps a l'utilisateur de voir ce qui se passe sur l'IHM
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeExplosions() {
		for(int ligne = 1; ligne<=terrain.getNbLignes(); ligne++){
			for(int colonne = 1; colonne<=terrain.getNbColonnes(); colonne++){
				switch (terrain.getBloc(ligne,colonne).getType()) {
				case Explosion:
					terrain.getBloc(ligne,colonne).setType(BlocType.Vide);
					break;
				default:
					break;
				}
			}
		}
		setRemovedExplosions(true);
	}

	@Override
	public void setRemovedExplosions(boolean b) {
		removedExplosions = b;
	}
}