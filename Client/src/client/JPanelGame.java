/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN-PC
 */
public class JPanelGame extends JPanel implements Runnable{

    private Thread thread;
    private int [][] maTran;

    public int[][] getMaTran() {
        return maTran;
    }

    public void setMaTran(int[][] maTran) {
        this.maTran = maTran;
    }
    
    public void resetMaTran( ){
        this.maTran = new int[100][70];
    }
    
    
    public JPanelGame() {
        maTran = new int[100][70];
        thread = new Thread(this);
        thread.start();
    }
    
    public void paint(Graphics g){
        paintCacO(g);
    }
    
    public void paintCacO(Graphics g){
        
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 70; j++){
                if (maTran[i][j] == 0) 
                    g.setColor(Color.white);
                else if (maTran[i][j] == 1)
                    g.setColor(Color.blue);
                else if(maTran[i][j] == 2)  
                    g.setColor(Color.yellow);
                else if(maTran[i][j] == 3)  
                    g.setColor(Color.red);
                else if(maTran[i][j] == 4)  
                    g.setColor(Color.green);
                else if(maTran[i][j] == 5)  
                    g.setColor(Color.gray); 
                else if(maTran[i][j] == 6)  
                    g.setColor(Color.black); 
            
                g.fillRect(i * 8 + 1, j * 8 + 1, 8, 8);
            }
        }
        
    }
    
    public void run(){
        while(true){
            try {
                repaint();
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(JPanelGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
