/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Border;
import javax.swing.*;
import pkginterface.*;

/**
 *
 * @author ADMIN-PC
 */
public class JFrameClient extends JFrame implements ActionListener{

    public JButton btn_BatDau;
    private JButton btn_TaoPhong;
    private JButton btn_ThoatPhong;
    private JButton btn_TimPhong;
    private JComboBox<String> cb_Map;
    private JLabel jLabel1;
    private JScrollPane jSP_DanhSachNguoiChoi;
    private JLabel lb_Code;
    private JLabel lb_DanhSachNguoiChoi;
    private JLabel lb_TrangThai;
    private JPanel pn_DieuKhien;
    private JPanelGame pn_HienThi;
    private JTextField txt_Code;
    private JTextPane txt_DanhSachNguoiChoi;
    private JTextField txt_IPMayChu;
    private JTextField txt_TenNguoiChoi;
    private Registry registry;
    private GiaoDienQLDSPhongChoi cnqldspc;
    private int maCode = 0;
    private int idRan = 0;
    
    static private boolean trangThaiHienThi = false;




    
    public JFrameClient() {
        initComponents();
        
        lb_DanhSachNguoiChoi.setVisible(false);
        jSP_DanhSachNguoiChoi.setVisible(false);
        btn_BatDau.setVisible(false);
        btn_ThoatPhong.setVisible(false);
        lb_Code.setVisible(false);
        txt_DanhSachNguoiChoi.setEnabled(true);
        
        pn_HienThi.addKeyListener(new KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    batSuKienDiChuyen(evt);
                }
        });
    }
    
   private void initComponents() {

        pn_DieuKhien = new JPanel();
        jLabel1 = new  JLabel();
        btn_TaoPhong = new  JButton();
        btn_TimPhong = new  JButton();
        lb_DanhSachNguoiChoi = new  JLabel();
        jSP_DanhSachNguoiChoi = new  JScrollPane();
        txt_DanhSachNguoiChoi = new  JTextPane();
        btn_BatDau = new  JButton();
        btn_ThoatPhong = new  JButton();
        lb_Code = new  JLabel();
        txt_TenNguoiChoi = new  JTextField();
        txt_Code = new  JTextField();
        cb_Map = new  JComboBox<>();
        txt_IPMayChu = new  JTextField();
        lb_TrangThai = new  JLabel();
        pn_HienThi = new JPanelGame();

        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);

        pn_DieuKhien.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); 
        jLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jLabel1.setHorizontalAlignment( SwingConstants.CENTER);
        jLabel1.setText("RẮN SĂN MỒI");

        btn_TaoPhong.setText("Tạo Phòng");

        btn_TimPhong.setText("Tìm");

        lb_DanhSachNguoiChoi.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        lb_DanhSachNguoiChoi.setText("DANH SÁCH NGƯỜI CHƠI");

        txt_DanhSachNguoiChoi.setEnabled(false);
        jSP_DanhSachNguoiChoi.setViewportView(txt_DanhSachNguoiChoi);

        btn_BatDau.setText("Bắt Đầu");

        btn_ThoatPhong.setText("Thoát Phòng");
        lb_Code.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        lb_Code.setForeground(new java.awt.Color(0, 51, 255));
        lb_Code.setHorizontalAlignment( SwingConstants.CENTER);
        lb_Code.setText("Mã CODE:");

        txt_TenNguoiChoi.setText("Tên người chơi");

        txt_Code.setText("code");

        cb_Map.setModel(new  DefaultComboBoxModel<>(new String[] { "Bản đồ cổ điển", "Bản đồ 1", "Bản đồ 2", "Bản đồ 3", "Bản đồ 4" }));

        txt_IPMayChu.setText("127.0.0.1");

        lb_TrangThai.setFont(new java.awt.Font("Tahoma", 1, 15)); 
        lb_TrangThai.setForeground(new java.awt.Color(0, 0, 204));
        lb_TrangThai.setText("...");
        

        GroupLayout pn_DieuKhienLayout = new  GroupLayout(pn_DieuKhien);
        pn_DieuKhien.setLayout(pn_DieuKhienLayout);
        pn_DieuKhienLayout.setHorizontalGroup(
            pn_DieuKhienLayout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup(pn_DieuKhienLayout.createSequentialGroup()
                .addGroup(pn_DieuKhienLayout.createParallelGroup( GroupLayout.Alignment.LEADING)
                    .addGroup(pn_DieuKhienLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lb_DanhSachNguoiChoi)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pn_DieuKhienLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1,  GroupLayout.DEFAULT_SIZE,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup( GroupLayout.Alignment.TRAILING, pn_DieuKhienLayout.createSequentialGroup()
                        .addContainerGap( GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pn_DieuKhienLayout.createParallelGroup( GroupLayout.Alignment.TRAILING, false)
                            .addGroup( GroupLayout.Alignment.LEADING, pn_DieuKhienLayout.createSequentialGroup()
                                .addComponent(txt_IPMayChu,  GroupLayout.PREFERRED_SIZE, 89,  GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap( LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_TenNguoiChoi))
                            .addGroup(pn_DieuKhienLayout.createSequentialGroup()
                                .addComponent(btn_BatDau,  GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_ThoatPhong,  GroupLayout.PREFERRED_SIZE, 108,  GroupLayout.PREFERRED_SIZE))
                            .addGroup(pn_DieuKhienLayout.createSequentialGroup()
                                .addGroup(pn_DieuKhienLayout.createParallelGroup( GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_Code)
                                    .addComponent(cb_Map, 0, 160, Short.MAX_VALUE))
                                .addPreferredGap( LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pn_DieuKhienLayout.createParallelGroup( GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_TaoPhong,  GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                    .addComponent(btn_TimPhong,  GroupLayout.DEFAULT_SIZE,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jSP_DanhSachNguoiChoi,  GroupLayout.Alignment.LEADING))
                        .addGap(8, 8, 8))
                    .addGroup(pn_DieuKhienLayout.createSequentialGroup()
                        .addContainerGap( GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pn_DieuKhienLayout.createParallelGroup( GroupLayout.Alignment.LEADING)
                            .addComponent(lb_TrangThai)
                            .addComponent(lb_Code,  GroupLayout.PREFERRED_SIZE, 203,  GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pn_DieuKhienLayout.setVerticalGroup(
            pn_DieuKhienLayout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup(pn_DieuKhienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1,  GroupLayout.PREFERRED_SIZE, 38,  GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(pn_DieuKhienLayout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_IPMayChu,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TenNguoiChoi,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE))
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_DieuKhienLayout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_TimPhong)
                    .addComponent(txt_Code,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE))
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_DieuKhienLayout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_TaoPhong)
                    .addComponent(cb_Map,  GroupLayout.PREFERRED_SIZE, 23,  GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addComponent(lb_DanhSachNguoiChoi)
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSP_DanhSachNguoiChoi,  GroupLayout.PREFERRED_SIZE, 157,  GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pn_DieuKhienLayout.createParallelGroup( GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_ThoatPhong,  GroupLayout.DEFAULT_SIZE,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_BatDau,  GroupLayout.PREFERRED_SIZE, 56,  GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(lb_TrangThai)
                .addGap(45, 45, 45)
                .addComponent(lb_Code,  GroupLayout.PREFERRED_SIZE, 23,  GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.getAccessibleContext().setAccessibleName("lb_TenGame");
        btn_TaoPhong.getAccessibleContext().setAccessibleName("btn_TaoPhong");
        btn_TimPhong.getAccessibleContext().setAccessibleName("btn_TimPhong");

        GroupLayout pn_HienThiLayout = new  GroupLayout(pn_HienThi);
        pn_HienThi.setLayout(pn_HienThiLayout);
        pn_HienThiLayout.setHorizontalGroup(
            pn_HienThiLayout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGap(0, 840, Short.MAX_VALUE)
        );
        pn_HienThiLayout.setVerticalGroup(
            pn_HienThiLayout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

         GroupLayout layout = new  GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup( GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn_HienThi,  GroupLayout.DEFAULT_SIZE,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pn_DieuKhien,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                    .addComponent(pn_DieuKhien,  GroupLayout.DEFAULT_SIZE,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pn_HienThi,  GroupLayout.DEFAULT_SIZE,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }                      
    


    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand() == btn_TimPhong.getActionCommand()){
            timPhong();
        }else if(ae.getActionCommand() == btn_TaoPhong.getActionCommand()){
            taoPhong();
        }else if(ae.getActionCommand() == btn_ThoatPhong.getActionCommand()){
            thoatPhong();
        }     
        else if(ae.getActionCommand() == btn_BatDau.getActionCommand()){
            batDau();
        }
    }
    


    private void taoPhong() {
        try {
            registry = LocateRegistry.getRegistry(txt_IPMayChu.getText(), 5728);
            cnqldspc = (GiaoDienQLDSPhongChoi) registry.lookup("rmi://" + txt_IPMayChu.getText() +":5728/ransanmoi/qldspc");
            this.maCode = cnqldspc.taoPhong(cb_Map.getSelectedIndex());
            this.idRan = cnqldspc.vaoPhong(this.maCode, txt_TenNguoiChoi.getText());
            
            trangThaiHienThi = true;
            hienThiChiTiet(true);
            
            lb_Code.setVisible(true);
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
            Logger.getLogger(JFrameClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
            Logger.getLogger(JFrameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void timPhong() {
        try {
            System.out.println("Dang tim");
            pn_HienThi.requestFocusInWindow();
            registry = LocateRegistry.getRegistry(txt_IPMayChu.getText(), 5728);
            cnqldspc = (GiaoDienQLDSPhongChoi) registry.lookup("rmi://" + txt_IPMayChu.getText() +":5728/ransanmoi/qldspc");
            this.maCode = Integer.valueOf(txt_Code.getText());
            this.idRan = cnqldspc.vaoPhong(this.maCode, txt_TenNguoiChoi.getText());
            if(idRan == -2){
                JOptionPane.showMessageDialog(this, "Phòng đang chơi. Không thể vào");
            }else if(idRan == -1){
                JOptionPane.showMessageDialog(this, "Phòng đã đủ 4 người");
            }else if(idRan == 0){
                JOptionPane.showMessageDialog(this, "Kiểm tra lại mã code");
            } else if(idRan != 0){
                trangThaiHienThi = true;
                hienThiChiTiet(false);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Mã code phải là số có 4 chữ số");
            Logger.getLogger(JFrameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void thoatPhong() {
        try {
            if(cnqldspc.thoatPhong(this.maCode, this.idRan)){
                trangThaiHienThi = false;
                btn_BatDau.setEnabled(true);
                pn_HienThi.resetMaTran();
            }else{
                JOptionPane.showMessageDialog(this, "Thoát phòng thất bại");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(JFrameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void batDau() {
        try {
            cnqldspc.batDauChoi(maCode, idRan);
            pn_HienThi.requestFocusInWindow();
        } catch (RemoteException ex) {
            Logger.getLogger(JFrameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void batSuKienDiChuyen(KeyEvent evt) {
        try {
            int keyCode = evt.getKeyCode(); 
            switch( keyCode ) {
                case KeyEvent.VK_UP:
                    cnqldspc.diChuyen(maCode, idRan, 1);
                    break;
                case KeyEvent.VK_DOWN:
                    cnqldspc.diChuyen(maCode, idRan, 2);
                    break;
                case KeyEvent.VK_LEFT:
                    cnqldspc.diChuyen(maCode, idRan, 3);
                    break;
                case KeyEvent.VK_RIGHT :
                    cnqldspc.diChuyen(maCode, idRan, 4);
                    break;
            }
        } catch (RemoteException ex) {
            Logger.getLogger(JFrameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void hienThiChiTiet(boolean trangThaiTaoPhong) {
        lb_DanhSachNguoiChoi.setVisible(trangThaiHienThi);
        jSP_DanhSachNguoiChoi.setVisible(trangThaiHienThi);
        lb_TrangThai.setVisible(trangThaiHienThi);
        lb_Code.setVisible(trangThaiHienThi);
        btn_BatDau.setVisible(trangThaiHienThi);
        btn_ThoatPhong.setVisible(trangThaiHienThi);
        
        txt_IPMayChu.setEnabled(!trangThaiHienThi);
        txt_Code.setEnabled(!trangThaiHienThi);
        txt_TenNguoiChoi.setEnabled(!trangThaiHienThi);
        btn_TimPhong.setEnabled(!trangThaiHienThi);
        btn_TaoPhong.setEnabled(!trangThaiHienThi);
        cb_Map.setEnabled(!trangThaiHienThi);
        
        lb_Code.setText("Mã CODE: " + this.maCode);
        
        
        if(trangThaiTaoPhong == true){//Chu phong
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(trangThaiHienThi){
                        try {
                            String t = cnqldspc.hienThiDanhSachNguoiChoi(maCode);
                            int thoigian = cnqldspc.layThoiGianBatDauChoi(maCode);
                            if(thoigian != 10 && thoigian != 0 && thoigian != -1){
                                lb_TrangThai.setText("Trận đấu bắt đầu sau " + thoigian + "s");
                            }else
                                lb_TrangThai.setText("");
                            
                            pn_HienThi.setMaTran(cnqldspc.hienThiBanDoHienTai(maCode));
                            txt_DanhSachNguoiChoi.setText(t);
                            if(cnqldspc.checkIDRanChuPhong(maCode, idRan)){
                                btn_BatDau.setVisible(!cnqldspc.layTrangThaiDangChoi(maCode, idRan));
                            }else{
                                btn_BatDau.setVisible(false);
                            }
                            
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JFrameClient.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RemoteException ex) {
                            Logger.getLogger(JFrameClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    lb_DanhSachNguoiChoi.setVisible(trangThaiHienThi);
                    jSP_DanhSachNguoiChoi.setVisible(trangThaiHienThi);
                    lb_TrangThai.setVisible(trangThaiHienThi);
                    lb_Code.setVisible(trangThaiHienThi);
                    btn_BatDau.setVisible(trangThaiHienThi);
                    btn_ThoatPhong.setVisible(trangThaiHienThi);

                    txt_IPMayChu.setEnabled(!trangThaiHienThi);
                    txt_Code.setEnabled(!trangThaiHienThi);
                    txt_TenNguoiChoi.setEnabled(!trangThaiHienThi);
                    btn_TimPhong.setEnabled(!trangThaiHienThi);
                    btn_TaoPhong.setEnabled(!trangThaiHienThi);
                    cb_Map.setEnabled(!trangThaiHienThi);
                }
            }).start();
        }else{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(trangThaiHienThi){
                        try {
                            String t = cnqldspc.hienThiDanhSachNguoiChoi(maCode);
                         //   int thoigian = cnqldspc.layThoiGianBatDauChoi(maCode);
                          //  if(thoigian != 10 && thoigian != 0 && thoigian != -1){
                           //     lb_TrangThai.setText("Trận đấu bắt đầu " + thoigian);
                          //  }else
                                lb_TrangThai.setText("");
                            pn_HienThi.setMaTran(cnqldspc.hienThiBanDoHienTai(maCode));
                            txt_DanhSachNguoiChoi.setText(t);
                            if(cnqldspc.checkIDRanChuPhong(maCode, idRan)){
                                btn_BatDau.setVisible(!cnqldspc.layTrangThaiDangChoi(maCode, idRan));
                            }else{
                                btn_BatDau.setVisible(false);
                            }
                            
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JFrameClient.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RemoteException ex) {
                            Logger.getLogger(JFrameClient.class.getName()).log(Level.SEVERE, null, ex);
                        }      
                    }
                    lb_DanhSachNguoiChoi.setVisible(trangThaiHienThi);
                    jSP_DanhSachNguoiChoi.setVisible(trangThaiHienThi);
                    lb_TrangThai.setVisible(trangThaiHienThi);
                    lb_Code.setVisible(trangThaiHienThi);
                    btn_BatDau.setVisible(trangThaiHienThi);
                    btn_ThoatPhong.setVisible(trangThaiHienThi);

                    txt_IPMayChu.setEnabled(!trangThaiHienThi);
                    txt_Code.setEnabled(!trangThaiHienThi);
                    txt_TenNguoiChoi.setEnabled(!trangThaiHienThi);
                    btn_TimPhong.setEnabled(!trangThaiHienThi);
                    btn_TaoPhong.setEnabled(!trangThaiHienThi);
                    cb_Map.setEnabled(!trangThaiHienThi);
                }
            }).start();
        }
    }
    
    public static void main(String[] args) {
        JFrameClient frameMain = new JFrameClient();
        frameMain.setVisible(true);
        frameMain.setSize(1200, 670);

        frameMain.btn_TimPhong.addActionListener(frameMain);
        frameMain.btn_TaoPhong.addActionListener(frameMain);
        frameMain.btn_ThoatPhong.addActionListener(frameMain);
        frameMain.btn_BatDau.addActionListener(frameMain);
    } 
}
