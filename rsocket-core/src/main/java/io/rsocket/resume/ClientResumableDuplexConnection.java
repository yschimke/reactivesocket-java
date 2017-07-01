package io.rsocket.resume;

import io.rsocket.DuplexConnection;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ClientResumableDuplexConnection extends ResumableDuplexConnection {
  private Flux<DuplexConnection> connectionFactory;

  public ClientResumableDuplexConnection(ResumeCache cache,
      Flux<DuplexConnection> connectionFactory) {
    super(cache);
    this.connectionFactory = connectionFactory;
  }

  // TODO should this be a deferred method returning Mono<Void>?
  public Mono<Void> connect() {
    throw new UnsupportedOperationException();
  }
}
