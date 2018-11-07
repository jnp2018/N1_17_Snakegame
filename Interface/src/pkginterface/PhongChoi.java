/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author ADMIN-PC
 */
public class PhongChoi implements Serializable{
    private int maCode;
    private int tocDo;
    private int soLuongMoi;
    private int soLanChoiLai;
    private Diem viTriMoi;
    private ArrayList<Ran> arrayRan;
    private int[][] banDo;
    private int viTriBanDo;
    private boolean trangTraiChoi;
    private int thoiGianDemNguoc;

    public int getSoLanChoiLai() {
        return soLanChoiLai;
    }

    public void setSoLanChoiLai(int soLanChoiLai) {
        this.soLanChoiLai = soLanChoiLai;
    }

    
    
    public int getSoLuongMoi() {
        return soLuongMoi;
    }

    public void setSoLuongMoi(int soLuongMoi) {
        this.soLuongMoi = soLuongMoi;
    }

    public Diem getViTriMoi() {
        return viTriMoi;
    }

    public void setViTriMoi(Diem viTriMoi) {
        this.viTriMoi = viTriMoi;
    }


    
    
    

    public PhongChoi()  {
        arrayRan = new ArrayList<Ran>();
        this.soLanChoiLai = 0;
        banDo = new int[100][70];
    }

    public PhongChoi(int maCode, int tocDo, ArrayList<Ran> arrayRan, int[][] banDo2, int viTriBanDo, boolean trangTraiChoi, Diem viTriMoi, int thoiGianDemNguoc) {
        this.banDo = new int[100][70];
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 70; j++){
                this.banDo[i][j] = banDo2[i][j];
            }
        }
        this.soLanChoiLai = 0;
        this.viTriMoi = viTriMoi;
        this.maCode = maCode;
        this.tocDo = tocDo; 
        this.arrayRan = arrayRan;
        this.viTriBanDo = viTriBanDo;
        this.trangTraiChoi = trangTraiChoi;
        this.thoiGianDemNguoc = thoiGianDemNguoc;
        this.soLuongMoi = 0;
    }

    
    public int getViTriBanDo() {
        return viTriBanDo;
    }

    public void setViTriBanDo(int viTriBanDo) {
        this.viTriBanDo = viTriBanDo;
    }
    
    public int getThoiGianDemNguoc() {
        return thoiGianDemNguoc;
    }

    public void setThoiGianDemNguoc(int thoiGianDemNguoc) {
        this.thoiGianDemNguoc = thoiGianDemNguoc;
    }

    
    public boolean isTrangTraiChoi() {
        return trangTraiChoi;
    }

    public void setTrangTraiChoi(boolean trangTraiChoi) {
        this.trangTraiChoi = trangTraiChoi;
    }
    

    public int getMaCode() {
        return maCode;
    }

    public void setMaCode(int maCode) {
        this.maCode = maCode;
    }

    public int getTocDo() {
        return tocDo;
    }

    public void setTocDo(int tocDo) {
        this.tocDo = tocDo;
    }

    public ArrayList<Ran> getArrayRan() {
        return arrayRan;
    }

    public void setArrayRan(ArrayList<Ran> arrayRan) {
        this.arrayRan = arrayRan;
    }

    public int[][] getBanDo() {
        return banDo;
    }

    public void setBanDo(int[][] banDo) {
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 70; j++){
                this.banDo[i][j] = banDo[i][j];
            }
        }
    }
    
    public void setOBanDo(int x, int y, int giatri) {
        this.banDo[x][y] = giatri;
    }

    @Override
    public String toString() {
        return "PhongChoi{" + "\n\tmaCode=" + maCode + ", \n\ttocDo=" + tocDo + ",\n\t arrayRan=" + arrayRan + ", \n\tbanDo=" + banDo + "\n\ttrangThaiChoi = "+ this.trangTraiChoi+'}';
    }
    
    
}
