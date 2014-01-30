package utils;

/**
 * SuperType pour toutes les erreurs au sein des contrats.
 * @author Barbier & Deluze.
 */
public class ContratError extends Error {
	private static final long serialVersionUID = 6743308478514264663L;

	public ContratError(String message){
		super(message);
	}

}
