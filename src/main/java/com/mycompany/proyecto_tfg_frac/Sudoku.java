/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_tfg_frac;

import static java.lang.Math.sqrt;
import java.util.ArrayList;

/**
 *
 * @author Maria_Villalba
 */
public class Sudoku {
        public int[][] board;
    public int tamaño;

  public Sudoku(int[][] _board, int _tamaño){
      board = _board;
      tamaño = _tamaño;
  }

  public ArrayList<Polinomio> getSudokuEquations(){
      int contador = 0;
      //Los polinomios van a tener 81 variables
     ArrayList<Polinomio> ecuaciones_v = new ArrayList<>();
     ArrayList<Polinomio> ecuaciones_f = new ArrayList<>();
     ArrayList<Polinomio> ecuaciones_g = new ArrayList<>();


     for (int i = 0; i < board.length; i++) {
     for (int j = 0; j < board[i].length; j++) {
         int elementoActual = board[i][j];
        //si es distinto de 0 le añadimos la ecuacion (x_i-c)
        if (elementoActual != 0){
            Polinomio p = crearEcuacionValor(elementoActual, contador);
            ecuaciones_v.add(p);
        }else{
            //SUDOKU
            if(tamaño == 9){
               Polinomio f = crearEcuacion_f(contador);
               ecuaciones_f.add(f);
            //SHIDOKU
            }else if(tamaño == 4){
               Polinomio f = crearEcuacion_f_Shidoku(contador);
               ecuaciones_f.add(f);
            }
        }
        
        contador++;
    }
}
     ArrayList<ArrayList<Integer>> pares =  new ArrayList<ArrayList<Integer>>();

     
        int contador_fila = 0;
        
        for (int fil=1; fil<=tamaño; fil++){
            for (int i=contador_fila; i<(contador_fila+(tamaño-1)) ;i++){
                for (int j=i+1; j<contador_fila+(tamaño);j++){
                    if(i<j){
                       Polinomio g = new Polinomio();
                         if(tamaño == 9){
                             g = crearEcuacion_g(i,j);
                         }
                         if(tamaño == 4){
                             g = crearEcuacion_g_Shidoku(i,j);
                         }
                     ecuaciones_g.add(g);
                     //Añadimos el par
                     ArrayList<Integer> par1 = new ArrayList<Integer>();
                     par1.add(i); 
                     par1.add(j); 
                     pares.add(par1);
                    }
                }
            }
            contador_fila = contador_fila + tamaño;
        }
        
        //columnas. vemos si el resto de dividir i y j entre 4 es igual
        for (int i=0; i<((tamaño*tamaño)-1) ;i++){
            for (int j=i+1; j<(tamaño*tamaño);j++){
                if(i<j & i%tamaño == j%tamaño){
                   Polinomio g = new Polinomio();
                    if(tamaño == 9){
                        g = crearEcuacion_g(i,j);
                    }
                    if(tamaño == 4){
                        g = crearEcuacion_g_Shidoku(i,j);
                    }
                 ecuaciones_g.add(g);
             //Añadimos el par
                ArrayList<Integer> par1 = new ArrayList<Integer>();
                par1.add(i); 
                par1.add(j);
                pares.add(par1);
                }
            }
        }
        
        //cajitas
        for (int i = 0; i < (tamaño*tamaño)-1; i++) {
            for (int j = i + 1; j < tamaño*tamaño; j++) {
              if (enMismoCuadrado(i, j)) {
                  
                  //Comprobamos si ya está
                  ArrayList<Integer> par1 = new ArrayList<Integer>();
                  par1.add(i); 
                  par1.add(j); 
                  ArrayList<Integer> par2 = new ArrayList<Integer>();
                  par2.add(j);
                  par2.add(i);
                  
                if(!pares.contains(par1) & !pares.contains(par2)) {
                 pares.add(par1);
                 Polinomio g = new Polinomio();
                if(tamaño == 9){
                    g = crearEcuacion_g(i,j);
                }
                if(tamaño == 4){
                    g = crearEcuacion_g_Shidoku(i,j);
                }
                 ecuaciones_g.add(g); 
                }
              }
            }
          }

    

     ArrayList<Polinomio> ecuaciones = new ArrayList<>();
     ecuaciones.addAll(ecuaciones_v);
     ecuaciones.addAll(ecuaciones_f);
     ecuaciones.addAll(ecuaciones_g);
     return ecuaciones;

  }
  
