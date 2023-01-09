package modelo.bean;
import java.util.ArrayList; 
import java.util.Date;
import java.util.List;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

import dataAccess.DataAccess;
import dataAccess.DataAccessInterface;
import modelo.domain.*;


	

public class VerPreguntaBean {
	private Date fecha;
	private Event evento;
	private String sevento;
	private Question apuesta;
	private static List<Question> apuestas= new ArrayList<Question>();
	private static List<Event> eventos= new ArrayList<Event>();
	DataAccessInterface dataAccess = new DataAccess();
	BLFacade bl = new BLFacadeImplementation(dataAccess);
	
	
	public Event getEvento() {
		return evento;
	}
	
	public Question getApuesta() {
		return apuesta;
	}
	
	public List getApuestas() {
		return apuestas;
	}
	
	public List getEventos() {
		return eventos;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public String getSevento() {
		return sevento;
	}
	
	public void setEvento(Event evento) {
		this.evento=evento;
	}
	
	public void setApuesta(Question apuesta) {
		this.apuesta=apuesta;
	}
	
	public void setEventos(List<Event> eventos) {
		this.eventos=eventos;
	}
	
	public void setApuestas(List<Question> apuestas) {
		this.apuestas=apuestas;
	}
	
	public void setFecha(Date fecha) {
		this.fecha=fecha;
	}

	public void setSevento(String sevento) {
		this.sevento=sevento;
	}
	
	
	public static Event getObject(String event) {
		 for (Event ev: eventos){
		 if (event.equals(ev.toString()))
		 return ev;}
		 return null;
		}
	
	public void cargarEventos(){
		System.out.println(fecha);
		this.setEventos(bl.getEvents(fecha));
		System.out.println(eventos);
	}
	
	public void cargarApuestas(String sevento){
		evento=this.getObject(sevento);
		apuestas=evento.getQuestions();
		System.out.println(apuestas);
		
	}
	
}
