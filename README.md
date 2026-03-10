# AdManagerSDK

A simple and powerful **AdMob Ad Manager library for Android (Jetpack Compose + Kotlin)** built on top of Google AdMob.

This library helps developers integrate **all AdMob ad formats quickly with minimal code**.

## Supported Ads

* App Open Ads
* Interstitial Ads
* Rewarded Ads
* Rewarded Interstitial Ads
* Banner Ads
* Adaptive Banner Ads
* Native Ads
* Native Video Ads

---

# Features

* Easy AdMob integration
* Jetpack Compose support
* Automatic ad preloading
* Smart ad frequency control
* Debug & Production ad IDs
* Disable ads for premium users
* Clean Kotlin architecture

---

# Installation

Add **JitPack repository**.

```gradle
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Add dependency.

```gradle
implementation("com.github.ihridoydas:AdManagerSDK:1.0.0")
```

---

# AdMob Setup

Add your **AdMob App ID** in `AndroidManifest.xml`.

```xml
<meta-data
    android:name="com.google.android.gms.ads.APPLICATION_ID"
    android:value="ca-app-pub-xxxxxxxx~xxxxxxxx"/>
```

---

# Initialize AdManager

Create an `Application` class.

```kotlin
@HiltAndroidApp
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val config = AdsConfig(
            appOpenId = "ca-app-pub-3940256099942544/9257395921",
            interstitialId = "ca-app-pub-3940256099942544/1033173712",
            bannerId = "ca-app-pub-3940256099942544/6300978111",
            nativeId = "ca-app-pub-3940256099942544/2247696110",
            rewardedId = "ca-app-pub-3940256099942544/5224354917",
            rewardedInterstitialId = "ca-app-pub-3940256099942544/5354046379",
            nativeVideoId = "ca-app-pub-3940256099942544/1044960115"
        )

        AdManager.initialize(this, config)
    }
}
```

---

# Show Interstitial Ad

```kotlin
AdManager.showInterstitial(this)
```

Example:

```kotlin
Button(onClick = {
    AdManager.showInterstitial(this)
}) {
    Text("Show Ad")
}
```

---

# Show Rewarded Ad

```kotlin
AdManager.showRewarded(this) {

    // reward user here

}
```

---

# Show Rewarded Interstitial

```kotlin
AdManager.showRewardedInterstitial(this) {

    // reward callback

}
```

---

# App Open Ads

App Open Ads automatically load and show when the app starts.

No additional implementation required.

---

# Banner Ads (Jetpack Compose)

```kotlin
BannerAd()
```

Example:

```kotlin
Column {

    Text("Home Screen")

    BannerAd()

}
```

---

# Adaptive Banner Ads

```kotlin
AdaptiveBannerAd()
```

Adaptive banners automatically adjust to device width.

---

# Native Ads

```kotlin
NativeAdCard()
```

Example:

```kotlin
Column {

    Text("Recommended")

    NativeAdCard()

}
```

---

# Native Video Ads

```kotlin
NativeVideoAdCard()
```

Displays video ads when available.

---

# Disable Ads (Premium Users)

```kotlin
AdConfig.adsEnabled = false
```

Example:

```kotlin
if (user.isPremium) {
    AdConfig.adsEnabled = false
}
```

---

# Test Ad IDs

Use these IDs during development.

App Open

```
ca-app-pub-3940256099942544/9257395921
```

Interstitial

```
ca-app-pub-3940256099942544/1033173712
```

Banner

```
ca-app-pub-3940256099942544/6300978111
```

Native

```
ca-app-pub-3940256099942544/2247696110
```

Rewarded

```
ca-app-pub-3940256099942544/5224354917
```

Rewarded Interstitial

```
ca-app-pub-3940256099942544/5354046379
```

Native Video

```
ca-app-pub-3940256099942544/1044960115
```

---

# Project Structure

```
ads
 ├ AdManager
 ├ AdsConfig
 ├ AdIds
 ├ AppOpenAdManager
 ├ InterstitialAdManager
 ├ RewardedAdManager
 ├ RewardedInterstitialManager
 ├ NativeAdManager
 ├ NativeVideoAdManager
 ├ BannerAd
 ├ AdaptiveBannerAd
 ├ AdPreloader
 ├ AdFrequency
 └ AdCooldown
```

---

# License

MIT License

---

# Author

**Hridoy Das**

GitHub
https://github.com/ihridoydas
