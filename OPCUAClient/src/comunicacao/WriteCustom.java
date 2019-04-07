package comunicacao;


import static comunicacao.Connect.connect;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;


public class WriteCustom {
 
    public static CompletableFuture<StatusCode> write(
            final OpcUaClient client,
            final NodeId nodeId,
            final Object value) {

        return client.writeValue(nodeId, new DataValue(new Variant(value)));
    }

    public static void main(final String[] args) throws Exception {

        final boolean value = false;
          
        for(int i=0;i<7;i++) {
	        OpcUaClient client=connect().get();
	        NodeId node = new NodeId(4, "|var|CODESYS Control Win V3 x64.Application.PLC_PRG.a"+i);
	        write(client, node,value);
	        client.disconnect().get();
        }
        
        for(int s=0;s<7;s++) {
            OpcUaClient client=connect().get();
            NodeId node = new NodeId(4, "|var|CODESYS Control Win V3 x64.Application.PLC_PRG.s"+s);
            write(client, node,value);
            client.disconnect().get();
        }
    }
}