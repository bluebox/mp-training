package com.example12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Olympics{

	    private String playername="Cristiano Ronaldo";
	    private final Games game;

	    @Autowired
	    public Olympics(@Qualifier("game2") Games game){
	        System.out.println("Olympics bean created by Spring");
	        this.game = game;
	    }
	    
	    public String getPlayername() {
			return playername;
		}

		public void setPlayername(String playername) {
			this.playername = playername;
		}

		public Games getGame() {
	        return game;
	    }
}
