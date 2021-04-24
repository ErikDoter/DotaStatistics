package org.ruiners.dotastatistics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.math.BigInteger;


public class SignInActivity extends AppCompatActivity {
    private BigInteger idConst = new BigInteger("76561197960265728");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        final String REALM_PARAM = "DotaStatistics";
        Intent intent = new Intent(this, ProfileActivity.class);
        // Constructing openid url request
        String url = "https://steamcommunity.com/openid/login?" +
                "openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.identity=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.mode=checkid_setup&" +
                "openid.ns=http://specs.openid.net/auth/2.0&" +
                "openid.realm=https://" + REALM_PARAM + "&" +
                "openid.return_to=https://" + REALM_PARAM + "/signin/";
        Log.v("TAG", "StringUrl complete");
        // указываем страницу загрузки
        webView.loadUrl(url);
        Log.d("id64", "lowskill");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url,
                                      Bitmap favicon) {

                //checks the url being loaded
                //setTitle(url);
                Uri Url = Uri.parse(url);

                if (Url.getAuthority().equals(REALM_PARAM.toLowerCase())) {
                    // That means that authentication is finished and the url contains user's id.
                    webView.stopLoading();

                    // Extracts user id.
                    Uri userAccountUrl = Uri.parse(Url.getQueryParameter("openid.identity"));
                    String userId = userAccountUrl.getLastPathSegment();
                    BigInteger value = new BigInteger(userId);
                    BigInteger id32 = value.subtract(idConst);
                    Log.d("id64", id32.toString());
                    intent.putExtra("account_id", id32.toString());
                    startActivity(intent);
                    // Do whatever you want with the user's steam i
                }
            }
        });
    }
}