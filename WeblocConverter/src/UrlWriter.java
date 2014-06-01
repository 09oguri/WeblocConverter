import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;


public class UrlWriter {
	private static final String urlHeader = "[InternetShortcut]";
	private static final String urlValue = "URL=";

	private final String urlFilePath;
	private final Matcher matcher;

	public UrlWriter(String urlFilePath, Matcher matcher) {
		this.urlFilePath = urlFilePath;
		this.matcher = matcher;
	}

	public void write() throws IOException {
		if (matcher.find()) {
			File urlFile = new File(urlFilePath);
			FileWriter fw = new FileWriter(urlFile);
			BufferedWriter bw = new BufferedWriter(fw);

			String urlText = urlValue + matcher.group(1);
			bw.write(urlHeader);
			bw.newLine();
			bw.write(urlText);

			bw.flush();
			bw.close();
		}
	}

}
