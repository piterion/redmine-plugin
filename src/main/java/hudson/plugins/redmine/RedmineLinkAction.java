package hudson.plugins.redmine;

import hudson.model.Action;

/**
 * @author gaooh
 * @date 2008/10/26
 */
public class RedmineLinkAction implements Action {
	private final RedmineProjectProperty prop;

    public RedmineLinkAction(RedmineProjectProperty prop) {
        this.prop = prop;
    }

    public String getIconFileName() {
    	RedmineWebsiteConfig redmineConfig = prop.getRedmineWebsite();
		if (redmineConfig == null) {
			return null;
		} else {
	        return "/plugin/redmine/redmine-logo.png"; // redmine logo instead ruby
	    }
    }

    public String getDisplayName() {
    	if(prop.projectName != null && prop.projectName.contains("$")) {    		
			return "Redmine";
		}
        return "Redmine - " + prop.projectName;
    }

    public String getUrlName() {
    	RedmineWebsiteConfig redmineConfig = prop.getRedmineWebsite();
    	if (redmineConfig == null) {
    		return null;
    	} else {
    		if(prop.projectName != null && prop.projectName.contains("$")) {    		
    			return redmineConfig.baseUrl + "projects/";
    		}
    		return redmineConfig.baseUrl + "projects/" + prop.projectName; 
    	}
	}
}
