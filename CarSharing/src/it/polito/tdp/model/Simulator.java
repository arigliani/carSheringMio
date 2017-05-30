package it.polito.tdp.model;

import java.util.PriorityQueue;

import it.polito.tdp.model.Event.EventType;

public class Simulator {
	
	//parametri simulazione
	// sono paramtri che modifico per ogni simulazioe
	private int NC; //numero di auto totali, e una costante perche lo imposto all'inizio della simulazione e basta
	
	private int TRAVEL_MINIMUM_TIME=60; //1h
	private int TRAVEL_MAX_NUM=3;// numero massimo di TRAVEL_MIN_TIME per cui l' auto puo essere fuori
	
	//stato del modello
	private int autoPresenti;//ancora presenti in deposito
	
	//variabili diinteresse
	private int ClientiTot=0;// nuero di clienti totali arrivati al deposito
	private int clientiInsoddisfatti=0; //numero di clienti che non hanno trovato auto
	//lista degli eventi
	PriorityQueue<Event> queue;
	
	/**
	 * costruttore simulatore
	 * @param nC
	 */
	public Simulator(int nC) {
		super();
		NC = nC;
		this.autoPresenti=this.NC;
		this.queue=new PriorityQueue<>();
	}
	
	/**
	 * permette di caricare i clienti previsti in arrivo,
	 * all'inizio della simulazione.
	 * @param time
	 */
    public void addCliente(int time){
    	queue.add(new Event(time, EventType.NUOVO_CLIENTE));
		
	}
    
    public void run(){
    	//peek sbirciare, vede solo l'oggetto
    	//poll estrae il primo elemento della lista
    	while(!queue.isEmpty()){
    		Event e= queue.poll();
    		
    		//process event
    		switch(e.getType()){
    		case NUOVO_CLIENTE:
    			if(this.autoPresenti==0){
    				//non ho un auto
    				this.ClientiTot++;
    				this.clientiInsoddisfatti++;
    				
    				System.out.format("time %d - cliente insoddisfatto \n", e.getTime());
    				
    				
    			}else{
    				//affitta un'auto
    				this.ClientiTot++;
    				this.autoPresenti--;
    				
    				int durata= this.TRAVEL_MINIMUM_TIME*(int)(Math.random()*this.TRAVEL_MAX_NUM);
    				
    				queue.add(new Event(e.getTime()+durata, EventType.AUTO_RESTITUITA ));
    				System.out.format("Time %d - auto prestata (rientra alle %d) rimanenti %d\n", e.getTime(), e.getTime()+durata, this.autoPresenti);
    			}
    			break;
    		case AUTO_RESTITUITA:
    			this.autoPresenti++;
    			System.out.format("Time %d - auto restituita - rimanenti %d\n", e.getTime(),this.autoPresenti);
    			break;
    		}
    	}
    }

	public int getNC() {
		return NC;
	}

	public void setNC(int nC) {
		NC = nC;
		this.autoPresenti=this.NC;
	}

	public int getTRAVEL_MINIMUM_TIME() {
		return TRAVEL_MINIMUM_TIME;
	}

	public void setTRAVEL_MINIMUM_TIME(int tRAVEL_MINIMUM_TIME) {
		TRAVEL_MINIMUM_TIME = tRAVEL_MINIMUM_TIME;
	}

	public int getTRAVEL_MAX_NUM() {
		return TRAVEL_MAX_NUM;
	}

	public void setTRAVEL_MAX_NUM(int tRAVEL_MAX_NUM) {
		TRAVEL_MAX_NUM = tRAVEL_MAX_NUM;
	}

	public int getClientiTot() {
		return ClientiTot;
	}

	public int getClientiInsoddisfatti() {
		return clientiInsoddisfatti;
	}
	
	
	
	

}
