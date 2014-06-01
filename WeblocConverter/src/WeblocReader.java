import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class WeblocReader {
	private static final String oldWeblocRegex = "(http[\\S]*)";
	private static final String newWeblocRegex = "<string>(.*)</string>";
	private static final String newWeblocStartWith = "<?xml";
	private static final String newWeblocUrlTag = "<string>";

	private final String weblocFilePath;
	private boolean isNewWeblocRegex;

	public WeblocReader(String weblocFilePath) {
		this.weblocFilePath = weblocFilePath;
	}

	public String getWeblocText() throws IOException {
		File weblocFile = new File(weblocFilePath);
		FileReader fr = new FileReader(weblocFile);
		BufferedReader br = new BufferedReader(fr);

		String weblocText = br.readLine();
		if (weblocText != null && weblocText.startsWith(newWeblocStartWith)) {
			while (weblocText != null && !weblocText.contains(newWeblocUrlTag)) {
				weblocText = br.readLine();
			}
			isNewWeblocRegex = true;
		}
		br.close();

		return weblocText;
	}

	public String getWeblocRegex() {
		return isNewWeblocRegex ? newWeblocRegex : oldWeblocRegex;
	}

}
