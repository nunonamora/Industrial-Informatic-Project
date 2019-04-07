package comunicacao;
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




import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;



public class Write {

    private static final NodeId NODE_TO_WRITE = new NodeId(4, "|var|CODESYS Control Win V3 x64.Application.PLC_PRG.s2");

    public static CompletableFuture<StatusCode> write(
            final OpcUaClient client,
            final NodeId nodeId,
            final Object value) {

        return client.writeValue(nodeId, new DataValue(new Variant(value)));
    }

    public static void main(final String[] args) throws Exception {

        final boolean value = true;

        // first example

        Connect.connect()

                .thenCompose(client -> {

                    return write(
                            client,
                            NODE_TO_WRITE,
                            value // value to write
                    )

                            .whenComplete((result, error) -> {
                                if (error == null) {
                                    System.out.format("Result: %s%n", result);
                                } else {
                                    error.printStackTrace();
                                }
                            })

                            .thenCompose(v -> client.disconnect());
                })

                .get(); // wait for everything to complete
    }
}
