import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Queue;

public class Server implements InterfaceRMI {

	private int LN[];
	private int RN[];
	private Queue<String> cola = new LinkedList<String>();
    private String token;
	
	//constructor
	public Server (){} 
	
	//implementar metodos de la interfaz RMI	
	@Override
	public void request(String id, int seq){		
		int id_process = Integer.parseInt(id);
		//int sn = Integer.parseInt(seq);
		int max = Math.max(RN[id_process],seq);
		
		//agregar proceso a la cola
		cola.add(id); 
	
		RN[id_process] = max;
	}
	
	@Override
	public boolean waitToken() {
		return true;
	}
	
	@Override
	public boolean takeToken(String token) {
		
		cola.remove(); //elimina el proceso de la cola
		return true;
	}
	
	@Override
	public boolean kill() {
		return false;
	}
	
	public static void main(String args[]) {
        
        try {
            Server obj = new Server();
            InterfaceRMI stub = (InterfaceRMI) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("InterfaceRMI", stub);

            System.out.println("[Servidor] Servidor esperando clientes...");
        } catch (Exception e) {
            System.out.println("Error servidor: " + e.toString());
            //e.printStackTrace();
        }
    }
}
