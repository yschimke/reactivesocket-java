package io.rsocket.resume;

public class ClientResumableDuplexConnection extends ResumableDuplexConnection {
  public ClientResumableDuplexConnection(ResumeCache cache, ResumeToken token) {
    super(cache, token);
  }

  public void connect() {
    disconnect();

  }
}
