package mainfdgs;

import javax.swing.JFrame;


public class Fenetre extends JFrame {        
        public Fenetre(){
            this.setSize(500,400);
            this.setTitle("Jeu des 6 couleurs");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setVisible(true);
        }
}
