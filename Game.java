package mainfdgs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JPanel;

public class Game{
    
    //3 variables modifiable (jusqu'a 4 joueurs)
    public static int tailleGrille;
    public static char typeGrille;
    public static int nombrePlayer;
    
    public static int[] listeIA;
    public static int difficulty;
    
    public static int courantPlayer = 1;
    public static char[] couleursPlayer = new char[4];
    public static boolean partieTerminee = false;
    
    public static Board board;
    public static Joueurs J1;
    public static Joueurs J2;
    public static Joueurs J3;
    public static Joueurs J4;
    
    public Game(int nbrPlayer, int[] listeIA, int difficulty, char forme, int taille) {
        //on initialise le jeu
        Game.nombrePlayer = nbrPlayer;
        Game.listeIA = listeIA;
        Game.difficulty = difficulty;
        Game.typeGrille = forme;
        Game.tailleGrille = taille;
        
        if(forme == 'R'){
            Game.board = new Board("rectangle", tailleGrille);        
        }else{
            tailleGrille = 25;
            Game.board = new Board("losange", tailleGrille);
        }    
        //on modifie les paramètres des joueurs
        ajoutPlayer();
    }
    
    public static void BoucleJeu(char coulSelect){
        if(partieTerminee){
            quiEstGagnant();
            return;
        }
    
        switch (courantPlayer){
             case 1:
                 parcourirGrille(J1.couleur, coulSelect);
                 //nouvelle couleur du joueur
                 J1.couleur = coulSelect;
                 couleursPlayer[0] = coulSelect;
                 
                 //on verifie si le joueur 1 a gagné
                 if(board.isFinish(couleursPlayer[0]))
                     partieTerminee = true;
                 break;
             case 2:
                 parcourirGrille(J2.couleur, coulSelect);
                 J2.couleur = coulSelect;
                 couleursPlayer[1] = coulSelect;
                 
                 if(board.isFinish(couleursPlayer[1]))
                     partieTerminee = true;
                 break;
             case 3:
                 parcourirGrille(J3.couleur, coulSelect);
                 J3.couleur = coulSelect;
                 couleursPlayer[2] = coulSelect;
                 
                 if(board.isFinish(couleursPlayer[2]))
                     partieTerminee = true;
                 break;
             case 4:
                 parcourirGrille(J4.couleur, coulSelect);
                 J4.couleur = coulSelect;
                 couleursPlayer[3] = coulSelect;
                 
                 if(board.isFinish(couleursPlayer[3]))
                     partieTerminee = true;
                 break;
        }
        
        //on incrémente la variable courantPlayer
        joueursSuivant();
    
        if(Game.isIn(Game.courantPlayer, Game.listeIA)){
            char coulIA;
            
            do{
                if(Game.difficulty == 1){
                    System.out.println("IA "+Game.courantPlayer+" joue");
                    char[] all = {'R','B','J','V','I','O'};
                    coulIA = all[(int) (Math.random()*6)]; 
                }
                else{
                    coulIA = IA.bestMove(Game.couleursPlayer[Game.courantPlayer-1]);
                }
            }while(!Game.choixCouleur(coulIA, Game.courantPlayer) );
            
            Game.BoucleJeu(coulIA);
        }

        Main.fen.repaint();
    }

//=============================================================
     public static void joueursSuivant(){
            // on incremente le courantPlayer jusqu'au nombre max de joueur
            if ((courantPlayer%Joueurs.getNombrePlayer())==0)
                courantPlayer = 1;
            else
                courantPlayer++;
            
            Main.label.setText("Joueur "+Game.courantPlayer+" a votre tour:");
        }
     
