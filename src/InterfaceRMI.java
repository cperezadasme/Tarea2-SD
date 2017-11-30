import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMI extends Remote {

	void request(String id, int seq) throws RemoteException, IOException;
	boolean waitToken() throws RemoteException;
	boolean takeToken(String token) throws RemoteException;
	boolean kill() throws RemoteException;
}
