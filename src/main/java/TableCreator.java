import core.LinkedPurchase;
import core.LinkedPurchaseKey;
import org.hibernate.Session;

import java.util.List;

public class TableCreator {
    private static final String SQL_SELECT = """
            SELECT st.id as student_id, c.id as course_id
            FROM PurchaseList AS pl
            JOIN Students AS st ON pl.student_name = st.name
            JOIN Courses AS c ON pl.course_name = c.name""";
    private static final String SQL_DROP = "DROP TABLE IF EXISTS LinkedPurchaseList";

    private final Session session;

    public TableCreator(Session session) {
        this.session = session;
    }

    public void createTable() {
        System.out.println("Создаем таблицу..");
        session.beginTransaction();
        List<Object[]> rows = session.createSQLQuery(SQL_SELECT).getResultList();
        for (Object[] row : rows) {
            session.saveOrUpdate(getLinkedPurchase(row));
        }
        session.getTransaction().commit();
        System.out.println("Таблица создана!");
    }

    private LinkedPurchase getLinkedPurchase(Object[] row) {
        int studentId = Integer.parseInt(row[0].toString());
        int courseId = Integer.parseInt(row[1].toString());

        LinkedPurchase linkedPurchase = new LinkedPurchase();
        linkedPurchase.setKey(getLinkedPurchaseKey(studentId, courseId));
        linkedPurchase.setStudentId(studentId);
        linkedPurchase.setCourseId(courseId);
        return linkedPurchase;
    }

    private LinkedPurchaseKey getLinkedPurchaseKey(int studentId, int courseId) {
        LinkedPurchaseKey key = new LinkedPurchaseKey();
        key.setStudentId(studentId);
        key.setCourseId(courseId);
        return key;
    }

    public void showTable() {
        System.out.println("Выводим информацию по таблице..");
        if (isTableExists()) {
            String hql = "from " + LinkedPurchase.class.getSimpleName();
            session.createQuery(hql).getResultList().forEach(System.out::println);
        } else {
            System.out.println("Таблицы LinkedPurchaseList не существует");
        }
    }

    private boolean isTableExists() {
        return session.createSQLQuery("SHOW tables").getResultList()
                .stream().anyMatch(s -> s.toString().equals("linkedpurchaselist"));
    }

    public void dropTable() {
        System.out.println("Удаляем таблицу..");
        session.beginTransaction();
        session.createSQLQuery(SQL_DROP).executeUpdate();
        session.getTransaction().commit();
        System.out.println("Таблица удалена!");
    }
}