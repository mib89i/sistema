/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.utilitarios;

import java.text.Normalizer;

public class AnaliseString {

    public static String normalizeLower(String value) {

        value = Normalizer.normalize(value, Normalizer.Form.NFD);
        value = value.toLowerCase().replaceAll("[^\\p{ASCII}]", "");

        return value;
    }
    
    public static String normalizeUpper(String value) {

        value = Normalizer.normalize(value, Normalizer.Form.NFD);
        value = value.toUpperCase().replaceAll("[^\\p{ASCII}]", "");

        return value;
    }

}
