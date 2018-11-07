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
public class BanDo03 implements BanDo{
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
        for(int i = 19; i <= 46; i++){
            for(int j = 36; j <= 44; j++){
                maTran[i][j] = 5;
            }
        }
        
        for(int i = 68; i <= 76; i++){
            for(int j = 18; j <= 44; j++){
                maTran[i][j] = 5;
            }
        }
    }
    
}
