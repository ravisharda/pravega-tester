/*
 * Copyright (c) 2019 Dell Inc., or its subsidiaries. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 */
package io.pravega.example.tester;

import io.pravega.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class AppConfiguration {
    private static Logger log = LoggerFactory.getLogger(AppConfiguration.class);

    private final ClientConfig clientConfig;
    private final String defaultScope;

    public AppConfiguration(String[] args) {
        URI controllerURI = URI.create(getEnvVar("PRAVEGA_CONTROLLER_URI", "tcp://localhost:9090"));
        clientConfig = ClientConfig.builder().controllerURI(controllerURI).build();
        defaultScope = getEnvVar("PRAVEGA_SCOPE", "examples");
    }

    @Override
    public String toString() {
        return "AppConfiguration{" +
                "clientConfig=" + clientConfig +
                ", defaultScope='" + defaultScope + '\'' +
                '}';
    }

    public ClientConfig getClientConfig() {
        return clientConfig;
    }

    public String getDefaultScope() {
        return defaultScope;
    }

    protected static String getEnvVar(String name, String defaultValue) {
        String value = System.getenv(name);
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        return value;
    }
}
