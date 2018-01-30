/**
 * 
 */
package javaChat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author gabriel
 *
 */
public class ServerChatSocket implements Observer {

	private String host = "127.0.0.1";
	private int port = 1337;
	private ServerSocket listener = null;
	private boolean init = false;
	//private List<ClientConnectionHandler> clientes = null;
	private Hashtable<String, ClientConnectionHandler> clientes = null;

	public ServerChatSocket(int port) {
		this.port = port;
		//this.clientes = new ArrayList<ClientConnectionHandler>();
		this.clientes = new Hashtable<String,ClientConnectionHandler>();
	}

	public ServerChatSocket(String host, int port) {

		this.host = host;
		this.port = port;
		//this.clientes = new ArrayList<ClientConnectionHandler>();
		this.clientes = new Hashtable<String,ClientConnectionHandler>();
		
	}


	public void run() {

		try {
			listener = new ServerSocket(this.port);
			while (goOn()) {
				
				addThread(listener);
				
			}
			
		} catch (IOException e) {
			System.err.println("Could not listen on port: "+this.port+".");
            System.exit(-1);
		} finally {
			if (listener != null && !listener.isClosed()) {
				try {
					listener.close();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
		}

			
	}
	
	private boolean goOn() {
		//return true;
		
		boolean retorno = true;
		
		retorno = !init || clientes.size() == 0 ? true : false;
		
		return retorno;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		/*
		synchronized (this) {
			this.numThreads--;
		}*/
		
		ClientConnectionHandler connection = (ClientConnectionHandler) arg0;
		if(this.clientes.containsKey(connection.getClientKey())) {
			this.clientes.remove(connection.getClientKey());
		}
		
	}
	
	private void addThread(ServerSocket listener)  throws IOException{
		
		//new ClientConnectionHandler(listener.accept()).run();
		Socket clientSocket = listener.accept();
		
		ClientConnectionHandler clientHandler = new ClientConnectionHandler(clientSocket);
		clientHandler.addObserver(this);
		
		//Iniciamos thread
		(new Thread(clientHandler)).start();
		
		
		//clientes.put(clientHandler.getClientKey(),clientHandler);
		clientes.putIfAbsent(clientHandler.getClientKey(),clientHandler);
		
		/*
		synchronized (this) {
			if(!init) {
				init = true;
			}
			this.numThreads++;
		}*/
	}

}
