package fenetreOptions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsListener implements ActionListener {
	
	OptionsDemarrage options;
	
	public OptionsListener(String classe, int taille) { 
		this.options = new OptionsDemarrage(classe, taille);
	}
	
	public void actioPerformed(ActionEvent e){
		OptionsDemarrage options;
	}

	
	
		
	/*
	  	  public void actionPerformed(ActionEvent arg0) {        
	        options = new Optionsdemarrage(getPerso(),getTaille());
	        
	      }
	      public String getPerso(){
	          return (tranche1.isSelected()) ? tranche1.getText() : 
	                 (tranche2.isSelected()) ? tranche2.getText() : 
	                 (tranche3.isSelected()) ? tranche3.getText() :  
	                  tranche1.getText();  
	        }

	        public String getTaille(){
	          return (tailleField.getText().equals("")) ? "50" : tailleField.getText();
	        }  
	      */      
}
