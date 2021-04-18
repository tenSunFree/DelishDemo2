/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("androidx.navigation.safeargs")
}

android {
    compileSdkVersion(Versions.COMPILE_SDK)
    defaultConfig {
        applicationId = "com.example.delishdemo2"
        minSdkVersion(Versions.MIN_SDK)
        targetSdkVersion(Versions.TARGET_SDK)
        versionCode = Versions.versionCodeMobile
        versionName = Versions.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["dagger.hilt.disableModulesHaveInstallInCheck"] = "true"
                arguments["room.incremental"] = "true"
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }
    buildTypes {
        val urlName = "SPOONACULAR_BASE_URL"
        val baseUrl = "\"https://api.spoonacular.com/\""
        val keyName = "SPOONACULAR_KEY"
        val keyValue = "\"30acf9abd9004b98af253abef50b18dc\""
        val cuisinesName = "CUISINES_DATA_URL"
        val cuisinesValue =
            "\"https://firebasestorage.googleapis.com/v0/b/delish-d4e2b.appspot.com/o/getCuisines.json?alt=media&token=20daa785-e0e4-4ef5-97f8-b8c62f106900\""
        val ingredientsName = "INGREDIENTS_DATA_URL"
        val ingredientsValue =
            "\"https://firebasestorage.googleapis.com/v0/b/delish-d4e2b.appspot.com/o/ingredients.json?alt=media&token=9361ddbe-b7e9-4d18-b9a9-530f222e4890\""
        getByName("release") {
            buildConfigField("String", urlName, baseUrl)
            buildConfigField("String", keyName, keyValue)
            buildConfigField("String", cuisinesName, cuisinesValue)
            buildConfigField("String", ingredientsName, ingredientsValue)
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            buildConfigField("String", urlName, baseUrl)
            buildConfigField("String", keyName, keyValue)
            buildConfigField("String", cuisinesName, cuisinesValue)
            buildConfigField("String", ingredientsName, ingredientsValue)
            versionNameSuffix = "-debug"
        }
    }

    // debug and release variants share the same source dir
    sourceSets {
        getByName("debug") {
            java.srcDir("src/debugRelease/java")
        }
        getByName("release") {
            java.srcDir("src/debugRelease/java")
        }
    }

    // lint {
    //     // Eliminates UnusedResources false positives for resources used in DataBinding layouts
    //     isCheckGeneratedSources = true
    //     // Running lint over the debug variant is enough
    //     isCheckReleaseBuilds = false
    //     // See lint.xml for rules configuration
    //     isAbortOnError = false
    // }

    // Required for AR because it includes a library built with Java 8
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    // To avoid the compile error: "Cannot inline bytecode built with JVM target 1.8
    // into bytecode that is being built with JVM target 1.6"
    kotlinOptions {
        val options = this
        options.jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }

    buildFeatures {
        compose = true
        // buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
        viewBinding = true
    }

    packagingOptions {
        resources.excludes.add("META-INF/licenses/**")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

dependencies {
    api(platform(project(":depconstraints")))
    kapt(platform(project(":depconstraints")))
    // implementation(project(":shared"))
    // testImplementation(project(":test-shared"))
    // api(project(":model"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Libs.APP_COMPAT)
    implementation(Libs.CORE_KTX)

    // Architecture Components
    implementation(Libs.LIFECYCLE_LIVE_DATA_KTX)
    kapt(Libs.LIFECYCLE_COMPILER)
    testImplementation(Libs.ARCH_TESTING)
    implementation(Libs.NAVIGATION_FRAGMENT_KTX)
    implementation(Libs.NAVIGATION_UI_KTX)
    implementation(Libs.FRAGMENT_KTX)
    implementation(Libs.ROOM_KTX)
    implementation(Libs.ROOM_RUNTIME)
    kapt(Libs.ROOM_COMPILER)
    implementation(Libs.LIFECYCLE_EXTENSION)
    implementation(Libs.LIFECYCLE_RUN_TIME)

    // Dagger Hilt
    implementation(Libs.HILT_ANDROID)
    implementation(Libs.HILT_VIEWMODEL)
    kapt(Libs.HILT_COMPILER)
    kapt(Libs.ANDROIDX_HILT_COMPILER)
    kaptAndroidTest(Libs.HILT_COMPILER)
    kaptAndroidTest(Libs.ANDROIDX_HILT_COMPILER)

    // Kotlin
    implementation(Libs.KOTLIN_STDLIB)

    // Local unit tests
    testImplementation(Libs.JUNIT)
    testImplementation(Libs.EXT_JUNIT)
    testImplementation(Libs.ASSERT_J)
    testImplementation(Libs.MOCKK)

    // OkHttp
    implementation(Libs.OKHTTP)
    implementation(Libs.OKHTTP_LOGGING_INTERCEPTOR)
    testImplementation(Libs.OKHTTP_MOCK_SERVER)

    // Retrofit
    api(Libs.RETROFIT)
    api(Libs.MOSHI)
    api(Libs.MOSHI_KOTLIN)
    api(Libs.MOSHI_RETROFIT)

    // Data store
    api(Libs.DATA_STORE)

    // unit tests livedata
    testImplementation(Libs.ARCH_TESTING)

    // flow
    testImplementation(Libs.TURBINE)

    // COMPOSE
    implementation(Libs.COMPOSE_RUNTIME)
    implementation(Libs.COMPOSE_UI)
    implementation(Libs.COMPOSE_FOUNDATION_LAYOUT)
    implementation(Libs.COMPOSE_MATERIAL)
    implementation(Libs.COMPOSE_UI_GRAPHICS)
    implementation(Libs.COMPOSE_UI_TOOLING)
    implementation(Libs.COMPOSE_RUNTIME_LIVEDATA)
    implementation(Libs.COMPOSE_ANIMATION)
    implementation(Libs.COMPOSE_NAVIGATION)
    implementation(Libs.COMPOSE_ICON)
    implementation(Libs.COMPOSE_ACTIVITY)
    implementation(Libs.COMPOSE_CONSTRAINT)
    implementation(Libs.COMPOSE_PAGING)
    implementation(Libs.COMPOSE_VIEW_MODEL)

    implementation(Libs.INSETS)
    implementation(Libs.COIL)

    androidTestImplementation(Libs.COMPOSE_TEST)
}
