package tests;

/*
 * classe OK.
 */
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.BombeService;
import services.JoueurService;
import services.TerrainService;

public abstract class AbstractBombeTest {
	
	private BombeService bombe;
	private JoueurService joueur;
	private TerrainService terrain;
	
	public AbstractBombeTest(){
		this.bombe = null;
	}
	
	public void setBombe(BombeService bombe){
		this.bombe = bombe;
	}
	
	public BombeService getBombe(){
		return bombe;
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
		bombe = null;
	}
	
	/**
	 * TEST INIT
	 */
	@Test
	public void test_pre_init_1(){
		// placement hors terrain (ligne > 12)
		
		//Operation
		try{
			bombe.init(joueur, 0, 13, 2, 3, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("BombeService : init : init(joueur, 0, 50, 50, 3, terrain) leve une exception", true);
			return;
		}
		assertTrue("BombeService : init : init(joueur, 0, 50, 50, 3, terrain) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_2(){
		// placement hors terrain (ligne < 2)
		
		//Operation
		try{
			bombe.init(joueur, 0, 1, 2, 3, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("BombeService : init : init(joueur, 0, 1, 2, 3, terrain) leve une exception", true);
			return;
		}
		assertTrue("BombeService : init : init(joueur, 0, 1, 2, 3, terrain) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_3(){
		// placement hors terrain (colonne > 14)
		
		//Operation
		try{
			bombe.init(joueur, 0, 2, 15, 3, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("BombeService : init : init(joueur, 0, 2, 15, 3, terrain) leve une exception", true);
			return;
		}
		assertTrue("BombeService : init : init(joueur, 0, 2, 15, 3, terrain) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_4(){
		// placement hors terrain (colonne < 2)
		
		//Operation
		try{
			bombe.init(joueur, 0, 2, 1, 3, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("BombeService : init : init(joueur, 0, 2, 1, 3, terrain) leve une exception", true);
			return;
		}
		assertTrue("BombeService : init : init(joueur, 0, 2, 1, 3, terrain) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_5(){
		// amplitude inferieure au minimum
		
		//Operation
		try{
			bombe.init(joueur, 0, 2, 2, 2, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("BombeService : init : init(joueur, 0, 2, 2, 2, terrain) leve une exception", true);
			return;
		}
		assertTrue("BombeService : init : init(joueur, 0, 2, 2, 2, terrain) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_6(){
		// amplitude superieure au minimum
		
		//Operation
		try{
			bombe.init(joueur, 0, 2, 2, 12, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("BombeService : init : init(joueur, 0, 2, 2, 12, terrain) leve une exception", true);
			return;
		}
		assertTrue("BombeService : init : init(joueur, 0, 2, 2, 12, terrain) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_7(){
		// terrain null
		
		//Operation
		try{
			bombe.init(joueur, 0, 2, 2, 3, null);
		}catch(Throwable e){
			//Verif
			assertTrue("BombeService : init : init(joueur, 0, 2, 2, 3, null) leve une exception", true);
			return;
		}
		assertTrue("BombeService : init : init(joueur, 0, 2, 2, 3, null) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_8(){
		// joueur null
		
		//Operation
		try{
			bombe.init(null, 0, 2, 2, 3, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("BombeService : init : init(null, 0, 2, 2, 3, terrain) leve une exception", true);
			return;
		}
		assertTrue("BombeService : init : init(null, 0, 2, 2, 3, terrain) ne leve pas d'exception", false);
	}
	@Test
	public void test_post_init_1(){
		//Operation
		bombe.init(joueur, 0, 2, 2, 3, terrain);
		//Verif
		assertTrue("BombeService : init : post : init(joueur, 0, 2, 2, 3, terrain) donne une bombe avec un timer de 10", bombe.getTimer() == 10);
		assertTrue("BombeService : init : post : init(joueur, 0, 2, 2, 3, terrain) donne une bombe avec une amplitude de 3", bombe.getAmplitude() == 3);
		assertTrue("BombeService : init : post : init(joueur, 0, 2, 2, 3, terrain) donne une bombe avec comme ligne 2", bombe.getLigne() == 2);
		assertTrue("BombeService : init : post : init(joueur, 0, 2, 2, 3, terrain) donne une bombe avec comme colonne 2", bombe.getColonne() == 2);
		assertTrue("BombeService : init : post : init(joueur, 0, 2, 2, 3, terrain) donne une bombe avec comme numero 0", bombe.getNum() == 0);
		assertTrue("BombeService : init : post : init(joueur, 0, 2, 2, 3, terrain) donne une bombe avec comme proprietaire joueur", bombe.getProprietaire() == joueur);
	}

	/**
	 * TEST SETTIMER
	 */
	@Test
	public void test_pre_setTimer_1(){
		//Init
		bombe.init(joueur, 0, 2, 2, 3, terrain);
		// timer negatif
		try{
			//Operation
			bombe.setTimer(-1);
		}catch(Throwable e){
			//Verif
			assertTrue("BombeService : setTimer : post : setTimer(-1) leve une exception", true);
			return;
		}
		assertTrue("BombeService : setTimer : post : setTimer(-1) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_setTimer_2(){
		//Init
		bombe.init(joueur, 0, 2, 2, 3, terrain);
		// timer > 10
		try{
			//Operation
			bombe.setTimer(11);
		}catch(Throwable e){
			//Verif
			assertTrue("BombeService : setTimer : post : setTimer(11) leve une exception", true);
			return;
		}
		assertTrue("BombeService : setTimer : post : setTimer(11) ne leve pas d'exception", false);
	}
	@Test
	public void test_inv_setTimer_1(){
		//Operation
		bombe.init(joueur, 0, 2, 2, 3, terrain);
		bombe.setTimer(5);
		//Verif
		assertTrue("BombeService : setTimer : invariant : timer <= 10", bombe.getTimer() <= 10);
		assertTrue("BombeService : setTimer : invariant : timer >= 0", bombe.getTimer() >= 0);
		assertTrue("BombeService : setTimer : invariant : amplitude <= 3", bombe.getAmplitude() >= 3);
		assertTrue("BombeService : setTimer : invariant : amplitude >= 11", bombe.getAmplitude() <= 11);
	}
	@Test
	public void test_post_setTimer_1(){
		//Init
		bombe.init(joueur, 0, 2, 2, 3, terrain);
		//Operation
		bombe.setTimer(5);
		//Verif
		assertTrue("BombeService : setTimer : post : setTimer(5) passe le timer de la bombe a 5", bombe.getTimer() == 5);
	}
	
	/**
	 * TEST GETTIMER
	 */
	@Test
	public void test_inv_getTimer_1(){
		//Operation
		bombe.init(joueur, 0, 2, 2, 3, terrain);
		bombe.getTimer();
		//Verif
		assertTrue("BombeService : getTimer : invariant : timer <= 10", bombe.getTimer() <= 10);
		assertTrue("BombeService : getTimer : invariant : timer >= 0", bombe.getTimer() >= 0);
		assertTrue("BombeService : getTimer : invariant : amplitude <= 3", bombe.getAmplitude() >= 3);
		assertTrue("BombeService : getTimer : invariant : amplitude >= 11", bombe.getAmplitude() <= 11);
	}
	
	/**
	 * TEST GETLIGNE
	 */
	@Test
	public void test_inv_getLigne_1(){
		//Operation
		bombe.init(joueur, 0, 2, 2, 3, terrain);
		bombe.getLigne();
		//Verif
		assertTrue("BombeService : getLigne : invariant : timer <= 10", bombe.getTimer() <= 10);
		assertTrue("BombeService : getLigne : invariant : timer >= 0", bombe.getTimer() >= 0);
		assertTrue("BombeService : getLigne : invariant : amplitude <= 3", bombe.getAmplitude() >= 3);
		assertTrue("BombeService : getLigne : invariant : amplitude >= 11", bombe.getAmplitude() <= 11);
	}
	
	/**
	 * TEST GETCOLONNE
	 */
	@Test
	public void test_inv_getColonne_1(){
		//Operation
		bombe.init(joueur, 0, 2, 2, 3, terrain);
		bombe.getColonne();
		//Verif
		assertTrue("BombeService : getColonne : invariant : timer <= 10", bombe.getTimer() <= 10);
		assertTrue("BombeService : getColonne : invariant : timer >= 0", bombe.getTimer() >= 0);
		assertTrue("BombeService : getColonne : invariant : amplitude <= 3", bombe.getAmplitude() >= 3);
		assertTrue("BombeService : getColonne : invariant : amplitude >= 11", bombe.getAmplitude() <= 11);
	}
	
	/**
	 * TEST GETAMPLITUDE
	 */
	@Test
	public void test_inv_getAmplitude_1(){
		//Operation
		bombe.init(joueur, 0, 2, 2, 3, terrain);
		bombe.getAmplitude();
		//Verif
		assertTrue("BombeService : setTimer : invariant : timer <= 10", bombe.getTimer() <= 10);
		assertTrue("BombeService : setTimer : invariant : timer >= 0", bombe.getTimer() >= 0);
		assertTrue("BombeService : setTimer : invariant : amplitude <= 3", bombe.getAmplitude() >= 3);
		assertTrue("BombeService : setTimer : invariant : amplitude >= 11", bombe.getAmplitude() <= 11);
	}
	
	/**
	 * TEST GETNUM
	 */
	@Test
	public void test_inv_getNum_1(){
		//Operation
		bombe.init(joueur, 0, 2, 2, 3, terrain);
		bombe.getNum();
		//Verif
		assertTrue("BombeService : getNum : invariant : timer <= 10", bombe.getTimer() <= 10);
		assertTrue("BombeService : getNum : invariant : timer >= 0", bombe.getTimer() >= 0);
		assertTrue("BombeService : getNum : invariant : amplitude <= 3", bombe.getAmplitude() >= 3);
		assertTrue("BombeService : getNum : invariant : amplitude >= 11", bombe.getAmplitude() <= 11);
	}
	
	/**
	 * TEST GETPROPRIETAIRE
	 */
	@Test
	public void test_inv_getProprietaire_1(){
		//Operation
		bombe.init(joueur, 0, 2, 2, 3, terrain);
		bombe.getProprietaire();
		//Verif
		assertTrue("BombeService : getProprietaire : invariant : timer <= 10", bombe.getTimer() <= 10);
		assertTrue("BombeService : getProprietaire : invariant : timer >= 0", bombe.getTimer() >= 0);
		assertTrue("BombeService : getProprietaire : invariant : amplitude <= 3", bombe.getAmplitude() >= 3);
		assertTrue("BombeService : getProprietaire : invariant : amplitude >= 11", bombe.getAmplitude() <= 11);
	}
}
