package implementation;
import java.util.Random;

import contrats.BlocContrat;

import services.BlocService;
import services.TerrainService;
import utils.BlocReward;
import utils.BlocType;


public class Terrain implements TerrainService {
	private int lignes;
	private int colonnes;
	private BlocService[][] terrain;
	
	public Terrain(){}
	
	public void init(int lignes, int colonnes){
		if(lignes <= 5 || colonnes <= 5){
			throw new IllegalArgumentException();
		}
		this.lignes = lignes;
		this.colonnes = colonnes;
		terrain = new BlocContrat[lignes+1][colonnes+1];
		Random rand = new Random(System.currentTimeMillis());
		for(int i=1; i<=lignes; i++){
			for(int j=1; j<=colonnes; j++){
				if(i==1 || i==lignes || j==1 || j==colonnes){
					terrain[i][j] = new BlocContrat(new Bloc());
					terrain[i][j].init(BlocType.MurMetal, BlocReward.Rien);
				}else{
					if(i%2==1 && j%2==1){
						terrain[i][j] = new BlocContrat(new Bloc());
						terrain[i][j].init(BlocType.MurMetal, BlocReward.Rien);
					}else{
						switch(rand.nextInt(10)){
						case 1:
						case 2:
						case 3: 
						case 4: // 4 chances sur 10 de creer un mur
							switch (rand.nextInt(BlocReward.values().length + 5)) {
							case 0:
							case 1:
								terrain[i][j] = new BlocContrat(new Bloc());
								terrain[i][j].init(BlocType.MurBrique, BlocReward.FireUp);
								break;
							case 2:
							case 3:
								terrain[i][j] = new BlocContrat(new Bloc());
								terrain[i][j].init(BlocType.MurBrique, BlocReward.BombUp);
								break;
							case 4:
								terrain[i][j] = new BlocContrat(new Bloc());
								terrain[i][j].init(BlocType.MurBrique, BlocReward.WallPass);
								break;
							case 5:
								terrain[i][j] = new BlocContrat(new Bloc());
								terrain[i][j].init(BlocType.MurBrique, BlocReward.BombPass);
								break;
							case 6:
								terrain[i][j] = new BlocContrat(new Bloc());
								terrain[i][j].init(BlocType.MurBrique, BlocReward.FireSuit);
								break;
							default:
								terrain[i][j] = new BlocContrat(new Bloc());
								terrain[i][j].init(BlocType.MurBrique, BlocReward.Rien);
								break;
							}
							break;
						default:
							terrain[i][j] = new BlocContrat(new Bloc()); // bloc vide
							terrain[i][j].init(BlocType.Vide, BlocReward.Rien);
							break;
						}
					}
				}
			}
		}
	}
	
	@Override
	public int getNbColonnes() {
		return colonnes;
	}

	@Override
	public int getNbLignes() {
		return lignes;
	}

	@Override
	public BlocService getBloc(int ligne, int colonne) {
		if(ligne < 1 || colonne < 1 || ligne > getNbLignes() || colonne > getNbColonnes()){
			throw new IllegalArgumentException();
		}
		return terrain[ligne][colonne];
	}
}
