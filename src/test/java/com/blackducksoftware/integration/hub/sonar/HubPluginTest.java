/**
 * Black Duck Hub Plugin for SonarQube
 *
 * Copyright (C) 2018 Black Duck Software, Inc.
 * http://www.blackducksoftware.com/
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.blackducksoftware.integration.hub.sonar;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.sonar.api.Plugin;
import org.sonar.api.SonarRuntime;
import org.sonar.api.internal.SonarRuntimeImpl;
import org.sonar.api.internal.google.common.collect.Sets;
import org.sonar.api.utils.Version;

import com.blackducksoftware.integration.hub.sonar.compute.ComputeVulnerabilityRating;
import com.blackducksoftware.integration.hub.sonar.metric.HubSonarMetrics;
import com.blackducksoftware.integration.hub.sonar.web.HubSonarPageDefinition;

public class HubPluginTest {
    @Test
    public void pluginContainsPropertyExtensionsTest() {
        final SonarRuntime runtime = SonarRuntimeImpl.forSonarLint(Version.create(6, 5));
        final Plugin.Context context = new Plugin.Context(runtime);
        final HubPlugin hubPlugin = new HubPlugin();
        hubPlugin.define(context);

        assertTrue(context.getExtensions().containsAll(hubPlugin.getGlobalPropertyExtensions()));
    }

    @Test
    public void pluginContainsServerSideClassesTest() {
        final SonarRuntime runtime = SonarRuntimeImpl.forSonarLint(Version.create(6, 5));
        final Plugin.Context context = new Plugin.Context(runtime);
        final HubPlugin hubPlugin = new HubPlugin();
        hubPlugin.define(context);

        assertTrue(context.getExtensions().containsAll(Sets.newHashSet(HubSensor.class, HubSonarMetrics.class, HubSonarPageDefinition.class)));
    }

    @Test
    public void pluginContainsMeasureComputersTest() {
        final SonarRuntime runtime = SonarRuntimeImpl.forSonarLint(Version.create(6, 5));
        final Plugin.Context context = new Plugin.Context(runtime);
        final HubPlugin hubPlugin = new HubPlugin();
        hubPlugin.define(context);

        assertTrue(context.getExtensions().contains(ComputeVulnerabilityRating.class));
    }
}
