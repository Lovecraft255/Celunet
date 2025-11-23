/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package celune.persistencia;

import celunet.logica.Celular;
import celunet.logica.Cliente;
import java.util.List;

/**
 *
 * @author raidenov
 */
public class ControladoraPersistencia {

    ClienteJpaController clientJpa = new ClienteJpaController();
    CelularJpaController celuJpa = new CelularJpaController();

    public void crearClienteYCelu(Cliente client, Celular celu) {
        clientJpa.create(client);
        celu.setCliente(client);
        celuJpa.create(celu);
    }

    public void agregarCelu(Cliente cliente, Celular celu) {
        celu.setCliente(cliente);
        celuJpa.create(celu);
    }

    public void agregarCelu(Celular celu) {

        celuJpa.edit(celu);
    }

    public List<Celular> traerCelus() {
        return celuJpa.findCelularEntities();
    }

    public void borrarCelu(int numCliente) {
        try {
            celuJpa.destroy(numCliente);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Celular traerCelu(int numCliente) {
        return celuJpa.findCelular(numCliente);
    }

    public Cliente buscarClientePorNumero(String num) {
        return clientJpa.findClienteByNumero(num);
    }

    
}
