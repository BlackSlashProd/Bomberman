package tests;

import implementation.Terrain;
import contrats.TerrainContrat;

public class TerrainTestContrat extends AbstractTerrainTest{

	@Override
	public void beforeTests() {
		this.setTerrain(new TerrainContrat(new Terrain()));
	}

}
