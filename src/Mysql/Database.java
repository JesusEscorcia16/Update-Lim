package Mysql;

import Clases.FileManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import javax.swing.JOptionPane;

public class Database {

    private static Connection conexion;
    private static ResultSet r;
    private static Statement sentencia;

    public Database() {
        conexion = null;
        r = null;
        sentencia = null;
    }

    private static void DataBaseConect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://db4free.net:3306/lesoftinc";// Ruta de la base de datos
            conexion = DriverManager.getConnection(url, "lesoftinc", "20denoviembre"); //Loggin a la base de datos
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
    }

    private static void cerrarConexion() {
        try {
            conexion.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static boolean setSerial(String serial) {
        try {
            DataBaseConect();
            String query = "INSERT INTO activacion(serial) VALUES(?)";
            PreparedStatement sentencia = conexion.prepareStatement(query);
            sentencia.setString(1, serial);
            sentencia.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        } finally {
            cerrarConexion();
        }
    }

    public static ResultSet getSeriales() {
        try {
            DataBaseConect();
            String query = "SELECT * FROM activacion";
            sentencia = conexion.createStatement();
            r = sentencia.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            cerrarConexion();
        }
        return r;
    }

    public static ResultSet verificarSerial(String serial) {
        try {
            DataBaseConect();
            String query = "SELECT * FROM activacion WHERE serial = '" + serial + "'";
            sentencia = conexion.createStatement();
            r = sentencia.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            cerrarConexion();
        }
        return r;
    }

    public static void activar(String serial, java.sql.Date fecha_inicio, java.sql.Date fecha_fin, String idDisk, String correo) {
        try {
            DataBaseConect();
            String query = "UPDATE activacion SET num_pc = (num_pc-1),"
                    + " correo = '" + correo + "' WHERE serial = '" + serial + "'";
            registrarOrdenador(serial, idDisk);
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    public static void registrarOrdenador(String serial, String idDisk) {
        try {
            DataBaseConect();
            String query = "INSERT INTO ordenadores(serial, id_disk) VALUES('" + serial + "','" + idDisk + "')";
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    public static ResultSet getVersion() {
        try {
            DataBaseConect();
            String query = "SELECT * FROM version";
            sentencia = conexion.createStatement();
            r = sentencia.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            cerrarConexion();
        }
        return r;
    }

    public static ResultSet getOrdenadores() {
        try {
            DataBaseConect();
            String query = "SELECT * FROM version";
            sentencia = conexion.createStatement();
            r = sentencia.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            cerrarConexion();
        }
        return r;
    }

    public static void getJar() {
        File file = new File("LIM.jar");
        InputStream input = null;
        Blob blob = null;
        FileOutputStream output = null;
        try {
            DataBaseConect();
            sentencia = conexion.createStatement();
            r = sentencia.executeQuery("SELECT jar FROM version");

            output = new FileOutputStream(file);

            if (r.next()) {
                blob = r.getBlob("jar");
                input = blob.getBinaryStream();
                byte[] buffer = new byte[1024];
                FileManager.SaveData(input, "Lim.jar");
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                
                if (output != null) {                    
                    output.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            cerrarConexion();
        }
    }
}
