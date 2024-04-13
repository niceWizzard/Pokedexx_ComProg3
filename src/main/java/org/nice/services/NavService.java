package org.nice.services;

import org.nice.lib.navigation.NavRoute;
import org.nice.lib.navigation.NavigationPanel;
import org.nice.pages.AboutPage;
import org.nice.pages.home.HomePage;

public class NavService {
    private static NavigationPanel instance;

    public static final String NAV_ABOUT = "about";
    public static final String NAV_HOME = "home";
    public static NavigationPanel getInstance() {
        if(instance == null) {
            instance = new NavigationPanel(new NavRoute[]{
                    new NavRoute(new HomePage(), NAV_HOME),
                    new NavRoute(new AboutPage(), NAV_ABOUT)
            });
        }
        return instance;
    }
}
