import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    int RN[];
    String estado; // R,A,V
    InterfaceRMI stub; //para acceder a los metodos implementados en el server
    
	//constructor
	private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            InterfaceRMI stub = (InterfaceRMI) registry.lookup("InterfaceRMI");
            
            //metodo de la interfaz
            String response = stub.sayHello();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
