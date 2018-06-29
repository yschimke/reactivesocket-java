/*
 * Copyright 2015-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.rsocket.transport.netty;

import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.rsocket.test.TransportTest;
import io.rsocket.transport.netty.client.WebsocketClientTransport;
import io.rsocket.transport.netty.server.WebsocketServerTransport;
import java.net.InetSocketAddress;
import java.time.Duration;
import reactor.ipc.netty.http.client.HttpClient;
import reactor.ipc.netty.http.server.HttpServer;

final class WebsocketSecureTransportTest implements TransportTest {

  private final TransportPair transportPair =
      new TransportPair<>(
          () -> new InetSocketAddress("localhost", 0),
          (address, server) ->
              WebsocketClientTransport.create(
                  HttpClient.create(
                      options ->
                          options
                              .connectAddress(server::address)
                              .sslSupport(
                                  c -> c.trustManager(InsecureTrustManagerFactory.INSTANCE))),
                  String.format(
                      "https://%s:%d/",
                      server.address().getHostName(), server.address().getPort())),
          address ->
              WebsocketServerTransport.create(
                  HttpServer.create(options -> options.listenAddress(address).sslSelfSigned())));

  @Override
  public void requestChannel2_000_000() {
    // disable
  }

  @Override
  public Duration getTimeout() {
    return Duration.ofMinutes(3);
  }

  @Override
  public TransportPair getTransportPair() {
    return transportPair;
  }
}
