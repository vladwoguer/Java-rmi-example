package com.vladwoguer.client;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.vladwoguer.exception.AuthorizationException;
import com.vladwoguer.server.RMIInterface;

public class ClientOperation {

	private static RMIInterface lookUpServer;

	public static void main(String[] args) 
		throws MalformedURLException, RemoteException, NotBoundException {
		
		lookUpServer = (RMIInterface) Naming.lookup("//localhost/MyServer");
			
		String token;
		try {
			token = lookUpServer.auth("win-72-c", "code123");
			System.out.println(lookUpServer.checkStatus(token, "win-72-c"));
		} catch (AuthorizationException e) {
			System.err.println("Error 1: You have no authorization to do this!");
		}
		

	}

}
