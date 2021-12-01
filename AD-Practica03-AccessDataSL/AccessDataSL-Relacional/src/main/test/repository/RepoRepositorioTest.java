package repository;

import model.Repositorio;
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

class RepoRepositorioTest {
    private static RepoRepositorio repoRepositorio=new RepoRepositorio();
    private static List<Repositorio> list =new ArrayList<>();

    @BeforeAll
    static void init() {
        list.add(new Repositorio("f64c5364-faa7-41b7-bca9-3b27f95d8fa8","81ee1211-760c-493d-968a-380e6af67363", new Date(),
                List.of("5b64bfd6-08e4-4325-b037-bd4fcfafe783"),
                List.of("1f9b764e-570f-4041-a0c9-fc58a794eb0d")));

        list.add(new Repositorio("f1174508-8659-4654-82ce-af2704a152de","f89062d9-ba34-40a4-b6af-a21a0dc093be",new Date() ,
                List.of("649ad8bd-6d6e-4430-af04-9fcfe370db84;3a1690b0-b7f3-4303-8413-1f63578c3194"),
                List.of("7e96e277-26bc-4c08-a21e-6d92eb7638de")));

        list.add(new Repositorio("4863c6e9-606f-42bb-aaff-6e961de25054", "f89062d9-ba34-40a4-b6af-a21a0dc093be", new Date(),
                List.of(""), List.of("")));

        list.add(new Repositorio("ed38db33-7fd3-4242-91e4-a411d5fe3b1f",  "2d1d1422-fede-4e27-8883-3ffdb1be1a7c",new Date(),
                List.of("6e0bb43f-c639-4a79-8ea9-eb446d5bd624;1ebfc166-3cd9-4303-a322-f57dc5ee3e5e"),
                List.of("6c5b7c5a-d30b-400f-9c11-84dc2b49f01e")));

        list.add(new Repositorio("2a11c73f-faa0-4346-82ac-fe6115ed4d6a", "233b5d47-0ced-4e6f-8982-b2f95b6b25b9",new Date(),
                List.of("00efadeb-cdc5-456d-9b4a-bdd8d6fc2db5;699b5f3f-8e62-41e1-ba08-1546f0ab5dfb"),
                List.of("")));

        list.add(new Repositorio("eecd0485-8cc8-426b-81a5-ddffe5d83a67", "3730b275-3ed2-4d77-8ff4-f5ce82ea98ea",new Date(),
                List.of("3a1690b0-b7f3-4303-8413-1f63578c3194"), List.of("")));

    }

    @Test
    @Order(1)
    void getAll() throws SQLException {

        int num=repoRepositorio.getAll().get().size();
        Assertions.assertEquals(list.size(),num);

    }

    @Test
    @Order(2)
    void getById() throws SQLException {
        Optional<Repositorio> repositorio = repoRepositorio.getById("f64c5364-faa7-41b7-bca9-3b27f95d8fa8");
        Optional<Repositorio> repositorioNotFound = repoRepositorio.getById("be37-4930-b6ae");
        assertTrue(repositorio.isPresent());
        assertFalse(repositorioNotFound.isPresent());
    }

    @Test
    @Order(3)
    void save() throws SQLException {
        Repositorio repositorio =new Repositorio("","",new Date(),List.of(""),List.of(""));
        Optional<Repositorio> repositorioOptional  = repoRepositorio.save(repositorio);
        Assertions.assertEquals(repositorioOptional.orElse(null),repositorio);
    }

    @Test
    @Order(4)
    void update() throws SQLException {
        Repositorio repositorio =new Repositorio("","",new Date(),List.of(""),List.of(""));
        Optional<Repositorio> repositorioOptional  = repoRepositorio.update(repositorio);
        Assertions.assertEquals(repositorioOptional.orElse(null),repositorio);
    }

    @Test
    @Order(5)
    void delete() throws SQLException {
        Repositorio repositorio =new Repositorio("","",new Date(),List.of(""),List.of(""));
        Optional<Repositorio> repositorioOptional  = repoRepositorio.delete(repositorio);
        Assertions.assertEquals(repositorioOptional.orElse(null),repositorio);
    }

}