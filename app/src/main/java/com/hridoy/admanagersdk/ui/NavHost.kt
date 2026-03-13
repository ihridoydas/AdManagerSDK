/*
* MIT License
*
* Copyright (c) 2024 Hridoy Chandra Das
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*
*/
package com.hridoy.admanagersdk.ui

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hridoy.admanagersdk.local.language.LanguageDataStore
import com.hridoy.admanagersdk.local.theme.ThemeLocalDataStore
import com.hridoy.admanagersdk.navigation.ScreenDestinations
import com.hridoy.admanagersdk.navigation.canGoBack
import com.hridoy.admanagersdk.navigation.navigateTo
import com.hridoy.admanagersdk.navigation.screen
import com.hridoy.admanagersdk.screens.HomeScreen
import com.hridoy.admanagersdk.screens.ViewScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainAnimationNavHost(
    activity: Activity,
    navController: NavHostController,
    languageDataStore: LanguageDataStore,
    themeDataStore: ThemeLocalDataStore,
    startDestination: String = ScreenDestinations.HomeScreen.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        screen(ScreenDestinations.HomeScreen.route) {
            HomeScreen(
                activity = activity,
                navController = navController,
                languageDataStore = languageDataStore,
                themeDataStore = themeDataStore,
            )
        }
        screen(ScreenDestinations.ViewScreen.route) {
            ViewScreen(
                onBackPress = {
                    // navigateTo のためNavHostControllerを作成します。
                    navController.navigateTo(ScreenDestinations.HomeScreen.route)
                },
            )
        }
    }
    // Back Handler
    BackHandler {
        if (navController.canGoBack) {
            if (navController.currentBackStackEntry?.destination?.route != ScreenDestinations.HomeScreen.route) {
                navController.popBackStack()
            }
        }
    }
}
