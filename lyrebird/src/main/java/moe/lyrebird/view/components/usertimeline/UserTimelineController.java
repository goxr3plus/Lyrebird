/*
 *     Lyrebird, a free open-source cross-platform twitter client.
 *     Copyright (C) 2017-2018, Tristan Deloche
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package moe.lyrebird.view.components.usertimeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import moe.lyrebird.model.sessions.SessionManager;
import moe.lyrebird.model.twitter.observables.UserTimeline;
import moe.lyrebird.view.components.TimelineBasedController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.User;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * Mostly setup for the self timeline view of a given user.
 *
 * @see TimelineBasedController
 */
@Component
@Scope(SCOPE_PROTOTYPE)
public class UserTimelineController extends TimelineBasedController {

    private static final Logger LOG = LoggerFactory.getLogger(UserTimelineController.class);
    private final UserTimeline userTimeline;

    @Autowired
    public UserTimelineController(
            final UserTimeline userTimeline,
            final SessionManager sessionManager,
            final ConfigurableApplicationContext context
    ) {
        super(userTimeline, sessionManager, context, false);
        this.userTimeline = userTimeline;
    }

    /**
     * @param user The user whose self timeline we want to display
     */
    public void setTargetUser(final User user) {
        userTimeline.targetUserProperty().setValue(user);
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
