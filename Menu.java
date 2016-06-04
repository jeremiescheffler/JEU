package mainfdgs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class Menu extends JPanel {
    
    static JPanel container = new JPanel();
    private JLabel titre = new JLabel();
    private JComboBox comboNombrePlayer = new JComboBox();
    private int nombrePlayer = 2;
    private JCheckBox check1 = new JCheckBox("J1");
    private JCheckBox check2 = new JCheckBox("J2");
    private JCheckBox check3 = new JCheckBox("J3");
    private JCheckBox check4 = new JCheckBox("J4");
    private int[] listeIA = new int[4];
    private JRadioButton jr1 = new JRadioButton("Facile");
    private JRadioButton jr2 = new JRadioButton("Difficile");
    private ButtonGroup buttonGroup1 = new ButtonGroup();
    private int difficulty = 1;
    private JRadioButton jr3 = new JRadioButton("Carré");
    private JRadioButton jr4 = new JRadioButton("Losange");
    private char forme = 'R';
    private ButtonGroup buttonGroup2 = new ButtonGroup();
    private JComboBox comboTailleGrille = new JComboBox();
    private int tailleGrille = 13;
    private static Box b6;
    
    public Menu(){
        container.setLayout(new BorderLayout());
        //On initialise le conteneur avec tous les composants
        this.initComposant();
        this.add(container);
        
    }
    
    private void initComposant(){
        //TITRE
        Font police = new Font("Arial", Font.BOLD, 20);
        titre = new JLabel("<html><U>"+"Jeu des 6 couleurs"+"</U></html>");
        titre.setFont(police);
        titre.setHorizontalAlignment(JLabel.CENTER);
        
        // NOMBRE DE JOUEURS
        JLabel player = new JLabel("nombre de joueurs :");
        String[] tab = {"1","2","3","4"};
        comboNombrePlayer = new JComboBox(tab);
        comboNombrePlayer.setSelectedIndex(1);
        comboNombrePlayer.setPreferredSize(new Dimension(50,20));
        comboNombrePlayer.addActionListener(new ComboState());
        
        // CHOIX IA
        JLabel ia = new JLabel("choix des IA :");
        check1.addActionListener(new CheckListener());
        check2.addActionListener(new CheckListener());
        check3.addActionListener(new CheckListener());
        check4.addActionListener(new CheckListener());
        
        // CHOIX DIFFICULTE IA
        JLabel difficulte = new JLabel("Difficulté des IA :");
        jr1.setSelected(true);
        jr1.addActionListener(new StateListener1());
        jr2.addActionListener(new StateListener1());
        buttonGroup1.add(jr1);
        buttonGroup1.add(jr2);
        
        // CHOIX FORME GRILLE
        JLabel labForme  = new JLabel("Forme de la grille :");
        jr3.setSelected(true);
        jr3.addActionListener(new StateListener2());
        jr4.addActionListener(new StateListener2());
        buttonGroup2.add(jr3);
        buttonGroup2.add(jr4);
        
        String[] tab2 = new String[36];
        for(int i=5; i<41; i++){
            tab2[i-5] = Integer.toString(i);
        }
        
        //CHOIX TAILLE GRILLE
        JLabel labTaille = new JLabel("Taille grille :");
        comboTailleGrille = new JComboBox(tab2);
        comboTailleGrille.setSelectedIndex(8);
        comboTailleGrille.addActionListener(new ComboState2());
        
        // BOUTON PLAY
        JButton play = new JButton("Play");
        play.setPreferredSize(new Dimension(100,50));
        play.addActionListener(new PlayListener());
        
        // LAYOUT
        Box b1 = Box.createHorizontalBox();
        b1.add(titre);
        
        Box b2 = Box.createHorizontalBox();
        b2.add(Box.createHorizontalStrut(200));
        b2.add(player);
        b2.add(Box.createHorizontalStrut(30));
        b2.add(comboNombrePlayer);
        b2.add(Box.createHorizontalStrut(200));
        
        Box b3 = Box.createHorizontalBox();
        b3.add(ia);
        b3.add(Box.createHorizontalStrut(30));
        b3.add(check1);
        b3.add(check2);
        b3.add(check3);
        b3.add(check4);
        
        Box b4 = Box.createHorizontalBox();
        b4.add(difficulte);
        b4.add(Box.createHorizontalStrut(30));
        b4.add(jr1);
        b4.add(jr2);
        
        Box b5 = Box.createHorizontalBox();
        b5.add(labForme);
        b5.add(Box.createHorizontalStrut(30));
        b5.add(jr3);
        b5.add(jr4);
        
        b6 = Box.createHorizontalBox();
        b6.add(Box.createHorizontalStrut(200));
        b6.add(labTaille);
        b6.add(Box.createHorizontalStrut(30));
        b6.add(comboTailleGrille);
        b6.add(Box.createHorizontalStrut(250));
        
        Box b8 = Box.createVerticalBox();
        b8.add(b1);
        b8.add(Box.createVerticalStrut(30));
        b8.add(b2);
        b8.add(Box.createVerticalStrut(20));
        b8.add(b3);
        b8.add(Box.createVerticalStrut(20));
        b8.add(b4);
        b8.add(Box.createVerticalStrut(20));
        b8.add(b5);
        b8.add(Box.createVerticalStrut(20));
        b8.add(b6);
        container.add(b8, BorderLayout.NORTH);
        container.add(Box.createVerticalStrut(60));
        container.add(play, BorderLayout.SOUTH);
    }
    
    class ComboState implements ActionListener{
        public void actionPerformed(ActionEvent e) {
          nombrePlayer = Integer.parseInt(comboNombrePlayer.getSelectedItem().toString());
        }               
      }
    
    class ComboState2 implements ActionListener{
        public void actionPerformed(ActionEvent e) {
          tailleGrille = comboTailleGrille.getSelectedIndex()+5;
        }               
      }
    
    class PlayListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Main.demarrerJeu(nombrePlayer, listeIA, difficulty, forme, tailleGrille);
        }               
      }
    
    class CheckListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String ia = ((JCheckBox) e.getSource()).getText();
            Boolean etat = ((JCheckBox)e.getSource()).isSelected();
            switch(ia){
                case "J1":
                    if(etat)
                        listeIA[0] = 1;
                    else
                        listeIA[0] = 0;
                    break;
                    
                case "J2":
                    if(etat)
                        listeIA[1] = 2;
                    else
                        listeIA[1] = 0;
                    break;
                    
                case "J3":
                    if(etat)
                        listeIA[2] = 3;
                    else
                        listeIA[2] = 0;
                    break;
                    
                case "J4":
                    if(etat)
                        listeIA[3] = 4;
                    else
                        listeIA[3] = 0;
                    break;
                    
            }
          }
        }
    
    class StateListener1 implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(((JRadioButton)e.getSource()).getText() == "Difficile")
                difficulty = 2;
            else
                difficulty = 1;
            
        }
      }
    
    class StateListener2 implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(((JRadioButton)e.getSource()).getText() == "Carré"){
                forme = 'R';
                b6.setVisible(true);
                tailleGrille = comboTailleGrille.getSelectedIndex()+5;
            }
            else{
                forme = 'L';
                b6.setVisible(false);
                tailleGrille = 25;
            }
        }
      }
}
