/*
 * Copyright 2020 MariaDB Ab.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mariadb.r2dbc;

import static io.r2dbc.spi.ConnectionFactoryOptions.DRIVER;

import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.ConnectionFactoryProvider;
import io.r2dbc.spi.Option;
import java.time.Duration;
import org.mariadb.r2dbc.util.Assert;

public final class MariadbConnectionFactoryProvider implements ConnectionFactoryProvider {
  public static final String MARIADB_DRIVER = "mariadb";
  public static final Option<String> SOCKET = Option.valueOf("socket");
  public static final Option<Boolean> ALLOW_MULTI_QUERIES = Option.valueOf("allowMultiQueries");
  public static final Option<String> TLS_PROTOCOL = Option.valueOf("tlsProtocol");
  public static final Option<String> SERVER_SSL_CERT = Option.valueOf("serverSslCert");
  public static final Option<String> CLIENT_SSL_CERT = Option.valueOf("clientSslCert");
  public static final Option<Boolean> ALLOW_PIPELINING = Option.valueOf("allowPipelining");
  public static final Option<Boolean> USE_SERVER_PREPARE = Option.valueOf("useServerPrepStmts");
  public static final Option<Boolean> AUTO_COMMIT = Option.valueOf("autoCommit");
  public static final Option<Integer> PREPARE_CACHE_SIZE = Option.valueOf("prepareCacheSize");
  public static final Option<String> SSL_MODE = Option.valueOf("sslMode");
  public static final Option<String> CONNECTION_ATTRIBUTES = Option.valueOf("connectionAttributes");
  public static final Option<String> PAM_OTHER_PASSWORD = Option.valueOf("pamOtherPwd");
  public static final Option<Duration> SOCKET_TIMEOUT = Option.valueOf("socketTimeout");
  public static final Option<Boolean> TCP_KEEP_ALIVE = Option.valueOf("tcpKeepAlive");
  public static final Option<Boolean> TCP_ABORTIVE_CLOSE = Option.valueOf("tcpAbortiveClose");

  static MariadbConnectionConfiguration createConfiguration(
      ConnectionFactoryOptions connectionFactoryOptions) {
    Assert.requireNonNull(connectionFactoryOptions, "connectionFactoryOptions must not be null");
    return MariadbConnectionConfiguration.fromOptions(connectionFactoryOptions).build();
  }

  @Override
  public MariadbConnectionFactory create(ConnectionFactoryOptions connectionFactoryOptions) {
    return new MariadbConnectionFactory(createConfiguration(connectionFactoryOptions));
  }

  @Override
  public String getDriver() {
    return MARIADB_DRIVER;
  }

  @Override
  public boolean supports(ConnectionFactoryOptions connectionFactoryOptions) {
    Assert.requireNonNull(connectionFactoryOptions, "connectionFactoryOptions must not be null");

    String driver = connectionFactoryOptions.getValue(DRIVER);
    return driver != null && driver.equals(MARIADB_DRIVER);
  }
}
