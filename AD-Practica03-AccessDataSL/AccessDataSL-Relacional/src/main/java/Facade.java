import controller.*;
import database.DataBaseController;
import dto.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
/**
 * Patron Facade que organiza y facilita la llamada en el main
 * @see App
 * @author Dylan Hurtado y Javier González
 * @version 02/09/21 - 1.0
 */
public class Facade {
    private static Facade instance;

    private Facade() {
    }
    /**
     * Patron Singleton
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }
    /**
     * Comprobar servicio controller
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public void checkService() {
        DataBaseController controller = DataBaseController.getInstance();
        try {
            controller.open();
            Optional<ResultSet> rs = controller.select("SELECT 'Hello World'");
            if (rs.isPresent()) {
                rs.get().first();
                controller.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        }
    }
    /**
     * Iniciar la base de datos
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public void initDataBase() {
        String sqlFile = System.getProperty("user.dir") + File.separator + "sql" + File.separator + "init-db.sql";
        System.out.println("***************************\n"+
                "\t\tSQL FILE\n"+
                "***************************");
        System.out.println(sqlFile);
        DataBaseController controller = DataBaseController.getInstance();
        try {
            controller.open();
            controller.initData(sqlFile);
            controller.close();
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el fichero de datos iniciales: " + e.getMessage());
            System.exit(1);
        }
    }
    /**
     * Dar al usuario la opcion de generar xml o json
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public void selectJsonOrXml(){
        System.out.println("Selecciona una salida para visualizar las operaciones (xml or json): ");
        Scanner sc = new Scanner (System.in);
        String value=sc.next();

        while (!(value.equals("xml") || value.equals("json"))) {
            System.out.println("Error: No se ha introducido un valor valido");
            System.out.println("Introduzca json o introduzca xml :");
            value=sc.next();
        }

        if (value.equals("json")){
            salidaJSON();
        }
        if (value.equals("xml")){
            salidaXML();
        }
    }
    /**
     * Dar salida al JSON
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    private void salidaJSON(){
        System.out.println("-------------------------------\n"+
                "\t\tOPERACIONES CRUD\n"+
                "-------------------------------\n");

        System.out.println("**********************\n"+
                "\t\tCOMMITS\n"+
                "**********************");
        this.commitsJSON();

        System.out.println("**********************\n"+
                "\t\tDEPARTAMENTO\n"+
                "**********************");
        this.departamentoJSON();

        System.out.println("**********************\n"+
                "\t\tISSUE\n"+
                "**********************");
        this.issueJSON();

        System.out.println("**********************\n"+
                "\t\tPROGRAMADOR\n"+
                "**********************");
        this.programadorJSON();

        System.out.println("**********************\n"+
                "\t\tPROYECTO\n"+
                "**********************");
        this.proyectoJSON();

        System.out.println("**********************\n"+
                "\t\tREPOSITORIO\n"+
                "**********************");
        this.repositorioJSON();

        System.out.println("**********************\n"+
                "\t\tTECNOLOGIA\n"+
                "**********************");
        this.tecnologiaJSON();

        System.out.println("-------------------------------\n"+
                "\t\tOPERACIONES EXTRAS\n"+
                "-------------------------------\n");
        this.operacionesExtrasJSON();
    }
    /**
     * Dar salida al XML
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    private void salidaXML(){
        System.out.println("-------------------------------\n"+
                "\t\tOPERACIONES CRUD\n"+
                "-------------------------------\n");

        System.out.println("**********************\n"+
                "\t\tCOMMITS\n"+
                "**********************");
        this.commitsXML();

        System.out.println("**********************\n"+
                "\t\tDEPARTAMENTO\n"+
                "**********************");
        this.departamentoXML();

        System.out.println("**********************\n"+
                "\t\tISSUE\n"+
                "**********************");
        this.issueXML();

        System.out.println("**********************\n"+
                "\t\tPROGRAMADOR\n"+
                "**********************");
        this.programadorXML();

        System.out.println("**********************\n"+
                "\t\tPROYECTO\n"+
                "**********************");
        this.proyectoXML();

        System.out.println("**********************\n"+
                "\t\tREPOSITORIO\n"+
                "**********************");
        this.repositorioXML();

        System.out.println("**********************\n"+
                "\t\tTECNOLOGIA\n"+
                "**********************");
        this.tecnologiaXML();

        System.out.println("-------------------------------\n"+
                "\t\tOPERACIONES EXTRAS\n"+
                "-------------------------------\n");
        this.operacionesExtrasXML();
    }
    /**
     * Metodo salida de comits a xml
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    private void commitsXML() {
        CommitController commitController = CommitController.getInstance();

        System.out.println("GET Todos las Commits");
        commitController.getAllCommitsXML();

        System.out.println("GET Commit con ID = 5b64bfd6-08e4-4325-b037-bd4fcfafe783");
        commitController.getCommitByIdXML("5b64bfd6-08e4-4325-b037-bd4fcfafe783");

        System.out.println("POST Insertando Commit");
        CommitDTO commitDTO = CommitDTO.builder()
                .idCommit(UUID.randomUUID().toString())
                .titulo("tituloPrueba")
                .texto("textoPrueba")
                .fecha(LocalDate.now())
                .repositorio("f1174508-8659-4654-82ce-af2704a152de")
                .proyecto("f89062d9-ba34-40a4-b6af-a21a0dc093be")
                .autor("606aba4c-b76e-4fa3-9eb8-48e20d729353")
                .issue("6c5b7c5a-d30b-400f-9c11-84dc2b49f01e")
                .build();
        commitController.postCommitXML(commitDTO);

        System.out.println("UPDATE Commit con ID = 3a1690b0-b7f3-4303-8413-1f63578c3194");
        commitDTO = CommitDTO.builder()
                .idCommit("3a1690b0-b7f3-4303-8413-1f63578c3194")
                .titulo("tituloPrueba")
                .texto("textoPrueba")
                .fecha(LocalDate.now())
                .repositorio("f1174508-8659-4654-82ce-af2704a152de")
                .proyecto("f89062d9-ba34-40a4-b6af-a21a0dc093be")
                .autor("606aba4c-b76e-4fa3-9eb8-48e20d729353")
                .issue("6c5b7c5a-d30b-400f-9c11-84dc2b49f01e")
                .build();
        commitController.updateCommitXML(commitDTO);

        System.out.println("DELETE Commit con ID = 3a1690b0-b7f3-4303-8413-1f63578c3194");
        commitDTO = CommitDTO.builder()
                .idCommit("3a1690b0-b7f3-4303-8413-1f63578c3194")
                .fecha(LocalDate.now())
                .build();
        commitController.deleteCommitXML(commitDTO);
    }
    /**
     * Metodo salida de departamento a xml
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    private void departamentoXML() {
        DepartamentoController departamentoController = DepartamentoController.getInstance();

        System.out.println("GET Todos las Departamentos");
        departamentoController.getAllDepartamentosXML();

        System.out.println("GET Departamento con ID = 1e89386d-be37-4930-b6ae-bcba6c9917b4");
        departamentoController.getDepartamentoByIdXML("1e89386d-be37-4930-b6ae-bcba6c9917b4");

        System.out.println("POST Insertando Departamento");
        DepartamentoDTO departamentoDTO = DepartamentoDTO.builder()
                .idDepartamento(UUID.randomUUID().toString())
                .nombre("DepartamentoPrueba")
                .idJefe("")
                .trabajadores(List.of(""))
                .presupuesto(10000.0)
                .proyFinalizados(List.of(""))
                .proyDesarrollo(List.of(""))
                .presupuestoAnual(100000.0)
                .historialJefes(List.of(""))
                .build();
        departamentoController.postDepartamentoXML(departamentoDTO);

        System.out.println("UPDATE Departamento con ID = 512a0695-3294-4c2c-86d9-4babd4485fa8");
        departamentoDTO = DepartamentoDTO.builder()
                .idDepartamento("512a0695-3294-4c2c-86d9-4babd4485fa8")
                .nombre("DepartamentoPruebaUpdated")
                .idJefe("")
                .trabajadores(List.of(""))
                .presupuesto(10000.0)
                .proyFinalizados(List.of(""))
                .proyDesarrollo(List.of(""))
                .presupuestoAnual(100000.0)
                .historialJefes(List.of(""))
                .build();
        departamentoController.updateDepartamentoXML(departamentoDTO);

        System.out.println("DELETE Departamento con ID = 512a0695-3294-4c2c-86d9-4babd4485fa8");
        departamentoDTO = DepartamentoDTO.builder()
                .idDepartamento("512a0695-3294-4c2c-86d9-4babd4485fa8")
                .build();
        departamentoController.deleteDepartamentoXML(departamentoDTO);
    }
    /**
     * Metodo salida de issue a xml
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    private void issueXML() {

        IssueController issueController = IssueController.getInstance();

        System.out.println("GET Todos las Issues");
        issueController.getAllIssueXML();

        System.out.println("GET Issue con ID = 6c5b7c5a-d30b-400f-9c11-84dc2b49f01e");
        issueController.getIssueByIdXML("6c5b7c5a-d30b-400f-9c11-84dc2b49f01e");

        System.out.println("POST Insertando Issue");
        IssueDTO issueDTO = IssueDTO.builder()
                .idIssue(UUID.randomUUID().toString())
                .titulo("IssuePrueba")
                .texto("TextoPrueba")
                //Cuidado que es tipo Date
                .fecha(LocalDate.now())
                .programadores(List.of(""))
                .proyecto("2d1d1422-fede-4e27-8883-3ffdb1be1a7c")
                .repositorio("ed38db33-7fd3-4242-91e4-a411d5fe3b1f")
                .estado("pendiente")
                .build();
        issueController.postIssueXML(issueDTO);

        System.out.println("UPDATE Issue con ID = 1f9b764e-570f-4041-a0c9-fc58a794eb0d");
        issueDTO = IssueDTO.builder()
                .idIssue("1f9b764e-570f-4041-a0c9-fc58a794eb0d")
                .titulo("IssuePrueba")
                .texto("TextoPrueba")
                //Cuidado que es tipo Date
                .fecha(LocalDate.now())
                .programadores(List.of(""))
                .proyecto("2d1d1422-fede-4e27-8883-3ffdb1be1a7c")
                .repositorio("ed38db33-7fd3-4242-91e4-a411d5fe3b1f")
                .estado("pendiente")
                .build();
        issueController.updateIssueXML(issueDTO);

        System.out.println("DELETE Issue con ID = 1f9b764e-570f-4041-a0c9-fc58a794eb0d");
        issueDTO = IssueDTO.builder()
                .idIssue("1f9b764e-570f-4041-a0c9-fc58a794eb0d")
                .fecha(LocalDate.now())
                .build();
        issueController.deleteIssueXML(issueDTO);

    }
    /**
     * Metodo salida de programador a xml
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    private void programadorXML() {

        ProgramadorController programadorController = ProgramadorController.getInstance();

        System.out.println("GET Todos los Programadores");
        programadorController.getAllProgramadoresXML();

        System.out.println("GET Programador con ID = 1376acc9-79a9-4bf1-9084-c82e9a07f432");
        programadorController.getProgramadorByIdXML("1376acc9-79a9-4bf1-9084-c82e9a07f432");

        System.out.println("POST Insertando Programador");
        ProgramadorDTO programadorDTO = ProgramadorDTO.builder()
                .idProgramador(UUID.randomUUID().toString())
                .nombre("Prueba")
                .fechaAlta(LocalDate.now())
                //Cuidado que es tipo Date
                .idDepartamento("2d1d1422-fede-4e27-8883-3ffdb1be1a7c")
                .proyectosParticipa(List.of(""))
                .commits(List.of(""))
                .issues(List.of(""))
                .tecnologias(List.of(""))
                .salario(1350.0)
                .build();
        programadorController.postProgramadorXML(programadorDTO);

        System.out.println("UPDATE Programador con ID = 53269670-1586-49ac-9df5-62ddd55f96cc");
        programadorDTO = ProgramadorDTO.builder()
                .idProgramador("53269670-1586-49ac-9df5-62ddd55f96cc")
                .nombre("Prueba2")
                .fechaAlta(LocalDate.now())
                //Cuidado que es tipo Date
                .idDepartamento("2d1d1422-fede-4e27-8883-3ffdb1be1a7c")
                .proyectosParticipa(List.of(""))
                .commits(List.of(""))
                .issues(List.of(""))
                .tecnologias(List.of(""))
                .salario(1350.0)
                .build();
        programadorController.updateProgramadorXML(programadorDTO);

        System.out.println("DELETE Programador con ID = 1376acc9-79a9-4bf1-9084-c82e9a07f432");
        programadorDTO = ProgramadorDTO.builder()
                .idProgramador("1376acc9-79a9-4bf1-9084-c82e9a07f432")
                .fechaAlta(LocalDate.now())
                .build();
        programadorController.deleteProgramadorXML(programadorDTO);
    }

    /**
     * Metodo salida de proyecto a xml
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    private void proyectoXML() {
        ProyectoController proyectoController = ProyectoController.getInstance();

        System.out.println("GET Todos los Proyectos");
        proyectoController.getAllProyectosXML();

        System.out.println("GET Proyecto con ID = 81ee1211-760c-493d-968a-380e6af67363");
        proyectoController.getProyectoByIdXML("81ee1211-760c-493d-968a-380e6af67363");

        System.out.println("POST Insertando Proyecto");
        ProyectoDTO proyectoDTO = ProyectoDTO.builder()
                .idProyecto(UUID.randomUUID().toString())
                .nombre("Prueba")
                .idJefe("1376acc9-79a9-4bf1-9084-c82e9a07f432")
                .presupuesto(100.0)
                .fechaInicio(LocalDate.of(2015, 2,13))
                .fechaFin(LocalDate.now())
                .tecnologias(List.of(""))
                .idRepositorio("f64c5364-faa7-41b7-bca9-3b27f95d8fa8")
                .idDepartamento("1e89386d-be37-4930-b6ae-bcba6c9917b4")
                .build();
        proyectoController.postProyectoXML(proyectoDTO);

        System.out.println("UPDATE Proyecto con ID = 81ee1211-760c-493d-968a-380e6af67363");
        proyectoDTO = ProyectoDTO.builder()
                .idProyecto("81ee1211-760c-493d-968a-380e6af67363")
                .nombre("Prueba")
                .idJefe("1376acc9-79a9-4bf1-9084-c82e9a07f432")
                .presupuesto(100.0)
                .fechaInicio(LocalDate.of(2015, 2,13))
                .fechaFin(LocalDate.now())
                .tecnologias(List.of(""))
                .idRepositorio("f64c5364-faa7-41b7-bca9-3b27f95d8fa8")
                .idDepartamento("1e89386d-be37-4930-b6ae-bcba6c9917b4")
                .build();
        proyectoController.updateProyectoXML(proyectoDTO);

        System.out.println("DELETE Proyecto con ID = 81ee1211-760c-493d-968a-380e6af67363");
        proyectoDTO = ProyectoDTO.builder()
                .idProyecto("81ee1211-760c-493d-968a-380e6af67363")
                .fechaInicio(LocalDate.of(2015, 2,13))
                .fechaFin(LocalDate.now())
                .build();
        proyectoController.deleteProyectoXML(proyectoDTO);

    }
    /**
     * Metodo salida de repositorio a xml
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    private void repositorioXML() {

        RepositorioController repositorioController = RepositorioController.getInstance();

        System.out.println("GET Todos los Repositorios");
        repositorioController.getAllRepositoriosXML();

        System.out.println("GET Repositorio con ID = f64c5364-faa7-41b7-bca9-3b27f95d8fa8");
        repositorioController.getRepositorioByIdXML("f64c5364-faa7-41b7-bca9-3b27f95d8fa8");

        System.out.println("POST Insertando Repositorio");
        RepositorioDTO repositorioDTO = RepositorioDTO.builder()
                .idRepositorio(UUID.randomUUID().toString())
                .idProyecto("81ee1211-760c-493d-968a-380e6af67363")
                .fechaCreacion(LocalDate.now())
                .commits(List.of(""))
                .issues(List.of(""))
                .build();
        repositorioController.postRepositorioXML(repositorioDTO);

        System.out.println("UPDATE Repositorio con ID = f64c5364-faa7-41b7-bca9-3b27f95d8fa8");
        repositorioDTO = RepositorioDTO.builder()
                .idRepositorio("f64c5364-faa7-41b7-bca9-3b27f95d8fa8")
                .idProyecto("81ee1211-760c-493d-968a-380e6af67363")
                .fechaCreacion(LocalDate.now())
                .commits(List.of(""))
                .issues(List.of(""))
                .build();
        repositorioController.updateRepositorioXML(repositorioDTO);

        System.out.println("DELETE Repositorio con ID = f64c5364-faa7-41b7-bca9-3b27f95d8fa8");
        repositorioDTO = RepositorioDTO.builder()
                .idRepositorio("f64c5364-faa7-41b7-bca9-3b27f95d8fa8")
                .fechaCreacion(LocalDate.now())
                .build();
        repositorioController.deleteRepositorioXML(repositorioDTO);

    }
    /**
     * Metodo salida de tecnologia a xml
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    private void tecnologiaXML() {

        TecnologiaController tecnologiaController = TecnologiaController.getInstance();

        System.out.println("GET Todos las Tecnologias");
        tecnologiaController.getAllTecnologiasXML();

        System.out.println("GET Tecnologia con ID = 4f119f1b-7ccf-49f4-b56f-fdace8589b1c");
        tecnologiaController.getTecnologiaByIdXML("4f119f1b-7ccf-49f4-b56f-fdace8589b1c");

        System.out.println("POST Insertando Tecnologia");
        TecnologiaDTO tecnologiaDTO = TecnologiaDTO.builder()
                .idTecnologia(UUID.randomUUID().toString())
                .nombre("NodeJS")
                .build();
        tecnologiaController.postTecnologiaXML(tecnologiaDTO);

        System.out.println("UPDATE Tecnologia con ID = 4f119f1b-7ccf-49f4-b56f-fdace8589b1c");
        tecnologiaDTO = TecnologiaDTO.builder()
                .idTecnologia("4f119f1b-7ccf-49f4-b56f-fdace8589b1c")
                .nombre("Swift")
                .build();
        tecnologiaController.updateTecnologiaXML(tecnologiaDTO);

        System.out.println("DELETE Tecnologia con ID = 4f119f1b-7ccf-49f4-b56f-fdace8589b1c");
        tecnologiaDTO = TecnologiaDTO.builder()
                .idTecnologia("4f119f1b-7ccf-49f4-b56f-fdace8589b1c")
                .build();
        tecnologiaController.deleteTecnologiaXML(tecnologiaDTO);

    }

    /**
     * Metodo contenedor de las operaciones del apartado 5 en XML
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    private void operacionesExtrasXML() {
        operacion1XML();
        operacion2XML();
        operacion3XML();
        operacion4XML();
        operacion5XML();

    }
    /**
     * Operacion 1 XML
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     * @return De un departamento, sus proyectos y trabajadores con informacion completa
     */
    private void operacion1XML() {
        System.out.println("**Operacion 1:\n" +
                "**Obtener de un departamento, los proyectos (información completa) y trabajadores\n" +
                "asociados con sus datos completos**" );
        DepartamentoController departamentoController = DepartamentoController.getInstance();
        DepartamentoDTO departamentoDTO = DepartamentoDTO.builder()
                .idDepartamento("2d1d1422-fede-4e27-8883-3ffdb1be1a7c")
                .build();
        departamentoController.getDepartamentoFullInfoXML(departamentoDTO);
    }
    /**
     * Operacion 2 XML
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     * @return Listado de issues abiertas por proyecto que no se hayan consolidado en commits
     */
    private void operacion2XML() {
        System.out.println("**Operacion 2:\n" +
                "**Listado de issues abiertas por proyecto que no se hayan consolidado en commits.**" );
        IssueController issueController = IssueController.getInstance();
        IssueDTO issueDTO = IssueDTO.builder()
                .idIssue("7e96e277-26bc-4c08-a21e-6d92eb7638de")
                .proyecto("f89062d9-ba34-40a4-b6af-a21a0dc093be")
                .build();
        issueController.getAllAbiertasByProyectoXML(issueDTO);
    }
    /**
     * Operacion 3 XML
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     * @return Programadores de un proyecto ordenados por número de commits
     */
    private void operacion3XML() {
        System.out.println("**Operacion 3:\n" +
                "**Programadores de un proyecto ordenados por número de commits.**" );
        ProgramadorController programadorController = ProgramadorController.getInstance();
        ProyectoDTO proyectoDTO = ProyectoDTO.builder()
                .idProyecto("f89062d9-ba34-40a4-b6af-a21a0dc093be")
                .build();
        programadorController.getAllByProyectoSortByCommitsXML(proyectoDTO);
    }

