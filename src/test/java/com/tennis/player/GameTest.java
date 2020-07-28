package com.tennis.player;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

public class GameTest {

	public static final Logger logger = Logger.getLogger(GameTest.class.getName());
	
	private Player playerOne;
	private Player playerTwo;
	private Game  game;

	@BeforeAll
	static void setup() {
		logger.info("@BeforeAll executed");
	}

	@BeforeEach
	void setupGamer() {
		playerOne  = new Player("Tennis_1");
		playerTwo  = new Player("Tennis_2");
		game  = new Game(playerOne, playerTwo);
	}


	@Test
	void shouldHave_ZeroScore_BeforStartPlaying() {
		Assertions.assertEquals(playerOne.getScore(), 0);
		Assertions.assertEquals(playerTwo.getScore(), 0);
	}

	@Test
	void Given_this_Scenario_Player_On_Should_Win() {
		int[] gameScenario ={1,1,1,2,1};
		for(int i =0; i<gameScenario.length; i++){
			game.playParty(playerOne, playerTwo, gameScenario[i]);
		}

		Assertions.assertTrue(playerOne.getIsWinerParty());
		Assertions.assertFalse(playerTwo.getIsWinerParty());
	}

	@Test
	void Given_this_Scenario_Player_Two_Should_Win() {
		int[] gameScenario ={2,2,2,1,2};
		for(int i =0; i<gameScenario.length; i++){
			game.playParty(playerOne, playerTwo, gameScenario[i]);
		}

		Assertions.assertTrue(playerTwo.getIsWinerParty());
		Assertions.assertFalse(playerOne.getIsWinerParty());
	}

	@Test
	void Given_this_Scenario_With_Deuce_Player_Two_Should_Win() {
		int[] gameScenario ={2,1,2,1,2,1,2,2};
		for(int i =0; i<gameScenario.length; i++){
			game.playParty(playerOne, playerTwo, gameScenario[i]);
		}

		Assertions.assertTrue(playerTwo.getIsWinerParty());
		Assertions.assertFalse(playerOne.getIsWinerParty());
	}

	@Test
	void Given_this_Scenario_With_Deuce_Threed_Player_Two_Should_Win() {
		int[] gameScenario ={2,1,  2,1  ,2,1,  2,1 ,2,1 ,2,1, 2,2};
		for(int i =0; i<gameScenario.length; i++){
			game.playParty(playerOne, playerTwo, gameScenario[i]);
		}

		Assertions.assertTrue(playerTwo.getIsWinerParty());
		Assertions.assertFalse(playerOne.getIsWinerParty());
	}

	@Test
	void Given_this_Scenario_With_Deuce_Threed_Player_ONE_Should_Win() {

		int[] gameScenario ={1,2, 1,2 ,1,2,    1,2 ,2,1    ,2,1, 1,1};
		for(int i =0; i<gameScenario.length; i++){
			game.playParty(playerOne, playerTwo, gameScenario[i]);
		}

		Assertions.assertTrue(playerOne.getIsWinerParty());
		Assertions.assertFalse(playerTwo.getIsWinerParty());
	}

    @Test  // Scenario 6:0
	void Given_this_Scenario_Player_ONE_Win_All_Should_Win_Set() {

		int[][] gameScenario ={{1,1,1,1}, {1,1,1,1},{1,1,1,1},
				                {1,1,1,1},{1,1,1,1}, {1,1,1,1}};
		game.playSet(playerOne, playerTwo, gameScenario, true, null);

		Assertions.assertTrue(playerOne.winSet());
		Assertions.assertFalse(playerTwo.winSet());
	}

	@Test // scenario :=> 6:1
	void Given_this_Scenario_ONE_Player_ONE_Should_Win_Set() {

		int[][] gameScenario ={{1,1,1,1},{2,2,2,2},{1,1,1,1},
				               {1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}};
		game.playSet(playerOne, playerTwo, gameScenario, true, null);

		Assertions.assertTrue(playerOne.winSet());
		Assertions.assertFalse(playerTwo.winSet());
	}




	//@Test // scenario :=> 6:4
	void Given_this_Scenario_Tree_Player_One_Should_Win_Set() {

		int[][] gameScenario ={{2,2,2,2},{2,2,2,2},
				               {1,1,1,1},{2,2,2,2},
				               {2,2,2,2},{2,2,2,2},
				               {2,2,2,2}};
		game.playSet(playerOne, playerTwo, gameScenario, true, null);

		Assertions.assertFalse(playerOne.winSet());
		Assertions.assertTrue(playerTwo.winSet());
	}

