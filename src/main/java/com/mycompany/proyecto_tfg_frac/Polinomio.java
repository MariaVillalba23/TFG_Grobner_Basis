/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_tfg_frac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Maria_Villalba
 */
public class Polinomio {
     public ArrayList<Monomio> monomios;
    
     //CONSTRUCTORES
    public Polinomio( ArrayList<Monomio> _monomios){
        monomios = _monomios;
    }
    public Polinomio(){
        ArrayList<Monomio> _monomio = null;
        monomios = _monomio;
    }
    public Polinomio(Monomio _monomio){
        ArrayList<Monomio> monomios = new ArrayList<>();
        monomios.add(_monomio);
        this.monomios = monomios;
    }
    public Polinomio(String variables, String p){
         //xyz, x**2 + 3yz + z**2 - 1
         int nVariables = variables.length();
         String monomioAnterior = "";
         ArrayList<Monomio> monomios = new ArrayList<Monomio>();

         //Vamos recorriendo p, si encontramos un caracter igual a alguno de nVariables, vemos si tiene **, en ese caso añadimos exponente, sino un 1
        String[] pArray = p.split(" ");
        for (String monomio : pArray) {
            if(!("+".equals(monomio)) & !("-".equals(monomio))){
                //Creamos los exponentes vacios
                ArrayList<Integer> exponentes = new ArrayList<Integer>();
                for(int v=0;v<nVariables;v++){
                     exponentes.add(0);
                }
                String coeficiente = "";
                if(monomioAnterior.equals("-")){
                     coeficiente = "-";
                }
                for (int i = 0; i < monomio.length(); i++) {
                    char caracter = monomio.charAt(i);
                    //vemos si los elementos del principio son números
                   
                    if(Character.isDigit(caracter) || caracter == '-') {
                        coeficiente+=caracter;
                    }else{  
                        //vemos si el elemento es igual a alguna de las variables
                        int var=-1;
                        boolean encontrada=false;
                        while (!encontrada & var<(nVariables-1)){
                            var++;
                            encontrada = (caracter == (variables.charAt(var)));
                        }
                        //tenemos la variable var
                        if(encontrada){
                            if((i+1)< monomio.length() && '^' == (monomio.charAt(i+1))){
                                char exp = monomio.charAt(i+2);
                                exponentes.set(var, Character.getNumericValue(exp));
                                i=i+2;
                            }else{
                                 exponentes.set(var, 1);
                            }
                        }
                        
                    }
                    
                }
                if(coeficiente == ""){
                    coeficiente = "1";
                }else if("-".equals(coeficiente)){
                     coeficiente = "-1";
                }
                
                //Convertimos el número en una fracción
                ArrayList<Integer> coef = new ArrayList<Integer>();
                    coef.add(Integer.valueOf(coeficiente));
                    coef.add( 1);
                
                Monomio m = new Monomio(coef,exponentes);
                monomios.add(m);
        }
        monomioAnterior = monomio;
        }
        
        this.monomios = monomios;
     }
    public Polinomio(int max){
        ArrayList<Integer> d = new ArrayList<>();
        for(int i = 0; i<max; i++){
            d.add(0);
        }
        
        ArrayList<Integer> coef = new ArrayList<Integer>();
        coef.add(0);
        coef.add( 0);
        
        Monomio m = new Monomio(coef,d);
        
        ArrayList<Monomio> a_m = new ArrayList<Monomio>() {
            {
                add(m);
            }
        };
        
        this.monomios = a_m;
       
    }
    
    //MÉTODOS
    public Polinomio simplificacionPolinomios(){
        Set<Monomio> monomios = new HashSet<>(this.monomios);
        boolean terminos_iguales = true;
        while (terminos_iguales) {
           terminos_iguales = false;
           for (Monomio m1 : monomios) {
               for (Monomio m2 : monomios) {
                   if (m1 != m2 && m1.m_exp.equals(m2.m_exp)) {
                       Monomio suma = m1.sumaMonomio(m2);
                       monomios.remove(m1);
                       monomios.remove(m2);
                       monomios.add(suma);
                       terminos_iguales = true;
                       break;
                   }
               }
               if (terminos_iguales) {
                   break;
               }
           }
       }
        monomios.removeIf(m -> m.coeficiente.get(0) == 0 || m.m_exp.isEmpty());
        this.monomios = new ArrayList<>(monomios);
        return new Polinomio(this.monomios);
   }
  
