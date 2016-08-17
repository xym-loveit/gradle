/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.tooling.connection;

import org.gradle.api.Incubating;
import org.gradle.tooling.GradleConnectionException;

import java.io.File;
import java.net.URI;

/**
 * <p>Builds a new Gradle connection.</p>
 *
 * <pre autoTested=''>
 * GradleConnectionBuilder builder = GradleConnector.newGradleConnection();
 * // Add a participant with root directory 'someFolder' using the Gradle version defined in the build
 * builder.addParticipant(new File("someFolder"));
 * // Add a participant with root directory 'someOtherFolder' using Gradle Version 2.6
 * builder.addParticipant(new File("someOtherFolder")).useGradleVersion("2.6");
 * // Set the Gradle user home for the entire connection
 * builder.useGradleUserHomeDir(new File("/path/to/.gradle"));
 * GradleConnection connection = builder.build();
 *
 * try {
 *    // Use connection
 * } finally {
 *    connection.close();
 * }
 * </pre>
 */
@Incubating
public interface GradleConnectionBuilder {

    /**
     * Specifies the directory of the Gradle build to connect to.
     *
     * @param rootDirectory The root directory .
     *
     * @return this
     */
    GradleConnectionBuilder forRootDirectory(File rootDirectory);

    /**
     * Specifies the user's Gradle home directory to use. Defaults to {@code ~/.gradle}.
     *
     * @param gradleUserHomeDir The user's Gradle home directory to use.
     * @return this
     */
    GradleConnectionBuilder useGradleUserHomeDir(File gradleUserHomeDir);

    /**
     * Specifies the Gradle distribution described in the build should be used.
     *
     * @return this
     */
    GradleConnectionBuilder useBuildDistribution();

    /**
     * Specifies the Gradle distribution to use.
     *
     * @param gradleHome The Gradle installation directory.
     * @return this
     */
    GradleConnectionBuilder useInstallation(File gradleHome);

    /**
     * Specifies the version of Gradle to use.
     *
     * @param gradleVersion The version to use.
     * @return this
     */
    GradleConnectionBuilder useGradleVersion(String gradleVersion);

    /**
     * Specifies the Gradle distribution to use.
     *
     * @param gradleDistribution The distribution to use.
     *
     * @return this
     */
    GradleConnectionBuilder useDistribution(URI gradleDistribution);

    /**
     * Builds the connection. You should call {@link GradleConnection#close()} when you are finished with the connection.
     *
     * @return The connection. Never returns null.
     * @throws GradleConnectionException If the connection is invalid
     */
    GradleConnection build() throws GradleConnectionException;

}
