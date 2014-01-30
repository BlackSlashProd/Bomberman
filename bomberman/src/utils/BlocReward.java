package utils;

/**
 * Enumeration des differents rewards de bloc
 * @author Barbier & Deluze.
 */
public enum BlocReward {
	Rien,		// Aucun reward.
	BombUp,		// Le joueur peut poser une bombe de plus
	FireUp,		// Force du joueur augmentee de 1
	WallPass,	// Le joueur peut passer a travers les MurBrique
	BombPass,	// Le joueur peut passer a travers les bombes
	FireSuit;	// Le joueur est insensible aux explosions pendant 100 tours de jeu
}