    public static void quiEstGagnant(){
        boolean egalite = false;
        int[] points = {0,0,0,0};
        String text = "";
        
        //on compte le nombre de points de chaque joueur
        for (int i=0; i< tailleGrille; i++){
            for (int j=0; j< tailleGrille; j++){
                for (int k=0; k < nombrePlayer; k++){
                    if(board.grille[i][j] == couleursPlayer[k])
                        points[k]++;
                }
            }
        }
        //on cherche le maximum du tableau ce qui nous donne
        //le numero du gagnant
        int max = -1;
        int indice_max = -1;
        for (int m=0; m<4; m++){
            if (points[m] > max){
                max = points[m];
                indice_max = m;
            }
        }
        //on verifie si il y a egalite
        for (int k=0; k<4; k++){
            if(points[k] == max && k!=indice_max){
                egalite = true;
                text += "<html> Il y a egalite entre le joueur "+(indice_max+1)+" et "+(k+1)+" avec "+(max)+" points <br>";
                points[k] = -1;
            }
        }
        points[indice_max] = -1;
        
        if(egalite == false)
            text += "<html> Le joueur "+(indice_max+1)+" gagne avec "+max+" points ! <br>";
        
        //on affiche le score des autres joueurs
        for(int i=0; i<nombrePlayer; i++){
            if(points[i] != -1){
                text += "Le joueur "+(i+1)+" a "+(points[i])+" points <br>";
            }
        }
        
        text += "</br></html>";
        System.out.println(text);
        
        Main.fenetreFin(text);
        
    }
    
    public static void ajoutPlayer(){
        //on ajoute des joueurs et on definit leurs paramètres 
        
        char coul; 
        char[] all = {'R','V','B','I','J','O'};
                
        if (nombrePlayer >=1){
            do{
                coul = all[(int)(Math.random()*6)];
            }while(!choixCouleur(coul, 1));
            couleursPlayer[0] = coul;
            J1 = new Joueurs(1, coul , board);
        }    
        if (nombrePlayer >=2){
            do{
                coul = all[(int)(Math.random()*6)];
            }while(!choixCouleur(coul, 2));
            couleursPlayer[1] = coul;
            J2 = new Joueurs(2, coul , board);;
        }
        if (nombrePlayer >=3){
            do{
                coul = all[(int)(Math.random()*6)];
            }while(!choixCouleur(coul, 3));
            couleursPlayer[2] = coul;
            J3 = new Joueurs(3, coul , board);
        }
        if (nombrePlayer >=4){
            do{
                coul = all[(int)(Math.random()*6)];
            }while(!choixCouleur(coul, 4));
            couleursPlayer[3] = coul;
            J4 = new Joueurs(4, coul , board);
        }
    }
    
    public static boolean choixCouleur(char coul, int player){
        /** renvoie true si la couleur choisit existe et 
         * n'est pas utilisée par les adversaires
         */
        char[] all = {'R','V','B','I','J','O'};

        if(isIn(coul, all) && !isIn(coul, couleursPlayer)){
            return true;
        }
        return false;
    }
    
    public static boolean isIn(char element, char[] liste){
        //fonction qui renvoie true si l'element est dans la liste
        for(int i=0; i<liste.length; i++){
            if (liste[i]==element)
                return true;
        }
        return false;
    }
    
    public static boolean isIn(int element, int[] liste){
        //fonction qui renvoie true si l'element est dans la liste
        for(int i=0; i<liste.length; i++){
            if (liste[i]==element)
                return true;
        }
        return false;
    }
    
    public static void parcourirGrille(char coulPerso, char coulSelect){
        //deux boucles pour parcourir tout le tableau
        for (int i=0;i < board.len;i++){
             for (int j=0; j< board.len; j++){
                 
                 //si la couleur est celle du joueur
                 if(board.grille[i][j] == coulPerso){
                     //on remplace son ancienne couleur par la nouvelle
                     board.grille[i][j] = coulSelect;
                     
                     //on ajoute les cases autour de cette nouvelle case
                     for(int i1=-1; i1<2; i1++){
                         for(int i2=-1; i2<2; i2++){
                             try{
                                 //si la couleur minuscule correspond a celle en majuscule
                                 if (board.grille[i+i1][j+i2] == (char)((int)(coulSelect)+32))
                                     board.grille[i+i1][j+i2] = coulSelect;
                             }catch(ArrayIndexOutOfBoundsException e){}
                             //la boucle try catch permet de boucler meme sur les bords
                         }
                     }
                 }
             }
         }
    }
}


