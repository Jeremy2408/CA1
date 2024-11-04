package com.example.dit.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import com.example.dit.model.Loan;

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
    public Loan getLoanById(int id) {
        EntityManager em = emf.createEntityManager();
        Loan loan = em.find(Loan.class, id);
        em.close();
        return loan;
    }
    public Loan getLoanByIdWithDeposits(int id) {
        EntityManager em = emf.createEntityManager();
        Loan loan = em.find(Loan.class, id);
        if (loan != null) {
            loan.getDeposits().size(); 
        }
        em.close();
        return loan;
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