/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ev.esencialverde.gui;

import com.ev.esencialverde.data.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author dandi
 */
public class RegistrarVenta extends javax.swing.JFrame {
      private int productoId;
   private String productoNombre;
   private int prodContratoId;
   private int loteId;
   private Producto productoSelected;
   private HashMap<String,Producto> productosMap;
   private HashMap<String, Canal> canalesMap;
   private HashMap<Float, Precio> preciosMap;
   private Canal canalSelected;
   private Precio precioSelected;
   private DefaultListModel listaProductosModel;
   private int cantidad;
   private HashMap<String, ObjetoLista> objetosListaMap; 
   private float montoTotal;
   
   private void readProductos(){
       EsencialVerdeAccess access = EsencialVerdeAccess.getInstance();
       ResultSet productos = access.getProductos();
       try {
            while (productos.next()){
                Producto producto = new Producto(Integer.parseInt(productos.getString("productoId")), productos.getString("nombreBase"));
                productosMap.put(producto.getNombre(), producto);                
            }
       } catch (Exception ex){
           ex.printStackTrace();
       }
   }
   
   private void readCanales(){
       EsencialVerdeAccess access = EsencialVerdeAccess.getInstance();
       ResultSet canales = access.getCanales();
       try {
            while (canales.next()){
                Canal canal = new Canal(Integer.parseInt(canales.getString("canalId")), canales.getString("nombre"));
               canalesMap.put(canal.getNombre(), canal);
            }
       } catch (Exception ex){
           ex.printStackTrace();
       }
   }
   
   private void readLotes(){
       EsencialVerdeAccess access = EsencialVerdeAccess.getInstance();
       ResultSet lotes = access.getLotes();
       try {
            while (lotes.next()){
                Lote lote = new Lote(Integer.parseInt(lotes.getString("loteId")) , lotes.getString("fecha") , lotes.getString("productoNombre"), Integer.parseInt(lotes.getString("prodContratoId")), Integer.parseInt(lotes.getString("plantaId")), (int) Float.parseFloat(lotes.getString("cantidad")), Float.parseFloat(lotes.getString("costoProduccion")),Float.parseFloat(lotes.getString("precio"))); // Crea el lote
                try { // busca a ver si ya existe ese precio para el producto del lote. Si sí, añade la cantidad del lote al precio.
                      productosMap.get(lote.getProductoNombre()).getPrecios().get(lote.getPrecio()).insertCantidadLote(lote, lote.getCantidad());
                } catch (Exception e) { // Si el precio no existe, lo añade y añade el lote y la cantidad.
                      productosMap.get(lote.getProductoNombre()).insertPrecio(lote.getPrecio(), lote, lote.getCantidad());
                }
            }
       } catch (Exception ex){
           ex.printStackTrace();
       }
   }
   
   
    private void fillComboProductos(){
          comboProductos.removeAllItems();
          productosMap.forEach((key, value) -> {
                comboProductos.addItem((String) key);
        });
    }  
    
    private void fillComboCanales(){
          comboCanales.removeAllItems();
          canalesMap.forEach((key, value) -> {
                comboCanales.addItem((String) key);
        });
    }
    
    private void fillComboPrecios(){
          if (productoSelected == null){
                return;
          }
          comboPrecios.removeAllItems();
          preciosMap = productoSelected.getPrecios();
          preciosMap.forEach((key, value) -> {
                comboPrecios.addItem(Float.toString(key));
        });
    }
      /**
       * Creates new form RegistrarVenta
       */
      public RegistrarVenta() {
            initComponents();
            productosMap = new HashMap<>();
        canalesMap = new HashMap<>();
        cantidadText.setInputVerifier(new CantidadInputVerifier());
        listaProductosModel = new DefaultListModel();
        listaProductos.setModel(listaProductosModel);
        listaProductosModel.removeAllElements();
        objetosListaMap = new HashMap<>();
        montoTotal = 0;
        readProductos();
        readCanales();
        readLotes();
        fillComboProductos();
        fillComboCanales();
        comboCanales.setSelectedItem(null);
        comboProductos.setSelectedItem(null);
        comboPrecios.setSelectedItem(null);
        cantidadLoteText.setText("0");
      }

