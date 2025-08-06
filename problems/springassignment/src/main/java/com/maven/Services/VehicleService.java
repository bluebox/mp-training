package com.maven.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.maven.Interface.Sounds;
import com.maven.Interface.Tyres;

@Component
public class VehicleService {
	

	 @Autowired
	  private Tyres tyres;
	 @Autowired
      private Sounds speakers;
     @Autowired
     @Qualifier("Michellin")
     private Tyres tyre1;
	 
	 
	public void playMusic() {
		speakers.makeSound();
	}

		public void Tyresinstalled() {
			int random=(int)Math.random();
			System.out.println("the random number is :"+random);
			if(random%2==0) {
			tyres.rotate();
			}else {
				tyre1.rotate();
			}
		}
	 
       public VehicleService(Tyres tyres, Sounds speakers) {
		this.tyres = tyres;
		this.speakers = speakers;
	}
       
       
	   public Tyres getTyres() {
		return tyres;
	}
	public void setTyres(Tyres tyres) {
		this.tyres = tyres;
	}
	public Sounds getSpeakers() {
		return speakers;
	}
	public void setSpeakers(Sounds speakers) {
		this.speakers = speakers;
	}


	
       
}
