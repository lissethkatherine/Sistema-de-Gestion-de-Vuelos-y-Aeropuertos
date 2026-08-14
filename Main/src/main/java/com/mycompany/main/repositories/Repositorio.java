/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.repositories;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camper
 */
public class Repositorio<T> {

    private List<T> elementos = new ArrayList<>();

    public void agregar(T elemento) {
        elementos.add(elemento);
    }

    public List<T> obtenerTodos() {
        return elementos;
    }

    public int cantidad() {
        return elementos.size();
    }
}
