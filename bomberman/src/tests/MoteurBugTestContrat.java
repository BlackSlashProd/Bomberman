package tests;

import implementation.bug.Moteur;

import org.junit.Before;

import contrats.MoteurContrat;

public class MoteurBugTestContrat extends AbstractMoteurTest{

	@Override
	@Before
	public void beforeTests() {
		this.setMoteur(new MoteurContrat(new Moteur()));
	}

}
