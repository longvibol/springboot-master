package com.vibol.AopDemo;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vibol.AopDemo.interfaces.Speakers;
import com.vibol.AopDemo.interfaces.Tyres;
import com.vibol.AopDemo.model.Song;

@Component
public class VehicleServices {

	private Logger logger = Logger.getLogger(VehicleServices.class.getName());

	private Speakers speakers;
	private Tyres tyres;

	public String playMusic(boolean vehicleStarted, Song song) {
		Instant start = Instant.now();
		logger.info("method exeution start");
		String music = null;
		if (vehicleStarted) {
			music = speakers.makeSound(song);
		} else {
			logger.log(Level.SEVERE, "Vehicle not start to perform the operation");
		}
		logger.info("method execution end");
		Instant finish = Instant.now();

		long timeElapsed = Duration.between(start, finish).toMillis();
		logger.info("Time took to execute the method" + timeElapsed);

		return music;
	}

	public String moveVehicle(boolean vehicleStarged) {
		Instant start = Instant.now();
		logger.info("method execution start");
		String status = null;
		if (vehicleStarged) {
			status = tyres.rotate();
		} else {
			logger.log(Level.SEVERE, "Vehicle not started to perform the operation");
		}

		logger.info("Method excution end");
		Instant finish = Instant.now();

		long timeElapsed = Duration.between(start, finish).toMillis();
		logger.info("Time took to execute the method" + timeElapsed);

		return status;
	}
	
	 public String applyBrake(boolean vehicleStarted){
	        Instant start = Instant.now();
	        logger.info("method execution start");
	        String status = null;
	        if(vehicleStarted){
	            status = tyres.stop();
	        }else{
	            logger.log(Level.SEVERE,"Vehicle not started to perform the" +
	                    " operation");}
	        
	        logger.info("method execution end");
	        Instant finish = Instant.now();
	        long timeElapsed = Duration.between(start, finish).toMillis();
	        logger.info("Time took to execute the method : "+timeElapsed);
	        return status;	    
	 }
	 
	  public Speakers getSpeakers() {
	        return speakers;
	    }

	    public void setSpeakers(Speakers speakers) {
	        this.speakers = speakers;
	    }

	    public Tyres getTyres() {
	        return tyres;
	    }

	    @Autowired
	    public void setTyres(Tyres tyres) {
	        this.tyres = tyres;
	    }

}
