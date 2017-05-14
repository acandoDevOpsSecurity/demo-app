package org.owasp.appsensor.local.response;

import javax.inject.Inject;
import javax.inject.Named;

import org.owasp.appsensor.core.Response;
import org.owasp.appsensor.core.logging.Loggable;
import org.owasp.appsensor.core.response.ResponseHandler;
import org.owasp.appsensor.core.response.UserManager;
import org.slf4j.Logger;

@Named
@Loggable
public class CustomLocalResponseHandler implements ResponseHandler {

	/** Logger */
	private Logger logger;

	@Inject
	private UserManager userManager;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handle(Response response) {
		logger.error("++++++++++++++++++++++++++++++");
		logger.error("+++ CustomLocalResponseHandler will execute the AppSensor Response: Action="
				+ response.getAction() + " User=" + response.getUser().getUsername());
		logger.error("++++++++++++++++++++++++++++++");

		switch (response.getAction()) {
		case LOG:
			logger.warn(
					"Response executed for user:" + response.getUser().getUsername() + ", Action: Increased Logging");
			break;
		case LOGOUT:
			logger.warn(
					"Response executed for user <{}>, "
							+ "Action: Logging out malicious account, delegating to configured user manager <{}>",
					response.getUser().getUsername(), userManager.getClass().getName());
			userManager.logout(response.getUser());
			break;
		case DISABLE_USER:
			logger.warn(
					"Response executed for user <{}>, "
							+ "Action: Disabling malicious account, delegating to configured user manager <{}>",
					response.getUser().getUsername(), userManager.getClass().getName());
			userManager.disable(response.getUser());
			break;
		case DISABLE_COMPONENT_FOR_SPECIFIC_USER:
			logger.warn("Response executed for user:" + response.getUser().getUsername()
					+ ", Action: Disabling Component for Specific User");
			// TODO: fill in real code for disabling component for specific user
			break;
		case DISABLE_COMPONENT_FOR_ALL_USERS:
			logger.warn("Response executed for user:" + response.getUser().getUsername()
					+ ", Action: Disabling Component for All Users");
			// TODO: fill in real code for disabling component for all users
			break;
		default:
			throw new IllegalArgumentException("There has been a request for an action "
					+ "that is not supported by this response handler.  The requested action is: "
					+ response.getAction());
		}

	}

}