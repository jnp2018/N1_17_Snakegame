/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ADMIN-PC
 */
public interface GiaoDienQLDSPhongChoi extends Remote{
    public int taoPhong(int vitribanDo) throws RemoteException;
    public int vaoPhong(int code, String tenNguoiChoi) throws RemoteException;
    public int layThoiGianBatDauChoi(int code) throws RemoteException;
    public boolean thoatPhong(int maCode, int idRan) throws RemoteException;
    public boolean batDauChoi(int code, int idRanChuPhong) throws RemoteException;
    public boolean layTrangThaiDangChoi(int code, int idRanChuPhong) throws RemoteException;
    public boolean checkIDRanChuPhong(int code, int idRanChuPhong) throws RemoteException;
    public boolean diChuyen(int code, int idRan, int trangThaiDiChuyen) throws RemoteException;
    public String hienThiDanhSachNguoiChoi(int macode) throws RemoteException;
    public int[][] hienThiBanDoHienTai(int code)throws RemoteException;
}
