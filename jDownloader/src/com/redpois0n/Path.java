package com.redpois0n;

public class Path {
	
	public static final String[] paths = new String[] { "Temp/Documents (UNIX)", "Appdata/Library (UNIX)", "Desktop" };
	
	public static final int TEMPDOC = 0;

	public static final int APPDATALIB = 1;

	public static final int DESKTOP = 2;

	public static final String getPath(int path) {
		return paths[path];
	}
	
	public static final int getPath(String path) {
		for (int i = 0; i < paths.length; i++) {
			if (paths[i].equalsIgnoreCase(path)) {
				return i;
			}
		}
		
		return -1;
	}
}
