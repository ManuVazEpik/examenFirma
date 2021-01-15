/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firmaexamen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author manua
 */
public class validar {
    
    public boolean letrasEspacios(String var){
            boolean test= false;
            try{
                Pattern p = Pattern.compile("^[a-zA-Z]+(\\s*[a-zA-Z]*)*[a-zA-Z]+$");
                Matcher m = p.matcher(var);
                if(!m.find()){
                    test=false;
                }else{
                    test=true;
                }
            }catch(Exception e){
                System.out.println("Error en letras espacios");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
            return test;
        }
        public boolean letras(String var){
            boolean test= false;
            try{
                Pattern p = Pattern.compile("^[a-zA-ZÀ-ÿ]+$");
                Matcher m = p.matcher(var);
                if(!m.find()){
                    test=false;
                }else{
                    test=true;
                }
            }catch(Exception e){
                System.out.println("Error en letras espacios");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
            return test;
        }
        public boolean numeros(String var){
            boolean test= false;
            try{
                Pattern p = Pattern.compile("[0-9]*");
                Matcher m = p.matcher(var);
                if(!m.find()){
                    test=false;
                }else{
                    test=true;
                }
            }catch(Exception e){
                System.out.println("Error en letras espacios");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
            return test;
        }
    
}
