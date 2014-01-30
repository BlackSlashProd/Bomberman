package tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.MoteurService;
import utils.Action;

public abstract class AbstractMoteurTest {
	private MoteurService moteur;
	
	public AbstractMoteurTest(){
		moteur = null;
	}
	
	public void setMoteur(MoteurService moteur){
		this.moteur = moteur;
	}
	
	public MoteurService getMoteur(){
		return this.moteur;
	}
	
	@Before
	public abstract void beforeTests();

	@After
	public void afterTests(){
		this.moteur = null;
	}
	/**
	 * TEST INIT
	 */
	@Test
	public void test_pre_init_1(){
		//Operation
		try{
			moteur.init(0);
		}catch(Throwable e){
			//Verif
			assertTrue("MoteurService : Init : pre : init(0) leve une exception", true);
			return;
		}
		assertTrue("MoteurService : Init : pre : init(0) ne leve pas d'exception", false);
	}	
	@Test
	public void test_post_init_1(){
		//Operation
		moteur.init(500);
		assertTrue("MoteurService : Init : post : init(500) donne un moteur avec 500 steps restant.",moteur.getStepRestants()==500);
		assertTrue("MoteurService : Init : post : init(500) donne un joueur sur la ligne 2.",moteur.getJoueur().getLigne()==2);
		assertTrue("MoteurService : Init : post : init(500) donne un joueur sur la colonne 2.",moteur.getJoueur().getColonne()==2);
		assertTrue("MoteurService : Init : post : init(500) donne un kidnappeur sur l'avant dernière ligne.",moteur.getKidnappeur().getLigne()==moteur.getTerrain().getNbLignes()-1);
		assertTrue("MoteurService : Init : post : init(500) donne un kidnappeur sur l'avant dernière colonne.",moteur.getKidnappeur().getColonne()==moteur.getTerrain().getNbColonnes()-1);
		assertTrue("MoteurService : Init : post : init(500) donne un moteur sans bombes.",moteur.getNbBombes()==0);
		assertTrue("MoteurService : Init : post : init(500) donne un moteur avec 4 vilains.",moteur.getNbVilains()==4);
		assertTrue("MoteurService : Init : post : init(500) donne un terrain de 13 lignes.",moteur.getTerrain().getNbLignes()==13);
		assertTrue("MoteurService : Init : post : init(500) donne un terrain de 15 colonnes.",moteur.getTerrain().getNbColonnes()==15);
		assertTrue("MoteurService : Init : post : init(500) donne un terrain sans explosion affichée.",moteur.hasRemovedExplosions()==true);	
	}	
	/**
	 * TEST STEP
	 */
	@Test
	public void test_pre_step_1(){
		// Action null
		//init
		moteur.init(500);
		//Operation
		try{
			moteur.step(null);
		}catch(Throwable e){
			//Verif
			assertTrue("MoteurService : Init : step : step(null) leve une exception", true);
			return;
		}
		assertTrue("MoteurService : Init : step : step(null) ne leve pas d'exception", false);
	}
	@Test
	public void test_post_step_1(){
		//Operation
		moteur.init(500);
		moteur.step(Action.Rien);
		assertTrue("MoteurService : Step : post : step(Action.Rien) consomme 1 step.",moteur.getStepRestants()==499);
		assertTrue("MoteurService : Step : post : step(Action.Rien) possede un joueur sur la ligne 2.",moteur.getJoueur().getLigne()==2);
		assertTrue("MoteurService : Step : post : step(Action.Rien) possede un joueur sur la colonne 2.",moteur.getJoueur().getColonne()==2);
		assertTrue("MoteurService : Step : post : step(Action.Rien) possede un terrain de 13 lignes.",moteur.getTerrain().getNbLignes()==13);
		assertTrue("MoteurService : Step : post : step(Action.Rien) possede un terrain de 15 colonnes.",moteur.getTerrain().getNbColonnes()==15);
		assertTrue("MoteurService : Step : post : step(Action.Rien) possede un terrain sans explosion affichée.",moteur.hasRemovedExplosions()==true);	
	}	
	@Test
	public void test_post_step_2(){
		//Operation
		moteur.init(500);
		moteur.step(Action.Haut);
		assertTrue("MoteurService : Step : post : step(Action.Haut) consomme 1 step.",moteur.getStepRestants()==499);
		assertTrue("MoteurService : Step : post : step(Action.Haut) possede un joueur sur la ligne 2.",moteur.getJoueur().getLigne()==2);
		assertTrue("MoteurService : Step : post : step(Action.Haut) possede un joueur sur la colonne 2.",moteur.getJoueur().getColonne()==2);
		assertTrue("MoteurService : Step : post : step(Action.Haut) possede un terrain de 13 lignes.",moteur.getTerrain().getNbLignes()==13);
		assertTrue("MoteurService : Step : post : step(Action.Haut) possede un terrain de 15 colonnes.",moteur.getTerrain().getNbColonnes()==15);
		assertTrue("MoteurService : Step : post : step(Action.Haut) possede un terrain sans explosion affichée.",moteur.hasRemovedExplosions()==true);	
	}
	@Test
	public void test_post_step_3(){
		//Operation
		moteur.init(500);
		moteur.step(Action.Gauche);
		assertTrue("MoteurService : Step : post : step(Action.Gauche) consomme 1 step.",moteur.getStepRestants()==499);
		assertTrue("MoteurService : Step : post : step(Action.Gauche) possede un joueur sur la ligne 2.",moteur.getJoueur().getLigne()==2);
		assertTrue("MoteurService : Step : post : step(Action.Gauche) possede un joueur sur la colonne 2.",moteur.getJoueur().getColonne()==2);
		assertTrue("MoteurService : Step : post : step(Action.Gauche) possede un terrain de 13 lignes.",moteur.getTerrain().getNbLignes()==13);
		assertTrue("MoteurService : Step : post : step(Action.Gauche) possede un terrain de 15 colonnes.",moteur.getTerrain().getNbColonnes()==15);
		assertTrue("MoteurService : Step : post : step(Action.Gauche) possede un terrain sans explosion affichée.",moteur.hasRemovedExplosions()==true);	
	}
	@Test
	public void test_post_step_4(){
		//Operation
		moteur.init(500);
		moteur.step(Action.Droite);
		assertTrue("MoteurService : Step : post : step(Action.Droite) consomme 1 step.",moteur.getStepRestants()==499);
		assertTrue("MoteurService : Step : post : step(Action.Droite) possede un joueur sur la ligne 2.",moteur.getJoueur().getLigne()==2);
		assertTrue("MoteurService : Step : post : step(Action.Droite) deplace le joueur en incrementant sa colonne.",moteur.getJoueur().getColonne()==3);
		assertTrue("MoteurService : Step : post : step(Action.Droite) possede un terrain de 13 lignes.",moteur.getTerrain().getNbLignes()==13);
		assertTrue("MoteurService : Step : post : step(Action.Droite) possede un terrain de 15 colonnes.",moteur.getTerrain().getNbColonnes()==15);
		assertTrue("MoteurService : Step : post : step(Action.Droite) possede un terrain sans explosion affichée.",moteur.hasRemovedExplosions()==true);	
	}
	@Test
	public void test_post_step_5(){
		//Operation
		moteur.init(500);
		moteur.step(Action.Bas);
		assertTrue("MoteurService : Step : post : step(Action.Bas) consomme 1 step.",moteur.getStepRestants()==499);
		assertTrue("MoteurService : Step : post : step(Action.Bas) deplace le joueur en incrementant sa ligne.",moteur.getJoueur().getLigne()==3);
		assertTrue("MoteurService : Step : post : step(Action.Bas) possede un joueur sur la colonne 2.",moteur.getJoueur().getColonne()==2);
		assertTrue("MoteurService : Step : post : step(Action.Bas) possede un terrain de 13 lignes.",moteur.getTerrain().getNbLignes()==13);
		assertTrue("MoteurService : Step : post : step(Action.Bas) possede un terrain de 15 colonnes.",moteur.getTerrain().getNbColonnes()==15);
		assertTrue("MoteurService : Step : post : step(Action.Bas) possede un terrain sans explosion affichée.",moteur.hasRemovedExplosions()==true);	
	}
	@Test
	public void test_post_step_6(){
		//Operation
		moteur.init(500);
		moteur.step(Action.Bombe);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) consomme 1 step.",moteur.getStepRestants()==499);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede un joueur sur la ligne 2.",moteur.getJoueur().getLigne()==2);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede un joueur sur la colonne 2.",moteur.getJoueur().getColonne()==2);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede un terrain de 13 lignes.",moteur.getTerrain().getNbLignes()==13);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede un terrain de 15 colonnes.",moteur.getTerrain().getNbColonnes()==15);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede un terrain sans explosion affichée.",moteur.hasRemovedExplosions()==true);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede au moins une bombe.",moteur.getNbBombes()>=1);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede la bombe de numero 500.",moteur.bombeExiste(500)==true);
	}	
	@Test
	public void test_post_set_removed_explosions_1(){
		//Operation
		moteur.init(500);
		moteur.setRemovedExplosions(true);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede un moteur avec 500 steps restant.",moteur.getStepRestants()==500);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede un joueur sur la ligne 2.",moteur.getJoueur().getLigne()==2);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede un joueur sur la colonne 2.",moteur.getJoueur().getColonne()==2);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede un terrain de 13 lignes.",moteur.getTerrain().getNbLignes()==13);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede un terrain de 15 colonnes.",moteur.getTerrain().getNbColonnes()==15);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede un terrain sans explosions affichees.",moteur.hasRemovedExplosions()==true);
	}
	@Test
	public void test_post_set_removed_explosions_2(){
		//Operation
		moteur.init(500);
		moteur.setRemovedExplosions(false);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede un moteur avec 500 steps restant.",moteur.getStepRestants()==500);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede un joueur sur la ligne 2.",moteur.getJoueur().getLigne()==2);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede un joueur sur la colonne 2.",moteur.getJoueur().getColonne()==2);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede un terrain de 13 lignes.",moteur.getTerrain().getNbLignes()==13);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede un terrain de 15 colonnes.",moteur.getTerrain().getNbColonnes()==15);
		assertTrue("MoteurService : Step : post : step(Action.Bombe) possede un terrain avec des explosions affichees.",moteur.hasRemovedExplosions()==false);
	}
	/**
	 * SCENARIOS UTILISATEURS 
	 */
	@Test
	public void test_scen_util_1(){
		// Preambule 
		moteur.init(500);
		// Contenu
		moteur.step(Action.Gauche);
		moteur.step(Action.Haut);
		moteur.step(Action.Droite);
		moteur.step(Action.Gauche);
		moteur.step(Action.Bas);
		moteur.step(Action.Haut);
		// Tests
		if(!(moteur.getStepRestants()==494))
			assertTrue("Scenario 1 : getPasRestant()!=494",false);
		if(!(moteur.getJoueur().getLigne()==2))
			assertTrue("Scenario 1 : Joueur::getLigne()!=2",false);
		if(!(moteur.getJoueur().getColonne()==2))
			assertTrue("Scenario 1 : Joueur::getColonne()!=2",false);
		if(!(moteur.getTerrain().getNbLignes()==13))
			assertTrue("Scenario 1 : Terrain::getNbLignes()!=13",false);
		if(!(moteur.getTerrain().getNbColonnes()==15))
			assertTrue("Scenario 1 : Terrain::getNbColonnes()!=15",false);	
		if(!(moteur.getNbBombes()>=0))
			assertTrue("Scenario 1 : getNbBombes()>=0",false);	
	}
	@Test
	public void test_scen_util_2(){
		// Preambule 
		moteur.init(500);
		// Contenu
		moteur.step(Action.Bombe);
		// Tests
		if(!(moteur.getStepRestants()==499))
			assertTrue("Scenario 1 : getPasRestant()!=499",false);
		if(!(moteur.getJoueur().getLigne()==2))
			assertTrue("Scenario 1 : Joueur::getLigne()!=2",false);
		if(!(moteur.getJoueur().getColonne()==2))
			assertTrue("Scenario 1 : Joueur::getColonne()!=2",false);
		if(!(moteur.getTerrain().getNbLignes()==13))
			assertTrue("Scenario 1 : Terrain::getNbLignes()!=13",false);
		if(!(moteur.getTerrain().getNbColonnes()==15))
			assertTrue("Scenario 1 : Terrain::getNbColonnes()!=15",false);	
		if(!(moteur.getNbBombes()>=0))
			assertTrue("Scenario 1 : getNbBombes()>=1",false);	
		if(!(moteur.bombeExiste(500)==true))
			assertTrue("Scenario 1 : bombeExiste(500)!=true",false);	
	}
	@Test
	public void test_scen_util_3(){
		// Preambule 
		moteur.init(500);
		// Contenu
		moteur.step(Action.Bombe);
		moteur.step(Action.Rien);
		moteur.step(Action.Rien);
		moteur.step(Action.Rien);
		moteur.step(Action.Rien);
		moteur.step(Action.Rien);
		moteur.step(Action.Rien);
		moteur.step(Action.Rien);
		moteur.step(Action.Rien);
		moteur.step(Action.Rien);
		moteur.step(Action.Rien);
		// Tests
		if(!(moteur.getStepRestants()==489))
			assertTrue("Scenario 1 : getPasRestant()!=489",false);
		if(!(moteur.getJoueur().getLigne()==2))
			assertTrue("Scenario 1 : Joueur::getLigne()!=2",false);
		if(!(moteur.getJoueur().getColonne()==2))
			assertTrue("Scenario 1 : Joueur::getColonne()!=2",false);
		if(!(moteur.getTerrain().getNbLignes()==13))
			assertTrue("Scenario 1 : Terrain::getNbLignes()!=13",false);
		if(!(moteur.getTerrain().getNbColonnes()==15))
			assertTrue("Scenario 1 : Terrain::getNbColonnes()!=15",false);	
		if(!(moteur.getNbBombes()>=0))
			assertTrue("Scenario 1 : getNbBombes()>=0",false);	
		if(!(moteur.bombeExiste(500)==false))
			assertTrue("Scenario 1 : bombeExiste(500)!=false",false);
	}
}
