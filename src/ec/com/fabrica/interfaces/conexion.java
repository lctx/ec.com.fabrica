/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.fabrica.interfaces;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class conexion {
    Connection connect=null;
    public Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/fabrica","root","");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fallo Conexión Inténtalo más tarde");
        }
        return connect;
    }
}
