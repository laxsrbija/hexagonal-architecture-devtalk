package net.lazars.greeting.core.port.inbound;

public interface NameService {

  String getName(String id);

  String getNickname(String id);
}
