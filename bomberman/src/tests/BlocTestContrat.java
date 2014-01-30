package tests;

import implementation.Bloc;

import org.junit.Before;

import contrats.BlocContrat;

public class BlocTestContrat extends AbstractBlocTest{

	@Override
	@Before
	public void beforeTests() {
		this.setBloc(new BlocContrat(new Bloc()));
	}

}
