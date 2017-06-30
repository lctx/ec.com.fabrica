/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.fabrica.interfaces;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class conexion {

    Connection connect = null;

    Statement st = null;

    public Connection conectar() {
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            connect = (Connection) DriverManager.getConnection(url, "FABRICA", "FABRICA");
            st = connect.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "FALLÓ LA CONEXIÓN \n" + e);
        }
        return connect;
    }

    public void escribir(String tabla, String[] codigos, String[] campos) {
        //tabla es el nombre de la tabla "EMPLEADOS"
        //en codigos van los nombres de los campos ejm CED_EMP
        //en campos van los valores capturados de los jtexfield o lo que sea "1801"

        String codigos_sal = "";
        String num_val = "";
        String inser = "";
        for (int i = 0; i < codigos.length; i++) {
            //añade los ? por cada campo que tengamos
            if (i != codigos.length - 1) {
                codigos_sal += codigos[i] + ", ";
                num_val += "?, ";
            } else {
                num_val += "?";
                codigos_sal += codigos[i];
            }
        }
        //es el qwery ensamblado para mandarle a ejecutar
        inser = "INSERT INTO " + tabla + "(" + codigos_sal + ")" + " VALUES(" + num_val + ")";
        try {
            PreparedStatement INGRESO = connect.prepareStatement(inser);
            //añade los campos al qwery
            for (int i = 0; i < campos.length; i++) {
                INGRESO.setString(i + 1, campos[i]);
            }
            INGRESO.executeUpdate();

            INGRESO.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error sql");
            System.out.println("ingreso fallido");
        }

    }

    public void eliminar(String tabla, String campo, String valor) {
        ResultSet rs = null;
        String sql = "DELETE FROM " + tabla + " WHERE " + campo + "=" + valor;
        try {
            PreparedStatement stmt = connect.prepareStatement(sql);
            rs = stmt.executeQuery();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet consultar(String tabla) {
        ResultSet rs = null;
        String sql = "SELECT * FROM " + tabla;
        try {
            PreparedStatement stmt = connect.prepareStatement(sql);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return rs;
    }
    
    public ResultSet consultar(String tabla, String cam_consultar,String consulta) {
        ResultSet rs = null;
        String sql = "SELECT * FROM " + tabla+" WHERE "+cam_consultar+" = "+consulta;
        try {
            PreparedStatement stmt = connect.prepareStatement(sql);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return rs;
    }

}
