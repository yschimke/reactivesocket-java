package io.rsocket.resume;

import io.rsocket.DuplexConnection;

public class ServerResumableDuplexConnection extends ResumableDuplexConnection {
  public ServerResumableDuplexConnection(ResumeCache cache, ResumeToken token) {
    super(cache, token);
  }

  @Override public void reconnect(DuplexConnection newConnection) {
    super.reconnect(newConnection);
  }
}
