package tests;

import implementation.Bombe;
import implementation.Joueur;
import implementation.Terrain;

import org.junit.Before;

import services.JoueurService;
import services.TerrainService;
import contrats.BombeContrat;
import contrats.JoueurContrat;
import contrats.TerrainContrat;

public class BombeTestContrat extends AbstractBombeTest{

	@Override
	@Before
	public void beforeTests() {
		TerrainService t = new TerrainContrat(new Terrain());
		t.init(13, 15);
		this.setTerrain(t);
		JoueurService j = new JoueurContrat(new Joueur());
		j.init(2, 2, t);
		this.setJoueur(j);
		this.setBombe(new BombeContrat(new Bombe()));
	}

}
