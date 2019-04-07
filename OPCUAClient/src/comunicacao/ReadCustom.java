package comunicacao;

import static comunicacao.Connect.connect;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
//import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
 
public class ReadCustom {  
   
    public static CompletableFuture<DataValue> read(final OpcUaClient client, final NodeId nodeId) {
        return client.readValue(0, TimestampsToReturn.Both, nodeId);
    }
    
    public static void main(String[] args) throws Exception{

    	for(int i=0;i<10;i++) {
    		try {
            	NodeId node= new NodeId(4, "|var|CODESYS Control Win V3 x64.Application.PLC_PRG.a"+i);
                OpcUaClient client=connect().get();
                DataValue value= read(client, node).get();
                System.out.println("Variável a"+ i+ ":  " + value.getValue().getValue().toString());
                client.disconnect().get();
        	}
    		catch(NullPointerException e) {
        		//System.out.println("Variável s"+ i+ ": null");	
        	}
    	}
    	
    	for(int s=0;s<10;s++)  {
    		try {
	        	NodeId node= new NodeId(4, "|var|CODESYS Control Win V3 x64.Application.PLC_PRG.s"+s);
	            OpcUaClient client=connect().get();
	            DataValue value= read(client, node).get();
	            System.out.println("Variável s"+ s+ ":  " + value.getValue().getValue().toString());
	            client.disconnect().get();
    		}
    		catch(NullPointerException e) {
    		//System.out.println("Variável s"+ s+ ": null");	
    		}
        }	
    }
}