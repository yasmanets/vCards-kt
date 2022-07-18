# vCards-kt

[![](https://jitpack.io/v/yasmanets/vCards-kt.svg)](https://jitpack.io/#yasmanets/vCards-kt)

[![vCards-kt](.documentation/vcard-example.png?raw=true)](https://github.com/yasmanets/vCards-kt)

This library allows you to create vCards to import contacts to Outlook, iOS, Mac OS and Android devices from your website or application.

## Install

### Step 1. Add the `JitPack` repository to your root build.gradle

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2. Add the dependency

```gradle
dependencies {
    implementation 'com.github.yasmanets:vCards-kt:${version}'
}
```