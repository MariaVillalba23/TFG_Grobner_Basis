package com.mycompany.proyecto_tfg_frac;

import static java.lang.Integer.min;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Maria_Villalba
 */
public class Monomio {
    public ArrayList<Integer> coeficiente; //fraccion
    public ArrayList<Integer> m_exp;
    
    
    public Monomio(ArrayList<Integer> _coef,  ArrayList<Integer> _m_exp){
    coeficiente = _coef;
    m_exp = _m_exp;
 }
    
    public String leerMonomio(){
         String res;
         boolean vacio = true;
         for(int i : this.m_exp){
             if (i!=0) vacio = false;
        }
         
         if (this.coeficiente.get(1) == 1){
             res = String.valueOf(this.coeficiente.get(0));
         }else{
            res = String.valueOf(this.coeficiente.get(0)) + "/" + String.valueOf(this.coeficiente.get(1));
         }
         
        if (!vacio){
            for (int i=0; i<this.m_exp.size(); i++) {
                if (this.m_exp.get(i) != 0){
                    res = res + "x_" + String.valueOf((i+1));
                    if (this.m_exp.get(i) > 1){
                        res = res + "^" + String.valueOf(this.m_exp.get(i));
                    }
                }
            }
        }
        return res;
    }  
     
    public int exponenteMonomio(){
        int res = 0;
        for (Integer m_exp1 : this.m_exp) {
            res += m_exp1;
        }
        return res;
    }
     
      //DEGLEX ORDER
    public Monomio monomioMayor_deg (Monomio m1) {
    int e1 = m1.exponenteMonomio();
    int e2 = this.exponenteMonomio();
    if (e1 > e2) {
        return m1;
    } else if (e2 > e1) {
        return this;
    } else { // si tienen el mismo grado
        for (int i = 0; i < min(m1.m_exp.size(), this.m_exp.size()); i++) {
            int exp1 = m1.m_exp.get(i);
            int exp2 = this.m_exp.get(i);
            if (exp1 > exp2) {
                return m1;
            } else if (exp2 > exp1) {
                return this;
            }
        }
        // si llegamos aquí, todos los exponentes son iguales, por lo que devolvemos uno cualquiera
        return this;
    }
}
    
      //LEX ORDER
    public Monomio monomioMayor(Monomio m1){
      //Vamos recorriendo los exponentes de cada variable hasta encontrar uno menor
      int i = 0;
      
      while (i<=m1.m_exp.size()){
          if (m1.m_exp.get(i) > this.m_exp.get(i)){
              return m1;
          }else if(this.m_exp.get(i) > m1.m_exp.get(i)){
              return this;
          }
          i++;
      }
      
      return this;
  }
    
     // Función para calcular el máximo común divisor (GCD)
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
  
    //SUMA FRACCION
    public ArrayList<Integer> sumaFraccion (ArrayList<Integer> a, ArrayList<Integer> b){
        ArrayList<Integer> resultado = new ArrayList<>();
        
        int numerador1 = a.get(0);
        int denominador1 = a.get(1);
        
        int numerador2 = b.get(0);
        int denominador2 = b.get(1);
        
        int nuevoDenominador = denominador1 * denominador2;
        int nuevoNumerador = (numerador1 * denominador2) + (numerador2 * denominador1);
        
        // Simplificar la fracción resultante
        int gcd = gcd(nuevoNumerador, nuevoDenominador);
        
        nuevoNumerador /= gcd;
        nuevoDenominador /= gcd;
        
        resultado.add(nuevoNumerador);
        resultado.add(nuevoDenominador);
        
        return resultado;
    }
     
    //RESTA FRACCION     
    public ArrayList<Integer> restaFraccion(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> resultado = new ArrayList<>();
        
        int numerador1 = a.get(0);
        int denominador1 = a.get(1);
        
        int numerador2 = b.get(0);
        int denominador2 = b.get(1);
        
        int nuevoDenominador = denominador1 * denominador2;
        int nuevoNumerador = (numerador1 * denominador2) - (numerador2 * denominador1);
        
        // Simplificar la fracción resultante
        int gcd = gcd(nuevoNumerador, nuevoDenominador);
        
        nuevoNumerador /= gcd;
        nuevoDenominador /= gcd;
        
        resultado.add(nuevoNumerador);
        resultado.add(nuevoDenominador);
        
        return resultado;
    }
      
    //MULTIPLICACIÓN FRACCION
    public ArrayList<Integer> multiplicacionFraccion(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> resultado = new ArrayList<>();
        
        int numerador1 = a.get(0);
        int denominador1 = a.get(1);
        
        int numerador2 = b.get(0);
        int denominador2 = b.get(1);
        
        int nuevoNumerador = numerador1 * numerador2;
        int nuevoDenominador = denominador1 * denominador2;
        
        // Simplificar la fracción resultante
        int gcd = gcd(nuevoNumerador, nuevoDenominador);
        
        nuevoNumerador /= gcd;
        nuevoDenominador /= gcd;
        
        resultado.add(nuevoNumerador);
        resultado.add(nuevoDenominador);
        
        return resultado;
    }
     
    //DIVISIÓN FRACCION
    public ArrayList<Integer> divisionFraccion(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> resultado = new ArrayList<>();
        
        int numerador1 = a.get(0);
        int denominador1 = a.get(1);
        
        int numerador2 = b.get(0);
        int denominador2 = b.get(1);
        
        int nuevoNumerador = numerador1 * denominador2;
        int nuevoDenominador = denominador1 * numerador2;
            
        // Simplificar la fracción resultante
        int gcd = gcd(nuevoNumerador, nuevoDenominador);
        
        nuevoNumerador /= gcd;
        nuevoDenominador /= gcd;
        
        resultado.add(nuevoNumerador);
        resultado.add(nuevoDenominador);
        
        return resultado;
    }

    public Monomio sumaMonomio(Monomio m){
        ArrayList<Integer> coef = sumaFraccion(m.coeficiente,this.coeficiente);
        Monomio resultado = new Monomio(coef, this.m_exp);
        return resultado;
    }

    public Monomio restaMonomio(Monomio m){
        ArrayList<Integer> coef = restaFraccion(m.coeficiente,this.coeficiente);
        Monomio resultado = new Monomio(coef, this.m_exp);
        return resultado;
    }
    
    public Monomio divisionMonomios(Monomio m){
        ArrayList<Integer> coef = divisionFraccion(this.coeficiente,m.coeficiente);
            ArrayList<Integer> res_esp = new ArrayList<>();
            for (int i=0; i < m.m_exp.size();i++){
                res_esp.add(this.m_exp.get(i) - m.m_exp.get(i));
            }
            Monomio resultado = new Monomio(coef, res_esp);
            
            return resultado;
         }
         
    public Monomio multiplicacionMonomios(Monomio m){
        ArrayList<Integer> coef = multiplicacionFraccion(this.coeficiente,m.coeficiente);
        ArrayList<Integer> res_esp = new ArrayList<>();
        for (int i=0; i < m.m_exp.size();i++){
           res_esp.add(this.m_exp.get(i) + m.m_exp.get(i));
        }
       Monomio resultado = new Monomio(coef, res_esp);
       return resultado;
    }
    
    //comprobar si un monomio divide a otro
    public boolean esDivisible (Monomio m){
        boolean divisible = true;

        //restamos todas las variables de m a this y si es >= 0 es divisible
        for (int i = 0 ; i < this.m_exp.size(); i++){
            if (this.m_exp.get(i) - m.m_exp.get(i) < 0) {
                divisible = false;
            }
        }
        return divisible;
    }
      
      
}
