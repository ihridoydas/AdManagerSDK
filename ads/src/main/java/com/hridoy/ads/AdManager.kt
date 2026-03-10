/*
* MIT License
*
* Copyright (c) 2026 Hridoy Chandra Das
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
package com.hridoy.ads

import android.app.Activity
import android.app.Application
import com.google.android.gms.ads.MobileAds

object AdManager {
    lateinit var config: AdsConfig

    fun initialize(
        application: Application,
        config: AdsConfig,
    ) {
        this.config = config

        MobileAds.initialize(application)

        AdIds.appOpen = config.appOpenId
        AdIds.interstitial = config.interstitialId
        AdIds.banner = config.bannerId
        AdIds.native = config.nativeId
        AdIds.rewarded = config.rewardedId
        AdIds.rewardedInterstitial = config.rewardedInterstitialId
        AdIds.nativeVideo = config.nativeVideoId

        AppOpenAdManager.load(application)

        AdPreloader.preload(application)
    }

    fun showInterstitial(activity: Activity) {
        if (!AdConfig.adsEnabled) return
        if (!AdConfig.interstitialEnabled) return
        if (!AdFrequency.canShow()) return
        if (!AdCooldown.canShow()) return

        InterstitialAdManager.show(activity)
    }

    fun showRewarded(
        activity: Activity,
        reward: () -> Unit,
    ) {
        if (!AdConfig.adsEnabled) return
        if (!AdConfig.rewardedEnabled) return

        RewardedAdManager.show(activity, reward)
    }

    fun showRewardedInterstitial(
        activity: Activity,
        reward: () -> Unit,
    ) {
        if (!AdConfig.adsEnabled) return

        RewardedInterstitialManager.show(activity, reward)
    }

//    Usage in Activity
//    Example:
//    AdManager.showRewardedInterstitial(this) {
//
//        // reward user
//        Toast.makeText(this, "Reward Granted!", Toast.LENGTH_SHORT).show()
//
//    }
}
