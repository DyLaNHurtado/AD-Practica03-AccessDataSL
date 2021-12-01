package repository;

import model.Repositorio;
import model.Tecnologia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RepoTecnologiaTest {
    private static RepoTecnologia repoTecnologia=new RepoTecnologia();
    private static List<Tecnologia> list =new ArrayList<>();

    @BeforeAll
    static void init() {
        list.add(new Tecnologia("20bcca63-7b60-4a43-bb10-4c9735587d16",
                "Python"));

        list.add(new Tecnologia( "4f119f1b-7ccf-49f4-b56f-fdace8589b1c",
                "Kotlin"));

        list.add(new Tecnologia("cb231a1d-ffc8-4a64-b090-1334f5f4f740",
                "Java"));

    }

    @Test
    @Order(1)
    void getAll() throws SQLException {

        int num=repoTecnologia.getAll().get().size();
        Assertions.assertEquals(list.size(),num);

    }

    @Test
    @Order(2)
    void getById() throws SQLException {
        Optional<Tecnologia> repositorio = repoTecnologia.getById("20bcca63-7b60-4a43-bb10-4c9735587d16");
        Optional<Tecnologia> repositorioNotFound = repoTecnologia.getById("be37-4930-b6ae");
        assertTrue(repositorio.isPresent());
        assertFalse(repositorioNotFound.isPresent());
    }

    @Test
    @Order(3)
    void save() throws SQLException {
        Tecnologia tecnologia =new Tecnologia("","");
        Optional<Tecnologia> repositorioOptional  = repoTecnologia.save(tecnologia);
        Assertions.assertEquals(repositorioOptional.orElse(null),tecnologia);
    }

    @Test
    @Order(4)
    void update() throws SQLException {
        Tecnologia tecnologia =new Tecnologia("","1");
        Optional<Tecnologia> repositorioOptional  = repoTecnologia.update(tecnologia);
        Assertions.assertEquals(repositorioOptional.orElse(null),tecnologia);
    }

    @Test
    @Order(5)
    void delete() throws SQLException {
        Tecnologia tecnologia =new Tecnologia("","1");
        Optional<Tecnologia> repositorioOptional  = repoTecnologia.delete(tecnologia);
        Assertions.assertEquals(repositorioOptional.orElse(null),tecnologia);
    }

}