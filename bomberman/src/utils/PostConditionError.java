package utils;

/**
 * Erreur survenant si une postcondition d'un contrat n'est pas respectée.
 * @author Barbier & Deluze.
 */
public class PostConditionError extends ContratError {
	private static final long serialVersionUID = 9050150491786738283L;

	public PostConditionError(String message) {
		super("Post Condition failed: "+message);
	}
}
