/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import pkginterface.BanDo;

/**
 *
 * @author ADMIN-PC
 */
public class BanDo02 implements BanDo{
    int[][] maTran = new int[100][70];
    @Override
    public int[][] getMaTran() {
        return maTran;
    }

    @Override
    public void setMaTran(int x, int y, int giaTri) {
        maTran[x][y] = giaTri;
    }

    @Override
    public void taoMoiBanDo() {
        for(int i = 8; i <= 19; i++){
            for(int j = 7; j <= 20; j++){
                maTran[i][j] = 5;
            }
        }
        
        for(int i = 86; i <= 89; i++){
            for(int j = 58; j <= 67; j++){
                maTran[i][j] = 5;
            }
        }
    }
    
}
