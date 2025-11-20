/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package celune.persistencia;

import celunet.logica.Celular;
import celunet.logica.Cliente;

/**
 *
 * @author raidenov
 */
public class ControladoraPersistencia {
    ClienteJpaController clientJpa = new ClienteJpaController();
    CelularJpaController celuJpa = new CelularJpaController();

    public void guardar(Cliente client, Celular celu) {
        clientJpa.create(client);
        
        celuJpa.create(celu);
    }
    
}
