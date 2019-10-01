package uk.ac.stir.cs.ggp1;

import java.util.Random;
import java.io.*;

public class gate {

	RandomAccessFile admin;
	int internal_count;
	int ranumber;
	int count;
	Random ra;
	String me;

	public gate(String g) {
		try {
			admin = new RandomAccessFile("admin.txt", "rw");
		} catch (IOException e) {
		}
		internal_count = 0;
		count = 0;
		ra = new Random();
		me = g;
	}

	public void counting() {
		int i;
		byte t[] = { 0 };

		for (i = 0; i < 50; i++) {
			try {
				ranumber = ra.nextInt(500);
				admin.seek(0);
				admin.read(t);
				count = t[0];
				count = count + 1;
				internal_count++;
				t[0] = (byte) count;
				admin.seek(0);
				admin.write(t);
				ranumber = ra.nextInt(300);
				Thread.sleep(100);
			} catch (Exception e) {
			}
			System.out.println(me + ": Shared Counter: " + count + " internal counter: " + internal_count);
		}
	}
}
