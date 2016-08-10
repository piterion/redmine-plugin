package hudson.plugins.redmine;

import java.util.Collection;
import java.util.LinkedList;

import hudson.model.AbstractBuild;
import hudson.model.ParametersAction;

/**
 * 
 * @author pierre.schueler
 *
 */
public class ParameterUtil {
	public static String replaceBuildParameters(String origString, AbstractBuild<?, ?> build) {

		ParametersAction action = build.getAction(ParametersAction.class);
		if (action == null) {
			return origString;
		} else {
			Collection<String> lastResults = new LinkedList<String>();

			String result = origString;
			do {
				lastResults.add(result);
				result = action.substitute(build, result);
			} while (!lastResults.contains(result));
			return result;
		}
	}
}
