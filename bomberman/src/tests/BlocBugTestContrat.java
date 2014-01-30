package tests;

import implementation.Bloc;

import org.junit.Before;

import contrats.BlocContrat;

public class BlocBugTestContrat extends AbstractBlocTest{

	@Override
	@Before
	public void beforeTests() {
		this.setBloc(new BlocContrat(new Bloc()));
	}

}