    /**
     * Operacion 4 XML
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     * @return Programadores con su productividad completa: proyectos en los que participa, commits e issues asignadas con info completa
     */
    private void operacion4XML() {
        System.out.println("**Operacion 4:\n" +
                "**Programadores con su productividad completa: proyectos\n , commits" +
                " (información completa) e issues asignadas (información completa).**");
        ProgramadorController programadorController = ProgramadorController.getInstance();
        programadorController.getAllProgramadoresInfoXML();
    }
    /**
     * Operacion 5 XML
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     * @return Obtener los tres proyectos más caros en base a su presupuesto asignado y el salario de cada trabajador que participa
     */
    private void operacion5XML() {
        System.out.println("**Operacion 5:\n" +
                "**Obtener los tres proyectos más caros en base a su presupuesto asignado y el salario\n" +
                "de cada trabajador que participa**");
        ProyectoController proyectoController = ProyectoController.getInstance();
        proyectoController.getProyectosMasCarosXML();
    }

    /**
     * Metodo salida de comits a JSON
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    private void commitsJSON() {
        CommitController commitController = CommitController.getInstance();

        System.out.println("GET Todos las Commits");
        System.out.println(commitController.getAllCommitsJSON());

        System.out.println("GET Commit con ID = 5b64bfd6-08e4-4325-b037-bd4fcfafe783");
        System.out.println(commitController.getCommitByIdJSON("5b64bfd6-08e4-4325-b037-bd4fcfafe783"));

        System.out.println("POST Insertando Commit");
        CommitDTO commitDTO = CommitDTO.builder()
                .idCommit(UUID.randomUUID().toString())
                .titulo("tituloPrueba")
                .texto("textoPrueba")
                //ojo con el date que es DATE y no string
                .fecha(LocalDate.now())
                .repositorio("f1174508-8659-4654-82ce-af2704a152de")
                .proyecto("f89062d9-ba34-40a4-b6af-a21a0dc093be")
                .autor("606aba4c-b76e-4fa3-9eb8-48e20d729353")
                .issue("6c5b7c5a-d30b-400f-9c11-84dc2b49f01e")
                .build();
        System.out.println(commitController.postCommitJSON(commitDTO));

        System.out.println("UPDATE Commit con ID = 3a1690b0-b7f3-4303-8413-1f63578c3194");
        commitDTO = CommitDTO.builder()
                .idCommit("3a1690b0-b7f3-4303-8413-1f63578c3194")
                .titulo("tituloPrueba")
                .texto("textoPrueba")
                //ojo con el date que es DATE y no string
                .fecha(LocalDate.now())
                .repositorio("f1174508-8659-4654-82ce-af2704a152de")
                .proyecto("f89062d9-ba34-40a4-b6af-a21a0dc093be")
                .autor("606aba4c-b76e-4fa3-9eb8-48e20d729353")
                .issue("6c5b7c5a-d30b-400f-9c11-84dc2b49f01e")
                .build();
        System.out.println(commitController.updateCommitJSON(commitDTO));

        System.out.println("DELETE Commit con ID = 3a1690b0-b7f3-4303-8413-1f63578c3194");
        commitDTO = CommitDTO.builder()
                .idCommit("3a1690b0-b7f3-4303-8413-1f63578c3194")
                .fecha(LocalDate.now())
                .build();
        System.out.println(commitController.deleteCommitJSON(commitDTO));
    }
    /**
     * Metodo salida de departamento a JSON
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */

