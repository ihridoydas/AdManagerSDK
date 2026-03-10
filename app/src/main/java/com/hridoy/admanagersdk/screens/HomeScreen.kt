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
package com.hridoy.admanagersdk.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hridoy.admanagersdk.MainActivity
import com.hridoy.admanagersdk.R
import com.hridoy.admanagersdk.common.components.TemplatePreview
import com.hridoy.admanagersdk.datastore.ThemePreferences
import com.hridoy.admanagersdk.local.language.LanguageDataStore
import com.hridoy.admanagersdk.local.theme.ThemeDataStore
import com.hridoy.admanagersdk.navigation.ScreenDestinations
import com.hridoy.admanagersdk.ui.LanguageDropdown
import com.hridoy.admanagersdk.ui.ThemeToggleButton
import com.hridoy.ads.AdManager
import com.hridoy.ads.BannerAd
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    activity: Activity,
    navController: NavController,
    languageDataStore: LanguageDataStore,
    themeDataStore: ThemeDataStore,
) {
    val themeMode by themeDataStore.themeMode
        .collectAsState(initial = ThemePreferences.ThemeMode.SYSTEM)

    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        // 🔹 Top Left - Language Dropdown
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp),
        ) {
            LanguageDropdown(languageDataStore)
        }

        // 🔹 Top Right - Theme Toggle
        ThemeToggleButton(
            themeMode = themeMode,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
            onToggle = { newMode ->
                scope.launch {
                    themeDataStore.setThemeMode(newMode)
                }
            },
        )

        // 🔹 Center Content
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(
                    R.string.hello_developer,
                    stringResource(R.string.app_name),
                ),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Button(
                modifier = Modifier.size(120.dp, 40.dp),
                onClick = {
                    navController.navigate(ScreenDestinations.ViewScreen.route) {
                        popUpTo(ScreenDestinations.HomeScreen.route) {
                            inclusive = false
                        }
                    }
                },
            ) {
                Text(
                    text = "Lets Start!",
                    color = MaterialTheme.colorScheme.background,
                )
            }

            Text(
                text = "Ads Library Sample",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp),
            )

            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Button(
                    onClick = {
                        AdManager.showInterstitial(activity)
                    },
                ) {
                    Text("Show Interstitial Ad")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        AdManager.showRewarded(activity) {
                            println("User earned reward")
                        }
                    },
                ) {
                    Text("Show Rewarded Ad")
                }
            }

            // banner ad at bottom
            BannerAd()
        }
    }
}

@SuppressLint("ContextCastToActivity")
@TemplatePreview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        LocalContext.current as Activity,
        navController = rememberNavController(),
        languageDataStore = LanguageDataStore(LocalContext.current),
        themeDataStore = ThemeDataStore(LocalContext.current),
    )
}
