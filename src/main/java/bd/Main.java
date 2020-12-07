package bd;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		del();

	}

	public static void add() {
		try {
			String url = "jdbc:mysql://localhost/test?serverTimezone=Europe/Moscow&useSSL=false";
			String username = "root";
			String password = "";
			Scanner scan = new Scanner(System.in);
			Locale loc = new Locale("es", "ES");
			scan.useLocale(loc);
			String name1 = scan.nextLine();
			char s = '"';
			int number = scan.nextInt();

			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			try (Connection conn = DriverManager.getConnection(url, username, password)) {

				Statement statement = conn.createStatement();
				int rows = statement.executeUpdate(
						"INSERT INTO `name`(`Name`, `Number`) VALUES (" + s + name1 + s + "," + s + number + s + ")");
				System.out.printf("Добавлена %d строка", rows);
			}
		} catch (Exception ex) {
			System.out.println("Connection failed...");

			System.out.println(ex);
		}
	}

	public static void del() {
		try {
			String url = "jdbc:mysql://localhost/test?serverTimezone=Europe/Moscow&useSSL=false";
			String username = "root";
			String password = "";
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

			try (Connection conn = DriverManager.getConnection(url, username, password)) {

				Statement statement = conn.createStatement();

				int rows = statement.executeUpdate("DELETE FROM `name` WHERE `Number`='5445'");
				System.out.printf("%d row(s) deleted", rows);
			}
		} catch (Exception ex) {
			System.out.println("Connection failed...");

			System.out.println(ex);
		}
	}

	public static void see() {
		try {
			String url = "jdbc:mysql://localhost/test?serverTimezone=Europe/Moscow&useSSL=false";
			String username = "root";
			String password = "";
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

			try (Connection conn = DriverManager.getConnection(url, username, password)) {

				Statement statement = conn.createStatement();

				ResultSet resultSet = statement.executeQuery("SELECT `Name`, `Number` FROM `name` WHERE 1");
				while (resultSet.next()) {

					String name1 = resultSet.getString(1);
					int num = resultSet.getInt(2);

					System.out.println("Имя " + name1 + " " + "номер " + num);
				}
			}
		} catch (Exception ex) {
			System.out.println("Connection failed...");
			System.out.println(ex);
		}

	}
}
