import java.io.FileInputStream;
import java.util.Properties;
import javaConstants.Constants;

public class ReadPropertiesFile {
	public static void readConfig() throws Exception {
		try {

			Properties pro = new Properties();

			String path = System.getProperty("user.home")
					+ "/Desktop/cronJobProps.properties";

			pro.load(new FileInputStream(path));

			Constants.timeToRun = pro.getProperty("timeToRun");

		} catch (Exception e) {
			throw new Exception(e);
		}

	}

}