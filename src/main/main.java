/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.File;
import java.io.IOException;
import vista.VentanaPrincipal;

/**
 *
 * @author MANBIR
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static final String RUTA_ARCHIVO = System.getProperty("user.dir") + File.separator + "src" + File.separator + "datos";

    public static final File CARPETA_DATOS = new File(RUTA_ARCHIVO);
    public static final File CARPETA_DAM = new File(RUTA_ARCHIVO + File.separator + "dam");
    public static final File CARPETA_DAW = new File(RUTA_ARCHIVO + File.separator + "daw");

    public static File alumnos;

    public static void start() {
        try {
            if (!CARPETA_DATOS.exists()) {
                CARPETA_DATOS.mkdir();
            }
            if (!CARPETA_DAM.exists()) {
                CARPETA_DAM.mkdir();
                alumnos = new File(CARPETA_DAM + File.separator + "alumnos.txt");
                alumnos.createNewFile();
            }
            if (!CARPETA_DAW.exists()) {
                CARPETA_DAW.mkdir();
                alumnos = new File(CARPETA_DAW + File.separator + "alumnos.txt");
                alumnos.createNewFile();
            }
        } catch (IOException ex) {
            System.err.println("Error al iniciar los archivos.");
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        start();
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
