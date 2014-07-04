/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pemrograman2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


/**
 *
 * @author Reza saputra
 */
public class mahasiswa {
        public static void main(String[] x) throws Exception {
        //1. buat variabel bertipe FileReader
        String namafile = "src/main/resources/mahasiswa.csv";
        FileReader fr = new FileReader (namafile);
        
        //2. Bungkus dalam BufferedReader supaya ada method radLine
        BufferedReader reader = new BufferedReader (fr);
        
        //3. Looping, baca data, dan tampilkan
        String data = reader.readLine(); //header, ignore
        data = reader.readLine();
        
        // 0. Variabel untuk koneksi
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost/pemrograman2";
        String dbUser = "root";
        String dbPass = "";

        // 1. Aktivasi driver database
        Class.forName(dbDriver);

        // 2. Connect ke database
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        
        while(data != null){
            System.out.println("========================");
            String[] mahasiswa = data.split(",");
            String sql = "INSERT INTO mahasiswa (id, nim, nama, telp, angkatan, alamat) values (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mahasiswa[0]);
            ps.setString(2, mahasiswa[1]);
            ps.setString(3, mahasiswa[2]);
            ps.setString(4, mahasiswa[3]);
            ps.setString(5, mahasiswa[4]);
            ps.setString(6, mahasiswa[5]);
            
            ps.executeUpdate();
            data = reader.readLine();
        }
        //4. Tutup file
        reader.close();
        conn.close();
    }
}
