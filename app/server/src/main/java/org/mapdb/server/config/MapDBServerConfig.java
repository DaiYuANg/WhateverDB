package org.mapdb.server.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mapdb.core.config.MapDBConfig;

@EqualsAndHashCode(callSuper = true)
@Data
public class MapDBServerConfig extends MapDBConfig {
  private Integer port = 5150;

  private Boolean cluster = true;

  private Boolean webUI = true;
}
