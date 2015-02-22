package com.tt.game;


public class Card {
	
	private int id;
	private int[] power;
	private String name;
	
	public Card(int id, int[] power, String name){
		this.id = id;
		this.power = power;
		this.name = name;		
	}

	public int getId() {
		return id;
	}

	public int[] getPower() {
		return power;
	}

	public String getName() {
		return name;
	}
	
	

}
