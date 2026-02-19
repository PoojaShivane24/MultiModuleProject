package com.example.multimoduleprojectnew.navigation

import com.example.common_utils.Activities
import com.example.common_utils.Navigator
import com.example.news_presenter.GotoNewsActivity
import com.example.search_presentation.GotoSearchActivity

class DefaultNavigator : Navigator.Provider {
    override fun getActivities(activities: Activities): Navigator {
        return when (activities) {
            Activities.NewsActivity -> {
                GotoNewsActivity
            }
            Activities.SearchActivity -> {
                GotoSearchActivity
            }
        }
    }
}