/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.DefaultListModel;
import main.main;
import modelo.Alumno;

/**
 *
 * @author MANBIR
 */
public class ControladorAlumnos {

    static String read;
    static String registro = "";

    static FileReader fr;
    static BufferedReader br;
    static FileWriter fw;
    static BufferedWriter bw;

    public static DefaultListModel<String> updateFile(String course) {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        File studentFile = new File(main.RUTA_ARCHIVO + File.separator + course + File.separator + "alumnos.txt");

        if (!studentFile.exists()) {
            System.err.println("El archivo no existe: " + studentFile.getAbsolutePath());
            return modelo;
        }

        try {
            fr = new FileReader(studentFile);
            br = new BufferedReader(fr);
            while ((read = br.readLine()) != null) {
                String[] studentAtribut = read.split(";");
                Alumno student = new Alumno(
                        studentAtribut[0],
                        studentAtribut[1],
                        studentAtribut[2],
                        Integer.parseInt(studentAtribut[3]),
                        studentAtribut[4]
                );
                modelo.addElement(student.fromLine());
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo");
        }

        return modelo;
    }

    public static void addStudent(String course, String name, String surname, int age, String dni) {
        Alumno student = new Alumno(course, name, surname, age, dni);

        if (!checkDNI(dni, student.toFile())) {
            try {
                fw = new FileWriter(student.toFile(), true);
                bw = new BufferedWriter(fw);

                bw.write(student.toString() + System.getProperty("line.separator"));
                bw.flush();
                fw.close();
                bw.close();
            } catch (IOException E) {
                System.err.println("Error al agregar un alumno en la base de datos.");
            }
        }
    }

    public static void removeStudent(String course, String studentLine) {
        File studentFile = new File(main.RUTA_ARCHIVO + File.separator + course + File.separator + "alumnos.txt");
        String[] studentSeparatorLine = studentLine.split("\\s+");
        String dni = studentSeparatorLine[2].replace("(", "").replace(")", "");

        try {
            fr = new FileReader(studentFile);
            br = new BufferedReader(fr);
            while ((read = br.readLine()) != null) {
                if (!read.contains(dni)) {
                    registro += read + System.getProperty("line.separator");
                }
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            System.err.println("Error al leer o eliminar el alumno");
        }

        try {
            fw = new FileWriter(studentFile);
            bw = new BufferedWriter(fw);
            if (registro != null) {
                bw.write(registro);
            }
            registro = null;
            bw.flush();
            fw.close();
            bw.close();
        } catch (IOException e) {
            System.err.println("Error al subir la lista actulizada de alumnos.");
        }
    }

    public static void updateStudent(String course, String name, String surname, int age, String dni, String dniChange) {
        File studentFile = new File(main.RUTA_ARCHIVO + File.separator + course + File.separator + "alumnos.txt");
        Alumno student = new Alumno(course, name, surname, age, dniChange);

        try {
            fr = new FileReader(studentFile);
            br = new BufferedReader(fr);
            while ((read = br.readLine()) != null) {
                if (!read.contains(dni)) {
                    registro += read + System.getProperty("line.separator");
                } else {
                    registro += student.toString() + System.getProperty("line.separator");
                }
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            System.err.println("Error al leer o actualizar el alumno");
        }

        try {
            fw = new FileWriter(studentFile);
            bw = new BufferedWriter(fw);
            if (registro != null) {
                bw.write(registro);
            }
            registro = null;
            bw.flush();
            fw.close();
            bw.close();
        } catch (IOException e) {
            System.err.println("Error al subir la lista actulizada de alumnos al actualizar.");
        }
    }

    public static boolean checkDNI(String dni, File studentFile) {
        try {
            fr = new FileReader(studentFile);
            br = new BufferedReader(fr);
            while ((read = br.readLine()) != null) {
                if (read.contains(dni)) {
                    //Si existe que devuelve true
                    br.close();
                    return true;
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Error al realizar la verficacion del alumno");
            return false;
        }
        //Si no existe que devuelve false
        return false;
    }

    public static String showStudent(String course, String dni) {
        File studentFile = new File(main.RUTA_ARCHIVO + File.separator + course + File.separator + "alumnos.txt");
        String toLineStudent = null;
        try {
            fr = new FileReader(studentFile);
            br = new BufferedReader(fr);
            while ((read = br.readLine()) != null) {
                if (read.contains(dni)) {
                    toLineStudent = read;
                }
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            System.err.println("Error al intentar mostrar la lista de alumnos.");
        }
        return toLineStudent;
    }

    public static String[] searchStudent(String course, String dni) {
        File studentFile = new File(main.RUTA_ARCHIVO + File.separator + course + File.separator + "alumnos.txt");
        String[] studentAtribut = null;
        try {
            fr = new FileReader(studentFile);
            br = new BufferedReader(fr);
            while ((read = br.readLine()) != null) {
                if (read.contains(dni)) {
                    studentAtribut = read.split(";");
                }
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            System.err.println("Error al intentar mostrar el alumno.");
        }
        return studentAtribut;
    }

    public static int countStudent(String course) {
        int count = 0;
        File studentFile = new File(main.RUTA_ARCHIVO + File.separator + course + File.separator + "alumnos.txt");
        try {
            fr = new FileReader(studentFile);
            br = new BufferedReader(fr);
            while ((read = br.readLine()) != null) {
                count++;
            }
        } catch (IOException e) {
            System.err.println("Error al contar las lineas en el archivo alumnos.");
        }
        return count;
    }
}
