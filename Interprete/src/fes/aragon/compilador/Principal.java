/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.compilador;

import java.net.URISyntaxException;


/**
 *
 * @author mash
 */
public class Principal {
    private String ruta;

    public Principal() {
    }

    public String getRuta() throws URISyntaxException {
        ruta=System.getProperty("user.dir")+"/src/fes/aragon/compilador/Fuente.txt";
        return ruta;
    }
    public static void main(String[] args) {
        try {
        	Principal app=new Principal();
            parser inicio=new parser();
            inicio.cargar(app.getRuta());
            System.out.println("Entrar");
            } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }
    
}
