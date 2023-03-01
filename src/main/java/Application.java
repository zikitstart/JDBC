import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Application {

    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "2u8-ex8-vdK-WDU";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = (?)")) {

            statement.setInt(1, 7);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String firstName = "First name: " + resultSet.getString("first_name");
                String lastName = "Last name: " + resultSet.getString("last_name");
                String gender = "Gender: " + resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int cityId = resultSet.getInt("city_id");

                System.out.println(firstName);
                System.out.println(lastName);
                System.out.println(gender);
                System.out.println("Age: " + age);
                System.out.println("City id: " + cityId);
            }

            EmployeeDAO employeeDAO = new EmployeeDAOImpl();
            System.out.println("Получение конкретного объекта Employee по id:");
            System.out.println(employeeDAO.readById(9,connection));

            System.out.println("Получение списка всех объектов Employee из базы:");
            List<Employee> employeeList = new ArrayList<>(employeeDAO.readAll(connection));
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }

            //System.out.println("Изменение конкретного объекта Employee в базе по id:");
            //employeeDAO.updateById(6,"Анна","Серова","Ж",38,1, connection);

            //System.out.println("Создание(добавление) сущности Employee в таблицу:");
            //employeeDAO.create(new Employee("Гоги","Вартанов","М",57,2),connection);

            //System.out.println("Удаление конкретного объекта Employee из базы по id:");
            //employeeDAO.deleteById(15,connection);
        }
    }
}
