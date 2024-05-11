package org.whatever.db.server.config;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.whatever.db.core.config.MapDBConfig;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Getter
public class MapDBServerConfig extends MapDBConfig {

  @Builder.Default
  private Integer port = 5150;

  @Builder.Default
  private Boolean cluster = true;

  @Builder.Default
  private Boolean webUI = true;
}
