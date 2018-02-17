import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;
import fruithost.ConnectionController;
import fruithost.IConnector;
import javafx.scene.control.*;

public class MySQL implements IConnector {
	@Override
	public void setConnectionData(ConnectionController controller) {
		int start = 2;

		TextField hostname = new TextField("localhost");
		hostname.setId("hostname");
		controller.addComponent(new Label("Host Name/IP Address:"), 0, start, 0, 0);
		controller.addComponent(hostname, 1, start, 0, 0);

		++start;

		TextField port = new TextField("3306");
		port.setId("port");
		controller.addComponent(new Label("Port:"), 0, start, 0, 0);
		controller.addComponent(port, 1, start, 0, 0);

		++start;

		TextField username = new TextField("root");
		username.setId("username");
		controller.addComponent(new Label("User Name:"), 0, start, 0, 0);
		controller.addComponent(username, 1, start, 0, 0);

		++start;

		PasswordField password = new PasswordField();
		password.setId("password");
		controller.addComponent(new Label("Password:"), 0, start, 0, 0);
		controller.addComponent(password, 1, start, 0, 0);

		++start;
		CheckBox save = new CheckBox("Save Password");
		save.setId("save_password");
		controller.addComponent(save, 1, start, 0, 0);
	}

	@Override
	public boolean checkConnection(Driver driver, HashMap<String, Object> data) throws Exception {
		Connection connection	= null;
		Properties config		= new Properties();
		config.put("user",		data.get("username"));
		config.put("password",	data.get("password"));
		
		try {
			connection = driver.connect("jdbc:mysql://" + data.get("hostname") + ":" + data.get("port") + "/", config);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getLocalizedMessage());
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				/* Do Nothing */
			}
		}
	}
}
