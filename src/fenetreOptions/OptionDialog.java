package fenetreOptions;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;

import main.Game;

public class OptionDialog extends JDialog {
	private JLabel nomLabel, tailleLabel; 
	private JRadioButton tranche1, tranche2, tranche3; //Boutons pour gérer le choix de la classe
	private JTextField tailleField, nomField; //Champs (nom du joeur et taille de la map)
	private Game game;
	
	

	public OptionDialog(Game game, JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.game = game;
		this.setSize(800, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);
	}


	private void initComponent(){

		//Classes: 
		JPanel panPerso = new JPanel();
		panPerso.setBackground(Color.white);
		panPerso.setBorder(BorderFactory.createTitledBorder("Choix de la classe du personnage"));
		panPerso.setPreferredSize(new Dimension(600, 100));
		tranche1 = new JRadioButton("Mage");
		tranche1.setSelected(true);
		tranche2 = new JRadioButton("Guerrier");
		tranche2.setSelected(true);
		tranche3 = new JRadioButton("Archer");
		tranche3.setSelected(true);
		ButtonGroup bg = new ButtonGroup();
		bg.add(tranche1);
		bg.add(tranche2);
		bg.add(tranche3);
		panPerso.add(tranche1);
		panPerso.add(tranche2);
		panPerso.add(tranche3);
    
		//Champs de texte:
		JPanel panTexte = new JPanel();
		panTexte.setBackground(Color.white);
		panTexte.setPreferredSize(new Dimension(400, 125));
		panTexte.setBorder(BorderFactory.createTitledBorder("Taille du plateau"));
		tailleLabel = new JLabel("Taille : ");
		nomLabel = new JLabel("Nom: ");
		tailleField = new JTextField("50");
		tailleField.setPreferredSize(new Dimension(120, 50));
		nomField = new JTextField("Billy Mitchell");
		nomField.setPreferredSize(new Dimension(120, 50));
		panTexte.add(tailleLabel);
		panTexte.add(tailleField);
		panTexte.add(nomLabel);
		panTexte.add(nomField);
	

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panPerso);
		content.add(panTexte);
		
		

		//Validation/annulation:
		JPanel control = new JPanel();
		JButton okBouton = new JButton("Nouvelle Partie");
		JButton loadBouton = new JButton("Charger Partie");
		JButton cancelBouton = new JButton("Annuler");
		
    
             
		okBouton.addActionListener(new OkListener(game,this, bg, 
				tailleField, nomField));
		loadBouton.addActionListener(new LoadListener(game, this));
		cancelBouton.addActionListener(new CancelListener(game, this));

		control.add(okBouton);
		control.add(loadBouton);
		control.add(cancelBouton);
		


		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
    } 

	
}
  
  
  


