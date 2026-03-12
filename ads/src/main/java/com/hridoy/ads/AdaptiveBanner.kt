package com.hridoy.ads

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdaptiveBanner() {

    val context = LocalContext.current

    BoxWithConstraints {

        val adWidth = maxWidth.value.toInt()

        AndroidView(
            factory = {

                val adView = AdView(it)

                adView.adUnitId = AdIds.adaptiveBanner

                @Suppress("DEPRECATION")
                adView.setAdSize(
                    AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                        context,
                        adWidth
                    )
                )

                adView.loadAd(AdRequest.Builder().build())

                adView
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
