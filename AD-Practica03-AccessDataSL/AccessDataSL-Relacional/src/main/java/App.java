import model.Commit;
import model.Departamento;
import model.Programador;
import repository.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
            RepoProyecto repoProyecto= new RepoProyecto();
            RepoIssue repoIssue= new RepoIssue();
            RepoProgramador repoProgramador= new RepoProgramador();
            RepoDepartamento repoDepartamento= new RepoDepartamento();

        try {
            System.out.println(repoIssue.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
