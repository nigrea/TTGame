//package com.tt.game.engine;
//
//public class Game {
//
//	Card[][] hands;
//	int turn, turnCount = 0, pointsOne, pointsTwo;
//	Card[][] boardArray;
//
//	public Game(Card[][] hands) {
//		this.hands = hands;
//		boardArray = new Card[3][3];
//		turn = 1;
//	}
//
//	public void playCard(int player, int cardInHand, int x, int y) {
//		Card card = hands[player][cardInHand];
//		if (player == turn && canPlayCard(x, y) && card != null) {
//			boardArray[x][y] = card;
//			card.isPlayed(x, y);
////			doFlips(card);
//			turnCount++;
//			turn = (turnCount % 2) + 1;
//			pointsOne = 0;
//			pointsTwo = 0;
////			for (Card[] cardArray : boardArray) {
////				for (Card playedCard : cardArray) {
////					if(playedCard.side == 1){
////						pointsOne++;
////					}else{
////						pointsTwo++;
////					}
////				}
////			}
//			
//			if(turnCount >= 9){
//				endGame();
//			}
//		}
//	}
//
//	private void endGame() {
//		
//	}
//
//	public boolean canPlayCard(int x, int y) {
//		return boardArray[x][y] == null;
//	}
//
////	public void doFlips(Card card) {
////
////		if (card.y < 2 && boardArray[card.x][card.y + 1] != null) {
////			checkFlip(card, boardArray[card.x][card.y + 1], 1);
////		}
////
////		if (card.x < 2 && boardArray[card.x + 1][card.y] != null) {
////			checkFlip(card, boardArray[card.x + 1][card.y], 2);
////		}
////
////		if (card.y > 0 && boardArray[card.x][card.y - 1] != null) {
////			checkFlip(card, boardArray[card.x][card.y - 1], 3);
////		}
////
////		if (card.x > 0 && boardArray[card.x - 1][card.y] != null) {
////			checkFlip(card, boardArray[card.x - 1][card.y], 4);
////		}
////
////	}
//
////	public void checkFlip(Card flipper, Card toFlip, int attackingSide) {
////		if (flipper.side != toFlip.side && flipper.power[attackingSide] > toFlip.power[(attackingSide + 2) % 4]) {
////			toFlip.flip();
////			doFlips(toFlip);
////		}
////	}
//	
//	
//
//}
