package it.polito.tdp.model;

public class Event implements Comparable<Event> {
     //devono sempre essere presenti
	
	public enum EventType{NUOVO_CLIENTE, AUTO_RESTITUITA};//e una sorta di classe con solo costanti dentro
	
	public Event(int time, EventType type) {
		super();
		this.time = time;
		this.type = type;
	}





	private int time;// minuti dalla mezzanote
	private EventType type; // gli unici parametri che puo assumere e' nuovo cliente e auto restituita
	
	
	
	
	
	//per la priorita e necessaria la comparable
	@Override
	public int compareTo(Event other) { //deve restituire un int negativo se this e minore di other, positivo se viene dopo, 0 se e' uguale. ricordalo con this-other
		// se non fosse int si richiama il compare to dell classe dell' oggetto da confrontare
		
		return this.time-other.time;
	}





	public int getTime() {
		return time;
	}





	public void setTime(int time) {
		this.time = time;
	}





	public EventType getType() {
		return type;
	}





	public void setType(EventType type) {
		this.type = type;
	}

}
