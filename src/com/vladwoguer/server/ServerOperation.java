package com.vladwoguer.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import com.vladwoguer.exception.AuthorizationException;
import com.vladwoguer.utils.RandomString;

public class ServerOperation extends UnicastRemoteObject implements RMIInterface {

	private static final String CODE = "code123";
	private static final String MACHINE = "win-72-c";
	private static final long serialVersionUID = 1L;

	private List<String> authorizedTokens;

	private RandomString randomString;

	protected ServerOperation() throws RemoteException {
		super();

		authorizedTokens = new LinkedList<String>();
		randomString = new RandomString();
	}

	public static void main(String[] args) {

		try {

			Naming.rebind("//localhost/MyServer", new ServerOperation());
			System.err.println("Server ready");

		} catch (Exception e) {

			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();

		}

	}

	@Override
	public String auth(String machineName, String machineCode) throws RemoteException, AuthorizationException {
		if (MACHINE.equals(machineName) && CODE.equals(machineCode)) {
			String token = randomString.nextString();
			authorizedTokens.add(token);
			return token;
		}
		throw new AuthorizationException();
	}

	@Override
	public String checkStatus(String token, String echoMessage) throws RemoteException, AuthorizationException {
		if (authorizedTokens.contains(token)) {
			return String.format("Hello %s!", echoMessage);
		}
		throw new AuthorizationException();
	}

}
