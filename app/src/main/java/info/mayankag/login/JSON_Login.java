package info.mayankag.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JSON_Login extends AppCompatActivity {

    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_login);

        email = (EditText)findViewById(R.id.email_JSON);
        password = (EditText)findViewById(R.id.password_JSON);
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @SuppressWarnings("UnusedParameters")
    public void JSON_login(View view)
    {
        if(isNetworkAvailable())
        {
            JSONObject params = new JSONObject();
            try {
                params.put("email", email.getText().toString());
                params.put("password",password.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            final String JSON_login_url = getResources().getString(R.string.JSON_login_url);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,JSON_login_url, params, new Response.Listener<JSONObject>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Toast.makeText(JSON_Login.this,response.getString("message"),Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        Toast.makeText(JSON_Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(JSON_Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    return headers;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonObjectRequest);
        }
        else {
            Toast.makeText(this, R.string.internet_not_connected_message,Toast.LENGTH_SHORT).show();
        }
    }
}
