/*
 * Copyright 2024 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.discoveryengine.v1.spring;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.discoveryengine.v1.EngineServiceClient;
import com.google.cloud.discoveryengine.v1.EngineServiceSettings;
import com.google.cloud.spring.autoconfigure.core.GcpContextAutoConfiguration;
import com.google.cloud.spring.core.DefaultCredentialsProvider;
import com.google.cloud.spring.core.Retry;
import com.google.cloud.spring.core.util.RetryUtil;
import java.io.IOException;
import java.util.Collections;
import javax.annotation.Generated;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

// AUTO-GENERATED DOCUMENTATION AND CLASS.
/**
 * Auto-configuration for {@link EngineServiceClient}.
 *
 * <p>Provides auto-configuration for Spring Boot
 *
 * <p>The default instance has everything set to sensible defaults:
 *
 * <ul>
 *   <li>The default transport provider is used.
 *   <li>Credentials are acquired automatically through Application Default Credentials.
 *   <li>Retries are configured for idempotent methods but not for non-idempotent methods.
 * </ul>
 */
@Generated("by google-cloud-spring-generator")
@BetaApi("Autogenerated Spring autoconfiguration is not yet stable")
@AutoConfiguration
@AutoConfigureAfter(GcpContextAutoConfiguration.class)
@ConditionalOnClass(EngineServiceClient.class)
@ConditionalOnProperty(
    value = "com.google.cloud.discoveryengine.v1.engine-service.enabled",
    matchIfMissing = true)
@EnableConfigurationProperties(EngineServiceSpringProperties.class)
public class EngineServiceSpringAutoConfiguration {
  private final EngineServiceSpringProperties clientProperties;
  private final CredentialsProvider credentialsProvider;
  private static final Log LOGGER = LogFactory.getLog(EngineServiceSpringAutoConfiguration.class);

  protected EngineServiceSpringAutoConfiguration(
      EngineServiceSpringProperties clientProperties, CredentialsProvider credentialsProvider)
      throws IOException {
    this.clientProperties = clientProperties;
    if (this.clientProperties.getCredentials().hasKey()) {
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using credentials from EngineService-specific configuration");
      }
      this.credentialsProvider =
          ((CredentialsProvider) new DefaultCredentialsProvider(this.clientProperties));
    } else {
      this.credentialsProvider = credentialsProvider;
    }
  }

  /**
   * Provides a default transport channel provider bean, corresponding to the client library's
   * default transport channel provider. If the library supports both GRPC and REST transport, and
   * the useRest property is configured, the HTTP/JSON transport provider will be used instead of
   * GRPC.
   *
   * @return a default transport channel provider.
   */
  @Bean
  @ConditionalOnMissingBean(name = "defaultEngineServiceTransportChannelProvider")
  public TransportChannelProvider defaultEngineServiceTransportChannelProvider() {
    if (this.clientProperties.getUseRest()) {
      return EngineServiceSettings.defaultHttpJsonTransportProviderBuilder().build();
    }
    return EngineServiceSettings.defaultTransportChannelProvider();
  }

  /**
   * Provides a EngineServiceSettings bean configured to use a DefaultCredentialsProvider and the
   * client library's default transport channel provider
   * (defaultEngineServiceTransportChannelProvider()). It also configures the quota project ID and
   * executor thread count, if provided through properties.
   *
   * <p>Retry settings are also configured from service-level and method-level properties specified
   * in EngineServiceSpringProperties. Method-level properties will take precedence over
   * service-level properties if available, and client library defaults will be used if neither are
   * specified.
   *
   * @param defaultTransportChannelProvider TransportChannelProvider to use in the settings.
   * @return a {@link EngineServiceSettings} bean configured with {@link TransportChannelProvider}
   *     bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public EngineServiceSettings engineServiceSettings(
      @Qualifier("defaultEngineServiceTransportChannelProvider")
          TransportChannelProvider defaultTransportChannelProvider)
      throws IOException {
    EngineServiceSettings.Builder clientSettingsBuilder;
    if (this.clientProperties.getUseRest()) {
      clientSettingsBuilder = EngineServiceSettings.newHttpJsonBuilder();
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using REST (HTTP/JSON) transport.");
      }
    } else {
      clientSettingsBuilder = EngineServiceSettings.newBuilder();
    }
    clientSettingsBuilder
        .setCredentialsProvider(this.credentialsProvider)
        .setTransportChannelProvider(defaultTransportChannelProvider)
        .setEndpoint(EngineServiceSettings.getDefaultEndpoint())
        .setHeaderProvider(this.userAgentHeaderProvider());
    if (this.clientProperties.getQuotaProjectId() != null) {
      clientSettingsBuilder.setQuotaProjectId(this.clientProperties.getQuotaProjectId());
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Quota project id set to "
                + this.clientProperties.getQuotaProjectId()
                + ", this overrides project id from credentials.");
      }
    }
    if (this.clientProperties.getExecutorThreadCount() != null) {
      ExecutorProvider executorProvider =
          EngineServiceSettings.defaultExecutorProviderBuilder()
              .setExecutorThreadCount(this.clientProperties.getExecutorThreadCount())
              .build();
      clientSettingsBuilder.setBackgroundExecutorProvider(executorProvider);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Background executor thread count is "
                + this.clientProperties.getExecutorThreadCount());
      }
    }
    Retry serviceRetry = clientProperties.getRetry();
    if (serviceRetry != null) {
      RetrySettings updateEngineRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateEngineSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.updateEngineSettings().setRetrySettings(updateEngineRetrySettings);

      RetrySettings getEngineRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getEngineSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getEngineSettings().setRetrySettings(getEngineRetrySettings);

      RetrySettings listEnginesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listEnginesSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listEnginesSettings().setRetrySettings(listEnginesRetrySettings);

      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured service-level retry settings from properties.");
      }
    }
    Retry updateEngineRetry = clientProperties.getUpdateEngineRetry();
    if (updateEngineRetry != null) {
      RetrySettings updateEngineRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateEngineSettings().getRetrySettings(), updateEngineRetry);
      clientSettingsBuilder.updateEngineSettings().setRetrySettings(updateEngineRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for updateEngine from properties.");
      }
    }
    Retry getEngineRetry = clientProperties.getGetEngineRetry();
    if (getEngineRetry != null) {
      RetrySettings getEngineRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getEngineSettings().getRetrySettings(), getEngineRetry);
      clientSettingsBuilder.getEngineSettings().setRetrySettings(getEngineRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getEngine from properties.");
      }
    }
    Retry listEnginesRetry = clientProperties.getListEnginesRetry();
    if (listEnginesRetry != null) {
      RetrySettings listEnginesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listEnginesSettings().getRetrySettings(), listEnginesRetry);
      clientSettingsBuilder.listEnginesSettings().setRetrySettings(listEnginesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listEngines from properties.");
      }
    }
    return clientSettingsBuilder.build();
  }

  /**
   * Provides a EngineServiceClient bean configured with EngineServiceSettings.
   *
   * @param engineServiceSettings settings to configure an instance of client bean.
   * @return a {@link EngineServiceClient} bean configured with {@link EngineServiceSettings}
   */
  @Bean
  @ConditionalOnMissingBean
  public EngineServiceClient engineServiceClient(EngineServiceSettings engineServiceSettings)
      throws IOException {
    return EngineServiceClient.create(engineServiceSettings);
  }

  private HeaderProvider userAgentHeaderProvider() {
    String springLibrary = "spring-autogen-engine-service";
    String version = this.getClass().getPackage().getImplementationVersion();
    return () -> Collections.singletonMap("user-agent", springLibrary + "/" + version);
  }
}