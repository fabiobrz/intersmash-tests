package org.jboss.intersmash.tests.junit.annotations;

import org.junit.jupiter.api.Tag;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark test that runs against OpenShift.
 * Used per class.
 */
@Tag("openshift")
@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.TYPE })
public @interface OpenShiftTest {
}
