import java.io.File;
import java.io.FilenameFilter;


public class WeblocFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		int extIndex = name.lastIndexOf(".");
		String extName = name.substring(extIndex + 1);

		return extName.equalsIgnoreCase("webloc");
	}

}
