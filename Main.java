package mainfdgs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Main {
    public static Fenetre fen;
    public static JPanel col;
    public static JPanel container = new JPanel();
    public static Game g;
    public static JLabel label = new JLabel("Joueur 1 a votre tour:");
    
    public static void main(String[] args) {
        fen = new Fenetre();
        Menu menu = new Menu();
        fen.setContentPane(menu);
        fen.validate();
    }
    
    public static void demarrerJeu(int nombrePlayer, int[] listeIA, int difficulty, char forme, int tailleGrille) {
        g = new Game(nombrePlayer, listeIA, difficulty, forme, tailleGrille);
        
        col = new ColorBoard();
        col.setPreferredSize(new Dimension(600,600));
        
        JPanel bouttons = new JPanel();
        bouttons.setLayout(new GridLayout(8,1));
        bouttons.setPreferredSize(new Dimension(150,300));
        
        JButton rouge = new JButton("rouge");
        rouge.addActionListener(new ButtonListener());
        JButton vert = new JButton("vert");
        vert.addActionListener(new ButtonListener());
        JButton bleu = new JButton("bleu");
        bleu.addActionListener(new ButtonListener());
        JButton jaune = new JButton("jaune");
        jaune.addActionListener(new ButtonListener());
        JButton violet = new JButton("violet");
        violet.addActionListener(new ButtonListener());
        JButton orange = new JButton("orange");
        orange.addActionListener(new ButtonListener());
        
        bouttons.add(label);
        bouttons.add(rouge);
        bouttons.add(vert);
        bouttons.add(bleu);
        bouttons.add(jaune);
        bouttons.add(violet);
        bouttons.add(orange);
        
        JButton buttonRecommencer = new JButton("recommencer");
        buttonRecommencer.addActionListener(new RecommencerListener());
        bouttons.add(buttonRecommencer);
        
        container.add(col, BorderLayout.WEST);
        container.add(bouttons, BorderLayout.EAST);
        
        fen.setSize(800,600+40);
        fen.setLocationRelativeTo(null);
        fen.setContentPane(container);
        fen.validate();
        fen.repaint();
    }
    
    public static void fenetreFin(String text) {
        JPanel container = new JPanel();
        JLabel labText = new JLabel(text);
        Font police = new Font("Arial", Font.BOLD, 25);
        labText.setFont(police);
        labText.setPreferredSize(new Dimension(500,500));
        container.add(labText, BorderLayout.CENTER);
        
        JButton buttonRecommencer = new JButton("recommencer");
        buttonRecommencer.addActionListener(new RecommencerListener());
        container.add(buttonRecommencer);
        
        fen.setContentPane(container);
        fen.validate();
    }
}

class ButtonListener implements ActionListener{
    
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "rouge":
                if(Game.choixCouleur('R', Game.courantPlayer))
                    Game.BoucleJeu('R');
                else
                    Main.label.setText("choix invalide");
                break;
            case "vert":
                if(Game.choixCouleur('V', Game.courantPlayer))
                    Game.BoucleJeu('V');
                else
                    Main.label.setText("choix invalide");
                break;
            case "bleu":
                if(Game.choixCouleur('B', Game.courantPlayer))
                    Game.BoucleJeu('B');
                else
                    Main.label.setText("choix invalide");
                break;
            case "jaune":
                if(Game.choixCouleur('J', Game.courantPlayer))
                    Game.BoucleJeu('J');
                else
                    Main.label.setText("choix invalide");
                break;
            case "violet":
                if(Game.choixCouleur('I', Game.courantPlayer))
                    Game.BoucleJeu('I');
                else
                    Main.label.setText("choix invalide");
                break;
            case "orange":
                if(Game.choixCouleur('O', Game.courantPlayer))
                    Game.BoucleJeu('O');
                else
                    Main.label.setText("choix invalide");
                break;
        }
    }    
}

class RecommencerListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        Menu menu = new Menu();
        Main.fen.setSize(500,400);
        Main.fen.setContentPane(menu);
        Main.fen.setLocationRelativeTo(null);
        Main.fen.validate();
    }    
}
