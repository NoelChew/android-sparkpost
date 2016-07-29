# android-sparkpost
Android Library for SparkPost -> Email Delivery Service & Transactional Email

# How to Use
[Create an API key with SparkPost]
[Create an API key with SparkPost]: https://support.sparkpost.com/customer/portal/articles/1933377-create-api-keys

```
SparkPostEmailUtil.sendEmail(context,
                    sparkPostApiKey,
                    subject,
                    message,
                    new SparkPostSender(senderEmail, senderName),
                    new SparkPostRecipient(recipientEmail),
                    new EmailListener() {
                        @Override
                        public void onSuccess() {
                            // do something here
                        }

                        @Override
                        public void onError(String errorMessage) {
                            // do something here
                        }
                    });
```
To start sending emails with your own domain, you will need to [create a sending domain].
[create a sending domain]: https://support.sparkpost.com/customer/portal/articles/1933318-creating-sending-domains
Else, you can only send up to 50 emails using anyname@sparkpost.com

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

# Proguard
```
-keep class com.noelchew.sparkpostutil.library.** {*;}
```

# Disclaimer
I am not in any way affiliated with SparkPost. I created this library because I couldn't run obfuscation (proguard) on their [java library].
[java library]: https://github.com/SparkPost/java-sparkpost
