package javaChat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;

public class ClientConnectionHandler extends Observable implements Runnable{

	private Socket socket = null;
	//private InputStream is = null;
	private BufferedReader ibr = null;
	private OutputStream os = null;
	private boolean goOn = true;
	//private InetAddress inetAdress = null;
	private String clientKey = null;
	
	
	public ClientConnectionHandler(Socket socket) throws IOException {
		this.socket = socket;
		//this.is = this.socket.getInputStream();
		this.os = this.socket.getOutputStream();
		this.ibr = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		generaClientKey();
		//this.inetAdress = this.socket.getInetAddress();
		
	}
	
	@Override
	public void run() {
		
		System.out.println("Nuevo thread!!");
		String msgInput ="";
		while(goOn()) {
			
			try {
				
				msgInput = this.ibr.readLine();
				if("Exit".equalsIgnoreCase(msgInput)) {
					this.goOn = false;
				}
				this.os.write(msgInput.getBytes());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Hemos salido por lo que cerramos el socket.
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			notifyObservers();
		}
		
	}
	
	private boolean goOn() {
		return this.goOn;
	}
	
	private void generaClientKey() {
		StringBuffer key =  new StringBuffer();
		
		key.append(this.socket.getInetAddress().getHostAddress());
		key.append("_");
		key.append(this.socket.getPort());
		
		this.clientKey = key.toString();
		
	}
	
	public String getClientKey() {
		
		return this.clientKey;
	}
}
