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
	byte t[] = { 0 };

	public gate(String g) {
		try {
			admin = new RandomAccessFile("admin.txt", "rw");


		} catch (IOException e) {
		}
		me = g;
		internal_count = 0;
		if(g.compareToIgnoreCase("Bottom gate") == 0){
			turn = 0;
			try {
				count = admin.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			flag[0] = true;
			flag[1] = false;
		}else{

		}

	}

	public synchronized void counting() {
		int i;
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
					finish(turn);
				} catch (Exception e) {
				}
				System.out.println(me + ": Shared Counter: " + count + " internal counter: " + internal_count);
			}
		}
	}

	private void finish(int turn) {
		int other = 1 - turn;
		flag[turn] = false;
		flag[other] = true;
		notifyAll();
	}

	private void begin(int turn) {
		int other = 1 - turn;
		flag[turn] = true;
		while(flag[other] == true && turn == other){
			Thread.yield();
		}
	}
}
