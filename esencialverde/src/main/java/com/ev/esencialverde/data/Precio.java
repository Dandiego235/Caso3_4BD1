/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ev.esencialverde.data;

import java.util.HashMap;

/**
 *
 * @author dandi
 */
public class Precio implements Comparable<Precio>{
      private HashMap<Integer, Integer> cantidadPorLotes;
      private int cantidadTotal;
      private float precioProd;
      private Producto productoPrecio;
      
      public Precio(float pPrecioProd, Producto pProducto) {
            cantidadPorLotes = new HashMap<>();
            cantidadTotal = 0;
            precioProd = pPrecioProd;
            productoPrecio = pProducto;
      }

      public HashMap<Integer, Integer> getCantidadPorLotes() {
            return cantidadPorLotes;
      }

      public void setCantidadPorLotes(HashMap<Integer, Integer> cantidadPorLotes) {
            this.cantidadPorLotes = cantidadPorLotes;
      }
      
      public void insertCantidadLote(int pLote, int pCantidad){
            cantidadPorLotes.put(pLote, pCantidad);
            cantidadTotal += pCantidad;
      }

      public int getCantidadTotal() {
            return cantidadTotal;
      }
      
      @Override public int compareTo(Precio pPrecio) {
        // if the string are not equal
        if (this.precioProd  == pPrecio.precioProd) {
            return 0;
        } else if (this.precioProd  > pPrecio.precioProd) {
              return 1;
        }  else {
            return -1;
        }
    }

      public float getPrecioProd() {
            return precioProd;
      }

      public void setCantidadTotal(int cantidadTotal) {
            this.cantidadTotal = cantidadTotal;
      }

      public void setPrecioProd(float precioProd) {
            this.precioProd = precioProd;
      }
      
      public void modifyCantidad(int pCantidad) {
            cantidadTotal += pCantidad; 
      }     
}