    private void departamentoJSON() {
        DepartamentoController departamentoController = DepartamentoController.getInstance();

        System.out.println("GET Todos las Departamentos");
        System.out.println(departamentoController.getAllDepartamentosJSON());

        System.out.println("GET Departamento con ID = 1e89386d-be37-4930-b6ae-bcba6c9917b4");
        System.out.println(departamentoController.getDepartamentoByIdJSON("1e89386d-be37-4930-b6ae-bcba6c9917b4"));

        System.out.println("POST Insertando Departamento");
        DepartamentoDTO departamentoDTO = DepartamentoDTO.builder()
                .idDepartamento(UUID.randomUUID().toString())
                .nombre("DepartamentoPrueba")
                .idJefe("")
                .trabajadores(List.of(""))
                .presupuesto(10000.0)
                .proyFinalizados(List.of(""))
                .proyDesarrollo(List.of(""))
                .presupuestoAnual(100000.0)
                .historialJefes(List.of(""))
                .build();
        System.out.println(departamentoController.postDepartamentoJSON(departamentoDTO));

        System.out.println("UPDATE Departamento con ID = 512a0695-3294-4c2c-86d9-4babd4485fa8");
        departamentoDTO = DepartamentoDTO.builder()
                .idDepartamento("512a0695-3294-4c2c-86d9-4babd4485fa8")
                .nombre("DepartamentoPruebaUpdated")
                .idJefe("")
                .trabajadores(List.of(""))
                .presupuesto(10000.0)
                .proyFinalizados(List.of(""))
                .proyDesarrollo(List.of(""))
                .presupuestoAnual(100000.0)
                .historialJefes(List.of(""))
                .build();
        System.out.println(departamentoController.updateDepartamentoJSON(departamentoDTO));

        System.out.println("DELETE Departamento con ID = 512a0695-3294-4c2c-86d9-4babd4485fa8");
        departamentoDTO = DepartamentoDTO.builder()
                .idDepartamento("512a0695-3294-4c2c-86d9-4babd4485fa8")
                .build();
        System.out.println(departamentoController.deleteDepartamentoJSON(departamentoDTO));
    }
    /**
     * Metodo salida de issues a JSON
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    private void issueJSON() {
        IssueController issueController = IssueController.getInstance();

        System.out.println("GET Todos las Issues");
        System.out.println(issueController.getAllIssuesJSON());

        System.out.println("GET Issue con ID = 6c5b7c5a-d30b-400f-9c11-84dc2b49f01e");
        System.out.println(issueController.getIssueByIdJSON("6c5b7c5a-d30b-400f-9c11-84dc2b49f01e"));

        System.out.println("POST Insertando Issue");
        IssueDTO issueDTO = IssueDTO.builder()
                .idIssue(UUID.randomUUID().toString())
                .titulo("IssuePrueba")
                .texto("TextoPrueba")
                //Cuidado que es tipo Date
                .fecha(LocalDate.now())
                .programadores(List.of(""))
                .proyecto("2d1d1422-fede-4e27-8883-3ffdb1be1a7c")
                .repositorio("ed38db33-7fd3-4242-91e4-a411d5fe3b1f")
                .estado("pendiente")
                .build();
        System.out.println(issueController.postIssueJSON(issueDTO));

        System.out.println("UPDATE Issue con ID = 1f9b764e-570f-4041-a0c9-fc58a794eb0d");
        issueDTO = IssueDTO.builder()
                .idIssue("1f9b764e-570f-4041-a0c9-fc58a794eb0d")
                .titulo("IssuePrueba")
                .texto("TextoPrueba")
                //Cuidado que es tipo Date
                .fecha(LocalDate.now())
                .programadores(List.of(""))
                .proyecto("2d1d1422-fede-4e27-8883-3ffdb1be1a7c")
                .repositorio("ed38db33-7fd3-4242-91e4-a411d5fe3b1f")
                .estado("pendiente")
                .build();
        System.out.println(issueController.updateIssueJSON(issueDTO));

        System.out.println("DELETE Issue con ID = 1f9b764e-570f-4041-a0c9-fc58a794eb0d");
        issueDTO = IssueDTO.builder()
                .idIssue("1f9b764e-570f-4041-a0c9-fc58a794eb0d")
                .fecha(LocalDate.now())
                .build();
        System.out.println(issueController.deleteIssueJSON(issueDTO));
    }
    /**
     * Metodo salida de programadores a JSON
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    private void programadorJSON() {
        ProgramadorController programadorController = ProgramadorController.getInstance();

        System.out.println("GET Todos los Programadores");
        System.out.println(programadorController.getAllProgramadorsJSON());

        System.out.println("GET Programador con ID = 1376acc9-79a9-4bf1-9084-c82e9a07f432");
        System.out.println(programadorController.getProgramadorByIdJSON("1376acc9-79a9-4bf1-9084-c82e9a07f432"));

        System.out.println("POST Insertando Programador");
        ProgramadorDTO programadorDTO = ProgramadorDTO.builder()
                .idProgramador(UUID.randomUUID().toString())
                .nombre("Prueba")
                .fechaAlta(LocalDate.now())
                //Cuidado que es tipo Date
                .idDepartamento("2d1d1422-fede-4e27-8883-3ffdb1be1a7c")
                .proyectosParticipa(List.of(""))
                .commits(List.of(""))
                .issues(List.of(""))
                .tecnologias(List.of(""))
                .salario(1350.0)
                .build();
        System.out.println(programadorController.postProgramadorJSON(programadorDTO));

        System.out.println("UPDATE Programador con ID = 53269670-1586-49ac-9df5-62ddd55f96cc");
        programadorDTO = ProgramadorDTO.builder()
                .idProgramador("53269670-1586-49ac-9df5-62ddd55f96cc")
                .nombre("Prueba2")
                .fechaAlta(LocalDate.now())
                //Cuidado que es tipo Date
                .idDepartamento("2d1d1422-fede-4e27-8883-3ffdb1be1a7c")
                .proyectosParticipa(List.of(""))
                .commits(List.of(""))
                .issues(List.of(""))
                .tecnologias(List.of(""))
                .salario(1350.0)
                .build();
        System.out.println(programadorController.updateProgramadorJSON(programadorDTO));

        System.out.println("DELETE Programador con ID = 1376acc9-79a9-4bf1-9084-c82e9a07f432");
        programadorDTO = ProgramadorDTO.builder()
                .idProgramador("1376acc9-79a9-4bf1-9084-c82e9a07f432")
                .fechaAlta(LocalDate.now())
                .build();
        System.out.println(programadorController.deleteProgramadorJSON(programadorDTO));
    }

    /**
     * Metodo salida de proyectos a JSON
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    private void proyectoJSON() {
        ProyectoController proyectoController = ProyectoController.getInstance();

        System.out.println("GET Todos los Proyectos");
        System.out.println(proyectoController.getAllProyectosJSON());

        System.out.println("GET Proyecto con ID = 81ee1211-760c-493d-968a-380e6af67363");
        System.out.println(proyectoController.getProyectoByIdJSON("81ee1211-760c-493d-968a-380e6af67363"));

        System.out.println("POST Insertando Proyecto");
        ProyectoDTO proyectoDTO = ProyectoDTO.builder()
                .idProyecto(UUID.randomUUID().toString())
                .nombre("Prueba")
                .idJefe("1376acc9-79a9-4bf1-9084-c82e9a07f432")
                .presupuesto(100.0)
                .fechaInicio(LocalDate.of(2015, 2,13))
                .fechaFin(LocalDate.now())
                .tecnologias(List.of(""))
                .idRepositorio("f64c5364-faa7-41b7-bca9-3b27f95d8fa8")
                .idDepartamento("1e89386d-be37-4930-b6ae-bcba6c9917b4")
                .build();
        System.out.println(proyectoController.postProyectoJSON(proyectoDTO));

        System.out.println("UPDATE Proyecto con ID = 81ee1211-760c-493d-968a-380e6af67363");
        proyectoDTO = ProyectoDTO.builder()
                .idProyecto("81ee1211-760c-493d-968a-380e6af67363")
                .nombre("Prueba")
                .idJefe("1376acc9-79a9-4bf1-9084-c82e9a07f432")
                .presupuesto(100.0)
                .fechaInicio(LocalDate.of(2015, 2,13))
                .fechaFin(LocalDate.now())
                .tecnologias(List.of(""))
                .idRepositorio("f64c5364-faa7-41b7-bca9-3b27f95d8fa8")
                .idDepartamento("1e89386d-be37-4930-b6ae-bcba6c9917b4")
                .build();
        System.out.println(proyectoController.updateProyectoJSON(proyectoDTO));

        System.out.println("DELETE Proyecto con ID = 81ee1211-760c-493d-968a-380e6af67363");
        proyectoDTO = ProyectoDTO.builder()
                .idProyecto("81ee1211-760c-493d-968a-380e6af67363")
                .fechaInicio(LocalDate.of(2015, 2,13))
                .fechaFin(LocalDate.now())
                .build();
        System.out.println(proyectoController.deleteProyectoJSON(proyectoDTO));
    }

    /**
     * Metodo salida de repositorio a JSON
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    private void repositorioJSON() {
        RepositorioController repositorioController = RepositorioController.getInstance();

        System.out.println("GET Todos los Repositorios");
        System.out.println(repositorioController.getAllRepositoriosJSON());

        System.out.println("GET Repositorio con ID = f64c5364-faa7-41b7-bca9-3b27f95d8fa8");
        System.out.println(repositorioController.getRepositorioByIdJSON("f64c5364-faa7-41b7-bca9-3b27f95d8fa8"));

        System.out.println("POST Insertando Repositorio");
        RepositorioDTO repositorioDTO = RepositorioDTO.builder()
                .idRepositorio(UUID.randomUUID().toString())
                .idProyecto("81ee1211-760c-493d-968a-380e6af67363")
                .fechaCreacion(LocalDate.now())
                .commits(List.of(""))
                .issues(List.of(""))
                .build();
        System.out.println(repositorioController.postRepositorioJSON(repositorioDTO));

        System.out.println("UPDATE Repositorio con ID = f64c5364-faa7-41b7-bca9-3b27f95d8fa8");
        repositorioDTO = RepositorioDTO.builder()
                .idRepositorio("f64c5364-faa7-41b7-bca9-3b27f95d8fa8")
                .idProyecto("81ee1211-760c-493d-968a-380e6af67363")
                .fechaCreacion(LocalDate.now())
                .commits(List.of(""))
                .issues(List.of(""))
                .build();
        System.out.println(repositorioController.updateRepositorioJSON(repositorioDTO));

        System.out.println("DELETE Repositorio con ID = f64c5364-faa7-41b7-bca9-3b27f95d8fa8");
        repositorioDTO = RepositorioDTO.builder()
                .idRepositorio("f64c5364-faa7-41b7-bca9-3b27f95d8fa8")
                .fechaCreacion(LocalDate.now())
                .build();
        System.out.println(repositorioController.deleteRepositorioJSON(repositorioDTO));
    }
    /**
     * Metodo salida de tecnologias a JSON
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    private void tecnologiaJSON() {
        TecnologiaController tecnologiaController = TecnologiaController.getInstance();

        System.out.println("GET Todos las Tecnologias");
        System.out.println(tecnologiaController.getAllTecnologiasJSON());

        System.out.println("GET Tecnologia con ID = 4f119f1b-7ccf-49f4-b56f-fdace8589b1c");
        System.out.println(tecnologiaController.getTecnologiaByIdJSON("4f119f1b-7ccf-49f4-b56f-fdace8589b1c"));

        System.out.println("POST Insertando Tecnologia");
        TecnologiaDTO tecnologiaDTO = TecnologiaDTO.builder()
                .idTecnologia(UUID.randomUUID().toString())
                .nombre("NodeJS")
                .build();
        System.out.println(tecnologiaController.postTecnologiaJSON(tecnologiaDTO));

        System.out.println("UPDATE Tecnologia con ID = 4f119f1b-7ccf-49f4-b56f-fdace8589b1c");
        tecnologiaDTO = TecnologiaDTO.builder()
                .idTecnologia("4f119f1b-7ccf-49f4-b56f-fdace8589b1c")
                .nombre("Swift")
                .build();
        System.out.println(tecnologiaController.updateTecnologiaJSON(tecnologiaDTO));

        System.out.println("DELETE Tecnologia con ID = 4f119f1b-7ccf-49f4-b56f-fdace8589b1c");
        tecnologiaDTO = TecnologiaDTO.builder()
                .idTecnologia("4f119f1b-7ccf-49f4-b56f-fdace8589b1c")
                .build();
        System.out.println(tecnologiaController.deleteTecnologiaJSON(tecnologiaDTO));
    }
    /**
     * Metodo contenedor de las operaciones del apartado 5 en JSON
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    private void operacionesExtrasJSON(){
        operacion1JSON();
        operacion2JSON();
        operacion3JSON();
        operacion4JSON();
        operacion5JSON();
    }
    /**
     * Operacion 1 JSON
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     * @return De un departamento, sus proyectos y trabajadores con informacion completa
     */
        private void operacion1JSON(){
            System.out.println("**Operacion 1:\n" +
                    "**Obtener de un departamento, los proyectos (información completa) y trabajadores\n" +
                    "asociados con sus datos completos**" );
            DepartamentoController departamentoController = DepartamentoController.getInstance();
            DepartamentoDTO departamentoDTO = DepartamentoDTO.builder()
                    .idDepartamento("2d1d1422-fede-4e27-8883-3ffdb1be1a7c")
                    .build();
            System.out.println(departamentoController.getDepartamentoFullInfoJSON(departamentoDTO));
        }

