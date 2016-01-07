package common.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.firefox.FirefoxProfile;

public class Internationalization {

	public static void runNative2Ascii(String languageFile, String outputFile) {

		FirefoxProfile fprofile = new FirefoxProfile();
		fprofile.setPreference("browser.download.dir", "D:\\WebDriverdownloads");
		fprofile.setPreference("browser.download.folderList", 2);

		Runtime runtime = Runtime.getRuntime();
		
		try {
			System.out.println("native2ascii -encoding utf8 \""
					+ new File(languageFile) + "\" \"" + new File(outputFile)
					+ "\"");
			runtime.exec("cmd /c native2ascii -encoding utf8 \""
					+ new File(languageFile) + "\" \"" + new File(outputFile)
					+ "\"");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String readParsedResourceFile(String fileName) {
		InputStream iStream = null;
		InputStreamReader iStreamReader = null;
		BufferedReader bReader = null;
		StringBuffer sBuffer = new StringBuffer();
		String content = null;
		String resultString = "";

		try {
			iStream = new FileInputStream(new File(fileName));
			iStreamReader = new InputStreamReader(iStream);
			bReader = new BufferedReader(iStreamReader);

			while ((content = bReader.readLine()) != null) {
				sBuffer.append(content);
			}
			content = sBuffer.toString();
			content = content.substring(10, content.length());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (iStream != null)
					iStream.close();
				if (iStreamReader != null)
					iStreamReader.close();
				if (bReader != null)
					bReader.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		Matcher m = Pattern.compile("\\\\u([0-9A-Fa-f]{4})").matcher(content);

		while (m.find()) {
			try {
				int cp = Integer.parseInt(m.group(1), 16);
				char[] chars = Character.toChars(cp);
				String rep = new String(chars);
				resultString += rep;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

}
