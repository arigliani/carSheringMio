package it.polito.tdp.carsharing;

import it.polito.tdp.model.Simulator;

public class Main {

	public static void main(String[] args) {
		Simulator sim= new Simulator(10);
		sim.setTRAVEL_MAX_NUM(3);
		
		//definisci i clienti, uno ogni 10 minuti, dalle 8:00 alle 17:00
		for(int time= 8*60; time<= 17*60; time=time+8){
			sim.addCliente(time);
		}
		
		sim.run();
		
		System.out.format("Clienti toali arrivati: %d \n", sim.getClientiTot());
		System.out.format("Clienti insoddisfatti: %d \n", sim.getClientiInsoddisfatti());
	}

}
