/*******************************************************************************
 * Copyright (c) 2017 Red Hat Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jens Reimann - initial API and implementation
 *******************************************************************************/



import static java.util.Arrays.asList;
import static java.util.Collections.nCopies;
import static java.util.Collections.singletonList;
import static org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn.Both;

import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.AttributeId;
//import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

//import io.glutamate.lang.Exceptions;
//import io.glutamate.str.Tables;



public class Read {

	
	private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ISO_INSTANT;
	private static DataValue value;
    //public static final NodeId NODE_TO_READ = new NodeId(4, "|var|CODESYS Control Win V3 x64.Application.GVL" );

    public static CompletableFuture<DataValue> read(
            final OpcUaClient client,
            final NodeId nodeId) {

        return client.readValue(0, TimestampsToReturn.Both, nodeId);
    }

    public static CompletableFuture<List<DataValue>> read(
            final OpcUaClient client,
            final AttributeId attributeId,
            final NodeId... nodeIds) {

        return client
                .read(
                        0,
                        Both,
                        asList(nodeIds),
                        nCopies(nodeIds.length, attributeId.uid()));
    }
    
    
    public static String toString(final Variant value) {

        return String.format("%s",value.getValue());
    }
    
    public static String toString(final StatusCode statusCode) {
        return StatusCodes
                .lookup(statusCode.getValue()) // lookup
                .map(s -> s[0]) // pick name
                .orElse(statusCode.toString()); // or default to "toString"
    }
    
    public static void dumpValues(final PrintStream out, final List<NodeId> nodeIds, final List<DataValue> values) {
        final int len = Integer.max(nodeIds.size(), values.size());

        final List<List<String>> data = new ArrayList<>(len);

        for (int i = 0; i < Integer.min(nodeIds.size(), values.size()); i++) {

            final List<String> row = new ArrayList<>(5);
            data.add(row);

             value = values.get(i);

            row.add(nodeIds.get(i).toParseableString());
            row.add(toString(value.getValue()));
       //     System.out.println(toString(value.getValue()));
            row.add(toString(value.getStatusCode()));
            row.add(TIMESTAMP_FORMATTER.format(value.getServerTime().getJavaDate().toInstant()));
            row.add(TIMESTAMP_FORMATTER.format(value.getSourceTime().getJavaDate().toInstant()));
            
           
        }

     /*   Exceptions.wrap(() -> {
            Tables.showTable(out,
                    asList("Node Id", "Value", "State", "Timestamp(Server)", "Timestamp(Source)"),
                    data, 2);
        });
	*/
     //   System.out.println();
        
    
    }

    
    //public static void main(String[] argv) throws Exception {
    public static void runRead()  throws Exception {

        // == first example
    
    	for( int i=0;i<5;i++) {
    	NodeId NODE_TO_READ = new NodeId(4, "|var|CODESYS Control Win V3 x64.Application.GVL.a"+i );
    	
        Connect.connect()

        
        
                .thenCompose(client -> {
                    return read(client, NODE_TO_READ)
                            .thenAccept(value -> {
                                dumpValues(
                                        System.out,
                                        singletonList(NODE_TO_READ),
                                        singletonList(value));
                            })
                            
                            
                            .thenCompose(v -> client.disconnect());
                })

                .get();
    
        System.out.println("Variável a"+ i + ": "+  toString(value.getValue())+"  Finalmente crl!!!");
    	}
    	
    	for(int s=0;s<3;s++) {
        	NodeId NODE_TO_READ = new NodeId(4, "|var|CODESYS Control Win V3 x64.Application.GVL.s"+s );
        	
            Connect.connect()

            
            
                    .thenCompose(client -> {
                        return read(client, NODE_TO_READ)
                                .thenAccept(value -> {
                                    dumpValues(
                                            System.out,
                                            singletonList(NODE_TO_READ),
                                            singletonList(value));
                                })
                                .thenCompose(v -> client.disconnect());
                    })

                    .get();
            System.out.println("Variável s"+ s + ": "+  toString(value.getValue())+"  Finalmente crl!!!");
            
        	}

    
    }
}
