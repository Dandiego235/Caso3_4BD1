/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ev.esencialgui;

/**
 *
 * @author Daniel Granados <dagranados@estudiantec.cr>
 */
import com.ev.esencialgui.data.EsencialVerdeAccess;

public class EsencialGUI {

    public static void main(String[] args) {
        EsencialVerdeAccess access = EsencialVerdeAccess.getInstance();
        access.getProductos();
        access.getCanales();
        access.getLotes();
    }
}
