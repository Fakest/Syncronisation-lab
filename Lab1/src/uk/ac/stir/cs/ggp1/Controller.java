package uk.ac.stir.cs.ggp1;

public class Controller {
    public static void main(String[] args){

        garden_gate_problem top = new garden_gate_problem("gate_top");
        garden_gate_problem bottom = new garden_gate_problem("gate_bottom");
        Thread t1 = new Thread(bottom);
        Thread t2 = new Thread(top);
        t1.start();
        t2.start();
    }
}
