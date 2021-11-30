package repository;

import junit.framework.Assert;
import model.Commit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RepoCommitTest {
    private static RepoCommit repoCommit=new RepoCommit();
    private static List<Commit> list =new ArrayList<>();

    @BeforeAll
    static void init(){
        list.add(new Commit("5b64bfd6-08e4-4325-b037-bd4fcfafe783", "commit 1", "texto 1",
                new GregorianCalendar(2002, Calendar.FEBRUARY, 1).getTime(), "f64c5364-faa7-41b7-bca9-3b27f95d8fa8",
                "81ee1211-760c-493d-968a-380e6af67363", "53269670-1586-49ac-9df5-62ddd55f96cc",
                "53269670-1586-49ac-9df5-62ddd55f96cc"));

        list.add(new Commit("649ad8bd-6d6e-4430-af04-9fcfe370db84", "commit 2", "texto 2",
                new GregorianCalendar(2012, Calendar.DECEMBER, 10).getTime(), "f1174508-8659-4654-82ce-af2704a152de",
                "f89062d9-ba34-40a4-b6af-a21a0dc093be", "606aba4c-b76e-4fa3-9eb8-48e20d729353",
                "6c5b7c5a-d30b-400f-9c11-84dc2b49f01e"));

        list.add(new Commit("3a1690b0-b7f3-4303-8413-1f63578c3194", "commit 3", "texto 3",
                new GregorianCalendar(2016, Calendar.JUNE, 10).getTime(), "f1174508-8659-4654-82ce-af2704a152de",
                "f89062d9-ba34-40a4-b6af-a21a0dc093be", "606aba4c-b76e-4fa3-9eb8-48e20d729353",
                "6c5b7c5a-d30b-400f-9c11-84dc2b49f01e"));
    }


    @Test
    @Order(1)
    void getAll() throws SQLException {

        int num=repoCommit.getAll().get().size();
        Assertions.assertEquals(list.size(),num);
    }

    @Test
    @Order(2)
    void getById() throws SQLException {
        Optional<Commit> commit = repoCommit.getById("5b64bfd6-08e4-4325-b037-bd4fcfafe783");
        Optional<Commit> commit2 = repoCommit.getById("8e4-4325-b037-bd4fcfafe783");
        assertTrue(commit.isPresent());
        assertFalse(commit2.isPresent());
    }

    @Test
    @Order(3)
    void save() throws SQLException {
        Commit commit =new Commit("","1","1",new Date(),"","","","");
        Optional<Commit> commitOptional = repoCommit.save(commit);
        Assertions.assertEquals(commitOptional.orElse(null),commit);

    }

    @Test
    @Order(4)
    void update() throws SQLException {
        Commit commit =new Commit("","2","2",new Date(),"","","","");
        Optional<Commit> commitOptional = repoCommit.update(commit);
        Assertions.assertEquals(commitOptional.orElse(null), commit);
    }

    @Test
    @Order(5)
    void delete() throws SQLException {
        Commit commit =new Commit("","2","2",new Date(),"","","","");
        Optional<Commit> commitOptional = repoCommit.delete(commit);
        Assertions.assertEquals(commitOptional.orElse(null), commit);
    }

}