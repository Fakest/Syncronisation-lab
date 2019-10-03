package uk.ac.stir.cs.ggp1;

import java.io.*;

public class garden_gate_problem implements Runnable{
	RandomAccessFile admin;
	String g;

	public garden_gate_problem(String g) {
		this.g = g;
		try {
			admin = new RandomAccessFile("admin.txt", "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {

		byte init[] = { 0 };
		if (g.compareToIgnoreCase("gate_bottom") == 0) {
			try {
				g = "Bottom Gate";

				admin.seek(0);
				admin.write(init);
				admin.close();
			} catch (IOException e) {
				System.out.println("something wrong with file access" + e);
			}
		}else {
			g = "Top gate";
		}
		gate counter = new gate(g);
		counter.counting();
		}
	}


