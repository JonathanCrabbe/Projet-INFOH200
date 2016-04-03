package fenetreOptions;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FenetreOption extends JFrame {
  
  public FenetreOption(){       {
        OptionDialog de = new OptionDialog(null, "Options de jeu", true);
        OptionsDemarrage Odemar = de.showDemarrage(); 
        JOptionPane jop = new JOptionPane();
        jop.showMessageDialog(null, Odemar.toString(), "Informations joueur", JOptionPane.INFORMATION_MESSAGE);
      }         
    ;      
    this.setVisible(true);      
  }
}
