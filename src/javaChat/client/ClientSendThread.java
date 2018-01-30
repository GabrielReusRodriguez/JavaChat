/**
 * 
 */
package javaChat.client;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gabriel
 *
 */
public class ClientSendThread implements Runnable {

	private OutputStream os = null;
	private List<Message> toSend = null;
	private boolean execute = true;
	
	public ClientSendThread(OutputStream os,List<Message> toSend) {
		this.os = os;
		this.toSend = toSend;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(this.execute) {
			if(toSend.size() > 0) {
				Message msg = toSend.remove(0);
				try {
					os.write(msg.getMsg().getBytes());
				} catch (IOException e) {
					//e.printStackTrace();
					
				}
			}
		}
	}
	
	
	public void stop() {
		this.execute = false;
	}

}
