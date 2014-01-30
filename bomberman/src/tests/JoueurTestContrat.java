package tests;

import implementation.Joueur;
import implementation.Terrain;

import org.junit.Before;

import services.TerrainService;
import contrats.JoueurContrat;
import contrats.TerrainContrat;

public class JoueurTestContrat extends AbstractJoueurTest {

	@Override
	@Before
	public void beforeTests() {
		TerrainService t = new TerrainContrat(new Terrain());
		t.init(13, 15);
		this.setTerrain(t);
		this.setJoueur(new JoueurContrat(new Joueur()));
	}
}