  public boolean enMismoCuadrado(int i, int j){
      
    int i1 = i%tamaño;
    int j1 = j%tamaño;
    int i2 = i/tamaño;
    int j2 = j/tamaño;
    int modi1 = (int) (i1/ sqrt(tamaño));
    int modj1 = (int) (j1/ sqrt(tamaño));
    int modi2 = (int) (i2/ sqrt(tamaño));
    int modj2 = (int) (j2/ sqrt(tamaño));
  // Verificamos si las variables están en el mismo cuadrado
        return (modi1 == modj1) & (modi2 == modj2);
  }
  
  public Polinomio crearEcuacionValor (int valor, int posicion){
      //(x_i-c)
      
      //Creamos dos monomios con 81 variables
      ArrayList<Integer> exponentes_m1 = new ArrayList();
      ArrayList<Integer> exponentes_m2 = new ArrayList();

      for(int i=0; i< (tamaño*tamaño); i++){
          exponentes_m2.add(0);
          if (i==posicion){
              exponentes_m1.add( 1);
          }else{
              exponentes_m1.add( 0);
          }
      }
      
      ArrayList<Integer> coef = new ArrayList<>();
      coef.add(1);
      coef.add(1);
      
      ArrayList<Integer> _valor = new ArrayList<>();
      _valor.add(-valor);
      _valor.add(1);
      
      Monomio m1 = new Monomio(coef, exponentes_m1);
      Monomio m2 = new Monomio(_valor, exponentes_m2);
      ArrayList<Monomio> m = new ArrayList();
      m.add(m1);
      m.add(m2);
      
      Polinomio p = new Polinomio( m);
      
      return p;
  }
  
  //f(xi) = xi^9 - 45xi^8 + 855xi^7 - 9450xi^6 + 63273xi^5 - 269325xi^4 + 723680xi^3 - 1164240xi^2 + 984960xi – 362880
  public Polinomio crearEcuacion_f (int posicion){
      ArrayList<Integer> exponentes_m9 = new ArrayList();
      ArrayList<Integer> exponentes_m8 = new ArrayList();
      ArrayList<Integer> exponentes_m7 = new ArrayList();
      ArrayList<Integer> exponentes_m6 = new ArrayList();
      ArrayList<Integer> exponentes_m5 = new ArrayList();
      ArrayList<Integer> exponentes_m4 = new ArrayList();
      ArrayList<Integer> exponentes_m3 = new ArrayList();
      ArrayList<Integer> exponentes_m2 = new ArrayList();
      ArrayList<Integer> exponentes_m1 = new ArrayList();
      ArrayList<Integer> exponentes_m0 = new ArrayList();
      
       for(int i=0; i< (tamaño*tamaño); i++){
          exponentes_m0.add(i, 0);
          if (i==posicion){
              exponentes_m9.add( 9);
              exponentes_m8.add(8);
              exponentes_m7.add( 7);
              exponentes_m6.add( 6);
              exponentes_m5.add( 5);
              exponentes_m4.add( 4);
              exponentes_m3.add( 3);
              exponentes_m2.add( 2);
              exponentes_m1.add(1);
          }else{
              exponentes_m9.add(0);
              exponentes_m8.add( 0);
              exponentes_m7.add(0);
              exponentes_m6.add( 0);
              exponentes_m5.add(0);
              exponentes_m4.add( 0);
              exponentes_m3.add( 0);
              exponentes_m2.add( 0);
              exponentes_m1.add( 0);
          }
      }
       
      ArrayList<Monomio> m = new ArrayList<>();
      
      ArrayList<Integer> coef9 = new ArrayList<>();
      coef9.add(1);
      coef9.add(1);
      
      Monomio m9 = new Monomio(coef9, exponentes_m9);
      m.add(m9);
      
      ArrayList<Integer> coef8 = new ArrayList<>();
      coef8.add(-45);
      coef8.add(1);
      Monomio m8 = new Monomio(coef8, exponentes_m8);
      m.add(m8);

      ArrayList<Integer> coef7 = new ArrayList<>();
       coef7.add(855);
       coef7.add(1);
       Monomio m7 = new Monomio(coef7, exponentes_m7);
       m.add(m7);
       
        ArrayList<Integer> coef6 = new ArrayList<>();
        coef6.add(-9450);
        coef6.add(1);

        Monomio m6 = new Monomio(coef6, exponentes_m6);
        m.add(m6);

        ArrayList<Integer> coef5 = new ArrayList<>();
        coef5.add(63273);
        coef5.add(1);

        Monomio m5 = new Monomio(coef5, exponentes_m5);
        m.add(m5);

        ArrayList<Integer> coef4 = new ArrayList<>();
        coef4.add(-269325);
        coef4.add(1);

        Monomio m4 = new Monomio(coef4, exponentes_m4);
        m.add(m4);

        ArrayList<Integer> coef3 = new ArrayList<>();
        coef3.add(723680);
        coef3.add(1);

        Monomio m3 = new Monomio(coef3, exponentes_m3);
        m.add(m3);

        ArrayList<Integer> coef2 = new ArrayList<>();
        coef2.add(-1164240);
        coef2.add(1);

        Monomio m2 = new Monomio(coef2, exponentes_m2);
        m.add(m2);

        ArrayList<Integer> coef1 = new ArrayList<>();
        coef1.add(984960);
        coef1.add(1);

        Monomio m1 = new Monomio(coef1, exponentes_m1);
        m.add(m1);

        ArrayList<Integer> coef0 = new ArrayList<>();
        coef0.add(-362880);
        coef0.add(1);

        Monomio m0 = new Monomio(coef0, exponentes_m0);
        m.add(m0);
      
      Polinomio p = new Polinomio(m);
      
      return p;
      
  }
  
