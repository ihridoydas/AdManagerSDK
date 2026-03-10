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
package com.hridoy.admanagersdk

import android.app.Application
import com.hridoy.admanagersdk.util.TestAdIds
import com.hridoy.ads.AdManager
import com.hridoy.ads.AdsConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val config = if (BuildConfig.DEBUG) {
            AdsConfig(
                appOpenId = TestAdIds.APP_OPEN,
                interstitialId = TestAdIds.INTERSTITIAL,
                bannerId = TestAdIds.BANNER,
                nativeId = TestAdIds.NATIVE,
                rewardedId = TestAdIds.REWARDED,
                rewardedInterstitialId = TestAdIds.REWARDED_INTERSTITIAL,
                nativeVideoId = TestAdIds.NATIVE_VIDEO,
            )
        } else {
            AdsConfig(
                appOpenId = "real-app-open-id",
                interstitialId = "real-interstitial-id",
                bannerId = "real-banner-id",
                nativeId = "real-native-id",
                rewardedId = "real-rewarded-id",
                rewardedInterstitialId = "real-rewarded-interstitial-id",
                nativeVideoId = "real-native-video-id",
            )
        }

        AdManager.initialize(this, config)
    }
}
