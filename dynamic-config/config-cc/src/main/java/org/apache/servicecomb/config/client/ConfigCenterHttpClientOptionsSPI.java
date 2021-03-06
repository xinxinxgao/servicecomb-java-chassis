/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicecomb.config.client;

import org.apache.servicecomb.deployment.Deployment;
import org.apache.servicecomb.deployment.DeploymentProvider;
import org.apache.servicecomb.foundation.vertx.client.http.HttpClientOptionsSPI;

import com.netflix.config.ConcurrentCompositeConfiguration;

import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpVersion;

public class ConfigCenterHttpClientOptionsSPI implements HttpClientOptionsSPI {
  public static final String CLIENT_NAME = "config-center";

  @Override
  public String clientName() {
    return CLIENT_NAME;
  }

  @Override
  public int getOrder() {
    return -100;
  }

  @Override
  public boolean enabled() {
    return Deployment.getSystemBootStrapInfo(DeploymentProvider.SYSTEM_KEY_CONFIG_CENTER) != null;
  }

  @Override
  public String getConfigTag() {
    return "cc.consumer";
  }

  @Override
  public ConcurrentCompositeConfiguration getConfigReader() {
    return ConfigCenterConfig.getConcurrentCompositeConfiguration();
  }

  @Override
  public int getEventLoopPoolSize() {
    return ConfigCenterConfig.INSTANCE.getEventLoopSize();
  }

  @Override
  public boolean useSharedVertx() {
    return false;
  }

  @Override
  public int getInstanceCount() {
    return ConfigCenterConfig.INSTANCE.getVerticalInstanceCount();
  }

  @Override
  public boolean isWorker() {
    return false;
  }

  @Override
  public String getWorkerPoolName() {
    return "pool-worker-config-center-client";
  }

  @Override
  public int getWorkerPoolSize() {
    return VertxOptions.DEFAULT_WORKER_POOL_SIZE;
  }

  @Override
  public HttpVersion getHttpVersion() {
    return HttpVersion.HTTP_1_1;
  }

  @Override
  public int getConnectTimeoutInMillis() {
    return ConfigCenterConfig.INSTANCE.getConnectionTimeout();
  }

  @Override
  public int getIdleTimeoutInSeconds() {
    return ConfigCenterConfig.INSTANCE.getIdleTimeoutInSeconds();
  }

  @Override
  public boolean isTryUseCompression() {
    return HttpClientOptions.DEFAULT_TRY_USE_COMPRESSION;
  }

  @Override
  public int getMaxWaitQueueSize() {
    return HttpClientOptions.DEFAULT_MAX_WAIT_QUEUE_SIZE;
  }

  @Override
  public int getMaxPoolSize() {
    return HttpClientOptions.DEFAULT_MAX_POOL_SIZE;
  }

  @Override
  public boolean isKeepAlive() {
    return HttpClientOptions.DEFAULT_KEEP_ALIVE;
  }

  @Override
  public int getMaxHeaderSize() {
    return HttpClientOptions.DEFAULT_MAX_HEADER_SIZE;
  }

  @Override
  public int getKeepAliveTimeout() {
    return HttpClientOptions.DEFAULT_KEEP_ALIVE_TIMEOUT;
  }

  @Override
  public int getHttp2MultiplexingLimit() {
    return HttpClientOptions.DEFAULT_HTTP2_MULTIPLEXING_LIMIT;
  }

  @Override
  public int getHttp2MaxPoolSize() {
    return HttpClientOptions.DEFAULT_HTTP2_MAX_POOL_SIZE;
  }

  @Override
  public boolean isUseAlpn() {
    return HttpClientOptions.DEFAULT_USE_ALPN;
  }

  @Override
  public boolean isProxyEnable() {
    return ConfigCenterConfig.INSTANCE.isProxyEnable();
  }

  @Override
  public String getProxyHost() {
    return ConfigCenterConfig.INSTANCE.getProxyHost();
  }

  @Override
  public int getProxyPort() {
    return ConfigCenterConfig.INSTANCE.getProxyPort();
  }

  @Override
  public String getProxyUsername() {
    return ConfigCenterConfig.INSTANCE.getProxyUsername();
  }

  @Override
  public String getProxyPassword() {
    return ConfigCenterConfig.INSTANCE.getProxyPasswd();
  }

  @Override
  public boolean isSsl() {
    return ConfigCenterConfig.INSTANCE.getServerUri().get(0).startsWith("https");
  }
}
