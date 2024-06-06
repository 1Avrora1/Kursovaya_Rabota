package com.mirea.kt.ribo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText usernameEditText = findViewById(R.id.etLogin);
        EditText passwordEditText = findViewById(R.id.etPassword);
        Button logButton = findViewById(R.id.loginButton);

        logButton.setOnClickListener(v -> {
            String lgn = usernameEditText.getText().toString();
            String pwd = passwordEditText.getText().toString();
            if (lgn.isEmpty() || pwd.isEmpty()) {
                Toast.makeText(this, R.string.incompleteData, Toast.LENGTH_LONG).show();
            } else {
                String server = "https://android-for-students.ru";
                String serverPath = "/coursework/login.php";
                HashMap<String, String> map = new HashMap();
                map.put("lgn", lgn);
                map.put("pwd", pwd);
                map.put("g", "RIBO-03-22");
                httpRunnable httpRunnable = new httpRunnable(server + serverPath, map);
                Thread th = new Thread(httpRunnable);
                th.start();
                try {
                    th.join();
                } catch (InterruptedException ex) {
                } finally {
                    try {
                        JSONObject jSONObject = new JSONObject(httpRunnable.getResponseBody());
                        int result = jSONObject.getInt("result_code");
                        switch (result) {
                            case 1:
                                startActivity(new Intent(this, MainActivity.class));
                                break;
                            case -1:
                                Toast.makeText(this, R.string.serverError, Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;

                        }
                    } catch (JSONException je) {
                        Toast.makeText(this, R.string.incompleteData, Toast.LENGTH_LONG).show();
                    }
                }
            }

        });


    }
}