package com.example.dit.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.example.dit.model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");

    public void addStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
    }

    public void removeStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(student));
        em.getTransaction().commit();
        em.close();
    }

    public Student merge(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student updatedStudent = em.merge(student);
        em.getTransaction().commit();
        em.close();
        return updatedStudent;
    }

    public List<Student> getAllStudents() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Student> students = new ArrayList<>();
        students = em.createQuery("from Student", Student.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return students;
    }
}  
     