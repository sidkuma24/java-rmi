

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

	static Registry registry = null;
	static RmiInterface stub = null;
	
	static Scanner sc = new Scanner(System.in);
    

    public static void main(String[] args) {

    	String input = null;
    	int choice = 0;
        String hostIP = "192.168.56.101";
    	// parser CL arguments
//    	if(args.length < 2) {
//    		System.out.println("Usage : java Client -host-ip [host-ip]");
//    		System.exit(1);
//    	}else {
//    		for(int i=0; i < args.length; ++i) {
//    			if(args[i].equals("-host-ip")) {
//    				hostIP = args[i+1];
//    			}
//    		}
//    	}
    	
        try {
            registry = LocateRegistry.getRegistry(hostIP);//put your hosts ip here
            System.out.println("Connecting to Host: " + hostIP + "...");
            stub = (RmiInterface) registry.lookup("RMI");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        
        while (true == true){
	        System.out.println("Please choose an option!");
	        System.out.println("  1: Create file");
	        System.out.println("  2: Read file");
	        System.out.println("  3: Write file");
	        System.out.println("  4: Delete file");
	        System.out.println("  5: Exit");
        	input = sc.nextLine();
        	
        	choice = Integer.parseInt(input);
        	input = "";
        	System.out.println(input);
        	
        	if(choice == 1){
        		createFile();
        	}
        	else if (choice == 2){
        		
        	}
        	else if (choice == 3){
        		
        	}
        	else if (choice == 4){
        		
        	}
        	else if (choice == 5){
        		System.exit(100);
        	}
        	
	
        }
    }

	private static void createFile() {
		
		String response = null;
		String fileName = null;
		
		
		System.out.println("what do you want to name your file?");
		fileName = sc.nextLine();
		
	
		
		try {
			response = stub.createFile(fileName);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("response: " + response);
		
	}
}