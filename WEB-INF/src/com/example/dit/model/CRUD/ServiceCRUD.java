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



}
