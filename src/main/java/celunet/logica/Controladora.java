/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package celunet.logica;

import celune.persistencia.ControladoraPersistencia;
import java.util.List;

/**
 *
 * @author raidenov
 */
public class Controladora {
    ControladoraPersistencia controlP = new ControladoraPersistencia();

    public void guardar(String name, String num, String marca, String modelo, String problema) {
       
        Cliente client = new Cliente();
        
        client.setNombre(name);
        client.setNumero(num);
        
        Celular celu = new Celular();
        
        celu.setMarca(marca);
        celu.setModelo(modelo);
        celu.setProblema(problema);
      
        controlP.guardar(client, celu);
    }

    public List <Celular> traerCelus() {
       
       return controlP.traerCelus(); 
        
    }
    
}
