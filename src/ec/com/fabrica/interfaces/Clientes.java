/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.fabrica.interfaces;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author julio
 */
public class Clientes extends javax.swing.JFrame {

    /**
     * Creates new form Empleados
     */
    DefaultTableModel model;
    static Connection cn;
    static Statement st;
    static ResultSet rs;
    PreparedStatement psd;

    public Clientes() {
        initComponents();
        cargarCiudades();
        carTablaEmpleados("");
        //iniciarTodo();
        tblClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int fila = tblClientes.getSelectedRow();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                if (tblClientes.getSelectedRow() != -1) {
                    txtCodigo.setText((String) (tblClientes.getValueAt(fila, 0)));
                    txtNombre1.setText((String) (tblClientes.getValueAt(fila, 1)));
                    txtDireccion.setText((String) (tblClientes.getValueAt(fila, 3)));
                    String gen = (tblClientes.getValueAt(fila, 6).toString());
                    String valgen = "";
                    if ("M".equals(gen)) {
                        valgen = "Masculino";
                    } else if ("F".equals(gen)) {
                        valgen = "Femenino";
                    }
                    cbxGenero.setSelectedItem(valgen);
                    String n = String.valueOf(tblClientes.getValueAt(fila, 7));
                    try {
                        calNacimiento.setDate(df.parse(n));
                    } catch (ParseException ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    txtNacionalidad.setText((String) (tblClientes.getValueAt(fila, 8)));
                    devProvTab(Integer.valueOf(tblClientes.getValueAt(fila, 9).toString()));
                    txtDireccion.setText((String) (tblClientes.getValueAt(fila, 10)));
                    txtCelular.setText((String) (tblClientes.getValueAt(fila, 11)));
                    txtTelefono.setText((String) (tblClientes.getValueAt(fila, 12)));
                    txtTitulo.setText((String) (tblClientes.getValueAt(fila, 13)));
                    txtSueldo.setText((String) (tblClientes.getValueAt(fila, 14)));
                    txtClave.setText((String) (tblClientes.getValueAt(fila, 16)));
                    cntActualizar();
                }//To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    public void conectar() {
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            cn = DriverManager.getConnection(url, "FABRICA", "FABRICA");
            st = cn.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "FALLÓ LA CONEXIÓN \n" + e);
        }
    }

    public void iniciarTodo() {
        txtCodigo.setEnabled(false);
        txtNombre1.setEnabled(false);
        txtDireccion.setEnabled(false);
        cbxTipoSangre.setSelectedItem(null);
        cbxTipoSangre.setEnabled(false);
        cbxGenero.setSelectedItem(null);
        cbxGenero.setEnabled(false);
        calNacimiento.setEnabled(false);
        txtNacionalidad.setEnabled(false);
        cbxProvincia.setSelectedItem(null);
        cbxProvincia.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtCelular.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtTitulo.setEnabled(false);
        txtSueldo.setEnabled(false);
        txtClave.setEnabled(false);
        txtCodigo.setText("");
        txtNombre1.setText("");
        txtDireccion.setText("");
        cbxTipoSangre.setSelectedItem(null);
        cbxTipoSangre.setEnabled(false);
        cbxGenero.setSelectedItem(null);
        cbxGenero.setEnabled(false);
        calNacimiento.setEnabled(false);
        txtNacionalidad.setText("");
        cbxProvincia.setSelectedItem(null);
        cbxProvincia.setEnabled(false);
        txtDireccion.setText("");
        txtCelular.setText("");
        txtTelefono.setText("");
        txtTitulo.setText("");
        txtSueldo.setText("");
        txtClave.setText("");
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnBorrar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnSalir.setEnabled(true);
    }

    public void cntNuevo() {
        txtCodigo.setEnabled(true);
        txtNombre1.setEnabled(true);
        txtDireccion.setEnabled(true);
        cbxTipoSangre.setSelectedItem(null);
        cbxTipoSangre.setEnabled(true);
        cbxGenero.setSelectedItem(null);
        cbxGenero.setEnabled(true);
        calNacimiento.setEnabled(true);
        txtNacionalidad.setEnabled(true);
        cbxProvincia.setSelectedItem(null);
        cbxProvincia.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtCelular.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtTitulo.setEnabled(true);
        txtSueldo.setEnabled(true);
        txtClave.setEnabled(true);
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnBorrar.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnSalir.setEnabled(true);
    }

    public void cntActualizar() {
        txtCodigo.setEnabled(true);
        txtNombre1.setEnabled(true);
        txtDireccion.setEnabled(true);
        cbxTipoSangre.setEnabled(true);
        cbxGenero.setEnabled(true);
        calNacimiento.setEnabled(true);
        txtNacionalidad.setEnabled(true);
        cbxProvincia.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtCelular.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtTitulo.setEnabled(true);
        txtSueldo.setEnabled(true);
        txtClave.setEnabled(true);
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnBorrar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnSalir.setEnabled(false);
    }

    int tc;

    public void devProvTab(Integer cod) {
        try {
            conectar();
            String sql = "select *from provincia";
            rs = st.executeQuery(sql);
            String nombre = "";
            String codigo = "";
            while (rs.next()) {
                if (Integer.valueOf(rs.getString("COD_PRO").toString().trim()) == cod) {
                    nombre = rs.getString("NOM_PRO").toString().trim();
                    codigo = rs.getString("COD_PRO").toString().trim();
                }
            }
            cbxProvincia.setSelectedItem(codigo + "  " + nombre);
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void cargarDep(){
        conexion cc = new conexion();
        com.mysql.jdbc.Connection cn = (com.mysql.jdbc.Connection) cc.conectar();
        String sql = "";
        String nombre,codigo;
        sql = "select *from DEPARTAMENTOS";
        try {
            com.mysql.jdbc.Statement psd = (com.mysql.jdbc.Statement) cn.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                //String codigo = rs.getString("CIU_COD").toString().trim();
                nombre = rs.getString("NOM_PRO").toString().trim();
                codigo = rs.getString("COD_PRO").toString().trim();
                cbxProvincia.addItem(codigo+" "+nombre);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void cargarCiudades() {
        try {
            conectar();
            String sql = "select *from provincia";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String nombre = rs.getString("NOM_PRO").toString().trim();
                String codigo = rs.getString("COD_PRO").toString().trim();
                cbxProvincia.addItem(codigo + "  " + nombre);
                if (codigo.length() == 1) {
                    tc = 2;
                } else if (codigo.length() == 2) {
                    tc = 3;
                }
            }
            rs.close();
            cn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    private boolean VerificarCedula(String cedula) {
        boolean cedulaCorrecta = false;

        try {

            if (cedula.length() == 10) {
                int tercerDigito = Integer.valueOf(cedula.substring(2, 3));
                if (tercerDigito < 6) {
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.valueOf(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.valueOf(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }
                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {
            return false;
        }
        return cedulaCorrecta;
    }

    public void GuardarEmpleado() {
        Boolean ced = VerificarCedula(txtCodigo.getText());
        Integer ai = Integer.valueOf(String.valueOf(new SimpleDateFormat("yyyy").format(calNacimiento.getDate())));
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy");
        Integer ac = Integer.valueOf(String.valueOf(date.format(now)));
        if (txtCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "debe ingresar la cédula");
            txtCodigo.requestFocus();
        } else if (txtNombre1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "debe ingresar el primer nombre");
            txtNombre1.requestFocus();
        } else if (txtDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "debe ingresar el apellido paterno");
            txtDireccion.requestFocus();
        } else if (!ced) {
            JOptionPane.showMessageDialog(null, "Cédula Incorrecta");
            txtCodigo.requestFocus();
        } else if (ai > (ac-18)){
            JOptionPane.showMessageDialog(null, "No se permiten Menores de edad");
            calNacimiento.setFocusable(true);
        } else {
            

            String CED_EMP, NOM1_EMP, NOM2_EMP, APE1_EMP, APE2_EMP, TIP_SAN_EMP, GEN_EMP, FEC_NAC_EMP, NAC_EMP,
                    PRO_EMP, DIR_EMP, CEL_EMP, TEL_EMP, TIT_EMP, CAR_EMP, CLA_EMP;
            Integer SUE_NOM;
            String[] nom = txtNombre1.getText().replaceAll(" +", " ").split(" ");
            String[] ape = txtDireccion.getText().replaceAll(" +", " ").split(" ");
            CED_EMP = txtCodigo.getText();
            APE1_EMP = " ";
            NOM1_EMP = " ";
            APE2_EMP = " ";
            NOM2_EMP = " ";
            if (nom.length == 2) {
                NOM1_EMP = nom[0];
                NOM2_EMP = nom[1];
            } else if (nom.length == 1) {
                NOM1_EMP = nom[0];
            }
            if (ape.length == 2) {
                APE1_EMP = ape[0];
                APE2_EMP = ape[1];
            } else if (nom.length == 1) {
                APE1_EMP = ape[0];
            }
            TIP_SAN_EMP = String.valueOf(cbxTipoSangre.getSelectedItem().toString());
            GEN_EMP = String.valueOf(cbxGenero.getSelectedItem().toString().substring(0, 1));
            FEC_NAC_EMP = (new SimpleDateFormat("dd/MM/yyyy").format(calNacimiento.getDate()));
            NAC_EMP = txtNacionalidad.getText();
            PRO_EMP = String.valueOf(cbxProvincia.getSelectedItem().toString().substring(0, tc));
            DIR_EMP = txtDireccion.getText();
            CEL_EMP = txtCelular.getText();
            TEL_EMP = txtTelefono.getText();
            TIT_EMP = txtTitulo.getText();
            SUE_NOM = Integer.valueOf(String.valueOf(txtSueldo.getText()));
            CLA_EMP = txtClave.getText();
            String sql = "";
            String nuevaFila;
            try {
                conectar();
                sql = "SELECT * FROM empleado WHERE CED_EMP LIKE '%" + CED_EMP + "%'";
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    nuevaFila = rs.getString("CED_EMP");
                    if (nuevaFila.equals(CED_EMP)) {
                        JOptionPane.showMessageDialog(null, "El empleado ya existe");
                    }
                }
                tblClientes.setModel(model);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

            try {
                conectar();
                sql = "insert into empleado(CED_EMP, NOM1_EMP, NOM2_EMP, APE1_EMP, APE2_EMP, TIP_SAN_EMP,GEN_EMP,"
                        + "FEC_NAC_EMP, NAC_EMP, PRO_EMP, DIR_EMP, CEL_EMP, TEL_EMP, TIT_EMP, SUE_NOM, CAR_EMP, CLA_EMP) "
                        + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                psd = cn.prepareStatement(sql);

                psd.setString(1, CED_EMP);
                psd.setString(2, NOM1_EMP);
                psd.setString(3, NOM2_EMP);
                psd.setString(4, APE1_EMP);
                psd.setString(5, APE2_EMP);
                psd.setString(6, TIP_SAN_EMP);
                psd.setString(7, GEN_EMP);
                psd.setString(8, FEC_NAC_EMP);
                psd.setString(9, NAC_EMP);
                psd.setString(10, PRO_EMP);
                psd.setString(11, DIR_EMP);
                psd.setString(12, CEL_EMP);
                psd.setString(13, TEL_EMP);
                psd.setString(14, TIT_EMP);
                psd.setInt(15, SUE_NOM);
                psd.setString(17, CLA_EMP);
                int n = psd.executeUpdate();
                System.out.println(sql);
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Se Insertó el Dato Correctamente");
                }
                rs.close();
                cn.close();
                iniciarTodo();
            } catch (SQLException ex) {
                for (int i = 0; i < tblClientes.getRowCount(); i++) {
                    if (txtCodigo == model.getValueAt(i, 0)) {
                        JOptionPane.showMessageDialog(null, "El empleado ya existe");
                    }
                }
                JOptionPane.showMessageDialog(null, ex);
            }
        }

    }

    public void carTablaEmpleados(String Dato) {

        String[] titulos = {"Cédula", "Nombre1", "Nombre2", "Apellido P", "Apellido M",
            "TS", "Género", "Fecha N", "Nacionalid", "# Prov", "Dirección", "Celular", "Teléfono",
            "Título", "Sueldo", "Cargo", "Clave"};
        String[] registros = new String[17];
        model = new DefaultTableModel(null, titulos);
        try {
            conectar();
            String sql = "select *from empleado where CED_EMP LIKE '%" + Dato + "%'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("CED_EMP");
                registros[1] = rs.getString("NOM1_EMP");
                registros[2] = rs.getString("NOM2_EMP");
                registros[3] = rs.getString("APE1_EMP");
                registros[4] = rs.getString("APE2_EMP");
                registros[5] = rs.getString("TIP_SAN_EMP");
                registros[6] = rs.getString("GEN_EMP");
                registros[7] = rs.getString("FEC_NAC_EMP");
                registros[8] = rs.getString("NAC_EMP");
                registros[9] = rs.getString("PRO_EMP");
                registros[10] = rs.getString("DIR_EMP");
                registros[11] = rs.getString("CEL_EMP");
                registros[12] = rs.getString("TEL_EMP");
                registros[13] = rs.getString("TIT_EMP");
                registros[14] = rs.getString("SUE_NOM");
                registros[15] = rs.getString("CAR_EMP");
                registros[16] = rs.getString("CLA_EMP");
                model.addRow(registros);
            }
            tblClientes.setModel(model);
            rs.close();
            cn.close();
            iniciarTodo();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre1 = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtCelular2 = new javax.swing.JTextField();
        txtRuc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);

        txtCodigo.setBackground(new java.awt.Color(0, 0, 51));
        txtCodigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(255, 255, 255));
        txtCodigo.setText("1231234");
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        txtNombre1.setBackground(new java.awt.Color(0, 0, 51));
        txtNombre1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNombre1.setForeground(new java.awt.Color(255, 255, 255));
        txtNombre1.setText("asdf asdf");
        txtNombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre1ActionPerformed(evt);
            }
        });
        txtNombre1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre1KeyTyped(evt);
            }
        });

        txtDireccion.setBackground(new java.awt.Color(0, 0, 51));
        txtDireccion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(255, 255, 255));
        txtDireccion.setText("asdf");
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Código*");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Nombre (s)*");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Dirección*");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Email");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Teléfono");

        txtTelefono.setBackground(new java.awt.Color(0, 0, 51));
        txtTelefono.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(255, 255, 255));
        txtTelefono.setText("2354");
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        txtCelular2.setBackground(new java.awt.Color(0, 0, 51));
        txtCelular2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCelular2.setForeground(new java.awt.Color(255, 255, 255));

        txtRuc.setBackground(new java.awt.Color(0, 0, 51));
        txtRuc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtRuc.setForeground(new java.awt.Color(255, 255, 255));
        txtRuc.setText("1231234");
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ruc*");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel30))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCelular2)
                            .addComponent(txtTelefono)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(90, 90, 90)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRuc)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(txtNombre1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtCelular2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 320, 220));

        jPanel3.setOpaque(false);

        btnNuevo.setBackground(new java.awt.Color(0, 0, 51));
        btnNuevo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/fabrica/imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("      Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(0, 0, 51));
        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/fabrica/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("   Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(0, 0, 51));
        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/fabrica/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnBorrar.setBackground(new java.awt.Color(0, 0, 51));
        btnBorrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBorrar.setForeground(new java.awt.Color(255, 255, 255));
        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/fabrica/imagenes/eliminar.png"))); // NOI18N
        btnBorrar.setText("     Borrar");

        btnCancelar.setBackground(new java.awt.Color(0, 0, 51));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/fabrica/imagenes/cancelar.png"))); // NOI18N
        btnCancelar.setText(" Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(0, 0, 51));
        btnSalir.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/fabrica/imagenes/salir.png"))); // NOI18N
        btnSalir.setText("          Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBorrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 170, 330));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblClientes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 670, 130));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/fabrica/imagenes/location.png"))); // NOI18N
        jLabel2.setText("Buscar");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, -1, -1));

        txtBuscar.setBackground(new java.awt.Color(0, 0, 51));
        txtBuscar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 220, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/fabrica/imagenes/FondoAzul.png"))); // NOI18N
        jLabel18.setOpaque(true);
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -110, 960, 810));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        GuardarEmpleado();
        carTablaEmpleados("");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        cntNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        iniciarTodo();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        iniciarTodo();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRucKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        if (txtTelefono.getText().length() <= 9) {
            if (!Character.isDigit(evt.getKeyChar())) {
                evt.consume();
            }
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        if (txtDireccion.getText().length() < 15) {
            if (!Character.isAlphabetic(evt.getKeyChar())) {
                evt.consume();
            }
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtNombre1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre1KeyTyped
        if (txtNombre1.getText().length() < 15) {
            if (!Character.isAlphabetic(evt.getKeyChar()) && evt.getKeyChar() != KeyEvent.VK_SPACE) {
                evt.consume();
            }
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombre1KeyTyped

    private void txtNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre1ActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        if (txtCodigo.getText().length() < 10) {
            System.out.println(txtCodigo.getText().length());
            if (!Character.isDigit(evt.getKeyChar())) {
                evt.consume();
            }
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCelular2;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
