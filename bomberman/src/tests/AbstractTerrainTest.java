package tests;

/*
 * Classe OK.
 */
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.TerrainService;
import utils.BlocReward;
import utils.BlocType;

public abstract class AbstractTerrainTest {
	
	private TerrainService terrain;
	
	public AbstractTerrainTest(){
		this.terrain = null;
	}
	
	public void setTerrain(TerrainService terrain){
		this.terrain = terrain;
	}
	
	public TerrainService getTerrain(){
		return terrain;
	}
	
	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		terrain = null;
	}
	
	/**
	 * TEST INIT
	 */
	@Test
	public void test_pre_init_1(){
		// nb lignes <= 5
		
		//Operation
		try{
			terrain.init(5, 15);
		}catch(Throwable e){
			//Verif
			assertTrue("TerrainService : init : pre : init(5, 15) leve une exception", true);
			return;
		}
		assertTrue("TerrainService : init : pre : init(5, 15) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_2(){
		// nb colonnes <= 5
		
		//Operation
		try{
			terrain.init(13, 5);
		}catch(Throwable e){
			//Verif
			assertTrue("TerrainService : init : pre : init(13, 5) leve une exception", true);
			return;
		}
		assertTrue("TerrainService : init : pre : init(13, 5) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_3(){
		// nb colonnes <= 5
		
		//Operation
		try{
			terrain.init(5, 5);
		}catch(Throwable e){
			//Verif
			assertTrue("TerrainService : init : pre : init(5, 5) leve une exception", true);
			return;
		}
		assertTrue("TerrainService : init : pre : init(5, 5) ne leve pas d'exception", false);
	}
	@Test
	public void test_post_init_1(){
		//Operation
		terrain.init(13, 15);
		
		assertTrue("TerrainService : init : post : init(13,15) donne un terrain de 13 lignes de haut.", terrain.getNbLignes() == 13);
		assertTrue("TerrainService : init : post : init(13,15) donne un terrain de 15 colonnes de large.", terrain.getNbColonnes() == 15);
		for(int i = 1; i <= 13; i++){
			for(int j = 1; j <= 15; j++){
				assertTrue("TerrainService : init : post : init(13,15) donne un terrain dont toutes les cases sont des blocs.", terrain.getBloc(i, j) != null);
				if(i == 1){
					assertTrue("TerrainService : init : post : init(13,15) donne un terrain dont les murs exterieurs sont en metal.", terrain.getBloc(i, j).getType() == BlocType.MurMetal);
					assertTrue("TerrainService : init : post : init(13,15) donne un terrain dont les murs exterieurs n'ont pas de reward.", terrain.getBloc(i, j).getReward() == BlocReward.Rien);
				}
				if(i == 13){
					assertTrue("TerrainService : init : post : init(13,15) donne un terrain dont les murs exterieurs sont en metal.", terrain.getBloc(i, j).getType() == BlocType.MurMetal);
					assertTrue("TerrainService : init : post : init(13,15) donne un terrain dont les murs exterieurs n'ont pas de reward.", terrain.getBloc(i, j).getReward() == BlocReward.Rien);
				}
				if(j == 1){
					assertTrue("TerrainService : init : post : init(13,15) donne un terrain dont les murs exterieurs sont en metal.", terrain.getBloc(i, j).getType() == BlocType.MurMetal);
					assertTrue("TerrainService : init : post : init(13,15) donne un terrain dont les murs exterieurs n'ont pas de reward.", terrain.getBloc(i, j).getReward() == BlocReward.Rien);
				}
				if(j == 15){
					assertTrue("TerrainService : init : post : init(13,15) donne un terrain dont les murs exterieurs sont en metal.", terrain.getBloc(i, j).getType() == BlocType.MurMetal);
					assertTrue("TerrainService : init : post : init(13,15) donne un terrain dont les murs exterieurs n'ont pas de reward.", terrain.getBloc(i, j).getReward() == BlocReward.Rien);
				}
				if(i%2 == 1 && j%2 == 1){
					assertTrue("TerrainService : init : post : init(13,15) donne un terrain dont les cases ou i%2 = 1 et j%2 = 1 sont des murs en metal.", terrain.getBloc(i, j).getType() == BlocType.MurMetal);
					assertTrue("TerrainService : init : post : init(13,15) donne un terrain dont les cases ou i%2 = 1 et j%2 = 1 n'ont pas de reward.", terrain.getBloc(i, j).getReward() == BlocReward.Rien);
				}
			}
		}
	}
	/**
	 * TEST GETBLOC
	 */
	@Test
	public void test_pre_getBloc_1(){
		// ligne < 1
		
		//Operation
		try{
			terrain.getBloc(0, 1);
		}catch(Throwable e){
			//Verif
			assertTrue("TerrainService : getBloc : getBloc(0 ,) leve une exception", true);
			return;
		}
		assertTrue("TerrainService : getBloc : getBloc(0, 1) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_getBloc_2(){
		// colonne < 1
		
		//Operation
		try{
			terrain.getBloc(1, 0);
		}catch(Throwable e){
			//Verif
			assertTrue("TerrainService : getBloc : getBloc(1, 0) leve une exception", true);
			return;
		}
		assertTrue("TerrainService : getBloc : getBloc(1, 0) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_getBloc_3(){
		// ligne > 13
		
		//Operation
		try{
			terrain.getBloc(14, 1);
		}catch(Throwable e){
			//Verif
			assertTrue("TerrainService : getBloc : getBloc(14, 1) leve une exception", true);
			return;
		}
		assertTrue("TerrainService : getBloc : getBloc(14, 1) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_getBloc_4(){
		// colonne > 15
		
		//Operation
		try{
			terrain.getBloc(1, 16);
		}catch(Throwable e){
			//Verif
			assertTrue("TerrainService : getBloc : getBloc(1, 16) leve une exception", true);
			return;
		}
		assertTrue("TerrainService : getBloc : getBloc(1, 16) ne leve pas d'exception", false);
	}
	@Test
	public void test_inv_getBloc_1(){
		//Init
		terrain.init(13, 15);
		//Save
		int lignes = terrain.getNbLignes();
		int colonnes = terrain.getNbColonnes();
		//Operation
		terrain.getBloc(1, 1);
		
		assertTrue("TerrainService : getBloc : inv : getBloc(1,1) donne un terrain de 13 lignes de haut.", lignes == 13);
		assertTrue("TerrainService : getBloc : inv : getBloc(1,1) donne un terrain de 15 colonnes de large.", colonnes == 15);
		for(int i = 1; i <= 13; i++){
			for(int j = 1; j <= 15; j++){
				assertTrue("TerrainService : getBloc : inv : getBloc(1,1) ne modifie pas le terrain.", terrain.getBloc(i, j) != null);
				if(i == 1){
					assertTrue("TerrainService : getBloc : inv : getBloc(1,1) ne modifie pas le terrain.", terrain.getBloc(i, j).getType() == BlocType.MurMetal);
					assertTrue("TerrainService : getBloc : inv : getBloc(1,1) ne modifie pas le terrain.", terrain.getBloc(i, j).getReward() == BlocReward.Rien);
				}
				if(i == 13){
					assertTrue("TerrainService : getBloc : inv : getBloc(1,1) ne modifie pas le terrain.", terrain.getBloc(i, j).getType() == BlocType.MurMetal);
					assertTrue("TerrainService : getBloc : inv : getBloc(1,1) ne modifie pas le terrain.", terrain.getBloc(i, j).getReward() == BlocReward.Rien);
				}
				if(j == 1){
					assertTrue("TerrainService : getBloc : inv : getBloc(1,1) ne modifie pas le terrain.", terrain.getBloc(i, j).getType() == BlocType.MurMetal);
					assertTrue("TerrainService : getBloc : inv : getBloc(1,1) ne modifie pas le terrain.", terrain.getBloc(i, j).getReward() == BlocReward.Rien);
				}
				if(j == 15){
					assertTrue("TerrainService : getBloc : inv : getBloc(1,1) ne modifie pas le terrain.", terrain.getBloc(i, j).getType() == BlocType.MurMetal);
					assertTrue("TerrainService : getBloc : inv : getBloc(1,1) ne modifie pas le terrain.", terrain.getBloc(i, j).getReward() == BlocReward.Rien);
				}
				if(i%2 == 1 && j%2 == 1){
					assertTrue("TerrainService : getBloc : inv : getBloc(1,1) ne modifie pas le terrain.", terrain.getBloc(i, j).getType() == BlocType.MurMetal);
					assertTrue("TerrainService : getBloc : inv : getBloc(1,1) ne modifie pas le terrain.", terrain.getBloc(i, j).getReward() == BlocReward.Rien);
				}
			}
		}
	}
}
