package com.tt.game.screens;

import java.io.IOException;
import java.net.InetAddress;

import com.badlogic.gdx.Screen;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.tt.game.MyGame;
import com.tt.game.network.SomeRequest;
import com.tt.game.network.SomeResponse;
import com.esotericsoftware.kryonet.Listener;

public class FindServerScreen implements Screen {
	
	MyGame myGame;
	
	public FindServerScreen(MyGame myGame){
		this.myGame = myGame;
		
		Client client = new Client();
	    
		Kryo kryo = client.getKryo();
		kryo.register(SomeRequest.class);
		kryo.register(SomeResponse.class);
		
		client.start();
	    
	    InetAddress address = client.discoverHost(54777, 5000);
	    System.out.println(address);
	    
	    try {
			client.connect(5000, address, 54555, 54777);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    client.addListener(new Listener() {
	        public void received (Connection connection, Object object) {
	           if (object instanceof SomeResponse) {
	              SomeResponse response = (SomeResponse)object;
	              System.out.println(response.text);
	           }
	        }
	     });

	    SomeRequest request = new SomeRequest();
	    request.text = "Here is the request";
	    client.sendTCP(request);
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
