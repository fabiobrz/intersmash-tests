package org.jboss.intersmash.tests.wildfly.elytron.oidc.client.keycloak.util;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.http.HttpStatus;
import org.hamcrest.MatcherAssert;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * This class help interact with login page of Keycloak. It can assert the page is login page and have required fields.
 * Verify status code of page. And make login.
 */
public class KeycloakLoginPageUtilities {

	/** HTML ID of login form */
	private static final String FORM_LOGIN = "kc-form-login";
	/** HTML name of input for username */
	private static final String FIELD_USERNAME = "username";
	/** HTML name of input for password */
	private static final String FIELD_PASSWORD = "password";
	/** HTML name of button for login */
	private static final String BTN_LOGIN_LOGIN_PAGE = "login";

	public static void assertIsLoginPage(HtmlPage page) {
		try {
			assertThat(statusCodeOf(page)).isEqualTo(HttpStatus.SC_OK);
			HtmlForm loginForm = page.getHtmlElementById(FORM_LOGIN);
			assertThat(loginForm.getInputByName(FIELD_USERNAME) != null);
			assertThat(loginForm.getInputByName(FIELD_PASSWORD) != null);
			assertThat(loginForm.getButtonByName(BTN_LOGIN_LOGIN_PAGE) != null);
		} catch (ElementNotFoundException exception) {
			fail("The input element with name " + exception.getAttributeValue() + " was not found");
		}
	}

	public static Page makeLogin(HtmlPage loginPage, String user, String password) throws IOException {
		HtmlForm loginForm = loginPage.getHtmlElementById(FORM_LOGIN);
		HtmlInput userNameInput = loginForm.getInputByName(FIELD_USERNAME);
		HtmlInput passwordInput = loginForm.getInputByName(FIELD_PASSWORD);
		HtmlButton loginButton = loginForm.getButtonByName(BTN_LOGIN_LOGIN_PAGE);
		userNameInput.type(user);
		passwordInput.type(password);
		return loginButton.click();
	}

	public static int statusCodeOf(Page response) {
		return response.getWebResponse().getStatusCode();
	}

	public static void assertIsExpectedRealm(HtmlPage page, String realmName) {
		List<Object> foundObjects = page.getByXPath(
				String.format("/html/body//div[contains(text(),'%s')]", realmName));
		Optional<Object> first = foundObjects.stream().findFirst();
		MatcherAssert.assertThat(String.format("The HTML 'DIV' element with text '%s' was not found", realmName),
				!first.isEmpty());
	}
}
