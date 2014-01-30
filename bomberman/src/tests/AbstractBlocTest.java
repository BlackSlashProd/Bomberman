package tests;

/*
 * classe OK.
 */
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.BlocService;
import utils.BlocReward;
import utils.BlocType;

public abstract class AbstractBlocTest {
	
	private BlocService bloc;
	
	public AbstractBlocTest(){
		this.bloc = null;
	}
	
	public void setBloc(BlocService bloc){
		this.bloc = bloc;
	}
	
	public BlocService getBloc(){
		return bloc;
	}
	
	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		bloc = null;
	}
	
	/**
	 * TEST INIT
	 */
	@Test
	public void test_pre_init_1(){
		// BlocType null
		
		//Operation
		try{
			bloc.init(null, BlocReward.Rien);
		}catch(Throwable e){
			//Verif
			assertTrue("BlocService : Init : pre : init(null, BlocReward.Rien) leve une exception", true);
			return;
		}
		assertTrue("BlocService : Init : pre : init(null, BlocReward.Rien) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_2(){
		// BlocReward null
		
		//Operation
		try{
			bloc.init(BlocType.Vide, null);
		}catch(Throwable e){
			//Verif
			assertTrue("BlocService : Init : pre : init(BlocType.Vide, null) leve une exception", true);
			return;
		}
		assertTrue("BlocService : Init : pre : init(BlocType.Vide, null) ne leve pas d'exception", false);
	}
	@Test
	public void test_pre_init_3(){
		// BlocReward null
		
		//Operation
		try{
			bloc.init(null, null);
		}catch(Throwable e){
			//Verif
			assertTrue("BlocService : Init : pre : init(null, null) leve une exception", true);
			return;
		}
		assertTrue("BlocService : Init : pre : init(null, null) ne leve pas d'exception", false);
	}
	@Test
	public void test_post_init_1(){
		//Operation
		bloc.init(BlocType.MurBrique, BlocReward.Rien);
		//Verif
		assertTrue("BlocService : Init : post : init(MurBrique, Rien) donne un bloc de type MurBrique",bloc.getType()==BlocType.MurBrique);
		assertTrue("BlocService : Init : post : init(MurBrique, Rien) donne un bloc avec le reward Rien",bloc.getReward()==BlocReward.Rien);
		assertTrue("BlocService : Init : post : init(MurBrique, Rien) donne un bloc destructible",bloc.isDestructible() == true);
	}
	@Test
	public void test_post_init_2(){
		//Operation
		bloc.init(BlocType.MurMetal, BlocReward.Rien);
		//Verif
		assertTrue("BlocService : Init : post : init(MurMetal, Rien) donne un bloc de type MurBrique",bloc.getType()==BlocType.MurMetal);
		assertTrue("BlocService : Init : post : init(MurMetal, Rien) donne un bloc avec le reward Rien",bloc.getReward()==BlocReward.Rien);
		assertTrue("BlocService : Init : post : init(MurMetal, Rien) donne un bloc destructible",bloc.isDestructible() == false);
	}
	@Test
	public void test_post_init_3(){
		//Operation
		bloc.init(BlocType.Explosion, BlocReward.Rien);
		//Verif
		assertTrue("BlocService : Init : post : init(Explosion, Rien) donne un bloc de type MurBrique",bloc.getType()==BlocType.Explosion);
		assertTrue("BlocService : Init : post : init(Explosion, Rien) donne un bloc avec le reward Rien",bloc.getReward()==BlocReward.Rien);
		assertTrue("BlocService : Init : post : init(Explosion, Rien) donne un bloc destructible",bloc.isDestructible() == true);
	}
	@Test
	public void test_post_init_4(){
		//Operation
		bloc.init(BlocType.Vide, BlocReward.Rien);
		//Verif
		assertTrue("BlocService : Init : post : init(Vide, Rien) donne un bloc de type MurBrique",bloc.getType()==BlocType.Vide);
		assertTrue("BlocService : Init : post : init(Vide, Rien) donne un bloc avec le reward Rien",bloc.getReward()==BlocReward.Rien);
		assertTrue("BlocService : Init : post : init(Vide, Rien) donne un bloc destructible",bloc.isDestructible() == true);
	}
	/**
	 * TEST SETTYPE
	 */
	@Test
	public void test_pre_setType_1(){
		// BlocType null
		//init
		bloc.init(BlocType.Vide, BlocReward.Rien);
		//Operation
		try{
			bloc.setType(null);
		}catch(Throwable e){
			//Verif
			assertTrue("BlocService : Init : setType : setType(null) leve une exception", true);
			return;
		}
		assertTrue("BlocService : Init : setType : setType(null) ne leve pas d'exception", false);
	}
	@Test
	public void test_post_setType_1(){
		//init
		bloc.init(BlocType.Vide, BlocReward.Rien);
		//Operation
		bloc.setType(BlocType.MurMetal);
		//Verif
		assertTrue("BlocService : setType : post : setType(MurMetal) donne un bloc de type MurMetal",bloc.getType()==BlocType.MurMetal);
		assertTrue("BlocService : setType : post : setType(MurMetal) donne un bloc qui n'est pas destructible", bloc.isDestructible() == false);
	}
	@Test
	public void test_post_setType_2(){
		//init
		bloc.init(BlocType.Vide, BlocReward.Rien);
		//Operation
		bloc.setType(BlocType.MurBrique);
		//Verif
		assertTrue("BlocService : setType : post : setType(MurBrique) donne un bloc de type MurBrique",bloc.getType()==BlocType.MurBrique);
		assertTrue("BlocService : setType : post : setType(MurBrique) donne un bloc qui est destructible", bloc.isDestructible() == true);
	}
	@Test
	public void test_post_setType_3(){
		//init
		bloc.init(BlocType.Vide, BlocReward.Rien);
		//Operation
		bloc.setType(BlocType.Explosion);
		//Verif
		assertTrue("BlocService : setType : post : setType(Explosion) donne un bloc de type Explosion",bloc.getType()==BlocType.Explosion);
		assertTrue("BlocService : setType : post : setType(Explosion) donne un bloc qui est destructible", bloc.isDestructible() == true);
	}
	@Test
	public void test_post_setType_4(){
		//init
		bloc.init(BlocType.Vide, BlocReward.Rien);
		//Operation
		bloc.setType(BlocType.Vide);
		//Verif
		assertTrue("BlocService : setType : post : setType(Vide) donne un bloc de type Vide",bloc.getType()==BlocType.Vide);
		assertTrue("BlocService : setType : post : setType(Vide) donne un bloc qui est destructible", bloc.isDestructible() == true);
	}
	
	/**
	 * TEST POUR SETREWARD
	 */
	@Test
	public void test_pre_setReward_1(){
		// BlocType null
		//init
		bloc.init(BlocType.Vide, BlocReward.Rien);
		//Operation
		try{
			bloc.setReward(null);
		}catch(Throwable e){
			//Verif
			assertTrue("BlocService : Init : setReward : setReward(null) leve une exception", true);
			return;
		}
		assertTrue("BlocService : Init : setReward : setReward(null) ne leve pas d'exception", false);
	}
	@Test
	public void test_post_setReward_1(){
		//init
		bloc.init(BlocType.Vide, BlocReward.Rien);
		//Operation
		bloc.setReward(BlocReward.BombPass);
		//Verif
		assertTrue("BlocService : setReward : post : setReward(BombPass) donne un bloc posseant le reward BombPass",bloc.getReward()==BlocReward.BombPass);
	}
	@Test
	public void test_post_setReward_2(){
		//init
		bloc.init(BlocType.Vide, BlocReward.Rien);
		//Operation
		bloc.setReward(BlocReward.BombUp);
		//Verif
		assertTrue("BlocService : setReward : post : setReward(BombUp) donne un bloc possedant le reward BombUp",bloc.getReward()==BlocReward.BombUp);
	}
	@Test
	public void test_post_setReward_3(){
		//init
		bloc.init(BlocType.Vide, BlocReward.Rien);
		//Operation
		bloc.setReward(BlocReward.FireSuit);
		//Verif
		assertTrue("BlocService : setReward : post : setReward(FireSuit) donne un bloc possedant le reward FireSuit",bloc.getReward()==BlocReward.FireSuit);
	}
	@Test
	public void test_post_setReward_4(){
		//init
		bloc.init(BlocType.Vide, BlocReward.Rien);
		//Operation
		bloc.setReward(BlocReward.FireUp);
		//Verif
		assertTrue("BlocService : setReward : post : setReward(FireUp) donne un bloc possedant le reward FireUp",bloc.getReward()==BlocReward.FireUp);
	}
	@Test
	public void test_post_setReward_5(){
		//init
		bloc.init(BlocType.Vide, BlocReward.Rien);
		//Operation
		bloc.setReward(BlocReward.Rien);
		//Verif
		assertTrue("BlocService : setReward : post : setReward(Rien) donne un bloc possedant le reward Rien",bloc.getReward()==BlocReward.Rien);
	}
	@Test
	public void test_post_setReward_6(){
		//init
		bloc.init(BlocType.Vide, BlocReward.Rien);
		//Operation
		bloc.setReward(BlocReward.WallPass);
		//Verif
		assertTrue("BlocService : setReward : post : setReward(WallPass) donne un bloc possedant le reward WallPass",bloc.getReward()==BlocReward.WallPass);
	}
	/**
	 * Pas d'invariants dans ce service, on ne teste pas les accesseurs
	 */
}
