package main;

import implementation.IHM;
import implementation.Moteur;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import contrats.MoteurContrat;
import services.MoteurService;

public class Main {

	public static void main(String[] args) {
		MoteurService m = new MoteurContrat(new Moteur());
		m.init(500);
		IHM ihm = new IHM(m);
		JFrame fenetre = new JFrame("Monsieur Bombe");
		fenetre.add(ihm);
		fenetre.setIconImage(new ImageIcon("ressources/icon_player_1_alive.png").getImage());
		fenetre.setSize((m.getTerrain().getNbColonnes() + 2)* 32 + 5, (m.getTerrain().getNbLignes() +2) * 32 + 27);
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
		ihm.repaint();
		try { Thread.sleep(1500); } catch (InterruptedException e) {}
		while(!m.isGameOver()){
			ihm.repaint();
			m.step(ihm.getAction());
			ihm.repaint();
		}
		System.out.println("La partie est finie ! Gagnant : "+m.getGagnant());
	}
}
