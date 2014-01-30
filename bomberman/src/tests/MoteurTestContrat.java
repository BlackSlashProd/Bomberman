package tests;

import implementation.Moteur;

import org.junit.Before;

import contrats.MoteurContrat;

public class MoteurTestContrat extends AbstractMoteurTest{

	@Override
	@Before
	public void beforeTests() {
		this.setMoteur(new MoteurContrat(new Moteur()));
	}

}
