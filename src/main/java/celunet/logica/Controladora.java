/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package celunet.logica;

import celune.persistencia.ControladoraPersistencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raidenov
 */
public class Controladora {

    ControladoraPersistencia controlP = new ControladoraPersistencia();

    public void guardar(String name, String num, String marca, String modelo, String problema) {

        // 1. Buscar si el cliente ya existe
        Cliente clienteExistente = controlP.buscarClientePorNumero(num);

        Celular celu = new Celular();
        celu.setMarca(marca);
        celu.setModelo(modelo);
        celu.setProblema(problema);

        if (clienteExistente != null) {
            // Cliente ya existe → solo agrego nuevo celular
            celu.setCliente(clienteExistente);
            controlP.agregarCelu(clienteExistente, celu);

        } else {
            // Cliente NO existe → creo cliente + su primer celular
            Cliente nuevo = new Cliente();
            nuevo.setNombre(name);
            nuevo.setNumero(num);

            celu.setCliente(nuevo);
            controlP.crearClienteYCelu(nuevo, celu);
        }

    }

    public List<Celular> traerCelus() {

        return controlP.traerCelus();

    }

    

    public void borrarCelu(int numCliente) {
        controlP.borrarCelu(numCliente);
    }

    public Celular traerCelu(int numCliente) {
        return controlP.traerCelu(numCliente);
    }

}
