package utils;

/**
 * Erreur survenant si une precondition d'un contrat n'est pas respectée.
 * @author Barbier & Deluze.
 */
public class PreConditionError extends ContratError {
	private static final long serialVersionUID = 9050050491786738283L;

	public PreConditionError(String message) {
		super("Pre Condition failed: "+message);
	}
}
