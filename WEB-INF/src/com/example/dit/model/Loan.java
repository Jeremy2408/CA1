package com.example.dit.model;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "loan")
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private double amount;
    private double remainingBalance;
    private boolean fullyPaid;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Deposit> deposits = new ArrayList<>();

    @ManyToOne
    //@JoinColumn(name = "studentNumber", referencedColumnName = "studentNumber")
    private Student student;

    private String studentNumber;

    

    public Loan(String description, double amount, String studentNumber) {
        this.description = description;
        this.amount = amount;
        this.remainingBalance = amount;
        this.studentNumber = studentNumber;
        this.fullyPaid = false;
    }

    public Loan() {
    }

     

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @XmlElement
    public double getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(double remainingBalance) {
        this.remainingBalance = remainingBalance;
        this.fullyPaid = remainingBalance <= 0;
    }

    @XmlElement
    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public void addDeposit(Deposit deposit) {
        this.deposits.add(deposit);
        deposit.setLoan(this);
        this.remainingBalance -= deposit.getAmount();
        this.fullyPaid = this.remainingBalance <= 0;
    }

    @XmlElement
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @XmlElement
    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    @XmlElement
    public boolean isFullyPaid() {
        return fullyPaid;
    }

    public void setFullyPaid(boolean fullyPaid) {
        this.fullyPaid = fullyPaid;
    }

    
}