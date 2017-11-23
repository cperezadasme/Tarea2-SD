import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements InterfaceRMI {

	private int last[];
	private ArrayList<Integer> cola;
	
	//constructor
	public Server (){} 
	
	//implementar metodos de la interfaz RMI	
	public void request(String id, int seq) {}
	
	public boolean waitToken() {
		return true;
	}
	
	public boolean takeToken(String token) {
		return true;
	}
	
	public void kill() {}
	
	public static void main(String args[]) {
        
        try {
            Server obj = new Server();
            InterfaceRMI stub = (InterfaceRMI) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("InterfaceRMI", stub);

            System.out.println("[Servidor] Servidor esperando clientes...");
        } catch (Exception e) {
            System.out.println("Server exception: " + e.toString());
            //e.printStackTrace();
        }
    }
	
}
