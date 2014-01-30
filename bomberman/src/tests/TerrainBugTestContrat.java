package tests;

import implementation.bug.Terrain;
import contrats.TerrainContrat;

public class TerrainBugTestContrat extends AbstractTerrainTest{

	@Override
	public void beforeTests() {
		this.setTerrain(new TerrainContrat(new Terrain()));
	}

}
