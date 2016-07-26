# android-sparkpost
Android Library for SparkPost -> Email Delivery Service & Transactional Email

# Integration
This library is hosted by jitpack.io.

Root level gradle:
```
allprojects {
 repositories {
    jcenter()
    maven { url "https://jitpack.io" }
 }
}
```

Application level gradle:
```
dependencies {
    compile 'com.github.noelchew:android-sparkpost:0.1.0'
}
```
Note: do not add the jitpack.io repository under buildscript
