package modelo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import modelo.domain.*;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;



public class CrearPreguntaBean {
	private Date fecha;
	private String pregunta;
	private float apuesta;
	private Event evento;
	private String sevento;
	private String mensaje="";
	private static List<Event> eventos= new ArrayList<Event>();
	BLFacade bl = new BLFacadeImplementation(DataAccess.getInstance());

	public Event getEvento() {
		return evento;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public List getEventos() {
		return eventos;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public String getPregunta() {
		return pregunta;
	}
	
	public float getApuesta() {
		return apuesta;
	}
	
	public String getSevento() {
		return sevento;
	}
	
	public void setEvento(Event evento) {
		this.evento=evento;
	}
	
	public void setEventos(List<Event> eventos) {
		this.eventos=eventos;
	}
	
	public void setFecha(Date fecha) {
		this.fecha=fecha;
	}
	
	public void setPregunta(String pregunta) {
		this.pregunta=pregunta;
	}
	
	public void setApuesta(float apuesta) {
		this.apuesta=apuesta;
	}

	public void setSevento(String sevento) {
		this.sevento=sevento;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje=mensaje;
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
		if(eventos.isEmpty()) {
			sevento="";
		}
		else {
		sevento=eventos.get(0).toString();
		System.out.println(sevento);
	}
		}
	
	public void eventoSeleccionado() {
		this.evento=CrearPreguntaBean.getObject(sevento);
		System.out.println(sevento);
		System.out.println(evento);
	}
	
	public void pruebaDatos() {
		System.out.println(fecha);
		System.out.println(pregunta);
		System.out.println(apuesta);
		System.out.println(sevento);
		System.out.println(evento);
		
		if(evento==null) {
			this.mensaje="evento nulo";
			System.out.println(evento);
		}
		else {
		try {
			bl.createQuestion(evento, pregunta, apuesta);
			this.mensaje="pregunta creada correctamente";
			System.out.println(evento.getQuestions());
		} catch (EventFinished e) {
			this.mensaje="El evento ha finalizado";
			e.printStackTrace();
		} catch (QuestionAlreadyExist e) {
			this.mensaje="Esta pregunta ya existe";
			e.printStackTrace();
		}
	}
	}
}
