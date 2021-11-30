package repository;

import model.Commit;
import model.Departamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RepoDepartamentoTest {
    private static RepoDepartamento repoDepartamento=new RepoDepartamento();
    private static List<Departamento> list =new ArrayList<>();

    @BeforeAll
    static void init() {
        list.add(new Departamento("1e89386d-be37-4930-b6ae-bcba6c9917b4", "Recursos Humanos", "53269670-1586-49ac-9df5-62ddd55f96cc",
                List.of("ba2bfe86-855d-4f2c-bb92-2ad8f1db788e;53269670-1586-49ac-9df5-62ddd55f96cc"), 5000.0,
                List.of("81ee1211-760c-493d-968a-380e6af67363"), List.of("f89062d9-ba34-40a4-b6af-a21a0dc093be"), 25000.0,
                List.of("606aba4c-b76e-4fa3-9eb8-48e20d729353;53269670-1586-49ac-9df5-62ddd55f96cc12q")));

        list.add(new Departamento("2d1d1422-fede-4e27-8883-3ffdb1be1a7c", "Marketing", "1376acc9-79a9-4bf1-9084-c82e9a07f432",
                List.of("1376acc9-79a9-4bf1-9084-c82e9a07f432;5cc55142-469b-4d42-9b9b-b9df2614bcc7;d63f0d73-3f1b-4afd-b5d0-821449daa4a3"),
                12000.0, List.of(""),
                List.of("f89062d9-ba34-40a4-b6af-a21a0dc093be;10f2db5c-a0c3-40ec-a1bf-a95cab6bebdf"), 100000.0,
                List.of("606aba4c-b76e-4fa3-9eb8-48e20d729353;d63f0d73-3f1b-4afd-b5d0-821449daa4a3;1376acc9-79a9-4bf1-9084-c82e9a07f432")));

        list.add(new Departamento("512a0695-3294-4c2c-86d9-4babd4485fa8", "Logistica", "606aba4c-b76e-4fa3-9eb8-48e20d729353",
                List.of("606aba4c-b76e-4fa3-9eb8-48e20d729353;6a69db52-f903-4978-ac08-dc32831d362e;6ba7cbcb-7f95-4c6f-b735-2a5e0a363e52"),
                15500.0, List.of("3730b275-3ed2-4d77-8ff4-f5ce82ea98ea;81ee1211-760c-493d-968a-380e6af67363"),
                List.of("f89062d9-ba34-40a4-b6af-a21a0dc093be"), 92800.0,
                List.of("5cc55142-469b-4d42-9b9b-b9df2614bcc7;606aba4c-b76e-4fa3-9eb8-48e20d729353")));


    }

    @Test
    @Order(1)
    void getAll() throws SQLException {

        int num=repoDepartamento.getAll().get().size();
        Assertions.assertEquals(list.size(),num);

    }

    @Test
    @Order(2)
    void getById() throws SQLException {
        Optional<Departamento> departamento = repoDepartamento.getById("1e89386d-be37-4930-b6ae-bcba6c9917b4");
        Optional<Departamento> departamentoNotFound = repoDepartamento.getById("be37-4930-b6ae");
        assertTrue(departamento.isPresent());
        assertFalse(departamentoNotFound.isPresent());
    }

    @Test
    @Order(3)
    void save() throws SQLException {
        Departamento departamento =new Departamento("","","",List.of(""),0.0,List.of(""),List.of(""),0.0,List.of(""));
        Optional<Departamento> departamentoOptional  = repoDepartamento.save(departamento);
        Assertions.assertEquals(departamentoOptional.orElse(null),departamento);
    }

    @Test
    @Order(4)
    void update() throws SQLException {
        Departamento departamento =new Departamento("","1","1",List.of(""),0.0,List.of(""),List.of(""),0.0,List.of(""));
        Optional<Departamento> departamentoOptional  = repoDepartamento.update(departamento);
        Assertions.assertEquals(departamentoOptional.orElse(null),departamento);
    }

    @Test
    @Order(5)
    void delete() throws SQLException {
        Departamento departamento =new Departamento("","1","1",List.of(""),0.0,List.of(""),List.of(""),0.0,List.of(""));
        Optional<Departamento> departamentoOptional  = repoDepartamento.delete(departamento);
        Assertions.assertEquals(departamentoOptional.orElse(null),departamento);
    }
}