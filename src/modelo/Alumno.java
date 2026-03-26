/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;
import main.main;

/**
 *
 * @author MANBIR
 */
public class Alumno {

    private String course;
    private String name;
    private String surname;
    private int age;
    private String dni;

    public Alumno(String course, String name, String surname, int age, String dni) {
        this.course = course;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.dni = dni;
    }

    public File toFile() {
        return new File(main.RUTA_ARCHIVO + File.separator + course + File.separator + "alumnos.txt");
    }

    public String fromLine() {
        return this.name + " " + surname + " " + "(" + dni + ")";
    }

    @Override
    public String toString() {
        return course + ";" + name + ";" + surname + ";" + age + ";" + dni;
    }
}
