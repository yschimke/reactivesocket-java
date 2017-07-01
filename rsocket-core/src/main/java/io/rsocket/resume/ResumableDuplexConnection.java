package io.rsocket.resume;

import io.rsocket.DuplexConnection;
import io.rsocket.Frame;
import javax.annotation.Nullable;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ResumableDuplexConnection implements DuplexConnection {
  private final ResumeCache cache;
  private @Nullable ResumeToken token;
  private @Nullable DuplexConnection connection;
  private Status status;

  public ResumableDuplexConnection(ResumeCache cache) {
    this.cache = cache;
  }

  public enum Status {
    NEW,
    CONNECTED,
    DISCONNECTED,
    CLOSED
  }

  @Override public double availability() {
    return ;
  }

  @Override public Mono<Void> close() {
    return Mono.fromRunnable(() -> {
      status = Status.CLOSED;
      connection = null;
    });
  }

  @Override public Mono<Void> onClose() {
    return null;
  }

  @Override public Mono<Void> send(Publisher<Frame> frame) {
    return null;
  }

  @Override public Flux<Frame> receive() {
    return null;
  }

  public void disconnect() {
  }

  protected void reconnect(DuplexConnection newConnection) {
  }
}
