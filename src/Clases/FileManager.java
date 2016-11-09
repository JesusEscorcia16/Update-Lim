package Clases;

import java.awt.HeadlessException;
import java.io.*;

/**
 * Esta clase convierte cualquier objeto en archivo de texto binario.<br/>
 * Estos archivos de textos binarios aportan persistencia al programa.
 *
 * @author Jesus Hernando Escorcia Lora
 * @version 03/04/2016
 *
 */
public class FileManager {

    //Atributos privados de la clase
    private static ObjectInputStream archivoIn;
    private static ObjectOutputStream archivoOut;

    /**
     * Método que convierte los objetos en archivos de texto binarios.
     *
     * @param objeto Recibe al objeto que será serializado.
     * @param archivo Recibe el nombre del archivo de texto binario.
     */
    public static void SaveData(Object objeto, String archivo) {
        try {
            archivoOut = new ObjectOutputStream(new FileOutputStream(archivo));
            archivoOut.writeObject(objeto);
            archivoOut.close();
        } catch (IOException | HeadlessException er) {
            //JOptionPane.showMessageDialog(null, er.getMessage());
        }
    }

    /**
     * Método que convierte los archivos de texto binarios a objetos.
     *
     * @param archivo Recibe el nombre del archivo de texto binario que será
     * convertido en objeto.
     * @return Retorna un objeto de tipo Object que contiene el objeto leido en
     * el archivo de texto binario. Este objeto que retorna debe ser casteado al
     * objeto que se requiere en la llamada del método.
     */
    public static Object ReadData(String archivo) {
        Object objeto = null;
        try {
            archivoIn = new ObjectInputStream(new FileInputStream(archivo));
            objeto = archivoIn.readObject();
            archivoIn.close();

        } catch (IOException | ClassNotFoundException | HeadlessException er) {
            //JOptionPane.showMessageDialog(null, "Archivo de configuración no encontrado.");
        }
        return objeto;
    }
}