    //f(xi) = x^4 - 10x^3 + 35x^2 - 50x + 24
  public Polinomio crearEcuacion_f_Shidoku (int posicion){
      ArrayList<Integer> exponentes_m4 = new ArrayList();
      ArrayList<Integer> exponentes_m3 = new ArrayList();
      ArrayList<Integer> exponentes_m2 = new ArrayList();
      ArrayList<Integer> exponentes_m1 = new ArrayList();
      ArrayList<Integer> exponentes_m0 = new ArrayList();
      
       for(int i=0; i<(tamaño*tamaño); i++){
          exponentes_m0.add(i, 0);
          if (i==posicion){
              exponentes_m4.add( 4);
              exponentes_m3.add( 3);
              exponentes_m2.add( 2);
              exponentes_m1.add(1);
          }else{
              exponentes_m4.add( 0);
              exponentes_m3.add( 0);
              exponentes_m2.add( 0);
              exponentes_m1.add( 0);
          }
      }
       
        ArrayList<Monomio> m = new ArrayList<>();
        ArrayList<Integer> coef4 = new ArrayList<>();
        coef4.add(1);
        coef4.add(1);

        Monomio m4 = new Monomio(coef4, exponentes_m4);
        m.add(m4);

        ArrayList<Integer> coef3 = new ArrayList<>();
        coef3.add(-10);
        coef3.add(1);

        Monomio m3 = new Monomio(coef3, exponentes_m3);
        m.add(m3);

        ArrayList<Integer> coef2 = new ArrayList<>();
        coef2.add(35);
        coef2.add(1);

        Monomio m2 = new Monomio(coef2, exponentes_m2);
        m.add(m2);

        ArrayList<Integer> coef1 = new ArrayList<>();
        coef1.add(-50);
        coef1.add(1);

        Monomio m1 = new Monomio(coef1, exponentes_m1);
        m.add(m1);

        ArrayList<Integer> coef0 = new ArrayList<>();
        coef0.add(24);
        coef0.add(1);

        Monomio m0 = new Monomio(coef0, exponentes_m0);
        m.add(m0);

      
      Polinomio p = new Polinomio(m);
      
      return p;
      
  }
  
