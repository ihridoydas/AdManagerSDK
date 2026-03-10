plugins {
    id(libs.plugins.androidLibrary.get().pluginId)
    alias(libs.plugins.spotless)
    id("maven-publish")
}

android {
    namespace = "com.hridoy.ads"

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    publishing {
        singleVariant("release")
    }
}

dependencies {

    implementation(libs.play.services.ads)

    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.android.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.hilt.compose.navigation)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.foundation.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {

                from(components["release"])

                groupId = "com.github.ihridoydas"
                artifactId = "ads"
                version = "1.0.2"

            }
        }
    }
}
