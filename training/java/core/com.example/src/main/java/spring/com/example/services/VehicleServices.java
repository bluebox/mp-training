package spring.com.example.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import spring.com.example.interfaces.Speaker;
import spring.com.example.interfaces.Tyres;

@Component
@Scope("singleton")
public class VehicleServices {

	 @Autowired
	public Speaker speaker;
	 // it will create speaker=new BoseSpeaker();
	 @Autowired
	private Tyres tyre;
	
	public String playMusic() {
		String music=speaker.makeSound();
		//System.out.print(music);
		return music;
	}
	public String moveVehicle() {
		String tyred=tyre.rotate();
		//System.out.print(tyred);
		return tyred;
	}
//	
//	public void setSpeaker(Speaker sp) {
//		this.speaker=sp;
//	}
//	public Speaker getSpeaker() {
//		return this.speaker;
//	}
//	// @Autowired
//	public void setTyre(Tyres tyre) {
//		this.tyre=tyre;
//	}
//	public Tyres getTyres() {
//		return this.tyre;
//	}
	
}
