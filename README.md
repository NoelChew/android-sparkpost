# android-sparkpost

[![Release](https://jitpack.io/v/noelchew/android-sparkpost.svg)](https://jitpack.io/#noelchew/android-sparkpost)

Android Library for SparkPost -> Email Delivery Service & Transactional Email

## How to Use
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
## Account Setup
You will need to [create a SparkPost API key].
[create a SparkPost API key]: https://support.sparkpost.com/customer/portal/articles/1933377-create-api-keys

To start sending emails with your own domain, you will need to [create a sending domain].
[create a sending domain]: https://support.sparkpost.com/customer/portal/articles/1933318-creating-sending-domains

Next, you will need to [verify the sending domain].
[verify the sending domain]: https://support.sparkpost.com/customer/portal/articles/1933360-verify-sending-domains

With a verified sending domain, you can [send up to 100,000 emails a month].
[send up to 100,000 emails a month]: https://www.sparkpost.com/pricing

Else, you can [send up to 50 emails using anyname@sparkpostbox.com]
[send up to 50 emails using anyname@sparkpostbox.com]: https://developers.sparkpost.com/api/index#header-rate-limiting

## Integration
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
    compile 'com.github.noelchew:android-sparkpost:x.y.z'
}
```
Note: do not add the jitpack.io repository under buildscript

## Proguard
```
-keep class com.noelchew.sparkpostutil.library.** {*;}
```

## Disclaimer
I am not in any way affiliated with SparkPost. I created this library because I failed to exclude their [java library] from my proguard configuration. Do shed some light if you have done it successfully.
[java library]: https://github.com/SparkPost/java-sparkpost
