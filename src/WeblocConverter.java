import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WeblocConverter {
	private static final String urlExt = ".url";

	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.out.println("[usage] java WeblocConverter dirname");
			return;
		}

		String dirName = args[0];
		File dir = new File(dirName);
		String[] weblocFileNames = dir.list(new WeblocFilter());

		for (int i = 0; i < weblocFileNames.length; i++) {
			String weblocFilePath = dirName + "\\" + weblocFileNames[i];
			WeblocReader wr = new WeblocReader(weblocFilePath);

			String weblocText = wr.getWeblocText();
			String regex = wr.getWeblocRegex();
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(weblocText);

			String wlWithoutExt = weblocFileNames[i]
					.substring(0, weblocFileNames[i].lastIndexOf("."));
			String urlFilePath = dirName + "\\" + wlWithoutExt + urlExt;
			UrlWriter uw = new UrlWriter(urlFilePath, matcher);

			uw.write();
		}
	}

}
