import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMI extends Remote {

	public void request(String id, int seq) throws RemoteException;
	public boolean waitToken() throws RemoteException;
	public boolean takeToken(String token) throws RemoteException;
	public void kill() throws RemoteException;
}
