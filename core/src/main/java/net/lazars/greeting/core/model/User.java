package net.lazars.greeting.core.model;

import java.util.List;
import lombok.Builder;

@Builder
public record User(String id, String name, List<String> nicknames) {}
