package com.noelchew.sparkpostutil.library;

import android.content.Context;
import android.text.TextUtils;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by noelchew on 11/23/15.
 */
public class SparkPostEmailUtil {
    public static void sendEmail(Context context, String apiKey, String subject, String message, SparkPostSender sender, SparkPostRecipient recipient, EmailListener listener) {
        ArrayList<SparkPostRecipient> recipients = new ArrayList<>();
        recipients.add(recipient);
        sendEmail(context, apiKey, subject, message, sender, recipients, listener);
    }

    public static void sendEmail(Context context, String apiKey, String subject, String message, SparkPostRecipient recipient, EmailListener listener) {
        SparkPostSender sender = new SparkPostSender("feedback@sparkpostbox.com", "unknown user");
        ArrayList<SparkPostRecipient> recipients = new ArrayList<>();
        recipients.add(recipient);
        sendEmail(context, apiKey, subject, message, sender, recipients, listener);
    }

    public static void sendEmail(Context context, String apiKey, String subject, String message, String recipientEmail, EmailListener listener) {
        // need to verify sending domain first
        SparkPostRecipient recipient1 = new SparkPostRecipient("feedback@sparkpostbox.com");
        sendEmail(context, apiKey, subject, message, recipient1, listener);
    }

    public static void sendEmail(Context context, String apiKey, String subject, String message, ArrayList<SparkPostRecipient> recipients, EmailListener listener) {
        SparkPostSender sender = new SparkPostSender("feedback@sparkpostbox.com", "unknown user");
        sendEmail(context, apiKey, subject, message, sender, recipients, listener);
    }

    public static void sendEmail(final Context context, String apiKey, String subject, String message, SparkPostSender sender, ArrayList<SparkPostRecipient> recipients, final EmailListener emailListener) {
        FutureCallback<String> callback = new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
                try {
                    if (!TextUtils.isEmpty(result)) {
                        SparkPostResultWrapper requestResult = SparkPostResultWrapper.fromJson(result);
                        if (requestResult.getErrors() != null) {
                            if (!requestResult.getErrors().isEmpty()) {
                                emailListener.onError(requestResult.getErrors().get(0).getMessage());
                                return;
                            } else {
                                emailListener.onError(context.getString(R.string.ncutils_error));
                                return;
                            }
                        } else if (requestResult.getResults().getTotal_rejected_recipients() == 0) {
                            emailListener.onSuccess();
                            return;
                        }
                    }
                    emailListener.onError("No response.");


                } catch (Exception e1) {
                    e1.printStackTrace();
                    emailListener.onError(e1.getMessage());
                }
            }
        };
        SparkPostEmailJsonRequest sparkPostEmailJsonRequest = new SparkPostEmailJsonRequest(subject, message, recipients, sender);
        Ion.with(context)
                .load(SparkPostEmailJsonRequest.API_BASE_URL + SparkPostEmailJsonRequest.EMAIL_API_PATH)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", apiKey)
                .setStringBody(sparkPostEmailJsonRequest.toString())
                .asString()
                .setCallback(callback);
    }

}
