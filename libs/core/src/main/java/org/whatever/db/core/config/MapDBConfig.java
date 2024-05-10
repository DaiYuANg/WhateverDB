package org.whatever.db.core.config;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.nio.file.Path;

@Data
@SuperBuilder
@Getter
public class MapDBConfig {

  private final Path path;
}
