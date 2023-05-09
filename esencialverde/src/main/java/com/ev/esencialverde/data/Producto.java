/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ev.esencialverde.data;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author dandi
 */
public class Producto {
      private int id;
      private String nombre;
      private ArrayList<Precio> precios;
      
      public Producto(int pId, String pNombre){
            id = pId;
            pNombre = pNombre;
            precios = new ArrayList<>();
      }

      public int getId() {
            return id;
      }

      public String getNombre() {
            return nombre;
      }

      public ArrayList<Precio> getPrecios() {
            return precios;
      }

      public void setId(int id) {
            this.id = id;
      }

      public void setNombre(String nombre) {
            this.nombre = nombre;
      }

      public void setPrecios(ArrayList<Precio> precios) {
            this.precios = precios;
      }
      
      public void insertPrecio(Precio pPrecio){
            precios.add(pPrecio);
            Collections.sort(precios);
      }
}
