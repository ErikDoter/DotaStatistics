package org.ruiners.dotastatistics.repository;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.ruiners.dotastatistics.presentation.Presenter;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.function.Function;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpUtilCallback {
    public String answer;
    private Presenter presenter;

    public HttpUtilCallback(Presenter p){
        presenter = p;
    }
    public void Get(String URL) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    String s = responseBody.string();
                    configure(s);

                }
            }
        });
        while (this.answer == null) {
        }
        presenter.onLoad(answer);
    }

    private void configure(String response) {
        this.answer = response;
    }
}
