package fenetreOptions;

public class TailleInvalideException extends RuntimeException {

	public TailleInvalideException() {
		System.out.println("Erreur: la taille du plateau doit �tre au moins de 20 cases");
	}
}
