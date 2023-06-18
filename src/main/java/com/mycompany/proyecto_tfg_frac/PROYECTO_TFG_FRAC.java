/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.proyecto_tfg_frac;

import java.util.ArrayList;

/**
 *
 * @author Maria_Villalba
 */
public class PROYECTO_TFG_FRAC {

    public static void main(String[] args) {
        /*
         Polinomio c1 = new Polinomio("abcdef", "a^2 - 3a + 2");
        Polinomio c2 = new Polinomio("abcdef", "b^2 - 3b + 2");
        Polinomio c3 = new Polinomio("abcdef", "c^2 - 3c + 2");
        Polinomio c4 = new Polinomio("abcdef", "d^2 - 3d + 2");
        Polinomio c5 = new Polinomio("abcdef", "e^2 - 3e + 2");
        Polinomio c6 = new Polinomio("abcdef", "f^2 - 3f + 2");

        
        Polinomio p12 = new Polinomio("abcdef", "a + b - 3");
        Polinomio p15 = new Polinomio("abcdef", "a + c - 3");
        Polinomio p16 = new Polinomio("abcdef", "b + d - 3");
        Polinomio p23 = new Polinomio("abcdef", "d + e - 3");
        Polinomio p24 = new Polinomio("abcdef", "c + d - 3");
        Polinomio p28 = new Polinomio("abcdef", "d + f - 3");
        Polinomio p34 = new Polinomio("abcdef", "e + f - 3");


        ArrayList<Polinomio> grafo = new ArrayList<>();
        grafo.add(c1);
        grafo.add(c2);
        grafo.add(c3);
        grafo.add(c4);
        grafo.add(c5);
        grafo.add(c6);
        
        grafo.add(p12);
        grafo.add(p15);
        grafo.add(p16);
        grafo.add(p23);
        grafo.add(p24);
        grafo.add(p28);
        grafo.add(p34);

        
         for (Polinomio p:grafo){
            System.out.println( p.leerPolinomio());
        }
         
         
       ArrayList<Polinomio> reducir1 =  c1.reducirGrobner(grafo);
       System.out.println();
       System.out.println("El espacio reducido es:");

        for (Polinomio p:reducir1){
            System.out.println( p.leerPolinomio());
        }
         
       ArrayList<Polinomio> basesGrobner2 = c1.calcularBasesGrobner(reducir1);

       System.out.println();
       System.out.println("La base obtenida G es:");

        for (Polinomio p:basesGrobner2){
            System.out.println( p.leerPolinomio());
        }
        
       ArrayList<Polinomio> Gr2 =  c1.reducirGrobner(basesGrobner2);
       System.out.println();
       System.out.println("La base reducida Gr es:");

        for (Polinomio p:Gr2){
            System.out.println( p.leerPolinomio());
        }
       */
        /*
        int[][] board_shidoku = { 
            { 0, 4, 2, 3},
            { 0, 0, 1, 0},
            { 0, 2, 0, 1},
            { 4, 0, 0, 0}
        };
        
       Sudoku sudoku = new Sudoku(board_shidoku,4);
       
        int[][] board_sudoku = { 
            { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
            { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
            { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
            { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
            { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
            { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
            { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
            { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
            { 0, 0, 0, 0, 8, 0, 0, 7, 9 }
        };
        Sudoku sudoku2 = new Sudoku(board_sudoku,9);
       
        
        
       ArrayList<Polinomio> equations = sudoku.getSudokuEquations();
       //KILLER SUDOKU
       
       Polinomio sk1 = new Polinomio("abcdefghijklmnop", "a + b - 6");       
       Polinomio sk2 = new Polinomio("abcdefghijklmnop", "c + d + g - 6");
       Polinomio sk3 = new Polinomio("abcdefghijklmnop", "e + f + i + j - 8");
       Polinomio sk4 = new Polinomio("abcdefghijklmnop", "h + l + p - 9");
       Polinomio sk5 = new Polinomio("abcdefghijklmnop", "k + m + n + o - 11");


       equations.add(sk1);
       equations.add(sk2);
       equations.add(sk3);
       equations.add(sk4);
       equations.add(sk5);
       
       
       //DIAGONAL SUDOKU
       Polinomio sk1 = sudoku.crearEcuacion_g_Shidoku(0, 10);
       Polinomio sk2 = sudoku.crearEcuacion_g_Shidoku(0, 15);
       Polinomio sk3 = sudoku.crearEcuacion_g_Shidoku(3, 9);
       Polinomio sk4 = sudoku.crearEcuacion_g_Shidoku(3, 12);


       equations.add(sk1);
       equations.add(sk2);
       equations.add(sk3);
       equations.add(sk4);
       
        for (Polinomio p:equations){
            System.out.println( p.leerPolinomio());
        }
        
    ArrayList<Polinomio> reducida1 = equations.get(0).reducirGrobner(equations);

        
     ArrayList<Polinomio> basesGrobner2 = equations.get(0).calcularBasesGrobner(reducida1);
        System.out.println("La base obtenida G es:");

        for (Polinomio p:basesGrobner2){
            System.out.println( p.leerPolinomio());
        }

       
      ArrayList<Polinomio> reducida = equations.get(0).reducirGrobner(basesGrobner2);
      
        System.out.println("");
        System.out.println("");
        System.out.println("");
        
      System.out.println("La base reducida Gr es:");
      for (Polinomio p:reducida){
           System.out.println( p.leerPolinomio());
      }
     
         */
        
        /*
        Polinomio d1 = new Polinomio("x", "x^4 + 4x^3 - 5x^2 - 22x - 24");
        Polinomio d2 = new Polinomio("x", "x^2 + 3x + 2");
        
        System.out.println("Division univariable entre " + d1.leerPolinomio() + " y " + d2.leerPolinomio());
        
        Polinomio[] res = d1.divisionUnivariable(d2);
        System.out.println("Cociente: " + res[0].leerPolinomio());
        System.out.println("Resto: " + res[1].leerPolinomio());

        
        System.out.println("El máximo común divisor entre " + d1.leerPolinomio() + " y " + d2.leerPolinomio());
        
        Polinomio res = d1.algoritmoEuclideo(d2);
        System.out.println("mcd: " + res.leerPolinomio());
*/
        /*
        Polinomio d1 = new Polinomio("xy", "x^2 + y^2 + xy");
        Polinomio d2 = new Polinomio("xy", "x + y");
        Polinomio d3 = new Polinomio("xy", "x");
        Polinomio d4 = new Polinomio("xy", "y");
        Polinomio d5 = new Polinomio("xy", "y^2");
        */
        
        Polinomio d1 = new Polinomio("xy", "x");
        Polinomio d2 = new Polinomio("xy", "x + y");
        Polinomio d3 = new Polinomio("xy", "y^2 + xy+ x^2");
        Polinomio d4 = new Polinomio("xy", "-y");
        
         ArrayList<Polinomio> conjunto = new ArrayList<>();
        conjunto.add(d1);
        conjunto.add(d2);
        conjunto.add(d3);
        conjunto.add(d4);
        ArrayList<Polinomio> basesGrobner = d1.reducirGrobner(conjunto);

       System.out.println();
       System.out.println("La base reducida R es:");

        for (Polinomio p:basesGrobner){
            System.out.println( p.leerPolinomio());
        }
        
        /*
        System.out.println("El mcm de: " + d1.leerPolinomio() + " y " + d1.leerPolinomio() + " es:");

        System.out.println(d1.calcularmcmPolinomios(d2).leerMonomio());
        
         System.out.println("El S-Polinomio de: " + d1.leerPolinomio() + " y " + d1.leerPolinomio() + " es:");
        System.out.println(d1.calcularSPolinomio(d2).leerPolinomio());

        */
    }
     
   
    
    
}
