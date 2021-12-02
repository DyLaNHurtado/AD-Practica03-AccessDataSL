import utils.ApplicationProperties;

/**
 * Main que presentA las properties y empieza a mover la fachada.
 * @see Facade
 * @author Dylan Hurtado y Javier González
 * @version 02/09/21 - 1.0
 */
public class App {
    public static void main(String[] args) {
        ApplicationProperties properties = new ApplicationProperties();
        System.out.println("Bienvenid@s a " +
                properties.readProperty("app.title") + " "
                + properties.readProperty("app.version") +
                " hecho por : " +properties.readProperty("app.autores")+" de " +
                properties.readProperty("app.curso"));
        Facade facade = Facade.getInstance();
        // Chequeamos el sistema
        facade.checkService();

        // Iniciamos la base de datos al estado original en cada prueba
        if (properties.readProperty("database.init").equals("true"))
            facade.initDataBase();

        facade.selectJsonOrXml();
    }
}