	@Test // scenario :=> 6,4
	void Given_this_Scenario_FOUR_Player_ONE_Should_Win_Set() {

		int[][] gameScenario ={ {1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{1,1,1,1}};
		game.playSet(playerOne, playerTwo, gameScenario, true, null);

		Assertions.assertTrue(playerOne.winSet());
		Assertions.assertFalse(playerTwo.winSet());
	}
	@Test // scenario :=> 7:5
	void Given_this_Scenario_FIVE_Player_ONE_Should_Win_Set() {

		int[][] gameScenario ={ {1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{1,1,1,1}};

		game.playSet(playerOne, playerTwo, gameScenario, true, null);

		Assertions.assertTrue(playerOne.winSet());
		Assertions.assertFalse(playerTwo.winSet());
	}

	@Test // scenario :=> 7:5
	void Given_this_Scenario_SIX_Player_TWO_Should_Win_Set() {

		int[][] gameScenario ={ {1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{2,2,2,2},{2,2,2,2}};

		game.playSet(playerOne, playerTwo, gameScenario, true, null);

		Assertions.assertFalse(playerOne.winSet());
		Assertions.assertTrue(playerTwo.winSet());
	}



	@Test // scenario with tieBreak :=>  win all point
	void Given_this_Scenario_ONE_TieBreak_Game_Player_One_Should_Win() {

		int[][] gameScenario ={ {1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2}
		};
		int[] gameScenarioWithTieBreak ={1,1,1,1,1,1};
		game.playSet(playerOne, playerTwo, gameScenario, true, gameScenarioWithTieBreak);
		Assertions.assertTrue(game.isTieBreakGame());
		Assertions.assertTrue(playerOne.winSetWithTieBreak());

	 }

	@Test // scenario with tieBreak
	void Given_this_Scenario_TWO_TieBreak_Game_Player_One_Should_Win() {

		int[][] gameScenario ={ 	{1,1,1,1},{2,2,2,2},
									{1,1,1,1},{2,2,2,2},
									{1,1,1,1},{2,2,2,2},
									{1,1,1,1},{2,2,2,2},
									{1,1,1,1},{2,2,2,2},
									{1,1,1,1},{2,2,2,2}
		};


		int[] gameScenarioWithTieBreak ={1,1,2,2,1,1,1,1};
		game.playSet(playerOne, playerTwo, gameScenario, true, gameScenarioWithTieBreak);
		Assertions.assertTrue(game.isTieBreakGame());
		Assertions.assertTrue(playerOne.winSetWithTieBreak());

	}

	@Test // scenario with tieBreak
	void Given_this_Scenario_TWO_TieBreak_Game_Player_TWO_Should_Win() {

		int[][] gameScenario ={ {1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2},
								{1,1,1,1},{2,2,2,2}
		};
		int[] gameScenarioWithTieBreak ={2,2,2,2,1,1,1,1,2,2,};
		game.playSet(playerOne, playerTwo, gameScenario, true, gameScenarioWithTieBreak);
		Assertions.assertTrue(game.isTieBreakGame());
		Assertions.assertTrue(playerTwo.winSetWithTieBreak());

	}

	@Test // scenario Aleatoire
	void Given_this_Scenario_TWO_TieBreak_Game_Player_Random() {

		int[][] gameScenario ={ {1,1,1,1},{2,2,2,2},
				{1,1,1,1},{2,2,2,2},
				{1,1,1,1},{2,2,2,2},
				{1,1,1,1},{2,2,2,2},
				{1,1,1,1},{2,2,2,2},
				{1,1,1,1},{2,2,2,2}
		};

		game.playSetRandom(playerOne, playerTwo, gameScenario, true);
		System.out.println("player One "+ playerOne.winSetWithTieBreak()   +" with "+playerOne.getNumberPointWinWitTieBreak());
		System.out.println("player One "+  playerTwo.winSetWithTieBreak()+ " with "+playerTwo.getNumberPointWinWitTieBreak());


	}





	@AfterEach
	void tearThis() {
		logger.info("@AfterEach executed");
	}

	@AfterAll
	static void tear() {
		logger.info("@AfterAll executed");
	}

}
