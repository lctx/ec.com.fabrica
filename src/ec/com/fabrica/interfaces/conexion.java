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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class conexion {

    Connection connect = null;

    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/fabrica", "root", "");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fallo Conexión Inténtalo más tarde");
        }
        return connect;
    }

    public void escribir(String tabla, String[] codigos, String[] campos) {
        String codigos_sal = "";
        String campos_sal = "";
        String num_val = "";
        String inser = "";
        for (int i = 0; i < codigos.length; i++) {

            if (i != codigos.length - 1) {
                codigos_sal += codigos[i] + ", ";
                num_val += "?, ";
            } else {
                num_val += "?";
                codigos_sal += codigos[i];
            }
        }

        inser = "INSERT INTO " + tabla + "(" + codigos_sal + ")" + " VALUES(" + num_val + ")";
        try {
            PreparedStatement INGRESO = connect.prepareStatement(inser);
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

}
