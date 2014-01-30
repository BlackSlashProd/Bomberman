package main;

import java.util.Random;

import implementation.bug.Moteur;
import contrats.MoteurContrat;
import services.MoteurService;
import utils.Action;

public class MainNoIHMBug {

	public static void main(String[] args) {
		MoteurService m = new MoteurContrat(new Moteur());
		m.init(500);
		Random rand = new Random(System.currentTimeMillis());
		while(!m.isGameOver()){
			Action a = Action.values()[rand.nextInt(Action.values().length)];
			m.step(a);
		}
		System.out.println("La partie est finie ! Gagnant : "+m.getGagnant());
	}
}
