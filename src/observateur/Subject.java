package observateur;

import observateur.Observer;

public interface Subject {
	public void attach(Observer o);
	public void notifyObserver();

}