   //Busca el leading tearm --> El termino superior
    public Monomio ltPolinomio(){ 
        this.simplificacionPolinomios();
        Monomio actual;
        Monomio superior = this.monomios.get(0);
        for(int i=1; i< this.monomios.size(); i++){
            actual = this.monomios.get(i);
            superior = superior.monomioMayor(actual);
        }
        return superior;
    }
    
   //Leading coeficient --> El coeficiente del termino superior
    public ArrayList<Integer> lcPolinomio(){
        Monomio m = this.ltPolinomio();
        return m.coeficiente;
     }
     
   //Leading power product --> El monomio superior sin coeficiente
    public Monomio lpPolinomio(){
       ArrayList<Integer> variables = this.ltPolinomio().m_exp;
       ArrayList<Integer> coef = new ArrayList<Integer>();
       coef.add(1);
       coef.add(1);
       return new Monomio(coef,variables);
    }
     
    public String leerPolinomio(){
        StringBuilder sb = new StringBuilder();
        boolean primerTerm = true;
        for(Monomio m1 : this.monomios){
           if((m1.coeficiente.get(0) > 0 & m1.coeficiente.get(1) > 0) || (m1.coeficiente.get(0) < 0 & m1.coeficiente.get(1) < 0)){
               if(!primerTerm){
                   sb.append(" + ");
               }
               sb.append(m1.leerMonomio());
               primerTerm = false;
           } else if(m1.coeficiente.get(0) < 0 || m1.coeficiente.get(0) < 0){
               sb.append(" ").append(m1.leerMonomio());
               primerTerm = false;
           }
        }
       
        return sb.toString();
     } 
     
    public Polinomio sumaPolinomio(Polinomio p){
        this.simplificacionPolinomios();
        p.simplificacionPolinomios();

        ArrayList<Monomio> res = new ArrayList<>();
        for(Monomio m1 : this.monomios){
            boolean coincidente = false;
            for(Monomio m2 : p.monomios){
                if(m1.m_exp.equals(m2.m_exp)){
                    coincidente = true;
                    ArrayList<Integer> coef = m1.sumaFraccion(m1.coeficiente,m2.coeficiente);
                    if(coef.get(0) != 0){
                        Monomio nuevo_monomio = new Monomio(coef, m1.m_exp);
                        res.add(nuevo_monomio);
                    }
                    break;
                }
            }
            if (!coincidente) {
                res.add(m1);
            }
        }
        for(Monomio m1 : p.monomios){
            boolean coincidente = false;
            for(Monomio m2 : this.monomios){
                if(m1.m_exp.equals(m2.m_exp)){
                    coincidente = true;
                    break;
                }
            }
            if (!coincidente) {
                res.add(m1);
            }
        }
        return new Polinomio(res).simplificacionPolinomios();
    }
    
    public Polinomio restaPolinomio(Polinomio p){
       Polinomio p1 = new Polinomio(this.monomios);
        Polinomio p2 = new Polinomio(p.monomios);
        
       for(Monomio m:p2.monomios){
           m.coeficiente.set(0, m.coeficiente.get(0) * (-1));
       }
       return p1.sumaPolinomio(p2).simplificacionPolinomios();
   }
     
    public Polinomio multiplicacionPolinomios(Polinomio p){
       this.simplificacionPolinomios();
       p.simplificacionPolinomios();
       
     ArrayList<Monomio> res_monomios = new ArrayList<>();
       for(Monomio m1 : this.monomios){
       for(Monomio m2 : p.monomios){
        res_monomios.add( m1.multiplicacionMonomios(m2));
       }
     }
      return new Polinomio(res_monomios).simplificacionPolinomios();
   }
     
    public Polinomio[] divisionUnivariable (Polinomio g){
        // Primero simplificamos los polinomios
        this.simplificacionPolinomios();
        g.simplificacionPolinomios();

        // Creamos q y r inicializadas a o y f
        // El polinomio q va a tener tantas variables como f
        int var = 0;
        for(Monomio monomio : this.monomios){
           if(monomio.m_exp.size() > var){
              var = monomio.m_exp.size();
           }
        }
        Polinomio q = new Polinomio(var);
        Polinomio r = this;

        boolean r_vacio = false;
        int grado_r = r.lpPolinomio().exponenteMonomio();

        // Hemos terminado cuando f es vacío o el grado de r es menor que el de g
        while (!r_vacio && grado_r >= g.lpPolinomio().exponenteMonomio()){
           // División de lt(r)/lt(g)
           Monomio m_suma = r.ltPolinomio().divisionMonomios(g.ltPolinomio());
           Polinomio p_suma = new Polinomio(new ArrayList<>(Arrays.asList(m_suma)));

           // q = q + suma de lt(r)/lt(g)
           q = q.sumaPolinomio(p_suma);

           // r = r - suma de lt(r)/lt(g) * g
            Polinomio p_resta = p_suma.multiplicacionPolinomios(g);
            //para restarlo, le cambio el signo a los coeficientes de los monomios
            for (Monomio monomio : p_resta.monomios) {
                 monomio.coeficiente.set(0, monomio.coeficiente.get(0) * (-1));
            }     
            r = r.sumaPolinomio(p_resta);

           r_vacio = r.monomios.isEmpty();
           grado_r = r.lpPolinomio().exponenteMonomio();
        }

        q.simplificacionPolinomios();
        r.simplificacionPolinomios();

        Polinomio[] resultado = {q, r};
        return resultado;
    }
    
