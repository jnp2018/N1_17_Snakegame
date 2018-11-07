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
public class BanDo04 implements BanDo{
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
        for(int i = 55; i <= 99; i++){
            for(int j = 20; j <= 21; j++){
                maTran[i][j] = 5;
            }
        }
        
        for(int i = 47; i <= 48; i++){
            for(int j = 32; j <= 69; j++){
                maTran[i][j] = 5;
            }
        }
    }
    
}
