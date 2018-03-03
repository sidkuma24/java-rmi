import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
        
public class Server implements RmiInterface {
        
    HashMap<String,String> concurrency = new HashMap<String,String>();
    
        
    public static void main(String args[]) {
    	  
    	String hostIP = null;
      	// parser CL arguments
      	if(args.length < 2) {
      		System.out.println("Usage : java Server -host-ip [host-ip]");
      		System.exit(1);
      	}else {
      		for(int i=0; i < args.length; ++i) {
      			if(args[i].equals("-host-ip")) {
      				hostIP = args[i+1];
      			}
      		}
      	}
      	
        try {
        	
        	System.setProperty("java.rmi.server.hostname", hostIP);
            Server obj = new Server();
            RmiInterface stub = (RmiInterface) UnicastRemoteObject.exportObject(obj, 0);
           

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("RMI", stub);

            
            System.err.println("Server ready");
            
            
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

	@Override
	public String createFile(String fileName) throws RemoteException {
		
		String hostname = null;
		try {
			hostname = RemoteServer.getClientHost();
			System.out.println(hostname);
			concurrency.put(hostname, fileName);
			
		} catch (ServerNotActiveException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		File newfile = new File("userfiles", fileName + ".txt");
		File fileinuse = new File("userfiles", fileName + ".txt.lock");
		if(newfile.exists() || fileinuse.exists()){
			return "file name taken!";
		}
		else if(concurrency.containsValue(fileName)){
			return "file name in use!";
		}

		try {
			newfile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return e.toString();
		}
		
		concurrency.remove(hostname, fileName);
		
	
		return "file created!";
	}
	
	@Override
	public String writeFile(String fileName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readFile(String fileName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteFile(String fileName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}



}