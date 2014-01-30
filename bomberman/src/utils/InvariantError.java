package utils;

/**
 * Erreur survenant si un invariant n'est plus vrai.
 * @author Barbier & Deluze.
 */
public class InvariantError extends ContratError {
	private static final long serialVersionUID = 2050146913131637445L;

	public InvariantError(String message) {
		super("Invariant failed: "+message);
	}
}