      /**
       * This method is called from within the constructor to initialize the
       * form. WARNING: Do NOT modify this code. The content of this method is
       * always regenerated by the Form Editor.
       */
      @SuppressWarnings("unchecked")
      // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
      private void initComponents() {

            jCheckBox1 = new javax.swing.JCheckBox();
            jLabel1 = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            addButton = new javax.swing.JButton();
            jLabel6 = new javax.swing.JLabel();
            jScrollPane1 = new javax.swing.JScrollPane();
            listaProductos = new javax.swing.JList<>();
            jLabel7 = new javax.swing.JLabel();
            montoLabel = new javax.swing.JLabel();
            jButton2 = new javax.swing.JButton();
            jLabel9 = new javax.swing.JLabel();
            cantidadLoteText = new javax.swing.JLabel();
            comboPrecios = new javax.swing.JComboBox<>();
            comboCanales = new javax.swing.JComboBox<>();
            comboProductos = new javax.swing.JComboBox<>();
            cantidadText = new javax.swing.JTextField();
            registrarButton = new javax.swing.JButton();
            cancelarButton = new javax.swing.JButton();

            jCheckBox1.setText("jCheckBox1");

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel1.setText("Registrar Venta");
            getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 12, -1, -1));

            jLabel2.setText("Canal");
            getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 68, -1, -1));

            jLabel3.setText("Producto");
            getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 123, -1, -1));

            jLabel4.setText("Precio");
            getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

            jLabel5.setText("Cantidad");
            getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 238, -1, -1));

            addButton.setText("Añadir");
            addButton.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        addButtonActionPerformed(evt);
                  }
            });
            getContentPane().add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 279, -1, -1));

            jLabel6.setText("Desglose");
            getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 322, -1, -1));

            jScrollPane1.setViewportView(listaProductos);

            getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 322, -1, -1));

            jLabel7.setText("Monto Total");
            getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 474, -1, -1));

            montoLabel.setText("0");
            getContentPane().add(montoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 474, 59, -1));

            jButton2.setText("Borrar");
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton2ActionPerformed(evt);
                  }
            });
            getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 370, -1, -1));

            jLabel9.setText("Cantidad  disponible:");
            getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, -1));

            cantidadLoteText.setText("0");
            getContentPane().add(cantidadLoteText, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 180, 90, -1));

            comboPrecios.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        comboPreciosActionPerformed(evt);
                  }
            });
            getContentPane().add(comboPrecios, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 200, -1));

            comboCanales.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        comboCanalesActionPerformed(evt);
                  }
            });
            getContentPane().add(comboCanales, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 200, -1));

            comboProductos.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        comboProductosActionPerformed(evt);
                  }
            });
            getContentPane().add(comboProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 200, -1));

            cantidadText.setText(" ");
            getContentPane().add(cantidadText, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 200, -1));

            registrarButton.setText("Registrar Venta");
            registrarButton.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        registrarButtonActionPerformed(evt);
                  }
            });
            getContentPane().add(registrarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 510, -1, -1));

            cancelarButton.setText("Cancelar");
            cancelarButton.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cancelarButtonActionPerformed(evt);
                  }
            });
            getContentPane().add(cancelarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 510, -1, -1));

            pack();
      }// </editor-fold>//GEN-END:initComponents

      private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_jButton2ActionPerformed

      private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
            // TODO add your handling code here:
            cantidad = -1;
        try {
              cantidad = Integer.valueOf(cantidadLoteText.getText());
        }
        catch (Exception e) {
              return;
        }
        if (canalSelected == null || precioSelected == null || productoSelected == null || cantidad == -1) {
              return;
        }
        String stringLista = productoSelected.getNombre() + " " + cantidad + " " + (cantidad * precioSelected.getPrecioProd());
        objetosListaMap.put(stringLista, new ObjetoLista(productoSelected, cantidad, (cantidad * precioSelected.getPrecioProd())));
        listaProductosModel.addElement(stringLista);     
        montoTotal += (cantidad * precioSelected.getPrecioProd());
        montoLabel.setText(Float.toString(montoTotal));   
        precioSelected.modifyCantidad(-(cantidad));
        cantidadLoteText.setText(Integer.toString(precioSelected.getCantidadTotal()));
      }//GEN-LAST:event_addButtonActionPerformed

      private void registrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarButtonActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_registrarButtonActionPerformed

      private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_cancelarButtonActionPerformed

      private void comboCanalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCanalesActionPerformed
            // TODO add your handling code here:
            canalSelected = canalesMap.get((String) comboCanales.getSelectedItem());
      }//GEN-LAST:event_comboCanalesActionPerformed

      private void comboProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProductosActionPerformed
            // TODO add your handling code here:
            productoSelected = productosMap.get((String) comboProductos.getSelectedItem());
        fillComboPrecios();
      }//GEN-LAST:event_comboProductosActionPerformed

      private void comboPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPreciosActionPerformed
            // TODO add your handling code here:
            if (productoSelected == null) {
                  return;
            }
            if (comboPrecios.getSelectedItem() == null){
                  return;
            }
            precioSelected = preciosMap.get(Float.parseFloat((String) comboPrecios.getSelectedItem()));
            if (precioSelected == null) {
                  return;
            }
            cantidadLoteText.setText(Integer.toString(precioSelected.getCantidadTotal()));
      }//GEN-LAST:event_comboPreciosActionPerformed

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
                  java.util.logging.Logger.getLogger(RegistrarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(RegistrarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(RegistrarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(RegistrarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                        new RegistrarVenta().setVisible(true);
                  }
            });
      }

      // Variables declaration - do not modify//GEN-BEGIN:variables
      private javax.swing.JButton addButton;
      private javax.swing.JButton cancelarButton;
      private javax.swing.JLabel cantidadLoteText;
      private javax.swing.JTextField cantidadText;
      private javax.swing.JComboBox<String> comboCanales;
      private javax.swing.JComboBox<String> comboPrecios;
      private javax.swing.JComboBox<String> comboProductos;
      private javax.swing.JButton jButton2;
      private javax.swing.JCheckBox jCheckBox1;
      private javax.swing.JLabel jLabel1;
      private javax.swing.JLabel jLabel2;
      private javax.swing.JLabel jLabel3;
      private javax.swing.JLabel jLabel4;
      private javax.swing.JLabel jLabel5;
      private javax.swing.JLabel jLabel6;
      private javax.swing.JLabel jLabel7;
      private javax.swing.JLabel jLabel9;
      private javax.swing.JScrollPane jScrollPane1;
      private javax.swing.JList<String> listaProductos;
      private javax.swing.JLabel montoLabel;
      private javax.swing.JButton registrarButton;
      // End of variables declaration//GEN-END:variables
}
