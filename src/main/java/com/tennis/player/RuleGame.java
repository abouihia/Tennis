package com.tennis.player;

import lombok.experimental.UtilityClass;

import java.util.function.BiPredicate;

/**
 * @author brahimabouihia
 * @since 2020-07-28
 */
@UtilityClass
public   final class RuleGame {


       BiPredicate<Player,Player> isPlayerWinSetWithoutTieBreakPredicate = (playerOne, playerTwo)
            ->   playerOne.winSet() ||  playerTwo.winSet();


       BiPredicate<Player,Player> isTieBreakPredicate= (playerOne, playerTwo)
            ->  playerOne.winSet() == playerTwo.winSet();



       BiPredicate<Player,Player> isDeucePredicate  = (playerOne, playertwo)
            ->  playerOne.getScore() == 3  && playertwo.getScore() ==3;
}
