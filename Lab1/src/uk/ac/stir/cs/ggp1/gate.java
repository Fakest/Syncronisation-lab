package uk.ac.stir.cs.ggp1;

import java.util.Random;
import java.io.*;

public class gate {
	private volatile boolean[] flag = new boolean[2];
	private volatile int turn;
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
		if(g.compareToIgnoreCase("Bottom gate") == 0){
			turn = 0;
		}else{
			turn = 1;
		}
		flag[0] = false;
		flag[1] = false;
	}

	public void counting() {
		int i;
		byte t[] = { 0 };


		synchronized (admin){
			for (i = 0; i < 50; i++) {
				try {
					begin(turn);
					admin.seek(0);
					admin.read(t);
					count = t[0];
					count = count + 1;
					internal_count++;
					t[0] = (byte) count;
					admin.seek(0);
					admin.write(t);
					ranumber = ra.nextInt(300);
					Thread.sleep(ranumber);
					finish(turn);
				} catch (Exception e) {
				}
				System.out.println(me + ": Shared Counter: " + count + " internal counter: " + internal_count);
			}
		}

	}

	private void finish(int c) {
		flag[c] = false;
	}

	private void begin(int c) {
		int other = 1 - c;
		flag[c] = true;
		turn = other;
		while(flag[other] == true && turn == other){
			Thread.yield();
		}
	}
}
