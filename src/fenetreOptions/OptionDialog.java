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
	private OptionsDemarrage options = new OptionsDemarrage();
	private boolean sendData;
	private JLabel persoLabel, tailleLabel,taille2Label;
	private JRadioButton tranche1, tranche2, tranche3;
	private JTextField tailleField;
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

	public OptionsDemarrage getOptions(){
		this.sendData = false;     
		return this.options;      
	}

	private void initComponent(){

		//Personnage 
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
    
		//La taille
		JPanel panTaille = new JPanel();
		panTaille.setBackground(Color.white);
		panTaille.setPreferredSize(new Dimension(400, 125));
		panTaille.setBorder(BorderFactory.createTitledBorder("Taille du plateau"));
		tailleLabel = new JLabel("Taille : ");
		//taille2Label = new JLabel(" cm");
		tailleField = new JTextField("50");
		tailleField.setPreferredSize(new Dimension(120, 50));
		panTaille.add(tailleLabel);
		panTaille.add(tailleField);
		//panTaille.add(taille2Label);

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panPerso);
		content.add(panTaille);


		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
		JButton cancelBouton = new JButton("Annuler");
    
             
		okBouton.addActionListener(new OkListener(game,this, bg, tailleField));

		control.add(okBouton);
		control.add(cancelBouton);


		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
    } 

	
}
  
  
  


