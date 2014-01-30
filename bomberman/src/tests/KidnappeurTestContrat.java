package tests;

import implementation.Kidnappeur;
import implementation.Terrain;

import org.junit.Before;

import services.TerrainService;
import contrats.KidnappeurContrat;
import contrats.TerrainContrat;

public class KidnappeurTestContrat extends AbstractJoueurTest {

	@Override
	@Before
	public void beforeTests() {
		TerrainService t = new TerrainContrat(new Terrain());
		t.init(13, 15);
		this.setTerrain(t);
		this.setJoueur(new KidnappeurContrat(new Kidnappeur()));
	}
}
