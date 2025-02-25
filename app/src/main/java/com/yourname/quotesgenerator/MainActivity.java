package com.yourname.quotesgenerator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuote;
    private Button btnGenerate, btnShare, btnCopy;
    private String currentQuote = "";
    private RequestQueue requestQueue;
    private AlphaAnimation fadeIn;
    private ScaleAnimation buttonClickEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuote = findViewById(R.id.tvQuote);
        btnGenerate = findViewById(R.id.btnGenerate);
        btnShare = findViewById(R.id.btnShare);
        btnCopy = findViewById(R.id.btnCopy);

        requestQueue = Volley.newRequestQueue(this);

        fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(800);

        buttonClickEffect = new ScaleAnimation(
                1.0f, 0.95f, 1.0f, 0.95f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        buttonClickEffect.setDuration(100);
        buttonClickEffect.setFillAfter(false);

        fetchQuote();

        btnGenerate.setOnClickListener(v -> {
            v.startAnimation(buttonClickEffect);
            fetchQuote();
        });

        btnShare.setOnClickListener(v -> {
            v.startAnimation(buttonClickEffect);
            shareQuote();
        });

        btnCopy.setOnClickListener(v -> {
            v.startAnimation(buttonClickEffect);
            copyToClipboard();
        });
    }

    private void fetchQuote() {
        String url = "https://zenquotes.io/api/random";
        tvQuote.setText("Fetching quote...");
        tvQuote.setAlpha(0f);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject quoteObject = response.getJSONObject(0);
                            currentQuote = quoteObject.getString("q") + " - " + quoteObject.getString("a");
                            tvQuote.setText(currentQuote);
                            tvQuote.startAnimation(fadeIn);
                            tvQuote.setAlpha(1f);
                            btnShare.setVisibility(View.VISIBLE);
                            btnCopy.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            tvQuote.setText("Error fetching quote.");
                            btnShare.setVisibility(View.GONE);
                            btnCopy.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tvQuote.setText("Check your internet connection.");
                        btnShare.setVisibility(View.GONE);
                        btnCopy.setVisibility(View.GONE);
                    }
                });

        requestQueue.add(request);
    }

    private void shareQuote() {
        if (currentQuote.isEmpty()) {
            Toast.makeText(this, "No quote to share!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, currentQuote);
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

    private void copyToClipboard() {
        if (currentQuote.isEmpty()) {
            Toast.makeText(this, "No quote to copy!", Toast.LENGTH_SHORT).show();
            return;
        }
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Quote", currentQuote);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Quote copied to clipboard!", Toast.LENGTH_SHORT).show();
    }
}
