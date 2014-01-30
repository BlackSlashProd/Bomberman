package tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.JoueurService;
import services.TerrainService;
import utils.BlocReward;

public abstract class AbstractJoueurTest {
	
	private JoueurService joueur;
	private TerrainService terrain;
	
	public AbstractJoueurTest(){
		this.joueur = null;
	}
	
	public JoueurService getJoueur() {
		return joueur;
	}

	public void setJoueur(JoueurService joueur) {
		this.joueur = joueur;
	}

	public TerrainService getTerrain() {
		return terrain;
	}

	public void setTerrain(TerrainService terrain) {
		this.terrain = terrain;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		joueur = null;
	}
	/**
	 * TEST INIT
	 */
	@Test
	public void test_post_init_1(){
		//Operation
		joueur.init(2, 2, terrain);
		//Verif
		assertTrue("JoueurService : init : post : init(2, 2, terrain) donne un joueur avec une force de 3", joueur.getForce() == 3);
		assertTrue("JoueurService : init : post : init(2, 2, terrain) donne un joueur a la ligne 2", joueur.getLigne() == 2);
		assertTrue("JoueurService : init : post : init(2, 2, terrain) donne un joueur a la colonne 2", joueur.getColonne() == 2);
		assertTrue("JoueurService : init : post : init(2, 2, terrain) donne un joueur vulnerable aux explosions", joueur.getInvulnerableStep() == 0);
		assertTrue("JoueurService : init : post : init(2, 2, terrain) donne un joueur pouvant poser 1 bombe", joueur.getNbBombePosable() == 1);
		assertTrue("JoueurService : init : post : init(2, 2, terrain) donne un joueur en vie", joueur.isVivant() == true);
		assertTrue("JoueurService : init : post : init(2, 2, terrain) donne un joueur qui ne peut pas passer a travers les bombes", joueur.passeBombe() == false);
		assertTrue("JoueurService : init : post : init(2, 2, terrain) donne un joueur qui ne peut pas passer a travers les murs", joueur.passeMuraille() == false);
	}
	@Test
	public void test_pre_init_1(){
		// placement hors contrat (ligne < 2)
		
		//Operation
		try{
			joueur.init(1, 2, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("JoueurService : init : init(1, 2, terrain) leve une exception", true);
			return;
		}
		assertTrue("JoueurService : init : init(1, 2, terrain) ne leve pas d'exception", false);
	}
	public void test_pre_init_2(){
		// placement hors contrat (colonne < 2)
		
		//Operation
		try{
			joueur.init(2, 1, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("JoueurService : init : init(2, 1, terrain) leve une exception", true);
			return;
		}
		assertTrue("JoueurService : init : init(2, 1, terrain) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_3(){
		// placement hors contrat (ligne > 12)
		
		//Operation
		try{
			joueur.init(13, 2, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("JoueurService : init : init(13, 2, terrain) leve une exception", true);
			return;
		}
		assertTrue("JoueurService : init : init(13, 2, terrain) ne leve pas d'exception", false);
	}
	public void test_pre_init_4(){
		// placement hors contrat (colonne > 14)
		
		//Operation
		try{
			joueur.init(2, 15, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("JoueurService : init : init(2, 15, terrain) leve une exception", true);
			return;
		}
		assertTrue("JoueurService : init : init(2, 15, terrain) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_5(){
		// terrain null
		
		//Operation
		try{
			joueur.init(2, 2, null);
		}catch(Throwable e){
			//Verif
			assertTrue("JoueurService : init : init(2, 2, null) leve une exception", true);
			return;
		}
		assertTrue("JoueurService : init : init(2, 2, null) ne leve pas d'exception", false);
	}
	/**
	 * TEST SETFORCE
	 */
	@Test
	public void test_post_setForce_1(){
		//Init
		joueur.init(2, 2, terrain);
		//Operation
		joueur.setForce(5);
		//Verif
		assertTrue("JoueurService : setForce : post : setForce(5) passe la force du joueur a 5", joueur.getForce() == 5);
	}
	@Test
	public void test_pre_setForce_1(){
		//Init
		joueur.init(2, 2, terrain);
		// force < 3
		try{
			//Operation
			joueur.setForce(2);
		}catch(Throwable e){
			//Verif
			assertTrue("JoueurService : setForce : pre : setForce(2) leve une exception", true);
			return;
		}
		assertTrue("JoueurService : setForce : pre : setForce(2) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_setForce_2(){
		//Init
		joueur.init(2, 2, terrain);
		// force > 11
		try{
			//Operation
			joueur.setForce(12);
		}catch(Throwable e){
			//Verif
			assertTrue("JoueurService : setForce : pre : setForce(12) leve une exception", true);
			return;
		}
		assertTrue("JoueurService : setForce : pre : setForce(12) ne leve pas d'exception", false);
	}
	/**
	 * TEST SETLIGNE
	 */
	@Test
	public void test_post_setLigne_1(){
		//Init
		joueur.init(2, 2, terrain);
		//Operation
		joueur.setLigne(5, terrain);
		//Verif
		assertTrue("JoueurService : setLigne : post : setLigne(5, t) passe la ligne du joueur a 5", joueur.getLigne() == 5);
	}
	@Test
	public void test_pre_setLigne_1(){
		//Init
		joueur.init(2, 2, terrain);
		// ligne < 2
		try{
			//Operation
			joueur.setLigne(1, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("JoueurService : setLigne : pre : setLigne(1, t) leve une exception", true);
			return;
		}
		assertTrue("JoueurService : setLigne : pre : setLigne(1, t) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_setLigne_2(){
		//Init
		joueur.init(2, 2, terrain);
		// ligne > 12
		try{
			//Operation
			joueur.setLigne(13, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("JoueurService : setLigne : pre : setLigne(13, t) leve une exception", true);
			return;
		}
		assertTrue("JoueurService : setLigne : pre : setLigne(13, t) ne leve pas d'exception", false);
	}
	/**
	 * TEST SETCOLONNE
	 */
	@Test
	public void test_post_setColonne_1(){
		//Init
		joueur.init(2, 2, terrain);
		//Operation
		joueur.setColonne(5, terrain);
		//Verif
		assertTrue("JoueurService : setColonne : post : setColonne(5, t) passe la colonne du joueur 5", joueur.getColonne() == 5);
	}
	@Test
	public void test_pre_setColonne_1(){
		//Init
		joueur.init(2, 2, terrain);
		// ligne < 2
		try{
			//Operation
			joueur.setColonne(1, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("JoueurService : setColonne : pre : setColonne(1, t) leve une exception", true);
			return;
		}
		assertTrue("JoueurService : setColonne : pre : setColonne(1, t) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_setColonne_2(){
		//Init
		joueur.init(2, 2, terrain);
		// ligne > 12
		try{
			//Operation
			joueur.setColonne(15, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("JoueurService : setColonne : pre : setColonne(15, t) leve une exception", true);
			return;
		}
		assertTrue("JoueurService : setColonne : pre : setColonne(15, t) ne leve pas d'exception", false);
	}
	/**
	 * TEST SETPASSEBOMBE
	 */
	@Test
	public void test_post_setPasseBombe_1(){
		//Init
		joueur.init(2, 2, terrain);
		//Operation
		joueur.setPasseBombe(true);
		//Verif
		assertTrue("JoueurService : setPasseBombe : post : setPasseBombe(true) passe la capacite du joueur a passer a travers les bombes a true", joueur.passeBombe() == true);
	}
	@Test
	public void test_post_setPasseBombe_2(){
		//Init
		joueur.init(2, 2, terrain);
		//Operation
		joueur.setPasseBombe(false);
		//Verif
		assertTrue("JoueurService : setPasseBombe : post : setPasseBombe(false) passe la capacite du joueur a passer a travers les bombes a true", joueur.passeBombe() == false);
	}
	/**
	 * TEST SETPASSEMURAILLE
	 */
	@Test
	public void test_post_setPasseMuraille_1(){
		//Init
		joueur.init(2, 2, terrain);
		//Operation
		joueur.setPasseMuraille(true);
		//Verif
		assertTrue("JoueurService : setPasseMuraille : post : setPasseMuraille(true) passe la capacite du joueur a passer a travers les murs a true", joueur.passeMuraille() == true);
	}
	@Test
	public void test_post_setPasseMuraille_2(){
		//Init
		joueur.init(2, 2, terrain);
		//Operation
		joueur.setPasseMuraille(false);
		//Verif
		assertTrue("JoueurService : setPasseMuraille : post : setPasseMuraille(false) passe la capacite du joueur a passer a travers les murs a true", joueur.passeMuraille() == false);
	}
	/**
	 * TEST SETVIVANT
	 */
	@Test
	public void test_post_setVivant_1(){
		//Init
		joueur.init(2, 2, terrain);
		//Operation
		joueur.setVivant(true);
		//Verif
		assertTrue("JoueurService : setVivant : post : setVivant(true) rend la vie au joueur", joueur.isVivant() == true);
	}
	@Test
	public void test_post_setVivant_2(){
		//Init
		joueur.init(2, 2, terrain);
		//Operation
		joueur.setVivant(false);
		//Verif
		assertTrue("JoueurService : setVivant : post : setVivant(false) donne la mort au joueur", joueur.isVivant() == false);
	}
	/**
	 * TEST SETNBBOMBEPOSABLE
	 */
	@Test
	public void test_post_setNbBombePosable_1(){
		//Init
		joueur.init(2, 2, terrain);
		//Operation
		joueur.setNbBombePosable(5);
		//Verif
		assertTrue("JoueurService : setNbBombePosable : post : setNbBombePosable(5) passe le nombre de bombes que le joueur peut encore poser a 5", joueur.getNbBombePosable() == 5);
	}
	@Test
	public void test_pre_setNbBombePosable_1(){
		//Init
		joueur.init(2, 2, terrain);
		// nb < 0
		try{
			//Operation
			joueur.setNbBombePosable(-1);
		}catch(Throwable e){
			//Verif
			assertTrue("JoueurService : setNbBombePosable : pre : setNbBombePosable(-1) leve une exception", true);
			return;
		}
		assertTrue("JoueurService : setNbBombePosable : pre : setNbBombePosable(-1) ne leve pas d'exception", false);
	}
	/**
	 * TEST SETINVULNERABLE
	 */
	@Test
	public void test_post_setInvulnerable_1(){
		//Init
		joueur.init(2, 2, terrain);
		//Operation
		joueur.setInvulnerable();
		//Verif
		assertTrue("JoueurService : setVivant : post : setInvulnerable() rend le joueur invulnerable pour 100 tours de jeu", joueur.getInvulnerableStep() == 100);
	}
	/**
	 * TEST ADDREWARD
	 */
	@Test
	public void test_post_addReward_1(){
		//Init
		joueur.init(2, 2, terrain);
		//Operation
		joueur.addReward(BlocReward.BombPass);
		//Verif
		assertTrue("JoueurService : addReward : post : addReward(BlocReward.BombPass) permet au joueur de passer a travers les bombes", joueur.passeBombe() == true);
	}
	@Test
	public void test_post_addReward_2(){
		//Init
		joueur.init(2, 2, terrain);
		//Save
		int nbB = joueur.getNbBombePosable();
		//Operation
		joueur.addReward(BlocReward.BombUp);
		//Verif
		assertTrue("JoueurService : addReward : post : addReward(BlocReward.BombUp) permet au joueur de poser une bombe de plus", joueur.getNbBombePosable() == nbB + 1);
	}
	@Test
	public void test_post_addReward_3(){
		//Init
		joueur.init(2, 2, terrain);
		//Operation
		joueur.addReward(BlocReward.FireSuit);
		//Verif
		assertTrue("JoueurService : addReward : post : addReward(BlocReward.FireSuit) rend le joueur insensible aux explosions pour 100 tours de jeu", joueur.getInvulnerableStep() == 100);
	}
	@Test
	public void test_post_addReward_4(){
		//Init
		joueur.init(2, 2, terrain);
		//Save
		int f = joueur.getForce();
		//Operation
		joueur.addReward(BlocReward.FireUp);
		//Verif
		assertTrue("JoueurService : addReward : post : addReward(BlocReward.FireUp) augmente la force du joueur de 2", joueur.getForce() == f + 2);
	}
	@Test
	public void test_post_addReward_5(){
		//Init
		joueur.init(2, 2, terrain);
		joueur.setForce(11);
		//Save
		int f = joueur.getForce();
		//Operation
		joueur.addReward(BlocReward.FireUp);
		//Verif
		assertTrue("JoueurService : addReward : post : addReward(BlocReward.FireUp) n'augmente la force du joueur si celui ci a deja 11 de force", joueur.getForce() == f);
	}
	@Test
	public void test_post_addReward_6(){
		//Init
		joueur.init(2, 2, terrain);
		//Operation
		joueur.addReward(BlocReward.WallPass);
		//Verif
		assertTrue("JoueurService : addReward : post : addReward(BlocReward.WallPass) permet au joueur de passer a travers les murs", joueur.passeMuraille() == true);
	}
	@Test
	public void test_inv_addReward_1(){
		//Init
		joueur.init(2, 2, terrain);
		//Save
		int f = joueur.getForce();
		boolean b = joueur.passeBombe();
		boolean m = joueur.passeMuraille();
		int c = joueur.getColonne();
		int l = joueur.getLigne();
		int p = joueur.getNbBombePosable();
		int i = joueur.getInvulnerableStep();
		//Operation
		joueur.addReward(BlocReward.Rien);
		//Verif
		assertTrue("JoueurService : addReward : inv : addReward(BlocReward.Rien) ne modifie pas la force du joueur", joueur.getForce() == f);
		assertTrue("JoueurService : addReward : inv : addReward(BlocReward.Rien) ne modifie pas la capacite du joueur a passer a travers les bombes", joueur.passeBombe() == b);
		assertTrue("JoueurService : addReward : inv : addReward(BlocReward.Rien) ne modifie pas la capacite du joueur a passer a travers les murs", joueur.passeMuraille() == m);
		assertTrue("JoueurService : addReward : inv : addReward(BlocReward.Rien) ne modifie pas la ligne du joueur", joueur.getColonne() == c);
		assertTrue("JoueurService : addReward : inv : addReward(BlocReward.Rien) ne modifie pas la colonne du joueur", joueur.getLigne() == l);
		assertTrue("JoueurService : addReward : inv : addReward(BlocReward.Rien) ne modifie pas le nombre de bombes posables par le joueur", joueur.getNbBombePosable() == p);
		assertTrue("JoueurService : addReward : inv : addReward(BlocReward.Rien) ne modifie pas l'invulneravilite du joueur", joueur.getInvulnerableStep() == i);
	}
}
