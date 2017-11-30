import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.io.IOException;

public class Client {
    private int RN[];
    private String estado; // R,A,V
    private InterfaceRMI stub; //para acceder a los metodos implementados en el server
    private boolean token; //true: si tiene el token, false si no.
    private String id; //id del proceso
    private int sn;
    private boolean bearer;
    private int delay;
    
	//constructor
	public Client(boolean b, int d) {
		this.estado = "V";
		this.sn = 0;
		this.token = false;
		this.bearer = b;
		this.delay = d;
		
	}
	
	private void request(String id, int seq) throws RemoteException, IOException{
		this.stub.request(id, seq); //hace request
		//actualiza RN
		
	}
	
	private boolean waitToken(){
		return false;}
	private boolean takeToken(){
		return false;}
	private boolean sendToken(){
		return false;}
	private void kill(){}

    public static void main(String[] args) {
    	
    	Client client = new Client(true, 3);
        String host;
        if (args.length<1){
        	System.out.println("Error, no se puede asignar host");
        }
       
    	host = args[0];
    	client.start(host);
    }
    
    void start(String host){
    	
    	try {
            Registry registry = LocateRegistry.getRegistry(host);
            InterfaceRMI stub = (InterfaceRMI) registry.lookup("InterfaceRMI");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        
    	while (!this.token){
    		if (this.estado.equals("V")){
            	//si no tiene el token y esta disponible
    			if (this.token){
    				this.sn = this.sn + 1;
    				RN[Integer.parseInt(this.id)] = this.sn;
    			 
    				//ejecutar resto del codigo
    				sleep(this.delay); //duerme un rato, simula que aun no entra en su seccion critica
    				
    				this.request(this.id, this.sn); 
    				this.estado = "A";
    			}
    		}
    		
            else if (this.estado.equals("A")){
            	if (this.stub.waitToken()){
            		
            	}
            }
    		this.token = true;
    	}
    	
    	//tiene el token
    	System.out.println("Proceso "+ this.id + "entrando a su sección crítica...");
    	this.stub.takeToken(token);
    	sleep(this.delay); // ejecuta SC
    	//sale de la SC, libera el token
    	this.token= false;
    	System.out.println("Proceso "+ this.id + "saliendo de su sección crítica...");
    	
        //metodo de la interfaz
        String response = stub.sayHello();
        System.out.println("response: " + response);
    }
}
