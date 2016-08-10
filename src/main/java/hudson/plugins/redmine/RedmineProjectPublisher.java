package hudson.plugins.redmine;

import java.io.IOException;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Notifier;
import hudson.tasks.Publisher;

/**
 * 
 * @author pierre.schueler
 *
 */
public class RedmineProjectPublisher extends Notifier {

	private String name;
	
	@DataBoundConstructor
	public RedmineProjectPublisher(String name) {
		this.name = name;
	}
	
	 public String getName() {
	        return name;
	    }

	@Override
	public boolean perform(AbstractBuild<?, ?> build, Launcher launcher,
			BuildListener listener) throws InterruptedException, IOException {
		RedmineProjectProperty rpp = build.getProject().getProperty(RedmineProjectProperty.class);
        if(rpp == null || rpp.getRedmineWebsite() == null) { // not configured
            return false; 
        }      

		build.addAction(new RedmineProjectAction(build, rpp));

		return true;
	}

	public BuildStepMonitor getRequiredMonitorService() {
		return BuildStepMonitor.NONE;
	}

	@Extension
	public static class DescriptorImpl extends BuildStepDescriptor<Publisher> {

		@SuppressWarnings("rawtypes")
		@Override
		public boolean isApplicable(Class<? extends AbstractProject> jobType) {
			return true;
		}

		@Override
		public String getDisplayName() {
			return "Redmine Project in Build";
		}

	}

}
