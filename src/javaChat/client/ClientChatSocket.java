/**
 * 
 */
package javaChat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import javaChat.exceptions.JavaChatException;

/**
 * @author gabriel
 *
 */
public class ClientChatSocket {
	
	//private String host =null;
	//private int port = 1337;
	private Socket socket = null;
	private boolean goOn = true;
	private static final int TIMEOUT = 500;
	private SocketAddress socketAddress = null;
	private ClientSendThread sendHandler = null;
	private ClientRecThread recHandler = null;
	private List<Message> toSend = null;
	private BufferedReader stdIn = null;
	
	
	
	public ClientChatSocket(String host,int port) {
		//this.port = port;
		//this.host = host;
		socketAddress = new InetSocketAddress(host, port);
		toSend = new ArrayList<Message>();
		stdIn= new BufferedReader(new InputStreamReader(System.in));
	}
	
	public ClientChatSocket(int port) {
		//this.port = port;
		//this.host = "127.0.0.1";
		socketAddress = new InetSocketAddress("127.0.0.1", port);
		toSend = new ArrayList<Message>();
		stdIn = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void run() throws JavaChatException{
		
		socket = new Socket();
		
		try {
			this.socket.connect(socketAddress,TIMEOUT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new JavaChatException(e);
		}
		
		try {
			
			this.sendHandler = new ClientSendThread(socket.getOutputStream(), toSend);
			this.recHandler = new ClientRecThread(socket);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			throw new JavaChatException(e1);
		}
		
		String consoleMsg ="";
		while(goOn()) {

			try {
				consoleMsg = stdIn.readLine();
				toSend.add(new Message(consoleMsg));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		
		//Saliendo....
		this.sendHandler.stop();
		this.recHandler.stop();
		
		
		try {
			socket.close();
		} catch (IOException e) {
			//e.printStackTrace();
			throw new JavaChatException(e);
		}
		
	}
	
	private boolean goOn() {
		return true;
	}

}
