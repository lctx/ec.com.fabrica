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
public class Empleados extends javax.swing.JFrame {

    /**
     * Creates new form Empleados
     */
    DefaultTableModel model;
    static Connection cn;
    static Statement st;
    static ResultSet rs;
    PreparedStatement psd;

    public Empleados() {
        initComponents();
        cargarCiudades();
        carTablaEmpleados("");
        cargarDep();
        //iniciarTodo();
        tblEmpleados.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int fila = tblEmpleados.getSelectedRow();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                if (tblEmpleados.getSelectedRow() != -1) {
                    txtCedula.setText((String) (tblEmpleados.getValueAt(fila, 0)));
                    txtNombre1.setText((String) (tblEmpleados.getValueAt(fila, 1)));
                    txtApellidop.setText((String) (tblEmpleados.getValueAt(fila, 2)));
                    cbxTipoSangre.setSelectedItem(tblEmpleados.getValueAt(fila, 3).toString());
                    String gen = (tblEmpleados.getValueAt(fila, 4).toString());
                    String valgen = "";
                    if ("M".equals(gen)) {
                        valgen = "Masculino";
                    } else if ("F".equals(gen)) {
                        valgen = "Femenino";
                    }
                    cbxGenero.setSelectedItem(valgen);
                    String n = String.valueOf(tblEmpleados.getValueAt(fila, 5));
                    try {
                        calNacimiento.setDate(df.parse(n));
                    } catch (ParseException ex) {
                        Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    txtNacionalidad.setText((String) (tblEmpleados.getValueAt(fila, 11)));
                    devProvTab(Integer.valueOf(tblEmpleados.getValueAt(fila, 10).toString()));
                    txtDireccion.setText((String) (tblEmpleados.getValueAt(fila, 6)));
                    txtCelular.setText((String) (tblEmpleados.getValueAt(fila, 7)));
                    txtCelular2.setText((String) (tblEmpleados.getValueAt(fila, 8)));
                    txtTelefono.setText((String) (tblEmpleados.getValueAt(fila, 9)));
                    txtTitulo.setText((String) (tblEmpleados.getValueAt(fila, 12)));
                    txtClave.setText((String) (tblEmpleados.getValueAt(fila, 14)));
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
        txtCedula.setEnabled(false);
        txtNombre1.setEnabled(false);
        txtApellidop.setEnabled(false);
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
        cbxCargo.setEnabled(false);
        cbxDepartamento.setEnabled(false);
        cbxDepartamento.setSelectedItem(null);
        cbxCargo.setSelectedItem(null);
        txtClave.setEnabled(false);
        txtCedula.setText("");
        txtNombre1.setText("");
        txtApellidop.setText("");
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
        txtClave.setText("");
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnBorrar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnSalir.setEnabled(true);
    }

    public void cntNuevo() {
        txtCedula.setEnabled(true);
        txtNombre1.setEnabled(true);
        txtApellidop.setEnabled(true);
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
        cbxCargo.setSelectedItem(null);
        cbxCargo.setEnabled(true);
        cbxDepartamento.setSelectedItem(null);
        cbxDepartamento.setEnabled(true);
        txtClave.setEnabled(true);
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnBorrar.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnSalir.setEnabled(true);
    }

    public void cntActualizar() {
        txtCedula.setEnabled(true);
        txtNombre1.setEnabled(true);
        txtApellidop.setEnabled(true);
        cbxTipoSangre.setEnabled(true);
        cbxGenero.setEnabled(true);
        calNacimiento.setEnabled(true);
        txtNacionalidad.setEnabled(true);
        cbxProvincia.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtCelular.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtTitulo.setEnabled(true);
        cbxCargo.setEnabled(true);
        cbxDepartamento.setEnabled(true);
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
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void devDep(String cod) {
        String nomc = "";
        String codc = "";
        String coddep = "";
        try {
            conectar();
            String sql = "select *from cargos";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if ((rs.getString("COD_CAR").toString().trim()) == cod) {
                    nomc = rs.getString("NOM_CAR").toString().trim();
                    codc = rs.getString("COD_CAR").toString().trim();
                    coddep = rs.getString("COD_DEP").toString().trim();
                }
            }
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }try {
            conectar();
            String sql = "select *from departamentos";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if ((rs.getString("COD_DEP").toString().trim()) == coddep) {
                    nomc = rs.getString("NOM_CAR").toString().trim();
                    codc = rs.getString("COD_CAR").toString().trim();
                    coddep = rs.getString("COD_DEP").toString().trim();
                }
            }
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbxProvincia.setSelectedItem(codc + "  " + nomc);
    }

    public void cargarDep() {
        String sql = "";
        String nombre, codigo;
        try {
            conectar();
            sql = "select *from DEPARTAMENTOS";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                nombre = rs.getString("NOM_DEP").toString().trim();
                codigo = rs.getString("COD_DEP").toString().trim();
                cbxDepartamento.addItem(codigo + " " + nombre);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void cargarCargos() {
        String COD_DEP;
        String[] tem = (cbxDepartamento.getSelectedItem().toString()).split(" ");
        COD_DEP = tem[0];
        String sql = "";
        String nombre, codigo;
        try {
            conectar();
            sql = "select *from CARGOS where COD_DEP = '" + COD_DEP + "'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                //String codigo = rs.getString("CIU_COD").toString().trim();
                nombre = rs.getString("NOM_CAR").toString().trim();
                codigo = rs.getString("COD_CAR").toString().trim();
                cbxCargo.addItem(codigo + " " + nombre);
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
        Boolean ced = VerificarCedula(txtCedula.getText());
        Integer ai = Integer.valueOf(String.valueOf(new SimpleDateFormat("yyyy").format(calNacimiento.getDate())));
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy");
        Integer ac = Integer.valueOf(String.valueOf(date.format(now)));
        if (txtCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "debe ingresar la cédula");
            txtCedula.requestFocus();
        } else if (txtNombre1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "debe ingresar el primer nombre");
            txtNombre1.requestFocus();
        } else if (txtApellidop.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "debe ingresar el apellido paterno");
            txtApellidop.requestFocus();
        } else if (!ced) {
            JOptionPane.showMessageDialog(null, "Cédula Incorrecta");
            txtCedula.requestFocus();
        } else if (ai > (ac - 18)) {
            JOptionPane.showMessageDialog(null, "No se permiten Menores de edad");
            calNacimiento.setFocusable(true);
        } else {

            String CED_EMP, NOM1_EMP, NOM2_EMP, APE1_EMP, APE2_EMP, TIP_SAN_EMP, GEN_EMP, FEC_NAC_EMP, NAC_EMP,
                    PRO_EMP, DIR_EMP, CEL_EMP, CEL2_EMP, TEL_EMP, TIT_EMP, CAR_EMP, CLA_EMP;
            String[] nom = txtNombre1.getText().replaceAll(" +", " ").split(" ");
            String[] ape = txtApellidop.getText().replaceAll(" +", " ").split(" ");
            CED_EMP = txtCedula.getText();
            APE1_EMP = " ";
            NOM1_EMP = " ";
            APE2_EMP = " ";
            NOM2_EMP = " ";
            if (nom.length == 2) {
                NOM1_EMP = nom[0];
                NOM2_EMP = nom[1];
            } else if (nom.length < 2) {
                NOM1_EMP = nom[0];
            }
            if (ape.length == 2) {
                APE1_EMP = ape[0];
                APE2_EMP = ape[1];
            } else if (nom.length < 2) {
                APE1_EMP = ape[0];
            }
            TIP_SAN_EMP = String.valueOf(cbxTipoSangre.getSelectedItem().toString());
            GEN_EMP = String.valueOf(cbxGenero.getSelectedItem().toString().substring(0, 1));
            FEC_NAC_EMP = (new SimpleDateFormat("dd/MM/yyyy").format(calNacimiento.getDate()));
            NAC_EMP = txtNacionalidad.getText();
            PRO_EMP = String.valueOf(cbxProvincia.getSelectedItem().toString().substring(0, tc));
            DIR_EMP = txtDireccion.getText();
            CEL_EMP = txtCelular.getText();
            CEL2_EMP = txtCelular2.getText();
            TEL_EMP = txtTelefono.getText();
            TIT_EMP = txtTitulo.getText();
            CAR_EMP = String.valueOf(cbxCargo.getSelectedItem().toString().substring(0, 3));
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
                tblEmpleados.setModel(model);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

            try {
                conectar();
                sql = "insert into empleado(CED_EMP, NOM1_EMP, NOM2_EMP, APE1_EMP, APE2_EMP, TIP_SAN_EMP,GEN_EMP,"
                        + "FEC_NAC_EMP, NAC_EMP, PRO_EMP, DIR_EMP, CEL_EMP, TEL_EMP, TIT_EMP, CAR_EMP, CLA_EMP, CEL2_EMP) "
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
                psd.setString(15, CAR_EMP);
                psd.setString(16, CLA_EMP);
                psd.setString(17, CEL2_EMP);
                int n = psd.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Se Insertó el Dato Correctamente");
                    System.out.println(sql);
                }
                rs.close();
                cn.close();
                iniciarTodo();
            } catch (SQLException ex) {
                for (int i = 0; i < tblEmpleados.getRowCount(); i++) {
                    if (txtCedula == model.getValueAt(i, 0)) {
                        JOptionPane.showMessageDialog(null, "El empleado ya existe");
                    }
                }
                JOptionPane.showMessageDialog(null, ex);
            }
        }

    }

    public void carTablaEmpleados(String Dato) {

        String[] titulos = {"Cédula", "Nombres", "Apellidos", "T Sangre", "Género", "Fecha N",
            "Dirección", "Celular", "Celular 2", "Teléfono",
            "# Provincia", "Nacionalidad", "Título", "Cargo", "Pin"};
        String nom1, nom2, ape1, ape2;
        String[] registros = new String[16];
        model = new DefaultTableModel(null, titulos);
        try {
            conectar();
            String sql = "select *from empleado where CED_EMP LIKE '%" + Dato + "%'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("CED_EMP");
                nom1 = rs.getString("NOM1_EMP");
                nom2 = rs.getString("NOM2_EMP");
                registros[1] = nom1 + " " + nom2;
                ape1 = rs.getString("APE1_EMP");
                ape2 = rs.getString("APE2_EMP");
                registros[2] = ape1 + " " + ape2;
                registros[3] = rs.getString("TIP_SAN_EMP");
                registros[4] = rs.getString("GEN_EMP");
                registros[5] = rs.getString("FEC_NAC_EMP");
                registros[6] = rs.getString("DIR_EMP");
                registros[7] = rs.getString("CEL_EMP");
                registros[8] = rs.getString("CEL2_EMP");
                registros[9] = rs.getString("TEL_EMP");
                registros[10] = rs.getString("PRO_EMP");
                registros[11] = rs.getString("NAC_EMP");
                registros[12] = rs.getString("TIT_EMP");
                registros[13] = rs.getString("CAR_EMP");
                registros[14] = rs.getString("CLA_EMP");
                model.addRow(registros);
            }
            tblEmpleados.setModel(model);
            rs.close();
            cn.close();
            //iniciarTodo();
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
        txtCedula = new javax.swing.JTextField();
        txtNombre1 = new javax.swing.JTextField();
        txtApellidop = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cbxTipoSangre = new javax.swing.JComboBox();
        calNacimiento = new com.toedter.calendar.JDateChooser();
        cbxGenero = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCelular2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtTitulo = new javax.swing.JTextField();
        txtClave = new javax.swing.JTextField();
        cbxProvincia = new javax.swing.JComboBox();
        jLabel36 = new javax.swing.JLabel();
        cbxDepartamento = new javax.swing.JComboBox();
        cbxCargo = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        txtNacionalidad = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleados = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);

        txtCedula.setBackground(new java.awt.Color(0, 0, 51));
        txtCedula.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCedula.setForeground(new java.awt.Color(255, 255, 255));
        txtCedula.setText("1231234");
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
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

        txtApellidop.setBackground(new java.awt.Color(0, 0, 51));
        txtApellidop.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtApellidop.setForeground(new java.awt.Color(255, 255, 255));
        txtApellidop.setText("asdf");
        txtApellidop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidopKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cédula");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Nombre (s)*");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Apellido (s)*");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("T Sangre");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Género");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("F Nacimiento");

        cbxTipoSangre.setBackground(new java.awt.Color(0, 0, 51));
        cbxTipoSangre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbxTipoSangre.setForeground(new java.awt.Color(255, 255, 255));
        cbxTipoSangre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));

        calNacimiento.setBackground(new java.awt.Color(102, 102, 255));
        calNacimiento.setForeground(new java.awt.Color(255, 255, 255));
        calNacimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        cbxGenero.setBackground(new java.awt.Color(0, 0, 51));
        cbxGenero.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbxGenero.setForeground(new java.awt.Color(255, 255, 255));
        cbxGenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Femenino" }));
        cbxGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxGeneroActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Celular 1");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Celular 2");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Teléfono");

        txtCelular.setBackground(new java.awt.Color(0, 0, 51));
        txtCelular.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCelular.setForeground(new java.awt.Color(255, 255, 255));
        txtCelular.setText("6789");
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCelularKeyTyped(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxTipoSangre, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                        .addComponent(calNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbxGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtApellidop, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtCelular2)
                                        .addGap(1, 1, 1))
                                    .addComponent(txtCelular)
                                    .addComponent(txtTelefono))))
                        .addGap(2, 2, 2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtApellidop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(cbxTipoSangre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cbxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25)
                    .addComponent(calNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtCelular2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 320, 400));

        jPanel2.setOpaque(false);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Título");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Cargo");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Provincia");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Dirección");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("PIN");

        txtDireccion.setBackground(new java.awt.Color(0, 0, 51));
        txtDireccion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(255, 255, 255));
        txtDireccion.setText("asdfasdf");
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        txtTitulo.setBackground(new java.awt.Color(0, 0, 51));
        txtTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTitulo.setForeground(new java.awt.Color(255, 255, 255));
        txtTitulo.setText("asdf");
        txtTitulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTituloKeyTyped(evt);
            }
        });

        txtClave.setBackground(new java.awt.Color(0, 0, 51));
        txtClave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtClave.setForeground(new java.awt.Color(255, 255, 255));

        cbxProvincia.setBackground(new java.awt.Color(0, 0, 51));
        cbxProvincia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbxProvincia.setForeground(new java.awt.Color(255, 255, 255));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Departamento");

        cbxDepartamento.setBackground(new java.awt.Color(0, 0, 51));
        cbxDepartamento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbxDepartamento.setForeground(new java.awt.Color(255, 255, 255));
        cbxDepartamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDepartamentoItemStateChanged(evt);
            }
        });
        cbxDepartamento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbxDepartamentoFocusLost(evt);
            }
        });

        cbxCargo.setBackground(new java.awt.Color(0, 0, 51));
        cbxCargo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbxCargo.setForeground(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Nacionalidad");

        txtNacionalidad.setBackground(new java.awt.Color(0, 0, 51));
        txtNacionalidad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNacionalidad.setForeground(new java.awt.Color(255, 255, 255));
        txtNacionalidad.setText("asdfa");
        txtNacionalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNacionalidadKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(100, 100, 100)
                                .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(82, 82, 82)
                                .addComponent(cbxCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel32)
                                        .addComponent(jLabel33)
                                        .addComponent(jLabel28))
                                    .addGap(49, 49, 49)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNacionalidad)
                                        .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                        .addComponent(txtDireccion)
                                        .addComponent(cbxProvincia, 0, 162, Short.MAX_VALUE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel36)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbxDepartamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(43, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(cbxProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(cbxDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(cbxCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 350, 390));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(22, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 170, 300));

        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblEmpleados);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 910, 150));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Buscar");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, -1, -1));

        txtBuscar.setBackground(new java.awt.Color(0, 0, 51));
        txtBuscar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 220, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/fabrica/imagenes/FondoAzul.png"))); // NOI18N
        jLabel18.setOpaque(true);
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -110, 960, 810));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        if (txtCedula.getText().length() < 10) {
            System.out.println(txtCedula.getText().length());
            if (!Character.isDigit(evt.getKeyChar())) {
                evt.consume();
            }
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtCelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyTyped
        if (txtCelular.getText().length() <= 10) {
            if (!Character.isDigit(evt.getKeyChar())) {
                evt.consume();
            }
        } else {
            evt.consume();
        }

    }//GEN-LAST:event_txtCelularKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        if (txtTelefono.getText().length() <= 9) {
            if (!Character.isDigit(evt.getKeyChar())) {
                evt.consume();
            }
        } else {
            evt.consume();
        }

    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtNombre1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre1KeyTyped
        if (txtNombre1.getText().length() < 20) {
            if (!Character.isAlphabetic(evt.getKeyChar()) && evt.getKeyChar() != KeyEvent.VK_SPACE) {
                evt.consume();
            }
        } else {
            evt.consume();
        }

    }//GEN-LAST:event_txtNombre1KeyTyped

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        GuardarEmpleado();
        carTablaEmpleados("");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtApellidopKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidopKeyTyped
        if (txtApellidop.getText().length() < 20) {
            if (!Character.isAlphabetic(evt.getKeyChar()) && evt.getKeyChar() != KeyEvent.VK_SPACE) {
                evt.consume();
            }
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_txtApellidopKeyTyped

    private void txtNacionalidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacionalidadKeyTyped
        if (txtNacionalidad.getText().length() < 15) {
            if (!Character.isAlphabetic(evt.getKeyChar())) {
                evt.consume();
            }
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_txtNacionalidadKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        if (txtDireccion.getText().length() < 30) {
            if (!Character.isAlphabetic(evt.getKeyChar())) {
                evt.consume();
            }
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtTituloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTituloKeyTyped
        if (txtTitulo.getText().length() < 20) {
            if (!Character.isAlphabetic(evt.getKeyChar())) {
                evt.consume();
            }
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_txtTituloKeyTyped

    private void cbxGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxGeneroActionPerformed

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

    private void txtNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre1ActionPerformed

    private void cbxDepartamentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbxDepartamentoFocusLost
        // TODO add your handling code here:
        cbxCargo.removeAllItems();
        cargarCargos();
    }//GEN-LAST:event_cbxDepartamentoFocusLost

    private void cbxDepartamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDepartamentoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDepartamentoItemStateChanged

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
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Empleados().setVisible(true);
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
    private com.toedter.calendar.JDateChooser calNacimiento;
    private javax.swing.JComboBox cbxCargo;
    private javax.swing.JComboBox cbxDepartamento;
    private javax.swing.JComboBox cbxGenero;
    private javax.swing.JComboBox cbxProvincia;
    private javax.swing.JComboBox cbxTipoSangre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEmpleados;
    private javax.swing.JTextField txtApellidop;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCelular2;
    private javax.swing.JTextField txtClave;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
