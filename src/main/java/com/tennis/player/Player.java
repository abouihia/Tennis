package com.tennis.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.logging.Logger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

	Logger logger = Logger.getLogger(Player.class.getName());


	private String nameOfPlayer ;
	private int score;
	private boolean hasAvantage;
	private boolean isEgality;
	private Boolean isWinerParty =Boolean.FALSE;
	private int numberWinGame;

    private String messageForWiner ;
	private int nomberSetWin ;

	private int numberPointWinWitTieBreak;



	public Player(String name){
		this.nameOfPlayer  = name;
	}

	public void winBall(){
		 this.score = this.score+1;
	}

	public void setIsWinerParty(boolean isWinerParty){
		this.isWinerParty = isWinerParty;
		this.numberWinGame = this.numberWinGame +1;
		this.score =0;
	}


	public boolean  winSet(){
		return  (this.numberWinGame ==6 || this.numberWinGame ==7);
	}


	public boolean winSetWithTieBreak(){
		return  this.numberPointWinWitTieBreak >= 6 ;
	}









}
