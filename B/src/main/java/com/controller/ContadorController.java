package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.swing.Timer;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import com.DAO.ContadorDAO;
import com.model.Medida;
import com.resources.MyBatisConnectionFactory;

@ManagedBean
@ApplicationScoped
public class ContadorController implements Serializable {

	private static final long serialVersionUID = 1L;
	private volatile int contador;
	private List<Medida> mediciones;
	private List<Medida> medicionesAleatorias;
	private List<Medida> medicionesAcumuladas;
	private Medida medida;
	private int numMedidas;
	private String colorTemp;
	private String colorPreci;
	private Timer timer;
	private ContadorDAO contadorDAO;
	public ContadorController() {
		init();
	}

	@PostConstruct
	public void init() {
		System.out.println("Inicializando el Controller...");
		contadorDAO = new ContadorDAO(MyBatisConnectionFactory.getSqlSessionFactory());
		
		contador = 0;
		mediciones = new ArrayList<>();
		medicionesAleatorias = new ArrayList<>();
		medicionesAcumuladas = new ArrayList<>();
		inicializarMedidas();		
		obtenerMedicionAleatoria();
		colorTemp="verde.png";
		
	}
	
	public void obtenerMedicionAleatoria(){
		int a;
		numMedidas=mediciones.size();
		a = (int) (Math.random() * numMedidas) ;
		System.out.println(" -- >"+a);
		medicionesAleatorias = new ArrayList<>();
		medicionesAleatorias.add(mediciones.get(a));
		medicionesAcumuladas.add(mediciones.get(a));
		medida=medicionesAleatorias.get(0);
		calcularColor();
		System.out.println("Color Temperatura : "+colorTemp);
	}
	
	public void incrementar() {
		
		timer = new Timer (60000, new ActionListener () 
		{ 
		    public void actionPerformed(ActionEvent e) 
		    { 
		    	contador++;
		    	obtenerMedicionAleatoria();
				System.out.println("Contador : " + contador);
				String summary = "Nueva medicion detectada";
				String detail = "Temperatura : "+medicionesAleatorias.get(0).getTemperatura()+" Precipitacion : "+medicionesAleatorias.get(0).getPrecipitacion();
				EventBus eventBus = EventBusFactory.getDefault().eventBus();
				eventBus.publish("/counter", new FacesMessage(summary, detail));
				
		     } 
		}); 
		

		timer.start();
	}
	
	public void parar(){
		timer.stop();
	}
	public void inicializarMedidas(){		
		mediciones=contadorDAO.selectAll();		
	}
	
	public void  calcularColor(){
		if(medida.getTemperatura()<=10){
			colorTemp="azul.png";
		}else if(medida.getTemperatura()>10 && medida.getTemperatura()< 50 ){
			colorTemp="verde.png";
		}else{
			colorTemp="rojo.png";
		}
		
		if(medida.getPrecipitacion()<=10){
			colorPreci="azul.png";
		}else if(medida.getPrecipitacion()>10 && medida.getPrecipitacion()< 50 ){
			colorPreci="verde.png";
		}else{
			colorPreci="rojo.png";
		}
	}
	
	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public List<Medida> getMediciones() {
		return mediciones;
	}

	public void setMediciones(List<Medida> mediciones) {
		this.mediciones = mediciones;
	}

	public List<Medida> getMedicionesAleatorias() {
		return medicionesAleatorias;
	}

	public void setMedicionesAleatorias(List<Medida> medicionesAleatorias) {
		this.medicionesAleatorias = medicionesAleatorias;
	}

	public List<Medida> getMedicionesAcumuladas() {
		return medicionesAcumuladas;
	}

	public void setMedicionesAcumuladas(List<Medida> medicionesAcumuladas) {
		this.medicionesAcumuladas = medicionesAcumuladas;
	}

	public Medida getMedida() {
		return medida;
	}

	public void setMedida(Medida medida) {
		this.medida = medida;
	}

	public String getColorTemp() {
		return colorTemp;
	}

	public void setColorTemp(String colorTemp) {
		this.colorTemp = colorTemp;
	}

	public String getColorPreci() {
		return colorPreci;
	}

	public void setColorPreci(String colorPreci) {
		this.colorPreci = colorPreci;
	}	
	
}
