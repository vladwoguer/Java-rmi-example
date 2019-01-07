package com.vladwoguer.server;
import java.rmi.Remote;
import java.rmi.RemoteException;

import com.vladwoguer.exception.AuthorizationException;

public interface RMIInterface extends Remote {
	
	public String auth(String machineName, String machineCode)  throws RemoteException, AuthorizationException;

    public String checkStatus(String token, String echoMessage) throws RemoteException, AuthorizationException;  
}