/**
 * 
 */
package javaChat;

import javaChat.server.ServerChatSocket;

/**
 * @author gabriel
 *
 */
public class Server {
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Init...");
		ServerChatSocket server = new ServerChatSocket("127.0.0.1", 1337);
		server.run();
		System.out.println("...End");
	}

}
