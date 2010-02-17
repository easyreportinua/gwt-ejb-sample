/*
 * Copyright 2008 Jeff Dwyer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.sample.dynatable.utils;

/**
 * Updated by Jeff Dwyer to add HibernateFilter and allow explicit
 * Serialization
 *
 * Copyright 2006 George Georgovassilis <g.georgovassilis[at]gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class GWTService extends RemoteServiceServlet implements RemoteService {

    @Override
    public String processCall(String payload) throws SerializationException {
        try {
            RPCRequest rpcRequest = RPC_2_0_1.decodeRequest(payload, this
                    .getClass(), this);

            onAfterRequestDeserialized(rpcRequest);
            return RPC_2_0_1.invokeAndEncodeResponse(this, rpcRequest.getMethod(),
                    rpcRequest.getParameters(), rpcRequest.getSerializationPolicy(),
                    rpcRequest.getFlags());

        } catch (IncompatibleRemoteServiceException ex) {
            ex.printStackTrace();
            return RPC.encodeResponseForFailure(null, ex);
        } catch (Exception e) {
            e.printStackTrace();
            return RPC.encodeResponseForFailure(null, e);
        }
    }
}
