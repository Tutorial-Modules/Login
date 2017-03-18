package info.mayankag.login;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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



    @SuppressWarnings("UnusedParameters")
    public void JSON_login(View view)
    {
        if(NetworkAvailabilityCheck.isNetworkAvailable(this))
        {
            JSONObject params = new JSONObject();
            try {
                params.put("email", email.getText().toString());
                params.put("password",password.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            final ProgressDialog progressDialog = ProgressDialog.show(this,"","Signing");

            final String JSON_login_url = getResources().getString(R.string.JSON_login_url);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,JSON_login_url, params, new Response.Listener<JSONObject>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(JSONObject response) {
                    progressDialog.dismiss();
                    try {
                        if (response.getString("message").equals("Success"))
                            Toast.makeText(JSON_Login.this, R.string.login_success_toast, Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(JSON_Login.this, R.string.login_failed_toast, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
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