  //g(xi, xj) = 
   public Polinomio crearEcuacion_g (int i, int j){
      ArrayList<Integer> e_xi_m8 = new ArrayList();
      ArrayList<Integer> e_xij_m71 = new ArrayList();
      ArrayList<Integer> e_xij_m62 = new ArrayList();
      ArrayList<Integer> e_xij_m53 = new ArrayList();
      ArrayList<Integer> e_xij_m44 = new ArrayList();
      ArrayList<Integer> e_xij_m35 = new ArrayList();
      ArrayList<Integer> e_xij_m26 = new ArrayList();
      ArrayList<Integer> e_xij_m17 = new ArrayList();
      ArrayList<Integer> e_xj_m8 = new ArrayList();
      
       for(int e = 0; e < 81; e++){
          if (e==i){
              e_xj_m8.add(0);
              e_xi_m8.add( 8);
              e_xij_m71.add( 7);
              e_xij_m62.add( 6);
              e_xij_m53.add( 5);
              e_xij_m44.add( 4);
              e_xij_m35.add( 3);
              e_xij_m26.add( 2);
              e_xij_m17.add(1);
          }else if (e==j){
              e_xj_m8.add(8);
              e_xi_m8.add(0);
              e_xij_m71.add(1);
              e_xij_m62.add( 2);
              e_xij_m53.add( 3);
              e_xij_m44.add(4);
              e_xij_m35.add( 5);
              e_xij_m26.add( 6);
              e_xij_m17.add( 7);
          }else{
              e_xj_m8.add(0);
              e_xi_m8.add(0);
              e_xij_m71.add( 0);
              e_xij_m62.add(0);
              e_xij_m53.add(0);
              e_xij_m44.add(0);
              e_xij_m35.add( 0);
              e_xij_m26.add( 0);
              e_xij_m17.add( 0);
          }
      }
       
        ArrayList<Monomio> m = new ArrayList<>();
        ArrayList<Integer> coef_xj_m8 = new ArrayList<>();
        coef_xj_m8.add(1);
        coef_xj_m8.add(1);

        Monomio xj_m8 = new Monomio(coef_xj_m8, e_xj_m8);
        m.add(xj_m8);

        ArrayList<Integer> coef_xi_m8 = new ArrayList<>();
        coef_xi_m8.add(1);
        coef_xi_m8.add(1);

        Monomio xi_m8 = new Monomio(coef_xi_m8, e_xi_m8);
        m.add(xi_m8);

        ArrayList<Integer> coef_xij_m71 = new ArrayList<>();
        coef_xij_m71.add(1);
        coef_xij_m71.add(1);

        Monomio xij_m71 = new Monomio(coef_xij_m71, e_xij_m71);
        m.add(xij_m71);

        ArrayList<Integer> coef_xij_m62 = new ArrayList<>();
        coef_xij_m62.add(-38);
        coef_xij_m62.add(1);

        Monomio xij_m62 = new Monomio(coef_xij_m62, e_xij_m62);
        m.add(xij_m62);

        ArrayList<Integer> coef_xij_m53 = new ArrayList<>();
        coef_xij_m53.add(-253);
        coef_xij_m53.add(1);

        Monomio xij_m53 = new Monomio(coef_xij_m53, e_xij_m53);
        m.add(xij_m53);

        ArrayList<Integer> coef_xij_m44 = new ArrayList<>();
        coef_xij_m44.add(1725);
        coef_xij_m44.add(1);

        Monomio xij_m44 = new Monomio(coef_xij_m44, e_xij_m44);
        m.add(xij_m44);

        ArrayList<Integer> coef_xij_m35 = new ArrayList<>();
        coef_xij_m35.add(253);
        coef_xij_m35.add(1);

        Monomio xij_m35 = new Monomio(coef_xij_m35, e_xij_m35);
        m.add(xij_m35);

        ArrayList<Integer> coef_xij_m26 = new ArrayList<>();
        coef_xij_m26.add(-38);
        coef_xij_m26.add(1);

        Monomio xij_m26 = new Monomio(coef_xij_m26, e_xij_m26);
        m.add(xij_m26);

        ArrayList<Integer> coef_xij_m17 = new ArrayList<>();
        coef_xij_m17.add(1);
        coef_xij_m17.add(1);

        Monomio xij_m17 = new Monomio(coef_xij_m17, e_xij_m17);
        m.add(xij_m17);

      
      Polinomio p = new Polinomio(m);
      
      return p;
      
  }
   
