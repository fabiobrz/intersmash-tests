package org.jboss.intersmash.tests.wildfly.elytron.oidc.client.keycloak;

import cz.xtf.core.openshift.OpenShifts;
import io.fabric8.kubernetes.api.model.EnvVar;
import io.fabric8.kubernetes.api.model.EnvVarBuilder;
import org.jboss.intersmash.application.input.BuildInput;
import org.jboss.intersmash.application.input.BuildInputBuilder;
import org.jboss.intersmash.application.openshift.WildflyImageOpenShiftApplication;
import org.jboss.intersmash.tests.wildfly.WildflyApplicationConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * WildFly image based OpenShift application descriptor that uses the wildfly-with-elytron-oidc-client
 * deployment.
 */
public class WildflyWithElytronOidcClientApplication
		implements WildflyImageOpenShiftApplication, WildflyApplicationConfiguration {

	public static final String APP_NAME = "eap-elytron-oidc-client";

	private final BuildInput buildInput;
	private final List<EnvVar> environmentVariables = new ArrayList<>();

	public WildflyWithElytronOidcClientApplication() {

		String applicationDir = "wildfly/elytron-oidc-client-keycloak";
		buildInput = new BuildInputBuilder()
				.uri("https://github.com/Intersmash/intersmash-applications.git")
				.ref("main")
				.build();
		environmentVariables.add(new EnvVarBuilder()
				.withName("SSO_APP_SERVICE")
				.withValue(String.format("https://%s", BasicKeycloakOperatorApplication.getRoute()))
				.build());

		environmentVariables.add(
				new EnvVarBuilder().withName("MAVEN_S2I_ARTIFACT_DIRS")
						.withValue(applicationDir + "/target")
						.build());
		final String mavenAdditionalArgs = generateAdditionalMavenArgs()
				.concat(" -pl " + applicationDir + " -am");
		environmentVariables.add(
				new EnvVarBuilder().withName("MAVEN_ARGS_APPEND")
						.withValue(mavenAdditionalArgs)
						.build());
	}

	@Override
	public BuildInput getBuildInput() {
		return buildInput;
	}

	@Override
	public String getName() {
		return APP_NAME;
	}

	@Override
	public List<EnvVar> getEnvVars() {
		return Collections.unmodifiableList(environmentVariables);
	}

	/**
	 * Get a route to the application.
	 *
	 * @return route to the application
	 */
	public static String getRoute() {
		return OpenShifts.master().generateHostname(APP_NAME);
	}
}
