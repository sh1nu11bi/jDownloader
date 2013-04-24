package com.redpois0n;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import com.redpois0n.io.Base64OutputStream;

public class Build {
	
	public static void copy(InputStream in, OutputStream out) throws Exception {
		byte[] buffer = new byte[1024];
		int read;
		
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}
	
	public static void build(File output, List<DownloadFile> files) throws Exception {
		ZipFile downloader = new ZipFile("Bin.jar");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(output));
		
		
		Enumeration<? extends ZipEntry> entries = downloader.entries();
		
		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			
			out.putNextEntry(entry);
			InputStream in = downloader.getInputStream(entry);		
			copy(in, out);
			out.closeEntry();
		}
		
		ZipEntry config = new ZipEntry("c.dat");
		out.putNextEntry(config);
		
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new Base64OutputStream(out)));
		
		writer.write(files.size() + "");
		writer.newLine();
		
		for (DownloadFile dl : files) {
			writer.write(dl.getUrl());
			writer.newLine();
			writer.write(dl.getFileName());
			writer.newLine();
			writer.write(dl.getExtension());
			writer.newLine();
			writer.write(Path.getPath(dl.getPath()) + "");
			writer.newLine();
			writer.write(dl.isShell() + "");
			writer.newLine();
		}
		
		writer.close();
		
		downloader.close();
	}

}