   //xi^3 + xj^3 + xi^2xj + xixj^2 -10xi^2 - 10xj^2 - 10xixj + 35xi + 35xj - 50
   public Polinomio crearEcuacion_g_Shidoku (int i, int j){
      
      ArrayList<Integer> e_xi_m3 = new ArrayList();
      ArrayList<Integer> e_xj_m3 = new ArrayList();
      ArrayList<Integer> e_xij_m21 = new ArrayList();
      ArrayList<Integer> e_xij_m12 = new ArrayList();
      ArrayList<Integer> e_xi_m2 = new ArrayList();
      ArrayList<Integer> e_xj_m2 = new ArrayList();
      ArrayList<Integer> e_xij_m11 = new ArrayList();
      ArrayList<Integer> e_xi_m1 = new ArrayList();
      ArrayList<Integer> e_xj_m1 = new ArrayList();
      ArrayList<Integer> e_m0 = new ArrayList();
     
      
       for(int e = 0; e < tamaño*tamaño; e++){
          if (e==i){
              e_m0.add(0);
              e_xi_m3.add(3);
              e_xj_m3.add(0);
              e_xij_m21.add(2);
              e_xij_m12.add(1);
              e_xi_m2.add(2);
              e_xj_m2.add(0);
              e_xij_m11.add(1);
              e_xi_m1.add(1);
              e_xj_m1.add(0);
          }else if (e==j){
              e_m0.add(0);
              e_xi_m3.add(0);
              e_xj_m3.add(3);
              e_xij_m21.add(1);
              e_xij_m12.add(2);
              e_xi_m2.add(0);
              e_xj_m2.add(2);
              e_xij_m11.add(1);
              e_xi_m1.add(0);
              e_xj_m1.add(1);
          }else{
              e_m0.add(0);
              e_xi_m3.add(0);
              e_xj_m3.add(0);
              e_xij_m21.add(0);
              e_xij_m12.add(0);
              e_xi_m2.add(0);
              e_xj_m2.add(0);
              e_xij_m11.add(0);
              e_xi_m1.add(0);
              e_xj_m1.add(0);
          }
      }
       
        ArrayList<Monomio> m = new ArrayList<>();
        ArrayList<Integer> coef_xi_m3 = new ArrayList<>();
        coef_xi_m3.add(1);
        coef_xi_m3.add(1);

        Monomio xi_m3 = new Monomio(coef_xi_m3, e_xi_m3);
        m.add(xi_m3);

        ArrayList<Integer> coef_xj_m3 = new ArrayList<>();
        coef_xj_m3.add(1);
        coef_xj_m3.add(1);

        Monomio xj_m3 = new Monomio(coef_xj_m3, e_xj_m3);
        m.add(xj_m3);

        ArrayList<Integer> coef_xij_m21 = new ArrayList<>();
        coef_xij_m21.add(1);
        coef_xij_m21.add(1);

        Monomio xij_m21 = new Monomio(coef_xij_m21, e_xij_m21);
        m.add(xij_m21);

        ArrayList<Integer> coef_xij_m12 = new ArrayList<>();
        coef_xij_m12.add(1);
        coef_xij_m12.add(1);

        Monomio xij_m12 = new Monomio(coef_xij_m12, e_xij_m12);
        m.add(xij_m12);

        ArrayList<Integer> coef_xi_m2 = new ArrayList<>();
        coef_xi_m2.add(-10);
        coef_xi_m2.add(1);

        Monomio xi_m2 = new Monomio(coef_xi_m2, e_xi_m2);
        m.add(xi_m2);

        ArrayList<Integer> coef_xj_m2 = new ArrayList<>();
        coef_xj_m2.add(-10);
        coef_xj_m2.add(1);

        Monomio xj_m2 = new Monomio(coef_xj_m2, e_xj_m2);
        m.add(xj_m2);

        ArrayList<Integer> coef_xij_m11 = new ArrayList<>();
        coef_xij_m11.add(-10);
        coef_xij_m11.add(1);

        Monomio xij_m11 = new Monomio(coef_xij_m11, e_xij_m11);
        m.add(xij_m11);

        ArrayList<Integer> coef_xi_m1 = new ArrayList<>();
        coef_xi_m1.add(35);
        coef_xi_m1.add(1);

        Monomio xi_m1 = new Monomio(coef_xi_m1, e_xi_m1);
        m.add(xi_m1);

        ArrayList<Integer> coef_xj_m1 = new ArrayList<>();
        coef_xj_m1.add(35);
        coef_xj_m1.add(1);

        Monomio xj_m1 = new Monomio(coef_xj_m1, e_xj_m1);
        m.add(xj_m1);

        ArrayList<Integer> coef_m0 = new ArrayList<>();
        coef_m0.add(-50);
        coef_m0.add(1);

        Monomio m0 = new Monomio(coef_m0, e_m0);
        m.add(m0);

      
      Polinomio p = new Polinomio(m);
      
      return p;
      
  }
    
}