    public Polinomio algoritmoEuclideo(Polinomio p){
         Polinomio[] division;
         
         Polinomio f = this;
         Polinomio g = p;
         boolean g_vacio = false;
         
         while(!g_vacio){
          f.simplificacionPolinomios();
          g.simplificacionPolinomios();
             division = f.divisionUnivariable(g);
             
             f = g;
             g = division[1];
             
         g_vacio = true;
         if(!g.monomios.isEmpty()){
             for(Monomio m : g.monomios){
             for( Integer i:m.m_exp ){
                if(i != 0){
                    g_vacio = false;
                }
            }
            }

               }
         }

        ArrayList<Integer> lc_pol = f.lcPolinomio();
        ArrayList<Integer> lc_pol_invertido = new ArrayList<Integer>();
        lc_pol_invertido.add(lc_pol.get(1));
        lc_pol_invertido.add(lc_pol.get(0));

        
        ArrayList<Integer> d0 = new ArrayList<Integer>(){{
        add(0);
        }};  
        Monomio m0 = new Monomio(lc_pol_invertido,d0);
        ArrayList<Monomio> am0 = new ArrayList<Monomio>() {
            {
                add(m0);
            }
        };
        Polinomio p0 = new Polinomio(am0);
        
        return f.multiplicacionPolinomios(p0);

    }
    
    public ArrayList<Polinomio> divisionMultivariable(ArrayList<Polinomio> arrayPolinomios) {
        int s = arrayPolinomios.size();
        int var_max = 0;
        for (Polinomio p : arrayPolinomios) {
            for (Monomio m : p.monomios) {
                if (m.m_exp.size() > var_max) {
                    var_max = m.m_exp.size();
                }
            }
        }
        Polinomio p = new Polinomio(var_max);
        ArrayList<Polinomio> arrayU = new ArrayList<>();
        for (int i = 0; i < s; i++) {
            arrayU.add(p);
        }

        Polinomio r = new Polinomio(var_max);
        Polinomio h = this;
        boolean h_vacio = false;

        while (!h_vacio) {
            h.simplificacionPolinomios();
            r.simplificacionPolinomios();
            Monomio lp_h = h.lpPolinomio();

            Monomio lt_m = h.ltPolinomio();
            Polinomio lt_p = new Polinomio(lt_m);

            boolean encontrado = false;
            int i = 0;
            while (!encontrado & i < arrayPolinomios.size()) {
                Monomio lp_f = arrayPolinomios.get(i).lpPolinomio();

                if (lp_h.esDivisible(lp_f)) {
                    encontrado = true;
                } else {
                    i++;
                }
            }

            if (encontrado) {
                Monomio lt_f = arrayPolinomios.get(i).ltPolinomio();

                Monomio division_suma_m = lt_m.divisionMonomios(lt_f);
                Monomio division_resta_m = lt_m.divisionMonomios(lt_f);

                Polinomio division_suma_p = new Polinomio(division_suma_m);
                Polinomio division_resta = new Polinomio(division_resta_m);

                arrayU.set(i, arrayU.get(i).sumaPolinomio(division_suma_p));

                for (Monomio monomio : division_resta.monomios) {
                    monomio.coeficiente.set(0, monomio.coeficiente.get(0) * (-1));
                }
                Polinomio division_resta_p = division_resta.multiplicacionPolinomios(arrayPolinomios.get(i));
                h = h.sumaPolinomio(division_resta_p);
              //System.out.println("h = " + h.leerPolinomio());
            } else {
                r = r.sumaPolinomio(lt_p);
                //System.out.println("r = " + r.leerPolinomio());
                ArrayList<Monomio> _monomios = new ArrayList<Monomio>();
                for (Monomio monomio : lt_p.monomios) {
                    ArrayList<Integer> coef = new ArrayList<Integer>();
                    coef.add(monomio.coeficiente.get(0) * (-1));
                    coef.add(monomio.coeficiente.get(1));
                    Monomio m = new Monomio(coef, monomio.m_exp);
                    _monomios.add(m);
                }
                Polinomio resta = new Polinomio(_monomios);
                h = h.sumaPolinomio(resta);
               // System.out.println("h = " + h.leerPolinomio());
            }

            h_vacio = true;
              if(!h.monomios.isEmpty()){
                    for(Monomio m : h.monomios){
                        for( Integer n:m.m_exp ){
                           if(n != 0){
                           h_vacio = false;
                            }
                        }
                        if(m.coeficiente.get(0) != 0){h_vacio = false;}
                   }
               }
        }

        arrayU.add(r);

        return arrayU;
    }
    
