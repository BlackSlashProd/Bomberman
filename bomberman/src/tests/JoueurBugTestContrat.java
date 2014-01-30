package tests;

import implementation.bug.Joueur;
import implementation.bug.Terrain;

import org.junit.Before;

import services.TerrainService;
import contrats.JoueurContrat;
import contrats.TerrainContrat;

public class JoueurBugTestContrat extends AbstractJoueurTest {

	@Override
	@Before
	public void beforeTests() {
		TerrainService t = new TerrainContrat(new Terrain());
		t.init(13, 15);
		this.setTerrain(t);
		this.setJoueur(new JoueurContrat(new Joueur()));
	}
}
