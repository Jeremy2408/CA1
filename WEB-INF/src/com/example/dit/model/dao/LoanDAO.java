import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");

    public void addLoan(Loan loan) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(loan);
        em.getTransaction().commit();
        em.close();
    }

    public void removeLoan(Loan loan) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(loan));
        em.getTransaction().commit();
        em.close();
    }

    public Loan merge(Loan loan) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Loan updatedLoan = em.merge(loan);
        em.getTransaction().commit();
        em.close();
        return updatedLoan;
    }

    public List<Loan> getAllLoans() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Loan> loans = new ArrayList<>();
        loans = em.createQuery("from Loan", Loan.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return loans;
    }
}