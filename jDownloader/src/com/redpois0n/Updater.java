package com.redpois0n;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Updater {
	
	public static final boolean isAvailable() {
		try {
			String latest = new BufferedReader(new InputStreamReader(new URL("http://redpois0n.com/misc/version/getversion.php?proj=jdownloader").openStream())).readLine();
			return !latest.equals(Main.getVersion());
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
