package utils;

/**
 * Enumeration des differents types de bloc
 * @author Barbier & Deluze.
 */
public enum BlocType {
	Vide,		// Case vide.
	MurBrique,	// Mur pouvant etre detruit.
	MurMetal,	// Mur incassable.
	Explosion;	// Deflagration de la bombe. (utilise pour l'IHM)
}