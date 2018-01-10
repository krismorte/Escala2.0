/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.util;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author c007329
 */
public class Tradutor {

    private final static Locale locale = new Locale("PT");
    private static DateTimeFormatter mothName = DateTimeFormatter.ofPattern("MMMM", locale);
    private static DateTimeFormatter dayInitials = DateTimeFormatter.ofPattern("eee", locale);

    public static DateTimeFormatter getMothFormater() {
        return mothName;
    }

     public static DateTimeFormatter getDayFormater() {
        return dayInitials;
    }

    
}
