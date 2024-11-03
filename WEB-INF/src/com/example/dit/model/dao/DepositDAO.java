import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class DepositDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");

    public void addDeposit(Deposit deposit) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(deposit);
        em.getTransaction().commit();
        em.close();
    }

    public void removeDeposit(Deposit deposit) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(deposit));
        em.getTransaction().commit();
        em.close();
    }

    public Deposit merge(Deposit deposit) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Deposit updatedDeposit = em.merge(deposit);
        em.getTransaction().commit();
        em.close();
        return updatedDeposit;
    }

    public List<Deposit> getAllDeposits() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Deposit> deposits = new ArrayList<>();
        deposits = em.createQuery("from Deposit", Deposit.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return deposits;
    }
}
