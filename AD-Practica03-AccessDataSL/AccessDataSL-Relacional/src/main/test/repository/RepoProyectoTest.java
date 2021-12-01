package repository;

import model.Commit;
import model.Proyecto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RepoProyectoTest {

    private static RepoProyecto repoProyecto=new RepoProyecto();
    private static List<Proyecto> list =new ArrayList<>();

    @BeforeAll
    static void init(){
        list.add(new Proyecto("81ee1211-760c-493d-968a-380e6af67363", "Power Project",
                "1376acc9-79a9-4bf1-9084-c82e9a07f432", 8000.0,
                new Date(), new Date(),
                List.of("20bcca63-7b60-4a43-bb10-4c9735587d16"), "f64c5364-faa7-41b7-bca9-3b27f95d8fa8", "1e89386d-be37-4930-b6ae-bcba6c9917b4"));

        list.add(new Proyecto("f89062d9-ba34-40a4-b6af-a21a0dc093be", "HR Project",
                "1376acc9-79a9-4bf1-9084-c82e9a07f432", 3000.0,
                new Date(), new Date(),
                List.of("20bcca63-7b60-4a43-bb10-4c9735587d16;4f119f1b-7ccf-49f4-b56f-fdace8589b1c"),
                "f1174508-8659-4654-82ce-af2704a152de", "1e89386d-be37-4930-b6ae-bcba6c9917b4"));

        list.add(new Proyecto("10f2db5c-a0c3-40ec-a1bf-a95cab6bebdf", "DF Project",
                "1376acc9-79a9-4bf1-9084-c82e9a07f432", 1000.0,
                new Date(), new Date(),
                List.of("20bcca63-7b60-4a43-bb10-4c9735587d16"),
                "4863c6e9-606f-42bb-aaff-6e961de25054", "1e89386d-be37-4930-b6ae-bcba6c9917b4"));

        list.add(new Proyecto("2d1d1422-fede-4e27-8883-3ffdb1be1a7c", "CD Project",
                "1376acc9-79a9-4bf1-9084-c82e9a07f432", 8500.0,
                new Date(), new Date(),
                List.of("cb231a1d-ffc8-4a64-b090-1334f5f4f740"),
                "ed38db33-7fd3-4242-91e4-a411d5fe3b1f", "2d1d1422-fede-4e27-8883-3ffdb1be1a7c"));

        list.add(new Proyecto("233b5d47-0ced-4e6f-8982-b2f95b6b25b9", "Logic Project",
                "1376acc9-79a9-4bf1-9084-c82e9a07f432", 5000.0,
                new Date(), new Date(),
                List.of("20bcca63-7b60-4a43-bb10-4c9735587d16;4f119f1b-7ccf-49f4-b56f-fdace8589b1c"),
                "2a11c73f-faa0-4346-82ac-fe6115ed4d6a", "512a0695-3294-4c2c-86d9-4babd4485fa8"));

        list.add(new Proyecto("3730b275-3ed2-4d77-8ff4-f5ce82ea98ea", "JS Project",
                "1376acc9-79a9-4bf1-9084-c82e9a07f432", 7100.0,
                new Date(), new Date(),
                List.of("20bcca63-7b60-4a43-bb10-4c9735587d16"), "eecd0485-8cc8-426b-81a5-ddffe5d83a67", "512a0695-3294-4c2c-86d9-4babd4485fa8"));

    }



    @Test
    @Order(1)
    void getAll() throws SQLException {

        int num=repoProyecto.getAll().get().size();
        Assertions.assertEquals(list.size(),num);
    }

    @Test
    @Order(2)
    void getById() throws SQLException {
        Optional<Proyecto> proyecto = repoProyecto.getById("81ee1211-760c-493d-968a-380e6af67363");
        Optional<Proyecto> proyectoNotFound = repoProyecto.getById("8e4-4325-b037-bd4fcfafe783");
        assertTrue(proyecto.isPresent());
        assertFalse(proyectoNotFound.isPresent());
    }

    @Test
    @Order(3)
    void save() throws SQLException {
        Proyecto proyecto =new Proyecto("","","606aba4c-b76e-4fa3-9eb8-48e20d729353",0.0,new Date(),new Date(),List.of(""),"","");
        Optional<Proyecto> proyectoOptional = repoProyecto.save(proyecto);
        Assertions.assertEquals(proyectoOptional.orElse(null),proyecto);

    }

    @Test
    @Order(4)
    void update() throws SQLException {
        Proyecto proyecto =new Proyecto("","1","606aba4c-b76e-4fa3-9eb8-48e20d729353",0.0,new Date(),new Date(),List.of(""),"","");
        Optional<Proyecto> proyectoOptional = repoProyecto.update(proyecto);
        Assertions.assertEquals(proyectoOptional.orElse(null),proyecto);
    }

    @Test
    @Order(5)
    void delete() throws SQLException {
        Proyecto proyecto =new Proyecto("","1","606aba4c-b76e-4fa3-9eb8-48e20d729353",0.0,new Date(),new Date(),List.of(""),"","");
        Optional<Proyecto> proyectoOptional = repoProyecto.delete(proyecto);
        Assertions.assertEquals(proyectoOptional.orElse(null),proyecto);
    }

}