package com.noelchew.sparkpostutil.library;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
        SparkPostEmailJsonRequest sparkPostEmailJsonRequest = new SparkPostEmailJsonRequest(subject, message, recipients, sender);
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), sparkPostEmailJsonRequest.toString());
        Request request = new Request.Builder()
                .url(SparkPostEmailJsonRequest.API_BASE_URL + SparkPostEmailJsonRequest.EMAIL_API_PATH)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", apiKey)
                .post(body)
                .build();


        client.newCall(request).enqueue(new Callback() {
            Handler handler = new Handler(Looper.getMainLooper());

            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        emailListener.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) {
                try {
                    String result = response.body().string();
                    if (!TextUtils.isEmpty(result)) {
                        final SparkPostResultWrapper requestResult = SparkPostResultWrapper.fromJson(result);
                        if (requestResult.getErrors() != null) {
                            if (!requestResult.getErrors().isEmpty()) {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        emailListener.onError(new Throwable(requestResult.getErrors().get(0).getMessage()));
                                    }
                                });
                                return;
                            } else {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        emailListener.onError(new Throwable(context.getString(R.string.ncutils_error)));
                                    }
                                });
                                return;
                            }
                        } else if (requestResult.getResults().getTotal_rejected_recipients() == 0) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    emailListener.onSuccess();
                                }
                            });
                            return;
                        }
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            emailListener.onError(new Throwable("No response."));
                        }
                    });

                } catch (Exception e1) {
                    e1.printStackTrace();
                    emailListener.onError(e1);
                }
            }
        });
    }

}