    /**
     * Operacion 2 JSON
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     * @return Listado de issues abiertas por proyecto que no se hayan consolidado en commits
     */
    private void operacion2JSON(){
        System.out.println("**Operacion 2:\n" +
                "**Listado de issues abiertas por proyecto que no se hayan consolidado en commits.**" );
        IssueController issueController = IssueController.getInstance();
        IssueDTO issueDTO = IssueDTO.builder()
                .idIssue("7e96e277-26bc-4c08-a21e-6d92eb7638de")
                .proyecto("f89062d9-ba34-40a4-b6af-a21a0dc093be")
                .build();
        System.out.println(issueController.getAllAbiertasByProyectoJSON(issueDTO));
    }
    /**
     * Operacion 3 JSON
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     * @return Programadores de un proyecto ordenados por número de commits
     */
    private void operacion3JSON(){
        System.out.println("**Operacion 3:\n" +
                "**Programadores de un proyecto ordenados por número de commits.**" );
        ProgramadorController programadorController = ProgramadorController.getInstance();
        ProyectoDTO proyectoDTO = ProyectoDTO.builder()
                .idProyecto("f89062d9-ba34-40a4-b6af-a21a0dc093be")
                .build();
        System.out.println(programadorController.getAllByProyectoSortByCommitsJSON(proyectoDTO));
    }
    /**
     * Operacion 4 JSON
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     * @return Programadores con su productividad completa: proyectos en los que participa, commits e issues asignadas con info completa
     */
    private void operacion4JSON(){
        System.out.println("**Operacion 4:\n" +
                "**Programadores con su productividad completa: proyectos\n , commits" +
                " (información completa) e issues asignadas (información completa).**");
        ProgramadorController programadorController = ProgramadorController.getInstance();
        System.out.println(programadorController.getAllProgramadoresInfoJSON());
    }
    /**
     * Operacion 5 JSON
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     * @return Obtener los tres proyectos más caros en base a su presupuesto asignado y el salario de cada trabajador que participa
     */
    private void operacion5JSON(){
        System.out.println("**Operacion 5:\n" +
                "**Obtener los tres proyectos más caros en base a su presupuesto asignado y el salario\n" +
                "de cada trabajador que participa**");
        ProyectoController proyectoController = ProyectoController.getInstance();
        System.out.println(proyectoController.getProyectosMasCarosJSON());
    }

}
