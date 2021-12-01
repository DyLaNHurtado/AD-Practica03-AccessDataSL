package repository;

import model.Commit;
import model.Programador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RepoProgramadorTest {

    private static RepoProgramador repoProgramador=new RepoProgramador();
    private static List<Programador> list =new ArrayList<>();

    @BeforeAll
    static void init(){
        list.add(new Programador("1376acc9-79a9-4bf1-9084-c82e9a07f432", "Barnie Stinson", new Date(), "2d1d1422-fede-4e27-8883-3ffdb1be1a7c",
                List.of("233b5d47-0ced-4e6f-8982-b2f95b6b25b9;81ee1211-760c-493d-968a-380e6af67363"),
                List.of("00efadeb-cdc5-456d-9b4a-bdd8d6fc2db5"),
                List.of("1f9b764e-570f-4041-a0c9-fc58a794eb0d"), List.of("20bcca63-7b60-4a43-bb10-4c9735587d16"),1500.0));

        list.add(new Programador("53269670-1586-49ac-9df5-62ddd55f96cc", "Rick Sanchez", new Date(), "1e89386d-be37-4930-b6ae-bcba6c9917b4",
                List.of("233b5d47-0ced-4e6f-8982-b2f95b6b25b9;81ee1211-760c-493d-968a-380e6af67363"),
                List.of("699b5f3f-8e62-41e1-ba08-1546f0ab5dfb;5b64bfd6-08e4-4325-b037-bd4fcfafe783"),
                List.of("7e96e277-26bc-4c08-a21e-6d92eb7638de"),
                List.of("20bcca63-7b60-4a43-bb10-4c9735587d16;4f119f1b-7ccf-49f4-b56f-fdace8589b1c"),1200.0));

        list.add(new Programador("606aba4c-b76e-4fa3-9eb8-48e20d729353", "Andres Iniesta", new Date(), "512a0695-3294-4c2c-86d9-4babd4485fa8",
                List.of("2d1d1422-fede-4e27-8883-3ffdb1be1a7c"), List.of("6e0bb43f-c639-4a79-8ea9-eb446d5bd624"),
                List.of("6c5b7c5a-d30b-400f-9c11-84dc2b49f01e"),
                List.of("20bcca63-7b60-4a43-bb10-4c9735587d16;cb231a1d-ffc8-4a64-b090-1334f5f4f740"),1500.0));

        list.add(new Programador("6a69db52-f903-4978-ac08-dc32831d362e", "Hulk Hogan", new Date(), "512a0695-3294-4c2c-86d9-4babd4485fa8",
                List.of("2d1d1422-fede-4e27-8883-3ffdb1be1a7c"), List.of("1ebfc166-3cd9-4303-a322-f57dc5ee3e5e"),
                List.of(""),
                List.of("20bcca63-7b60-4a43-bb10-4c9735587d16;cb231a1d-ffc8-4a64-b090-1334f5f4f740"),1300.0));

        list.add(new Programador("6ba7cbcb-7f95-4c6f-b735-2a5e0a363e52", "Mark Knofler", new Date(), "512a0695-3294-4c2c-86d9-4babd4485fa8",
                List.of("2d1d1422-fede-4e27-8883-3ffdb1be1a7c"), List.of("f89062d9-ba34-40a4-b6af-a21a0dc093be"),
                List.of(""),
                List.of("cb231a1d-ffc8-4a64-b090-1334f5f4f740"),2000.0));

        list.add(new Programador("6ba7cbcb-7f95-4c6f-b735-2a5e0a363e52", "Mark Knofler", new Date(), "512a0695-3294-4c2c-86d9-4babd4485fa8",
                List.of("2d1d1422-fede-4e27-8883-3ffdb1be1a7c"), List.of("f89062d9-ba34-40a4-b6af-a21a0dc093be"),
                List.of(""),
                List.of("cb231a1d-ffc8-4a64-b090-1334f5f4f740"),2000.0));

        list.add(new Programador("5cc55142-469b-4d42-9b9b-b9df2614bcc7", "Will Smith", new Date(), "2d1d1422-fede-4e27-8883-3ffdb1be1a7c",
                List.of("233b5d47-0ced-4e6f-8982-b2f95b6b25b9"), List.of(""),
                List.of(""),
                List.of("20bcca63-7b60-4a43-bb10-4c9735587d16"),3000.0));

        list.add(new Programador("d63f0d73-3f1b-4afd-b5d0-821449daa4a3", "Mike Wazowski", new Date(), "2d1d1422-fede-4e27-8883-3ffdb1be1a7c",
                List.of("f89062d9-ba34-40a4-b6af-a21a0dc093be;10f2db5c-a0c3-40ec-a1bf-a95cab6bebdf;3730b275-3ed2-4d77-8ff4-f5ce82ea98ea"),
                List.of("3a1690b0-b7f3-4303-8413-1f63578c3194"),
                List.of(""),
                List.of("20bcca63-7b60-4a43-bb10-4c9735587d16;4f119f1b-7ccf-49f4-b56f-fdace8589b1c"),1600.0));

    }


    @Test
    @Order(1)
    void getAll() throws SQLException {

        int num=repoProgramador.getAll().get().size();
        Assertions.assertEquals(list.size(),num);
    }

    @Test
    @Order(2)
    void getById() throws SQLException {
        Optional<Programador> programador = repoProgramador.getById("1376acc9-79a9-4bf1-9084-c82e9a07f432");
        Optional<Programador> programadorNotFound = repoProgramador.getById("8e4-4325-b037-bd4fcfafe783");
        assertTrue(programador.isPresent());
        assertFalse(programadorNotFound.isPresent());
    }

    @Test
    @Order(3)
    void save() throws SQLException {
        Programador programador =new Programador("","",new Date(),"",List.of(""),List.of(""),List.of(""),List.of(""),0.0);
        Optional<Programador> programadorOptional = repoProgramador.save(programador);
        Assertions.assertEquals(programadorOptional.orElse(null),programador);

    }

    @Test
    @Order(4)
    void update() throws SQLException {
        Programador programador =new Programador("","",new Date(),"",List.of(""),List.of(""),List.of(""),List.of(""),0.0);
        Optional<Programador> programadorOptional = repoProgramador.update(programador);
        Assertions.assertEquals(programadorOptional.orElse(null),programador);
    }

    @Test
    @Order(5)
    void delete() throws SQLException {
        Programador programador =new Programador("","",new Date(),"",List.of(""),List.of(""),List.of(""),List.of(""),0.0);
        Optional<Programador> programadorOptional = repoProgramador.delete(programador);
        Assertions.assertEquals(programadorOptional.orElse(null),programador);
    }

}