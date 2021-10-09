import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.SessionFactoryHandler;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = SessionFactoryHandler.openSessionFactory();
        Session session = sessionFactory.openSession();

        TableCreator tableCreator = new TableCreator(session);

        tableCreator.createTable();
        tableCreator.showTable();

        tableCreator.dropTable();
        tableCreator.showTable();

        SessionFactoryHandler.closeSessionFactory(sessionFactory);
    }
}