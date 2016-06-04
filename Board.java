package mainfdgs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board {
    private char[] couleurs = {'r','j','i','o','b','v'};
    public int len = 0;
    public char[][] grille;
    private String typeGrille;
    public static int tailleCase;
    
    public Board(String typeGrille, int ptaille) {
        grille = new char[ptaille][ptaille];
        len = ptaille;
        this.typeGrille = typeGrille;
        
        if(typeGrille == "rectangle"){
            this.remplirRectangle();
        }else{
            this.remplirLosange();
        }
    
        tailleCase = (int)(600/ptaille);
    }
    
//============================================================
    private void remplirRectangle(){
        //on remplit le tableau de couleurs aléatoire
        for (int i=0; i< len; i++){
            for (int j=0; j< len; j++)
                grille[i][j] = (couleurs[(int)(Math.random()*6)]);
                
        }
    }
    
    private void remplirLosange(){
        //doit être de taille 25*25
        //on remplit la première moitié
        for(int i=0; i<13; i++){
            for(int j=12-i; j<=(12+i); j++){
                grille[i][j] = (couleurs[(int)(Math.random()*6)]);
            }
        }
        //on remplit la deuxième moitié
        for(int i=13; i<25; i++){
            for(int j=i-12; j< 24-i%13; j++){
                grille[i][j] = (couleurs[(int)(Math.random()*6)]);
            }
        }
    }
    
    public boolean isFinish(char coul){
        int nombreCase = 0;
        int nombreMin = 0;
        char[] liste =  {'r','v','j','b','i','o'};
        
        for(int i=0; i< len; i++){
            for(int j=0; j< len; j++){
                if(grille[i][j] == coul){
                    nombreCase++;
                }
                if(Game.isIn(grille[i][j], liste)){
                    nombreMin++;
                }
                
            }
        }
        
        if(this.typeGrille == "rectangle"){
            if (nombreCase >= (len*len/2) || nombreMin ==0){
                return true;
            }
        }else{
            if (nombreCase >= (13*13) || nombreMin ==0){
                return true;
            }
        }
    return false;
    }
    
}

//===========================================================
class ColorBoard extends JPanel{

    public void paintComponent(Graphics g){    
        Color[] nom_Couleur = {Color.red, Color.blue, Color.orange, Color.green, Color.magenta, Color.yellow};
        char[] char_Couleur = {'r','b','o','v','i','j','R','B','O','V','I','J'};
        int tC = Board.tailleCase;
                
        for (int i=0; i< Game.tailleGrille; i++){
             for(int j=0; j< Game.tailleGrille;j++){
                 
                 for(int k=0; k<12; k++)
                     if (Game.board.grille[i][j] == char_Couleur[k]){
                         g.setColor(nom_Couleur[k%6]);
                         g.fillRect(j*tC, i*tC, tC, tC);
                         
                         if(k>5){
                             for(int m=0; m<4; m++){
                                 if( Game.board.grille[i][j] == Game.couleursPlayer[m]){
                                     g.setColor(Color.black);
                                     g.drawString("J"+(m+1), j*tC+tC/3, i*tC+tC*2/3);
                                 }
                             }
                         }
                     }
                 
             }
             
         }
     }
}