    public Monomio calcularmcmPolinomios (Polinomio p){
        //Primero obtenemos el lp de cada uno
        Monomio m1 = this.lpPolinomio();
        Monomio m2 = p.lpPolinomio();
        
        //Ahora construimos un nuevo monomio que tenga el mayor exponente de cada una de sus variables
         ArrayList<Integer> exp_Spolinomio = new ArrayList<>(m1.m_exp.size());
         
        for(int i=0; i<m1.m_exp.size(); i++){
            if (m1.m_exp.get(i) >= m2.m_exp.get(i)){
                exp_Spolinomio.add(m1.m_exp.get(i));
            }else{
                 exp_Spolinomio.add(m2.m_exp.get(i));
            }
        }
        ArrayList<Integer> unidad = new ArrayList<Integer>();
        unidad.add(1);
        unidad.add(1);
        
        Monomio m_res = new Monomio(unidad, exp_Spolinomio);
        //Declaramos el s-polinomio monico con el monomio anterior
        //Polinomio resultado = new Polinomio (m_res);
        
        return m_res;
    }
    
    public Polinomio calcularSPolinomio (Polinomio g){
         Monomio L = this.calcularmcmPolinomios(g);
         
         Monomio lt_f =this.ltPolinomio();
         Monomio lt_g = g.ltPolinomio();
         
        // Monomio divisionF = L.divisionMonomios(lt_f);
         Polinomio divisionF = new Polinomio (L.divisionMonomios(lt_f));
         Monomio m_divisionG = L.divisionMonomios(lt_g);
         m_divisionG.coeficiente.set(0, m_divisionG.coeficiente.get(0) * (-1));

         Polinomio divisionG = new Polinomio(m_divisionG);
         
         Polinomio p_f = divisionF.multiplicacionPolinomios(this);
         Polinomio p_g = divisionG.multiplicacionPolinomios(g);
         
         Polinomio S = p_f.sumaPolinomio(p_g);
         
         return S.simplificacionPolinomios();
     }
    
