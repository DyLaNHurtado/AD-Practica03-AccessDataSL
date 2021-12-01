package repository;

import model.Commit;
import model.Issue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RepoIssueTest {
    private static RepoIssue reposIssue=new RepoIssue();
    private static List<Issue> list =new ArrayList<>();

    @BeforeAll
    static void init(){
        list.add(new Issue("1f9b764e-570f-4041-a0c9-fc58a794eb0d", "arreglo 1", "se ha encontrado un fallo en la clase",
                new Date(), List.of("53269670-1586-49ac-9df5-62ddd55f96cc;1376acc9-79a9-4bf1-9084-c82e9a07f432"),
                "81ee1211-760c-493d-968a-380e6af67363", "f64c5364-faa7-41b7-bca9-3b27f95d8fa8", "terminada"));

        list.add(new Issue("7e96e277-26bc-4c08-a21e-6d92eb7638de", "arreglo 2", "se ha encontrado un fallo en el atributo",
                new Date(), List.of("d63f0d73-3f1b-4afd-b5d0-821449daa4a3"),
                "f89062d9-ba34-40a4-b6af-a21a0dc093be", "f1174508-8659-4654-82ce-af2704a152de", "terminada"));

        list.add(new Issue("6c5b7c5a-d30b-400f-9c11-84dc2b49f01e", "arreglo 3", "se ha encontrado un fallo en el metodo",
                new Date(), List.of("606aba4c-b76e-4fa3-9eb8-48e20d729353"),
                "2d1d1422-fede-4e27-8883-3ffdb1be1a7c", "ed38db33-7fd3-4242-91e4-a411d5fe3b1f", "pendiente"));



    }


    @Test
    @Order(1)
    void getAll() throws SQLException {

        int num=reposIssue.getAll().get().size();
        Assertions.assertEquals(list.size(),num);
    }

    @Test
    @Order(2)
    void getById() throws SQLException {
        Optional<Issue> issue = reposIssue.getById("1f9b764e-570f-4041-a0c9-fc58a794eb0d");
        Optional<Issue> issueNotFound = reposIssue.getById("8e4-4325-b037-bd4fcfafe783");
        assertTrue(issue.isPresent());
        assertFalse(issueNotFound.isPresent());
    }

    @Test
    @Order(3)
    void save() throws SQLException {
        Issue issue =new Issue("","","",new Date(),List.of(""),"","","");
        Optional<Issue> issueOptional = reposIssue.save(issue);
        Assertions.assertEquals(issueOptional.orElse(null),issue);

    }

    @Test
    @Order(4)
    void update() throws SQLException {
        Issue issue =new Issue("","1","1",new Date(),List.of(""),"","","");
        Optional<Issue> issueOptional = reposIssue.update(issue);
        Assertions.assertEquals(issueOptional.orElse(null),issue);
    }

    @Test
    @Order(5)
    void delete() throws SQLException {
        Issue issue =new Issue("","1","1",new Date(),List.of(""),"","","");
        Optional<Issue> issueOptional = reposIssue.delete(issue);
        Assertions.assertEquals(issueOptional.orElse(null),issue);
    }

}