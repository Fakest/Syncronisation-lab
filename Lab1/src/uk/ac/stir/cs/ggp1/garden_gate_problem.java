package uk.ac.stir.cs.ggp1;

import java.io.*;

public class garden_gate_problem {

	static public void main(String[] args) {
		RandomAccessFile admin;
		String g = "Top Gate";
		byte init[] = { 0 };

		if (args.length != 1)
			System.err.println("usage: java {gate_bottom,gate_top}");
		else {
			if (args[0].compareToIgnoreCase("gate_bottom") == 0) {
				try {
					g = "Bottom Gate";
					admin = new RandomAccessFile("admin.txt", "rw");
					admin.seek(0);
					admin.write(init);
					admin.close();
				} catch (IOException e) {
					System.out.println("something wrong with file access" + e);
				}
			}
			gate counter = new gate(g);
			counter.counting();
		}
	}
}
