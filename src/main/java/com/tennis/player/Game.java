package com.tennis.player;

import java.util.Random;
import java.util.logging.Logger;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Game {

    public static final Logger logger = Logger.getLogger(Game.class.getName());

	private  Player  playerOne, playerTwo;

	private boolean isDeuce;
	private boolean isSetNotFinish =true;
	private boolean isTieBreakGame ;

	public Game(Player playerOne, Player playerTwo){
		this.playerOne  = playerOne;
		this.playerTwo  = playerTwo;
	}


	public void playParty(Player playerOne , Player playerTwo, int winPointe) {

        logger.info("Player:" +winPointe+" how played");
			if (winPointe == 1) {// simuler que le joueur 1 a qui gagne la
				playerOne.winBall();
				if(! isDeuce  && playerOne.getScore() == 4){
						playerOne.setIsWinerParty(Boolean.TRUE);
                        logger.info("Player:" +winPointe+ " win ball ");
				}else if(isDeuce) {
					deuceParty(playerOne, playerTwo, winPointe, "  How win party ");
				}
			}else {
				playerTwo.winBall();
				if(! isDeuce && playerTwo.getScore() ==4){
						playerTwo.setIsWinerParty(Boolean.TRUE);
                        logger.info("Player:" +winPointe+ "win ball ");
				}else if(isDeuce) {
					deuceParty(playerTwo, playerOne, winPointe, " How win party ");
				}
			}

			if(RuleGame.isDeucePredicate.test(playerOne,playerTwo)){
                logger.info("Is Deuce");
				isDeuce = true;
			};

		}



	public void  playSet(Player playerOne , Player playerTwo,  int[][] gameScenario ,
						 boolean isNotTieBreak, int[] gameScenarioWithTieBreak){

		while(isNotTieBreak && isSetNotFinish ) {
			for (int i = 0; i < gameScenario.length; i++) {
				for (int j = 0; j < gameScenario[i].length ;j++) {
					playParty(playerOne, playerTwo, gameScenario[i][j]);
				}
				logger.info("fin du jeu " +i + " and the winer is player one "
						+(playerOne.getNumberWinGame() == 1)  + " and number of win "+ playerOne.getNumberWinGame());
				logger.info("fin du jeu " +i + " and the winer is  player two "
						+(playerTwo.getNumberWinGame()==1)+" and number of win "+ playerTwo.getNumberWinGame());

			}
			isSetNotFinish  = RuleGame.isPlayerWinSetWithoutTieBreakPredicate.negate().test(playerOne,playerTwo);

		}
		// start tieBreak game
		isTieBreakGame  = RuleGame.isTieBreakPredicate.test(playerOne, playerTwo);
		if(isTieBreakGame ){
			startTieBreakGame( playerOne,  playerTwo , gameScenarioWithTieBreak);
		}


	}


	public void startTieBreakGame(Player playerOne, Player playerTwo , int[] gameScenarioWithTieBreak){

		int i =0;
		do{

			Random rn = new Random();
			int winPointe = rn.nextInt(2) + 1;
			if (gameScenarioWithTieBreak[i]  == 1) {
				playerOne.setNumberPointWinWitTieBreak(playerOne.getNumberPointWinWitTieBreak()+1);

			}else{
				playerTwo.setNumberPointWinWitTieBreak(playerTwo.getNumberPointWinWitTieBreak()+1);
			}
			i++;
		}while ( testFinish(playerOne, playerTwo));


	}


	public void  playSetRandom(Player playerOne , Player playerTwo,  int[][] gameScenario,boolean isNotTieBreak){

		while(isNotTieBreak && isSetNotFinish ) {
			for (int i = 0; i < gameScenario.length; i++) {
				for (int j = 0; j < gameScenario[i].length ;j++) {
					playParty(playerOne, playerTwo, gameScenario[i][j]);
				}
				logger.info("fin du jeu " +i + " and the winer is player one "
						+(playerOne.getNumberWinGame() == 1)  + " and number of win "+ playerOne.getNumberWinGame());
				logger.info("fin du jeu " +i + " and the winer is  player two "
						+(playerTwo.getNumberWinGame()==1)+" and number of win "+ playerTwo.getNumberWinGame());

			}
			isSetNotFinish  = RuleGame.isPlayerWinSetWithoutTieBreakPredicate.negate().test(playerOne,playerTwo);

		}
		// start tieBreak game
		isTieBreakGame  = RuleGame.isTieBreakPredicate.test(playerOne, playerTwo);
		if(isTieBreakGame ){
			startTieBreakGameRandom( playerOne,  playerTwo );
		}


	}


	public void startTieBreakGameRandom(Player playerOne, Player playerTwo ){

		do{
			Random rn = new Random();
			int winPointe = rn.nextInt(2) + 1;
			if (winPointe == 1) {
				playerOne.setNumberPointWinWitTieBreak(playerOne.getNumberPointWinWitTieBreak()+1);

			}else{
				playerTwo.setNumberPointWinWitTieBreak(playerTwo.getNumberPointWinWitTieBreak()+1);

			}
		}while ( testFinish(playerOne, playerTwo));

	}


	public boolean testFinish(Player playerOne, Player  playerTwo){
		 if(playerOne.getNumberPointWinWitTieBreak() < 6  && playerTwo.getNumberPointWinWitTieBreak()< 6){
		  	 return true;
		  }
		 else if(playerOne.getNumberPointWinWitTieBreak() -  playerTwo.getNumberPointWinWitTieBreak()>= 2
				 ||
				 playerTwo.getNumberPointWinWitTieBreak() -  playerOne.getNumberPointWinWitTieBreak()>= 2){
		 	return false;
		 }
		 return true;
	}


	

	private void deuceParty(Player playerOne, Player playerTwo, int winPointe, String s) {
		if (playerTwo.isHasAvantage()) {
			playerTwo.setEgality(Boolean.FALSE);
			playerTwo.setHasAvantage(Boolean.FALSE);
			logger.info("Egality ");
		} else if (!playerOne.isHasAvantage()) {
			playerOne.setHasAvantage(Boolean.TRUE);
			logger.info("Player:" + winPointe + "Has Avantage ");
		} else {
			playerOne.setIsWinerParty(Boolean.TRUE);
			logger.info("Player:" + winPointe + s);
		}
	}








}
