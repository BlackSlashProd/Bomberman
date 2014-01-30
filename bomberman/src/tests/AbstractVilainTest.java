package tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.TerrainService;
import services.VilainService;
import utils.TypeVilain;

public abstract class AbstractVilainTest {
	private VilainService vilain;
	private TerrainService terrain;
	
	public AbstractVilainTest(){
		this.setVilain(null);
	}
	
	public VilainService getVilain() {
		return vilain;
	}

	public void setVilain(VilainService vilain) {
		this.vilain = vilain;
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
		vilain = null;
	}
	/**
	 * TEST INIT
	 */
	@Test
	public void test_post_init_1(){
		//Operation
		vilain.init(TypeVilain.BallonOrange, 1, 2, 2, terrain);
		//Verif
		assertTrue("VilainService : init : post : init(TypeVilain.BallonOrange, 1, 2, 2, terrain) donne un vilain de type BallonOrange", vilain.getType()==TypeVilain.BallonOrange);
		assertTrue("VilainService : init : post : init(TypeVilain.BallonOrange, 1, 2, 2, terrain) donne un vilain dont la ligne est 2", vilain.getLigne() == 2);
		assertTrue("VilainService : init : post : init(TypeVilain.BallonOrange, 1, 2, 2, terrain) donne un vilain dont la colonne est 2", vilain.getColonne() == 2);
		assertTrue("VilainService : init : post : init(TypeVilain.BallonOrange, 1, 2, 2, terrain) donne un vilain dont le numero est 1", vilain.getNum() == 1);
	}
	@Test
	public void test_post_init_2(){
		//Operation
		vilain.init(TypeVilain.FantomeBleu, 1, 2, 2, terrain);
		//Verif
		assertTrue("VilainService : init : post : init(TypeVilain.FantomeBleu, 1, 2, 2, terrain) donne un vilain de type FantomeBleu", vilain.getType()==TypeVilain.FantomeBleu);
		assertTrue("VilainService : init : post : init(TypeVilain.FantomeBleu, 1, 2, 2, terrain) donne un vilain dont la ligne est 2", vilain.getLigne() == 2);
		assertTrue("VilainService : init : post : init(TypeVilain.FantomeBleu, 1, 2, 2, terrain) donne un vilain dont la colonne est 2", vilain.getColonne() == 2);
		assertTrue("VilainService : init : post : init(TypeVilain.FantomeBleu, 1, 2, 2, terrain) donne un vilain dont le numero est 1", vilain.getNum() == 1);
	}
	@Test
	public void test_pre_init_1(){
		// placement hors contrat (ligne < 2)

		//Operation
		try{
			vilain.init(TypeVilain.BallonOrange, 1, 1, 2, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("VilainService : init : init(TypeVilain.BallonOrange, 1, 1, 2, terrain) leve une exception", true);
			return;
		}
		assertTrue("VilainService : init : init(TypeVilain.BallonOrange, 1, 1, 2, terrain) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_2(){
		// placement hors contrat (ligne > 12)

		//Operation
		try{
			vilain.init(TypeVilain.BallonOrange, 1, 13, 2, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("VilainService : init : init(TypeVilain.BallonOrange, 1, 13, 2, terrain) leve une exception", true);
			return;
		}
		assertTrue("VilainService : init : init(TypeVilain.BallonOrange, 1, 13, 2, terrain) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_3(){
		// placement hors contrat (colonne < 2)

		//Operation
		try{
			vilain.init(TypeVilain.BallonOrange, 1, 2, 1, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("VilainService : init : init(TypeVilain.BallonOrange, 1, 1, 2, terrain) leve une exception", true);
			return;
		}
		assertTrue("VilainService : init : init(TypeVilain.BallonOrange, 1, 1, 2, terrain) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_4(){
		// placement hors contrat (colonne > 14)

		//Operation
		try{
			vilain.init(TypeVilain.BallonOrange, 1, 2, 15, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("VilainService : init : init(TypeVilain.BallonOrange, 1, 13, 2, terrain) leve une exception", true);
			return;
		}
		assertTrue("VilainService : init : init(TypeVilain.BallonOrange, 1, 13, 2, terrain) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_5(){
		// terrain null

		//Operation
		try{
			vilain.init(TypeVilain.BallonOrange, 1, 2, 2, null);
		}catch(Throwable e){
			//Verif
			assertTrue("VilainService : init : init(TypeVilain.BallonOrange, 1, 2, 2, null) leve une exception", true);
			return;
		}
		assertTrue("VilainService : init : init(TypeVilain.BallonOrange, 1, 2, 2, null) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_6(){
		// type null

		//Operation
		try{
			vilain.init(null, 1, 2, 2, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("VilainService : init : init(null, 1, 2, 2, terrain) leve une exception", true);
			return;
		}
		assertTrue("VilainService : init : init(null, 1, 2, 2, terrain) ne leve pas d'exception", false);
	}
	
	/**
	 * TEST SETLIGNE
	 */
	@Test
	public void test_post_setLigne_1(){
		//Init
		vilain.init(TypeVilain.BallonOrange, 1, 2, 2, terrain);
		//Operation
		vilain.setLigne(5, terrain);
		//Verif
		assertTrue("VilainService : setLigne : post : setLigne(5, t) passe la ligne du vilain a 5", vilain.getLigne() == 5);
	}
	@Test
	public void test_pre_setLigne_1(){
		//Init
		vilain.init(TypeVilain.BallonOrange, 1, 2, 2, terrain);
		// ligne < 2
		try{
			//Operation
			vilain.setLigne(1, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("VilainService : setLigne : pre : setLigne(1, t) leve une exception", true);
			return;
		}
		assertTrue("VilainService : setLigne : pre : setLigne(1, t) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_setLigne_2(){
		//Init
		vilain.init(TypeVilain.BallonOrange, 1, 2, 2, terrain);
		// ligne > 12
		try{
			//Operation
			vilain.setLigne(13, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("VilainService : setLigne : pre : setLigne(13, t) leve une exception", true);
			return;
		}
		assertTrue("VilainService : setLigne : pre : setLigne(13, t) ne leve pas d'exception", false);
	}
	/**
	 * TEST SETCOLONNE
	 */
	@Test
	public void test_post_setColonne_1(){
		//Init
		vilain.init(TypeVilain.BallonOrange, 1, 2, 2, terrain);
		//Operation
		vilain.setColonne(5, terrain);
		//Verif
		assertTrue("VilainService : setColonne : post : setColonne(5, t) passe la colonne du vilain 5", vilain.getColonne() == 5);
	}
	@Test
	public void test_pre_setColonne_1(){
		//Init
		vilain.init(TypeVilain.BallonOrange, 1, 2, 2, terrain);
		// ligne < 2
		try{
			//Operation
			vilain.setColonne(1, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("VilainService : setColonne : pre : setColonne(1, t) leve une exception", true);
			return;
		}
		assertTrue("VilainService : setColonne : pre : setColonne(1, t) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_setColonne_2(){
		//Init
		vilain.init(TypeVilain.BallonOrange, 1, 2, 2, terrain);
		// ligne > 12
		try{
			//Operation
			vilain.setColonne(15, terrain);
		}catch(Throwable e){
			//Verif
			assertTrue("VilainService : setColonne : pre : setColonne(15, t) leve une exception", true);
			return;
		}
		assertTrue("VilainService : setColonne : pre : setColonne(15, t) ne leve pas d'exception", false);
	}
}
