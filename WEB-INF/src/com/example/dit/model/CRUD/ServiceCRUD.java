package com.example.dit.model.CRUD;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.dit.model.Student;
import com.example.dit.model.Loan;
import com.example.dit.model.Deposit;
import com.example.dit.model.dao.StudentDAO;
import com.example.dit.model.dao.LoanDAO;
import com.example.dit.model.dao.DepositDAO;

@Path("/students")
public class ServiceCRUD {

    private StudentDAO studentDAO = new StudentDAO();
    private LoanDAO loanDAO = new LoanDAO();
    private DepositDAO depositDAO = new DepositDAO();

    @POST
    @Path("/newStudent")
    @Consumes("application/json")
    public String addStudentToDBJSON(Student student) {
        studentDAO.addStudent(student);
        return "Student added to DB from JSON Param " + student.getName();
    }

    @POST
    @Path("/newLoan")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addLoanToDBJSON(Loan loan) {
        Student student = studentDAO.getAllStudents().stream()
                .filter(s -> s.getStudentNumber().equals(loan.getStudentNumber()))
                .findFirst()
                .orElse(null);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        loan.setStudent(student);
        loan.setRemainingBalance(loan.getAmount()); 
        loanDAO.addLoan(loan);
        return Response.status(Response.Status.CREATED).entity(loan).build();
    }

    @POST
    @Path("/loans/{loanId}/deposits")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addDepositToLoan(@PathParam("loanId") int loanId, Deposit deposit) {
        Loan loan = loanDAO.getLoanByIdWithDeposits(loanId);
        if (loan == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        deposit.setLoan(loan);
        loan.addDeposit(deposit);
        loanDAO.merge(loan);
        return Response.status(Response.Status.CREATED).entity(deposit).build();
    }
    @GET
    @Path("/json/students")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> listStudentsJSON() {
        return studentDAO.getAllStudents();
    }

    @GET
    @Path("/json/student/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudentByNameJSON(@PathParam("name") String name) {
        return studentDAO.getAllStudents().stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @GET
    @Path("/json/loans")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Loan> listLoansJSON() {
        return loanDAO.getAllLoans();
    }

    @GET
    @Path("/json/loan/{loanId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Loan getLoanJSON(@PathParam("loanId") int loanId) {
        return loanDAO.getLoanByIdWithDeposits(loanId);
    }

    @GET
    @Path("/json/deposits")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Deposit> listDepositsJSON() {
        return depositDAO.getAllDeposits();
    }

    @GET
    @Path("/json/deposit/{depositId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Deposit getDepositJSON(@PathParam("depositId") int depositId) {
        return depositDAO.getAllDeposits().stream()
                .filter(d -> d.getId() == depositId)
                .findFirst()
                .orElse(null);
    }

    @DELETE
    @Path("/json/student/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStudentByNameJSON(@PathParam("name") String name) {
        Student student = studentDAO.getAllStudents().stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        studentDAO.removeStudent(student);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    





}