    public ArrayList<Polinomio> calcularBasesGrobner (ArrayList<Polinomio> F){
          int contador = 1;
          
          //INICIALIZACION
          //Definimos G
          ArrayList<Polinomio> G = F;
          //Definimos el espacio de parejas de todos los polinomios de G
          ArrayList<ArrayList<Polinomio>> G_pares = new ArrayList<ArrayList<Polinomio>>();
          for (int i=0; i<G.size();i++){
              for (int j= i + 1; j<G.size();j++){
                  if (i!=j){
                      ArrayList<Polinomio> par = new ArrayList<Polinomio>();
                      par.add(G.get(i));
                      par.add(G.get(j));
                      G_pares.add(par);
                  }
              }
          }
          
          while (!G_pares.isEmpty()){
            
              System.out.println();
             
              System.out.println("PASO " + contador);
              System.out.println(G_pares.size());
              if (contador==12){
                    System.out.println("PASO " + contador);
              }
             
              contador++;
              //Elegimos un par cualquiera y lo excluimos de G_pares
              ArrayList<Polinomio> par_eliminado = G_pares.get(0);
              G_pares.remove(0);
              
              //Obtenemos el Spolinomio del par
             // System.out.println("Eliminamos el par {" + par_eliminado.get(0).leerPolinomio() + ", " + par_eliminado.get(1).leerPolinomio() + "}" );
              par_eliminado.get(0).simplificacionPolinomios();
              par_eliminado.get(1).simplificacionPolinomios();
              
              Polinomio S = par_eliminado.get(0).calcularSPolinomio(par_eliminado.get(1));
              
              
              if (!S.monomios.isEmpty()){
                  // System.out.println("El S-Polinomio del par elegido es " + S.leerPolinomio());
                  
                //Usamos el algoritmo de division multivariable para reducir S con respecto de G
                //Obtenemos el resto de la division (h)
                ArrayList<Polinomio> resultadoDivision = S.divisionMultivariable(G);
                Polinomio resto = resultadoDivision.get(resultadoDivision.size()-1);
                
                /*if (resto.monomios.isEmpty()){
                    System.out.println("El resto de la division es 0");

                }else{
                    System.out.println("El resto de la division es " + resto.leerPolinomio());
                }*/
                //Vemos si el resto es distinto de 0
                if (!resto.monomios.isEmpty()){
                    //Añadimos a G_pares {{u,resto} | para todo u en G}
                    for (Polinomio g:G){
                        ArrayList<Polinomio> par = new ArrayList<>();
                        par.add(g);
                        par.add(resto);
                        if(!G_pares.contains(par)){
                            G_pares.add(par);
                        }
                    }
                    //Añadimos resto a G
                   if(!G.contains(resto)){
                        G.add(resto);
                        /*
                        System.out.println("Los polinomios que están en G son:");
                         for (Polinomio p:G){
                             System.out.println( p.leerPolinomio());
                         }
                        */
                     }
                }
                if (!resto.monomios.isEmpty()){
                    //Añadimos resto a G
                    if(!G.contains(resto)){
                        G.add(resto);
                    }
                    //Añadimos a G_pares {{u,resto} | para todo u en G}
                    HashSet<Polinomio> G_hash = new HashSet<>(G); // convertir G a HashSet para búsqueda rápida
                    for (Polinomio g : G_hash){
                        ArrayList<Polinomio> par = new ArrayList<>();
                        if (!g.equals(resto)){
                              par.add(g);
                            par.add(resto);
                            if(!G_pares.contains(par)){
                                G_pares.add(par);
                            }
                        }
                    }
                }
              }else{
                //   System.out.println("El S-Polinomio es 0, G queda igual" );
              }
              /*
              System.out.println("Los pares de polinomios que están en G_par son:");
              for (ArrayList<Polinomio> p:G_pares){
                  System.out.println( "{" + p.get(0).leerPolinomio() + ", " + p.get(1).leerPolinomio() + "}");
                }
              */
          }
          
          return G;
      }
    
    public ArrayList<Polinomio> reducirGrobner (ArrayList<Polinomio> G) {
      ArrayList<Polinomio> H = new ArrayList<>();
       
      //hacemos una copia 
       ArrayList<Polinomio> R = new ArrayList<>();
       for (Polinomio p:G){
           Polinomio copia = new Polinomio(p.monomios);
           R.add(copia);
       }
       
      for(int i=0; i<G.size(); i++){
          Polinomio g = new Polinomio(G.get(i).monomios);
          R.remove(0);
          
          System.out.println();
            System.out.println("EL POLINOMIO ELIMINADO ES:" + g.leerPolinomio());
          
            System.out.println();
            System.out.println("H:");

            for (Polinomio p:H){
                System.out.println( p.leerPolinomio());
            }
             
            System.out.println();
            System.out.println("R:");

            for (Polinomio p:R){
                System.out.println( p.leerPolinomio());
            }
          
          
          //Usamos el algoritmo de division multivariable para reducir g con respecto de R
           //Obtenemos el resto de la division (h)
            ArrayList<Polinomio> resultadoDivision = g.divisionMultivariable(R);
            Polinomio resto = resultadoDivision.get(resultadoDivision.size()-1);
            if (!resto.monomios.isEmpty()){
                
                System.out.println("El resto de la division es " + resto.leerPolinomio());
                
                //Dividimos el resto por su lc para que sea mónico
                
                ArrayList<Integer> lc_double = new ArrayList<>(resto.lcPolinomio());
                
                for(Monomio m:resto.monomios){
                    ArrayList<Integer> monico = m.divisionFraccion(m.coeficiente,lc_double);
                    m.coeficiente.set(0, monico.get(0));
                    m.coeficiente.set(1, monico.get(1));
                }
                
                resto.simplificacionPolinomios();
                //Metemos el resto en la base reducida
                H.add(resto);

                //Metemos el resto en la base con la que estamos trabajando
                R.add(resto);
               
            }
                
          
      }
      
      return H;
     }
}
