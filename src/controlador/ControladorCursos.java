/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controlador;

import java.io.File;
import java.io.IOException;
import javax.swing.DefaultListModel;
import main.main;

/**
 *
 * @author MANBIR
 */
public class ControladorCursos {

    public static DefaultListModel updateFolder() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        modelo.clear();
        for (String e : main.CARPETA_DATOS.list()) {
            modelo.addElement(e);
        }
        return modelo;
    }

    public static void createCourse(String courseName) {
        try {
            String rutaCourse = main.RUTA_ARCHIVO + File.separator + courseName;
            File courseFile = new File(rutaCourse);
            if (!courseFile.exists()) {
                courseFile.mkdir();
                main.alumnos = new File(rutaCourse + File.separator + "alumnos.txt");
                main.alumnos.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error al crear el curso.");
        }
    }

    public static void removeCourse(String courseName) {
        String rutaCourse = main.RUTA_ARCHIVO + File.separator + courseName;
        File courseFile = new File(rutaCourse);
        main.alumnos = new File(rutaCourse + File.separator + "alumnos.txt");
        if (courseFile.exists()) {
            main.alumnos.delete();
            
            courseFile.delete();
        }
    }

    public static int countCourse() {
        return main.CARPETA_DATOS.list().length;
    }
}
