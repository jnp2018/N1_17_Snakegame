/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import pkginterface.*;

/**
 *
 * @author ADMIN-PC
 */
public class QLDSPhongChoi extends UnicastRemoteObject implements GiaoDienQLDSPhongChoi{
    private ArrayList<PhongChoi> arrayPhongChoi;
    private ArrayList<BanDo> arrayBanDo;
    private javax.swing.JTextArea txt_TrangThai;
   

    public JTextArea getTxt_TrangThai() {
        return txt_TrangThai;
    }

    public void setTxt_TrangThai(JTextArea txt_TrangThai) {
        this.txt_TrangThai = txt_TrangThai;
    }
    
    
    public QLDSPhongChoi()throws RemoteException{
        super();
        arrayPhongChoi = new ArrayList<PhongChoi>();
        arrayBanDo = new ArrayList<BanDo>();
        addBanDo();
    }

    public QLDSPhongChoi(ArrayList<PhongChoi> arrayPhongChoi, ArrayList<BanDo> arrayBanDo, JTextArea txt_TrangThai) throws RemoteException{
        this.arrayPhongChoi = arrayPhongChoi;
        this.arrayBanDo = arrayBanDo;
        this.txt_TrangThai = txt_TrangThai;
    }

    
    @Override
    public int taoPhong(int viTriBanDo) throws RemoteException {
        Random rd = new Random();
        int maCode = 0;
        
        while(true){
            maCode = rd.nextInt(9999);
            if(maCode > 1000){
                int dem = 0;
                for(int i = 0; i < arrayPhongChoi.size(); i++){
                    if(maCode == arrayPhongChoi.get(i).getMaCode()){
                        dem ++;
                    }
                }
                if(dem == 0)
                    break;
            }         
        }
        
        ArrayList<Ran> arrayRan = new ArrayList<Ran>();
        arrayRan.add(null);
        arrayRan.add(null);
        arrayRan.add(null);
        arrayRan.add(null);
            
        PhongChoi phongChoi = new PhongChoi(maCode, 400, arrayRan, arrayBanDo.get(viTriBanDo).getMaTran(),viTriBanDo, false, null, 10);
        this.arrayPhongChoi.add(phongChoi);
        
        
        
        txt_TrangThai.setText("Tao phong thanh cong CODE: " + phongChoi.toString() + "\n" + txt_TrangThai.getText());
        return phongChoi.getMaCode();
    }

    private void resetBanDo(PhongChoi pcHienTai){
        pcHienTai.setBanDo(arrayBanDo.get(pcHienTai.getViTriBanDo()).getMaTran());
    }
    
    private int timViTriPhong(int code){
        int viTriPhong = 0, dem = 0;
        for(int i = 0; i < this.arrayPhongChoi.size(); i++){
            if(code == this.arrayPhongChoi.get(i).getMaCode()){
                viTriPhong = i;
                dem ++;
                break;       
            }
        }
        
        if(dem != 0){
            return viTriPhong;
        }
        return -1;
    }
    
    private boolean capNhatViTriRanTrenBanDo(PhongChoi pcHienTai){
        
            this.resetBanDo(pcHienTai);
            if(pcHienTai.getViTriMoi() != null)
                pcHienTai.setOBanDo(pcHienTai.getViTriMoi().getX(), pcHienTai.getViTriMoi().getY(), 6);
            
            for(int i = 0; i < 4; i++){
                if(pcHienTai.getArrayRan().get(i) != null && pcHienTai.getArrayRan().get(i).getArrayDiem() != null){
                    for(int j = 0; j < pcHienTai.getArrayRan().get(i).getArrayDiem().size(); j++){
                        int x = pcHienTai.getArrayRan().get(i).getArrayDiem().get(j).getX();
                        int y = pcHienTai.getArrayRan().get(i).getArrayDiem().get(j).getY();

                        if(pcHienTai.getBanDo()[x][y] == 0 || pcHienTai.getBanDo()[x][y] == 6){
                            pcHienTai.setOBanDo(x, y, i + 1);
                        }
                    }
                }
            }
            return  true;
        
    }
    
