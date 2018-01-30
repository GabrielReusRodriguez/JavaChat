/**
 * 
 */
package javaChat;

import javaChat.client.ClientChatSocket;
import javaChat.exceptions.JavaChatException;

/**
 * @author gabriel
 *
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Init...");
		ClientChatSocket client  = new ClientChatSocket("127.0.0.1", 1337);
		try {
			client.run();
		} catch (JavaChatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("...End");
	}

}
