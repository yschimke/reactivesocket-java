package io.rsocket.resume;

import io.rsocket.DuplexConnection;
import io.rsocket.Frame;
import javax.annotation.Nullable;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ResumableDuplexConnection implements DuplexConnection {
  private final ResumeCache cache;
  private final ResumeToken token;
  private @Nullable DuplexConnection connection;

  public ResumableDuplexConnection(ResumeCache cache, ResumeToken token) {
    this.cache = cache;
    this.token = token;
  }

  public enum Status {
    DISCONNECTED,
    CONNECTED,
    CLOSED
  }

  @Override public double availability() {
    return 0;
  }

  @Override public Mono<Void> close() {
    return null;
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
