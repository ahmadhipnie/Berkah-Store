/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.gui;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author yogan
 */
public class RP {
    public String formatRupiah(int value){
        DecimalFormat formater = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols simbol = formater.getDecimalFormatSymbols();

        simbol.setCurrencySymbol("Rp. ");
        simbol.setMonetaryDecimalSeparator(',');// belakang sendiri pada format rupiah
        simbol.setGroupingSeparator('.');
        formater.setDecimalFormatSymbols(simbol);
        return formater.format(value);
    }
}
