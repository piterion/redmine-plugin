package hudson.plugins.redmine;

import hudson.model.Action;
import hudson.model.AbstractBuild;

/**
 * 
 * @author pierre.schueler
 *
 */
public class RedmineProjectAction implements Action {

	private AbstractBuild<?, ?> build;
	private RedmineProjectProperty redmineProjectProperty;

    public RedmineProjectAction(AbstractBuild<?, ?> build, RedmineProjectProperty redmineProjectProperty) {
        this.build = build;
		this.redmineProjectProperty = redmineProjectProperty;
    }

    public String getIconFileName() {    	
    	RedmineWebsiteConfig redmineConfig = redmineProjectProperty.getRedmineWebsite();
		if (redmineConfig == null) {
			return null;
		} else {
	        return "/plugin/redmine/redmine-logo.png"; // redmine logo instead ruby
	    }
    }

    public String getDisplayName() {
        return "Redmine - " + ParameterUtil.replaceBuildParameters(redmineProjectProperty.projectName, build);
    }

    public String getUrlName() {
    	RedmineWebsiteConfig redmineConfig = redmineProjectProperty.getRedmineWebsite();
    	if (redmineConfig == null) {
    		return null;
    	} else {
    		return redmineConfig.baseUrl + "projects/" + ParameterUtil.replaceBuildParameters(redmineProjectProperty.projectName, build);
    	}
	}
}
