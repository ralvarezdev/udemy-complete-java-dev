package practices.traversinggame.managers;

import java.io.InputStream;

import practices.MissingResourceFileException;

public enum ResourceManager {
	INSTANCE;

	// Paths
	private final static String RESOURCES_PATH = "../resources";
	private final static String ASSETS_PATH = String.join("/", RESOURCES_PATH, "assets");
	private final static String STYLES_PATH = String.join("/", RESOURCES_PATH, "styles");

	public ResourceManager getInstance() {
		return INSTANCE;
	}

	private String getResourceToExternalForm(String rootDirPath, String fileName) throws MissingResourceFileException {
		String resource = getClass().getResource("%s/%s".formatted(rootDirPath, fileName)).toExternalForm();

		if (resource == null)
			throw new MissingResourceFileException("Missing %s file.".formatted(fileName));

		return resource;
	}

	private InputStream getResourceAsStream(String rootDirPath, String fileName) throws MissingResourceFileException {
		InputStream resource = getClass().getResourceAsStream("%s/%s".formatted(rootDirPath, fileName));

		if (resource == null)
			throw new MissingResourceFileException("Missing %s file.".formatted(fileName));

		return resource;
	}

	private String getStyle(String fileName) throws MissingResourceFileException {
		return getResourceToExternalForm(STYLES_PATH, fileName);
	}

	public InputStream getAsset(String fileName) throws MissingResourceFileException {
		return getResourceAsStream(ASSETS_PATH, fileName);
	}

	public String getFontsStyle() throws MissingResourceFileException {
		return getStyle("fonts.css");
	}

	public String getGeneralStyle() throws MissingResourceFileException {
		return getStyle("general.css");
	}
}