    @Override
    public int vaoPhong(int code, String tenNguoiChoi) throws RemoteException {
        int id_ran = 0;
        int stt_ran = 0;
        int viTriPhong  = timViTriPhong(code);
        if(viTriPhong != -1){
            if(arrayPhongChoi.get(viTriPhong).isTrangTraiChoi() == true){
                return -2;
            }
            
            
            int soLuongRanHienTai = 0;
            for(int i = 0; i < 4; i++){
                if(arrayPhongChoi.get(viTriPhong).getArrayRan().get(i) != null){
                    soLuongRanHienTai ++;
                }
            }
            
            if(soLuongRanHienTai == 4){
                txt_TrangThai.setText("Phong " + code + " da du nguoi" + "\n" + txt_TrangThai.getText());
                return -1;
            }
            
           
            int sttRanCanThem = 1;
            for(int i = 0; i < 4; i++){
                if(arrayPhongChoi.get(viTriPhong).getArrayRan().get(i) == null){
                    sttRanCanThem = i + 1;
                    break;
                }
            }
            
           
            int idRanCanThem = 1;
            for(int i = 3; i >= 0; i--){
                if(arrayPhongChoi.get(viTriPhong).getArrayRan().get(i) != null){
                    idRanCanThem = arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).getiDRan() + 1;
                    break;
                }
            }
            
            ArrayList<Diem> arrDiem = new ArrayList<Diem>();
            if(sttRanCanThem == 1){
                        
                arrDiem.clear();
                
                arrDiem.add(new Diem(4, 2));
                arrDiem.add(new Diem(3, 2));
                arrDiem.add(new Diem(2, 2));
            }
            else if(sttRanCanThem == 2){
              
                arrDiem.clear();
                arrDiem.add(new Diem(99, 5));
                arrDiem.add(new Diem(99, 4));
                arrDiem.add(new Diem(99, 3));
                
            }else if(sttRanCanThem == 3){
               
                arrDiem.clear();
                arrDiem.add(new Diem(2, 67));
                arrDiem.add(new Diem(2, 68));
                arrDiem.add(new Diem(2, 69));
               
            }else if(sttRanCanThem == 4){
              
                arrDiem.clear();
                arrDiem.add(new Diem(96, 69));
                arrDiem.add(new Diem(97, 69));
                arrDiem.add(new Diem(98, 69));
            }
            
            Ran ran = new Ran(idRanCanThem, sttRanCanThem, tenNguoiChoi, 3, arrDiem);
            this.arrayPhongChoi.get(viTriPhong).getArrayRan().set(sttRanCanThem - 1, ran);
            txt_TrangThai.setText("Vao phong thanh cong: " + ran.toString() + "\n" + txt_TrangThai.getText());
            return ran.getiDRan();
        }
        return 0;
    }

    @Override
    public boolean thoatPhong(int maCode, int idRan) throws RemoteException {
        int viTriPhong  = timViTriPhong(maCode);
        if(viTriPhong != -1){
            int soLuongRanHienTai = 0;
            for(int i = 0; i < 4; i++){
                if(arrayPhongChoi.get(viTriPhong).getArrayRan().get(i) != null){
                    soLuongRanHienTai ++;
                }
            }
            
            
            if(soLuongRanHienTai == 1){
                txt_TrangThai.setText("Thanh vien cuoi cung da thoat. Phong " + maCode + " da bi xoa" + "\n" + txt_TrangThai.getText());
                this.arrayPhongChoi.remove(viTriPhong);
            }else{
                for(int i = 0; i < 4; i++){
                    if(this.arrayPhongChoi.get(viTriPhong).getArrayRan().get(i) != null){
                        if(idRan == this.arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).getiDRan()){
                            txt_TrangThai.setText("Thanh vien " + this.arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).getTenRan() + " da thoat phong " + maCode  + "\n" + txt_TrangThai.getText());
                            this.arrayPhongChoi.get(viTriPhong).getArrayRan().set(i, null);
                        }
                    }
                }
                this.capNhatViTriRanTrenBanDo(arrayPhongChoi.get(viTriPhong));
            }
            return true;
        }
        return false;
    }
    

    @Override
    public boolean diChuyen(int code, int idRan, int trangThaiDiChuyen) throws RemoteException {
        int viTriPhong = timViTriPhong(code);
        if(viTriPhong != -1){
            for(int i = 0; i < 4; i++){
                if(arrayPhongChoi.get(viTriPhong).getArrayRan().get(i) != null && arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).getArrayDiem() != null){
                    if(arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).getiDRan() == idRan){
                        
                        boolean trangtraiDiChuyenNgang = false;
                        boolean trangtraiDiChuyenDoc = false;

                        ArrayList<Diem> mangDiem = new ArrayList<Diem>();

                        mangDiem = arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).getArrayDiem();
                        
                        if(mangDiem.get(0).getY() == mangDiem.get(1).getY()){
                            trangtraiDiChuyenNgang = true;
                            trangtraiDiChuyenDoc = false;
                        }else{
                            trangtraiDiChuyenDoc = true;
                            trangtraiDiChuyenNgang = false;
                        }

                        ArrayList<Diem> mangDiemBiThayDoi = new ArrayList<Diem>();
                        if(trangThaiDiChuyen == 1 && trangtraiDiChuyenDoc == false){
                            mangDiemBiThayDoi.add(new Diem(mangDiem.get(0).getX(), mangDiem.get(0).getY() - 1));
                            for(int h = 1; h < mangDiem.size(); h++){
                                mangDiemBiThayDoi.add(mangDiem.get(h - 1));
                            }
                        }else if(trangThaiDiChuyen == 2 && trangtraiDiChuyenDoc == false){
                            mangDiemBiThayDoi.add(new Diem(mangDiem.get(0).getX(), mangDiem.get(0).getY() + 1));
                            for(int h = 1; h < mangDiem.size(); h++){
                                mangDiemBiThayDoi.add(mangDiem.get(h - 1));
                            }
                            
                        }else if(trangThaiDiChuyen == 3 && trangtraiDiChuyenNgang == false){
                            mangDiemBiThayDoi.add(new Diem(mangDiem.get(0).getX() - 1, mangDiem.get(0).getY()));
                            for(int h = 1; h < mangDiem.size(); h++){
                                mangDiemBiThayDoi.add(mangDiem.get(h - 1));
                            }

                        }else if(trangThaiDiChuyen == 4 && trangtraiDiChuyenNgang == false){
                            mangDiemBiThayDoi.add(new Diem(mangDiem.get(0).getX() + 1, mangDiem.get(0).getY()));
                           for(int h = 1; h < mangDiem.size(); h++){
                               mangDiemBiThayDoi.add(mangDiem.get(h - 1));
                           }
                        }
                        
                        if(mangDiemBiThayDoi.get(0).getX() < 0 || mangDiemBiThayDoi.get(0).getX() >= 100 ||
                                        mangDiemBiThayDoi.get(0).getY() < 0 || mangDiemBiThayDoi.get(0).getY() >= 70){
                            txt_TrangThai.setText("Nguoi choi " + arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).getTenRan() + " da da thua cuoc. Phong " + arrayPhongChoi.get(viTriPhong).getMaCode()  + "\n" + txt_TrangThai.getText());
                            arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).setTrangThaiDangChoi(false);
                            arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).setArrayDiem(null);
                            capNhatViTriRanTrenBanDo(arrayPhongChoi.get(viTriPhong));
                            kiemTraNguoiChienThang(arrayPhongChoi.get(viTriPhong));
                            
                        }else if(kiemTraRanDamVaoVatCan(arrayPhongChoi.get(viTriPhong), i) == false){
                            txt_TrangThai.setText("Nguoi choi " + arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).getTenRan() + " da da thua cuoc. Phong " + arrayPhongChoi.get(viTriPhong).getMaCode()  + "\n" + txt_TrangThai.getText());
                            arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).setTrangThaiDangChoi(false);
                            arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).setArrayDiem(null);
                            capNhatViTriRanTrenBanDo(arrayPhongChoi.get(viTriPhong));
                            kiemTraNguoiChienThang(arrayPhongChoi.get(viTriPhong));
                        }else if(arrayPhongChoi.get(viTriPhong).getViTriMoi().getX() == mangDiemBiThayDoi.get(0).getX() && arrayPhongChoi.get(viTriPhong).getViTriMoi().getY() == mangDiemBiThayDoi.get(0).getY()){
                            mangDiemBiThayDoi.add(mangDiem.get(mangDiem.size() -1 ));
                            taoMoiAn(arrayPhongChoi.get(viTriPhong));
                            arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).setDoDai(arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).getDoDai() + 1);
                            arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).setArrayDiem(mangDiemBiThayDoi);
                        }else{
                            arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).setArrayDiem(mangDiemBiThayDoi);
                        }
                        break;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
  

    @Override
    public String hienThiDanhSachNguoiChoi(int maCode) throws RemoteException {
        String t = "";
        int viTriPhong = timViTriPhong(maCode);
        if(viTriPhong != -1){
            for(int i = 0; i < 4; i++){
                if(arrayPhongChoi.get(viTriPhong).getArrayRan().get(i) != null){
                    String mausac2 = "";
                    if(i == 0){
                        mausac2 = "(Xanh)";
                    }else if(i == 1){
                        mausac2 = "(Vàng)";
                    }else if(i == 2){
                        mausac2 = "(Đỏ)";
                    }else if(i == 3){
                        mausac2 = "(Lục)";
                    }
                    
                    String trangthaichoi2 = "";
                    if(this.arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).isTrangThaiDangChoi()){
                        trangthaichoi2 = "(Sống)";
                    }else{
                        trangthaichoi2 = "(Chết)";
                    }

                    t +=  mausac2 + " - "  
                      + this.arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).getTenRan() + " - "
                      + this.arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).getDoDai() + " - "
                      + trangthaichoi2+ "\n";
                    }
            } 
        }
        return t;
                
    }

    @Override
    public int[][] hienThiBanDoHienTai(int code) throws RemoteException {
        int viTriPhong = timViTriPhong(code);
        if(viTriPhong != -1){
            if(capNhatViTriRanTrenBanDo(arrayPhongChoi.get(viTriPhong))){
                return this.arrayPhongChoi.get(viTriPhong).getBanDo();
            }
        }
        return null;        
    }
    
    public void addBanDo(){
        int [][] matran = new int[100][70];
        
        BanDo bd = new BanDoCoDien();
        bd.taoMoiBanDo();
        arrayBanDo.add(bd);
 
        BanDo bd1 = new BanDo01();
        bd1.taoMoiBanDo();
        arrayBanDo.add(bd1);
        
        BanDo bd2 = new BanDo02();
        bd2.taoMoiBanDo();
        arrayBanDo.add(bd2);
        
        BanDo bd3 = new BanDo03();
        bd3.taoMoiBanDo();
        arrayBanDo.add(bd3);
        
        BanDo bd4 = new BanDo04();
        bd4.taoMoiBanDo();
        arrayBanDo.add(bd4);
    }
    
    @Override
    public boolean checkIDRanChuPhong(int code, int idRanChuPhong){
        int viTriPhong = timViTriPhong(code);
        
        if(viTriPhong != -1){
            int soLuongRanHienTai = 0;
            int demSoRanVaoSauChuPhong = 0;

            

            for(int i = 0; i < 4; i++){
                if(arrayPhongChoi.get(viTriPhong).getArrayRan().get(i) != null){
                    soLuongRanHienTai ++;
                    if(arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).getiDRan() > idRanChuPhong){
                        demSoRanVaoSauChuPhong++;
                    }
                }
            }

            
            if(demSoRanVaoSauChuPhong == (soLuongRanHienTai - 1)){
                return  true;
            }
        }
        
        return false;
    
    }
 
    @Override
    public boolean batDauChoi(int code, int idRanChuPhong) throws RemoteException {
        int viTriPhong = timViTriPhong(code);
        
        if(viTriPhong != 1){
            if(checkIDRanChuPhong(code, idRanChuPhong)){
                if(arrayPhongChoi.get(viTriPhong).getSoLanChoiLai() > 0){
                    for(int i = 0 ; i < 4; i++){
                        if(arrayPhongChoi.get(viTriPhong).getArrayRan().get(i) != null){
                            arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).setTrangThaiDangChoi(true);
                            int sttRan = arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).getsTTRanTrongPhong();
                            
                            ArrayList<Diem> arrDiem = new ArrayList<Diem>();
                            if(sttRan == 1){
                                         
                                arrDiem.clear();

                                arrDiem.add(new Diem(4, 2));
                                arrDiem.add(new Diem(3, 2));
                                arrDiem.add(new Diem(2, 2));
                            }
                            else if(sttRan == 2){
                              
                                arrDiem.clear();
                                arrDiem.add(new Diem(99, 5));
                                arrDiem.add(new Diem(99, 4));
                                arrDiem.add(new Diem(99, 3));

                            }else if(sttRan == 3){
                    
                                arrDiem.clear();
                                arrDiem.add(new Diem(2, 67));
                                arrDiem.add(new Diem(2, 68));
                                arrDiem.add(new Diem(2, 69));

                            }else if(sttRan == 4){
                           
                                arrDiem.clear();
                                arrDiem.add(new Diem(96, 69));
                                arrDiem.add(new Diem(97, 69));
                                arrDiem.add(new Diem(98, 69));
                            }
                            
                            arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).setArrayDiem(arrDiem);
                        }
                    }
                }
                
                
                txt_TrangThai.setText("Bat dau choi: " + code +"\n" + txt_TrangThai.getText());
                arrayPhongChoi.get(viTriPhong).setTrangTraiChoi(true);
                
                
                for(int i = 0; i < 4; i++){
                    if(arrayPhongChoi.get(viTriPhong).getArrayRan().get(i) != null){
                        arrayPhongChoi.get(viTriPhong).getArrayRan().get(i).setTrangThaiDangChoi(true);
                    }
                }
                
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(arrayPhongChoi.get(viTriPhong).getThoiGianDemNguoc() > 0){
                            try {
                                arrayPhongChoi.get(viTriPhong).setThoiGianDemNguoc(arrayPhongChoi.get(viTriPhong).getThoiGianDemNguoc() - 1);
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(QLDSPhongChoi.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                        if(taoMoiAn(arrayPhongChoi.get(viTriPhong))){
                            dichuyenThang(arrayPhongChoi.get(viTriPhong));
                        }
                    }
                }).start();
            }
        }
        return false;
    }
    
    
    private boolean taoMoiAn(PhongChoi pcHienTai) {
        Random rd = new Random();
        int x, y;
        
        while(true){
            x = rd.nextInt(100);
            y = rd.nextInt(70);
            if(pcHienTai.getBanDo()[x][y] == 0){
                pcHienTai.setSoLuongMoi(pcHienTai.getSoLuongMoi() + 1);
                pcHienTai.setViTriMoi(new Diem(x, y));
                txt_TrangThai.setText("Moi (" + x + " : " + y + ")\n" + txt_TrangThai.getText());
                
                if(pcHienTai.getSoLuongMoi() % 2 == 0 && pcHienTai.getTocDo() > 60){

                    pcHienTai.setTocDo(pcHienTai.getTocDo() - 50);
                }
                capNhatViTriRanTrenBanDo(pcHienTai);
                return true;
            }
        }
    }
    
    private void kiemTraNguoiChienThang(PhongChoi pcHienTai){
        int demNguoiChoiConSong = 0;
        int vitriNguoiChoiCuoiCung = 0;
        
        for(int i = 0; i < 4; i++){
            if(pcHienTai.getArrayRan().get(i) != null){
                if(pcHienTai.getArrayRan().get(i).isTrangThaiDangChoi() == true){
                    demNguoiChoiConSong++;
                    vitriNguoiChoiCuoiCung = i;
                }
            }
        }
        
        if(demNguoiChoiConSong == 1){
            pcHienTai.setTrangTraiChoi(false);
            pcHienTai.setSoLanChoiLai(pcHienTai.getSoLanChoiLai()+1);
            pcHienTai.setThoiGianDemNguoc(10);
        }
        
    }
    
    private void dichuyenThang(PhongChoi pcHienTai){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(pcHienTai.isTrangTraiChoi()){
                        for(int i = 0 ; i < 4; i++){
                            if(pcHienTai.getArrayRan().get(i) != null && pcHienTai.getArrayRan().get(i).getArrayDiem() != null){
                                ArrayList<Diem> mangDiem = new ArrayList<Diem>();
                                mangDiem = pcHienTai.getArrayRan().get(i).getArrayDiem();

                                ArrayList<Diem> mangDiemBiThayDoi = new ArrayList<Diem>();

                                if(mangDiem.get(0).getY() == mangDiem.get(1).getY()){
                                    if(mangDiem.get(0).getX() > mangDiem.get(1).getX()){
                                        
                                        mangDiemBiThayDoi.add(new Diem(mangDiem.get(0).getX() + 1, mangDiem.get(0).getY()));
                                        for(int j = 1; j < mangDiem.size(); j++){
                                            mangDiemBiThayDoi.add(mangDiem.get(j - 1));
                                        }

                                    }else{
                                       
                                        mangDiemBiThayDoi.add(new Diem(mangDiem.get(0).getX() - 1 , mangDiem.get(0).getY()));
                                        for(int j = 1; j < mangDiem.size(); j++){
                                            mangDiemBiThayDoi.add(mangDiem.get(j - 1));
                                        }
                                    }


                                }else{
                                    if(mangDiem.get(0).getY() < mangDiem.get(1).getY()){
                                        
                                         mangDiemBiThayDoi.add(new Diem(mangDiem.get(0).getX(), mangDiem.get(0).getY() - 1));
                                        for(int j = 1; j < mangDiem.size(); j++){
                                            mangDiemBiThayDoi.add(mangDiem.get(j - 1));
                                        }


                                    }else{
                                      
                                        mangDiemBiThayDoi.add(new Diem(mangDiem.get(0).getX(), mangDiem.get(0).getY() + 1));
                                        for(int j = 1; j < mangDiem.size(); j++){
                                            mangDiemBiThayDoi.add(mangDiem.get(j - 1));
                                        }
                                    }
                                }
                                
                                if(mangDiemBiThayDoi.get(0).getX() < 0 || mangDiemBiThayDoi.get(0).getX() >= 100 ||
                                        mangDiemBiThayDoi.get(0).getY() < 0 || mangDiemBiThayDoi.get(0).getY() >= 70){
                                    txt_TrangThai.setText("Nguoi choi " + pcHienTai.getArrayRan().get(i).getTenRan() + " da da thua cuoc. Phong " + pcHienTai.getMaCode()  + "\n" + txt_TrangThai.getText());
                                    pcHienTai.getArrayRan().get(i).setTrangThaiDangChoi(false);
                                    pcHienTai.getArrayRan().get(i).setArrayDiem(null);
                                    capNhatViTriRanTrenBanDo(pcHienTai);
                                    kiemTraNguoiChienThang(pcHienTai);
                                }else if(kiemTraRanDamVaoVatCan(pcHienTai, i) == false){
                                    txt_TrangThai.setText("Nguoi choi " + pcHienTai.getArrayRan().get(i).getTenRan() + " da da thua cuoc. Phong " + pcHienTai.getMaCode()  + "\n" + txt_TrangThai.getText());
                                    pcHienTai.getArrayRan().get(i).setTrangThaiDangChoi(false);
                                    pcHienTai.getArrayRan().get(i).setArrayDiem(null);
                                    capNhatViTriRanTrenBanDo(pcHienTai);
                                    kiemTraNguoiChienThang(pcHienTai);
                                }else if(pcHienTai.getViTriMoi().getX() == mangDiemBiThayDoi.get(0).getX() && pcHienTai.getViTriMoi().getY() == mangDiemBiThayDoi.get(0).getY()){
                                        mangDiemBiThayDoi.add(mangDiem.get(mangDiem.size() -1 ));
                                        taoMoiAn(pcHienTai);
                                        pcHienTai.getArrayRan().get(i).setDoDai(pcHienTai.getArrayRan().get(i).getDoDai() + 1);
                                        pcHienTai.getArrayRan().get(i).setArrayDiem(mangDiemBiThayDoi);
                                } else{
                                    pcHienTai.getArrayRan().get(i).setArrayDiem(mangDiemBiThayDoi);
                                }
                                
                            }
                        }

                        Thread.sleep(pcHienTai.getTocDo());
                    }
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(QLDSPhongChoi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
        
    
    }
    
    private boolean kiemTraRanDamVaoVatCan(PhongChoi pcHienTai, int viTriRan){
        Diem diemDau = pcHienTai.getArrayRan().get(viTriRan).getArrayDiem().get(0);
        
        if(pcHienTai.getBanDo()[diemDau.getX()][diemDau.getY()] == 5)
            return false;
        
        for(int i = 0; i < 4; i++){
            
            if(pcHienTai.getArrayRan().get(i) != null){
                if(i == viTriRan){
                    for(int j = 1; j < pcHienTai.getArrayRan().get(i).getArrayDiem().size(); j++){
                        Diem diemDauRanDangXet = pcHienTai.getArrayRan().get(i).getArrayDiem().get(j);
                        if(diemDau.getX() == diemDauRanDangXet.getX() && diemDau.getY() == diemDauRanDangXet.getY()){
                            return false;
                        }
                    }
                }else if(pcHienTai.getArrayRan().get(i).getArrayDiem() != null){
                    for(int j = 0; j < pcHienTai.getArrayRan().get(i).getArrayDiem().size(); j++){
                        if(j != viTriRan){
                            Diem diemDauRanDangXet = pcHienTai.getArrayRan().get(i).getArrayDiem().get(j);
                            if(diemDau.getX() == diemDauRanDangXet.getX() && diemDau.getY() == diemDauRanDangXet.getY()){
                                return false;
                            }
                        }
                    }
                }
            } 
        }
        
     
        return true;
    }

    @Override
    public int layThoiGianBatDauChoi(int code) throws RemoteException {
        int viTriPhong = timViTriPhong(code);
        if(viTriPhong != 1){
            if(arrayPhongChoi.get(viTriPhong).isTrangTraiChoi() == true){
                return arrayPhongChoi.get(viTriPhong).getThoiGianDemNguoc();
            }
        }
        return -1;
    }

    @Override
    public boolean layTrangThaiDangChoi(int code, int idRanChuPhong) throws RemoteException {
        int viTriPhong = timViTriPhong(code);
        return arrayPhongChoi.get(viTriPhong).isTrangTraiChoi();
        
    }


}
