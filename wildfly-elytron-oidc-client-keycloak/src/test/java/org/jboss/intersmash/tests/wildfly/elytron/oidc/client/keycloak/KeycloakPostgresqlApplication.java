package org.jboss.intersmash.tests.wildfly.elytron.oidc.client.keycloak;

import org.jboss.intersmash.application.openshift.PostgreSQLImageOpenShiftApplication;
import org.jboss.intersmash.application.openshift.template.PostgreSQLTemplate;

/**
 * Deploy the postgresql database using the {@link PostgreSQLTemplate#POSTGRESQL_EPHEMERAL} template. Use the template
 * default parameters.
 */
public class KeycloakPostgresqlApplication implements PostgreSQLImageOpenShiftApplication {

	public static final String POSTGRESQL_NAME = "postgresql";
	public static final String POSTGRESQL_DATABASE = "keycloak-db";
	public static final String POSTGRESQL_PASSWORD = "keycloak-1234";
	public static final String POSTGRESQL_USER = "user-keycloak";

	@Override
	public String getName() {
		return POSTGRESQL_NAME;
	}

	@Override
	public String getUser() {
		return POSTGRESQL_USER;
	}

	@Override
	public String getPassword() {
		return POSTGRESQL_PASSWORD;
	}

	@Override
	public String getDbName() {
		return POSTGRESQL_DATABASE;
	}

	public static String getServiceName() {
		return POSTGRESQL_NAME + "-service";
	}

	public static Long getServicePort() {
		return 5432L;
	}

	public static String getServiceSecretName() {
		return POSTGRESQL_NAME + "-credentials";
	}

	public static String getServiceDbName() {
		return POSTGRESQL_DATABASE;
	}
}
