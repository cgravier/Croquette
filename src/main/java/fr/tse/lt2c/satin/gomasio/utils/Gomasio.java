package fr.tse.lt2c.satin.gomasio.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;

import com.clarkparsia.empire.Empire;
import com.clarkparsia.empire.config.ConfigKeys;
import com.clarkparsia.empire.config.EmpireConfiguration;
import com.clarkparsia.empire.sesametwo.OpenRdfEmpireModule;
import com.clarkparsia.empire.sesametwo.RepositoryFactoryKeys;

public class Gomasio {

	/**
	 * Get a remote Sesame Entity Manager from a property File that contains the
	 * following properties :
	 * <ul>
	 * <li>URL : Url of the Sesame service. E.g :
	 * http://localhost:8080/openrdf-sesame/</li>
	 * <li>Repository : The repository hosted by Sesame. E.g. : SWDF if you
	 * created a SWDF named repository in Sesame WorkbenchUI</li>
	 * </ul>
	 * 
	 * @param propertyFile
	 * @return
	 */
	public static EntityManager getSesameEntityManager(String propertyFile) {

		System.setProperty("gomasio.properties", propertyFile);

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(propertyFile));
		} catch (IOException e) {
		}

		String url = properties.getProperty("URL");
		String repo = properties.getProperty("Repository");

		// Build Empire configuration.
		EmpireConfiguration empireCfg = new EmpireConfiguration();

		// Initialize Empire.
		Empire.init(empireCfg, new OpenRdfEmpireModule());

		// Configure target repository.
		Map<Object, Object> map = new HashMap<Object, Object>();

		map.put(ConfigKeys.FACTORY, "sesame");
		map.put(RepositoryFactoryKeys.URL, url);
		map.put(RepositoryFactoryKeys.REPO, repo);

		// Create Empire JPA persistence provider.
		PersistenceProvider provider = Empire.get().persistenceProvider();

		EntityManagerFactory emFactory = provider.createEntityManagerFactory(
				"", map);

		return emFactory.createEntityManager();
	}

	public String removeUnexpectedChars(String s) {
		s = s.replaceAll(",", "");
		s = s.replaceAll("\\{", "");
		s = s.replaceAll("\\}", "");
		s = s.replaceAll("\\\\", "");
		s = s.replaceAll("\\.", "");
		s = s.replaceAll("ldots", "");
		return s.toLowerCase();
	}

}
