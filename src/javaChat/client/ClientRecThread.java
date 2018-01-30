/**
 * 
 */
package javaChat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javaChat.exceptions.JavaChatException;

/**
 * @author gabriel
 *
 */
public class ClientRecThread implements Runnable{

	private BufferedReader br = null;
	private Socket socket = null;
	private boolean execute = true;
	
	public ClientRecThread(Socket socket) throws JavaChatException {
		this.socket = socket;
		try {
			br = new BufferedReader(new InputStreamReader( this.socket.getInputStream() ));
		} catch (IOException e) {
			//e.printStackTrace();
			throw new JavaChatException(e);
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String mensaje ="";
		while(this.execute) {
		
			try {
				mensaje = br.readLine();
				
				StringBuffer consoleMsg = new StringBuffer();
				consoleMsg.append(socket.getInetAddress().getHostAddress());
				consoleMsg.append("@");
				consoleMsg.append(socket.getPort());
				consoleMsg.append(":");
				consoleMsg.append(mensaje);
				System.out.println(consoleMsg.toString());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		
	}
	

	public void stop() {
		this.execute = false;
	}
}
