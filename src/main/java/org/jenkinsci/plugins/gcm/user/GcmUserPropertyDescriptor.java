package org.jenkinsci.plugins.gcm.user;

import hudson.model.UserProperty;
import hudson.model.UserPropertyDescriptor;
import hudson.model.User;
import net.sf.json.JSONObject;

import org.jenkinsci.plugins.gcm.Messages;
import org.kohsuke.stapler.StaplerRequest;

public final class GcmUserPropertyDescriptor extends UserPropertyDescriptor {

    /** Must match the parameter name used in {@code config.jelly}. */
    public static final String TOKEN_PARAM_NAME = "gcm.token";

    public GcmUserPropertyDescriptor() {
        super(GcmUserTokenProperty.class);
    }

    @Override
    public UserProperty newInstance(User user) {
        return new GcmUserTokenProperty(null);
    }

    @Override
    public UserProperty newInstance(StaplerRequest req, JSONObject formData) throws FormException {
        try {
            return new GcmUserTokenProperty(req.getParameter(TOKEN_PARAM_NAME));
        } catch (IllegalArgumentException e) {
            throw new FormException("Invalid GCM token", TOKEN_PARAM_NAME);
        }
    }

    @Override
    public String getDisplayName() {
        return Messages.Gcm_GoogleCloudMessagingToken();
    }

}
