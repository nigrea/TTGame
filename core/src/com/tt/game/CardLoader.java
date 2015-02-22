package com.tt.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class CardLoader {
	
	public static ArrayList<Card> loadCard(){
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		FileHandle file = Gdx.files.internal("data.csv");
		String text = file.readString();
		
		String lines[] = text.split("\\r?\\n");
		
		for (int i = 0; i < lines.length; i++) {
			
			String[] array = lines[i].split(",");
			
			int[] power = {Integer.parseInt(array[1]),Integer.parseInt(array[2]),Integer.parseInt(array[3]),Integer.parseInt(array[4])};
			
			cards.add(new Card(Integer.parseInt(array[0]),power,array[5]));
			
		}

		return cards;
		
	}

}
