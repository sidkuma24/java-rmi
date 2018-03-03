import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiInterface extends Remote {
   
    String createFile(String fileName) throws RemoteException;
    String writeFile(String fileName) throws RemoteException;
    String readFile(String fileName) throws RemoteException;
    String deleteFile(String fileName) throws RemoteException;
    
}
