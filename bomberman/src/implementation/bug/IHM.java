package implementation.bug;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import services.MoteurService;
import utils.Action;
import utils.BlocType;

public class IHM extends JPanel {
	private static final long serialVersionUID = -741055268433975063L;
	private MoteurService moteur;
	private BufferedImage floor, hardwall, softwall, player1, player2, bomb, 
							explosion, vilain1, vilain2, wallpass, bombpass, 
							firesuit, bombup, fireup, bonus_wall, bonus_bomb,
							bonus_firesuit, p1_alive, p1_dead, p2_alive,
							p2_dead, floor2, player1d, player2d;
	private Action action = Action.Rien;
	
	public Action getAction(){
		Action res = action;
		action = Action.Rien;
		return res;
	}
	private void setAction(Action a){
		action = a;
	}
	
	public IHM(final MoteurService m){
		moteur = m;
		setBackground(new Color(37, 96, 203));
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()){
				case KeyEvent.VK_UP:
					setAction(Action.Haut);
					System.out.println("HAUT");
					break;
				case KeyEvent.VK_DOWN:
					System.out.println("BAS");
					setAction(Action.Bas);
					break;
				case KeyEvent.VK_LEFT:
					System.out.println("GAUCHE");
					setAction(Action.Gauche);
					break;
				case KeyEvent.VK_RIGHT:
					System.out.println("DROITE");
					setAction(Action.Droite);
					break;
				case KeyEvent.VK_SPACE:
					System.out.println("BOMBE");
					setAction(Action.Bombe);
					break;
				default:
					break;
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyTyped(KeyEvent e) {}
		});
		try {
			floor    = ImageIO.read(new File("ressources/floor.png"));
			floor2   = ImageIO.read(new File("ressources/floor_2.png"));
			hardwall = ImageIO.read(new File("ressources/hard_wall.png"));
			softwall = ImageIO.read(new File("ressources/soft_wall.png"));
			player1  = ImageIO.read(new File("ressources/player_1.png"));
			player1d = ImageIO.read(new File("ressources/player_1_dead.png"));
			player2  = ImageIO.read(new File("ressources/player_2.png"));
			player2d = ImageIO.read(new File("ressources/player_2_dead.png"));
			bomb     = ImageIO.read(new File("ressources/bomb_1.png"));
			explosion= ImageIO.read(new File("ressources/explosion_1.png"));
			vilain1  = ImageIO.read(new File("ressources/vilain_bleue.png"));
			vilain2  = ImageIO.read(new File("ressources/vilain_orange.png"));
			wallpass = ImageIO.read(new File("ressources/wallpass_1.png"));
			bombpass = ImageIO.read(new File("ressources/bombpass_1.png"));
			firesuit = ImageIO.read(new File("ressources/firesuit_1.png"));
			bombup   = ImageIO.read(new File("ressources/bombup_1.png"));
			fireup   = ImageIO.read(new File("ressources/fireup_1.png"));
			bonus_wall = ImageIO.read(new File("ressources/bonus_wallpass.png"));
			bonus_bomb = ImageIO.read(new File("ressources/bonus_bombpass.png"));
			bonus_firesuit = ImageIO.read(new File("ressources/bonus_firesuit.png"));
			p1_alive = ImageIO.read(new File("ressources/icon_player_1_alive.png"));
			p1_dead = ImageIO.read(new File("ressources/icon_player_1_dead.png"));
			p2_alive = ImageIO.read(new File("ressources/icon_player_2_alive.png"));
			p2_dead = ImageIO.read(new File("ressources/icon_player_2_dead.png"));
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		g.drawString("Monsieur Bombe", 225, 12);
		g.drawString(String.valueOf(moteur.getStepRestants()), 260, 26);
		if(moteur.getJoueur().isVivant()){
			g.drawImage(p1_alive, 0, 0, null);
		}else{
			setBackground(new Color(219, 24, 24));
			g.drawImage(p1_dead, 0, 0, null);
		}
		if(moteur.getKidnappeur().isVivant()){
			g.drawImage(p2_alive, (moteur.getTerrain().getNbColonnes()+1) * 32, 0, null);
		}else{
			setBackground(new Color(68, 176, 86));
			g.drawImage(p2_dead, (moteur.getTerrain().getNbColonnes()+1) * 32, 0, null);
		}
		if(!moteur.getJoueur().isVivant() && !moteur.getKidnappeur().isVivant()){
			setBackground(new Color(37, 96, 203));
		}
		if(moteur.getJoueur().passeBombe()){
			g.drawImage(bonus_bomb, 32*1 +8, 8, 16, 16, null);
		}
		if(moteur.getKidnappeur().passeBombe()){
			g.drawImage(bonus_bomb, (moteur.getTerrain().getNbColonnes()-0) * 32 +8, 8, 16, 16, null);
		}
		if(moteur.getJoueur().passeMuraille()){
			g.drawImage(bonus_wall, 32*2 +8, 8, 16, 16, null);
		}
		if(moteur.getKidnappeur().passeMuraille()){
			g.drawImage(bonus_wall, (moteur.getTerrain().getNbColonnes()-1) * 32 +8, 8, 16, 16, null);
		}
		if(moteur.getJoueur().getInvulnerableStep() > 0){
			g.drawImage(bonus_firesuit, 32*3 +8, 8, 16, 16, null);
			g.setColor(Color.WHITE);
			g.drawString(String.valueOf(moteur.getJoueur().getInvulnerableStep()), 32*4 -8, 20);
		}
		if(moteur.getKidnappeur().getInvulnerableStep() > 0){
			g.drawImage(bonus_firesuit, (moteur.getTerrain().getNbColonnes()-2) * 32 +8, 8, 16, 16, null);
			g.setColor(Color.WHITE);
			g.drawString(String.valueOf(moteur.getKidnappeur().getInvulnerableStep()), (moteur.getTerrain().getNbColonnes()-3) * 32 +24, 20);
		}
		for(int i=1; i<=moteur.getTerrain().getNbLignes(); i++){
			for(int j=1; j<=moteur.getTerrain().getNbColonnes(); j++){
				g.drawImage(floor, j*32, i*32, null);
				switch (moteur.getTerrain().getBloc(i,j).getType()) {
				case Vide:
					switch (moteur.getTerrain().getBloc(i,j).getReward()) {
					case FireUp:
						g.drawImage(fireup, j*32, i*32, null);
						break;
					case BombUp:
						g.drawImage(bombup, j*32, i*32, null);
						break;
					case BombPass:
						g.drawImage(bombpass, j*32, i*32, null);
						break;
					case FireSuit:
						g.drawImage(firesuit, j*32, i*32, null);
						break;
					case WallPass:
						g.drawImage(wallpass, j*32, i*32, null);
						break;
					default:
						if(moteur.getTerrain().getBloc(i-1,j).getType() == BlocType.MurMetal){
							g.drawImage(floor2, j*32, i*32, null);
						}
						break;
					}
					break;
				case MurMetal:
					g.drawImage(hardwall, j*32, i*32, null);
					break;
				case MurBrique:
					g.drawImage(softwall, j*32, i*32, null);
					break;
				case Explosion:
					g.drawImage(explosion, j*32, i*32, null);
					break;
				default:
					break;
				}
				//g.setColor(Color.WHITE);
				//g.drawString(i+";"+j, j*32, i*32+12);
			}
		}
		for (Integer bombe : moteur.getBombesNum()) {
			g.drawImage(bomb, moteur.getBombe(bombe).getColonne()*32, moteur.getBombe(bombe).getLigne()*32, null);
			g.setColor(Color.WHITE);
			g.drawString(String.valueOf(moteur.getBombe(bombe).getTimer()), moteur.getBombe(bombe).getColonne()*32+12, moteur.getBombe(bombe).getLigne()*32+24);
		}
		if(moteur.getJoueur().isVivant())
			g.drawImage(player1, moteur.getJoueur().getColonne()*32 -1, moteur.getJoueur().getLigne()*32 - 18, null);
		else
			g.drawImage(player1d, moteur.getJoueur().getColonne()*32 -1, moteur.getJoueur().getLigne()*32 - 18, null);
		//g.setColor(Color.WHITE);
		//g.drawString(String.valueOf(joueur.getNum()), joueur.getColonne()*32+12, joueur.getLigne()*32+20);
		if(moteur.getKidnappeur().isVivant())
			g.drawImage(player2, moteur.getKidnappeur().getColonne()*32 -1, moteur.getKidnappeur().getLigne()*32 - 18, null);
		else
			g.drawImage(player2d, moteur.getKidnappeur().getColonne()*32 -1, moteur.getKidnappeur().getLigne()*32 - 18, null);
		//g.setColor(Color.WHITE);
		//g.drawString(String.valueOf(joueur.getNum()), joueur.getColonne()*32+12, joueur.getLigne()*32+20);
		for (Integer vilain : moteur.getVilainsNum()) {
			switch(moteur.getVilain(vilain).getType()){
			case FantomeBleu:
				g.drawImage(vilain1, moteur.getVilain(vilain).getColonne()*32, moteur.getVilain(vilain).getLigne()*32, null);
				break;
			case BallonOrange:
				g.drawImage(vilain2, moteur.getVilain(vilain).getColonne()*32, moteur.getVilain(vilain).getLigne()*32, null);
				break;
			default:
				break;
			}
		}
	}
}
