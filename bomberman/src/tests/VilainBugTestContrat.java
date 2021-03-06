package tests;

import implementation.bug.Terrain;
import implementation.bug.Vilain;

import org.junit.Before;

import services.TerrainService;


import contrats.TerrainContrat;
import contrats.VilainContrat;

public class VilainBugTestContrat extends AbstractVilainTest{

	@Override
	@Before
	public void beforeTests() {
		TerrainService t = new TerrainContrat(new Terrain());
		t.init(13, 15);
		this.setTerrain(t);
		setVilain(new VilainContrat(new Vilain()));
	}
}