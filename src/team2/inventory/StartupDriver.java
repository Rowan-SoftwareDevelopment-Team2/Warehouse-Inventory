package team2.inventory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import team2.inventory.controller.database.Connector;
import team2.inventory.view.ConfigGUI;
import team2.inventory.view.WelcomeScreen;

/** Startup Driver.
 * @author James A. Donnell Jr. */
public class StartupDriver {

	/** Configuration file name. */
	private static final String configFile = "db.config";
	/** Save location, dynamic to device. */
	public static String saveLocation = System.getenv("appdata") + "\\WIMS\\";

	/** Main method, creates connection and opens GUI.
	 * @param args Not utilized
	 * @throws SQLException TODO handle later. */
	public static void main(String[] args) throws SQLException {
		Connection connection = getConnection();

		@SuppressWarnings("unused")
		WelcomeScreen newRun = new WelcomeScreen(connection);
	}

	/** Gets connection and verifies folder structure exists.
	 * @return Connection */
	private static Connection getConnection() {
		// Verify folder exists, if not create it
		File mainDir = new File(saveLocation);
		if(!mainDir.exists())
			mainDir.mkdirs();

		// Verify config exists, if not create it
		File config = new File(saveLocation + configFile);
		if (!config.exists()) {
			createConfig(config);
		}

		try {
			return parseConfig(config);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/** Reads an existing config file and creates a database connection.
	 * @param config Configuration file.
	 * @return Connection.
	 * @throws SQLException Bad credentials/settings.
	 * @throws IOException Bad file. */
	private static Connection parseConfig(File config) throws SQLException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(config));
		String hostname = br.readLine();
		String port = br.readLine();
		String table = br.readLine();
		String username = br.readLine();
		String password = br.readLine();
		br.close();
		return Connector.createConnection(hostname, port, table, username, password);
	}

	/** Create the configurate file. Waits for completion.
	 * @param config Configuration file location. */
	private static void createConfig(File config) {
		ConfigGUI gui = new ConfigGUI(config);
		while(!gui.isDone()) { // Wait to finish
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}