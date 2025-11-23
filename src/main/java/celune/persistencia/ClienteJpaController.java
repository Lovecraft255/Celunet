package celune.persistencia;

import celunet.logica.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author raidenov
 */
public class ClienteJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ClienteJpaController() {
        // Debe coincidir con el nombre definido en persistence.xml
        emf = Persistence.createEntityManagerFactory("CelunetJPA");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // === MÉTODOS CRUD ===
    // CREATE
    public void create(Cliente cliente) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(cliente);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error al crear cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // READ - Buscar por ID
    public Cliente findCliente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    // READ - Listar todos
    public List<Cliente> findClienteEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // UPDATE
    public void edit(Cliente cliente) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(cliente);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // DELETE
    public void destroy(int id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Cliente cliente = em.find(Cliente.class, id);

            if (cliente != null) {
                // Elimina los celulares primero (por relación 1:N)
                cliente.getCelulares().clear();
                em.remove(cliente);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Cliente findClienteByNumero(String numero) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.numero = :num", Cliente.class);
            query.setParameter("num", numero);

            List<Cliente> resultados = query.getResultList();

            if (resultados.isEmpty()) {
                return null;
            }
            return resultados.get(0);
        } finally {
            em.close();
        }
    }

}
