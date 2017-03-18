package info.mayankag.login;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class POST_String_Login extends AppCompatActivity {

    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_string_login);

        email = (EditText)findViewById(R.id.email_POST);
        password = (EditText)findViewById(R.id.password_POST);
    }

    public void POST_login(View view) {
        if(NetworkAvailabilityCheck.isNetworkAvailable(this))
        {
            final ProgressDialog progressDialog = ProgressDialog.show(this,"","Signing");

            String POST_login_url = getResources().getString(R.string.POST_login_url);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, POST_login_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    try {
                        if (response.equals("Success"))
                            Toast.makeText(POST_String_Login.this, R.string.login_success_toast, Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(POST_String_Login.this, R.string.login_failed_toast, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    error.printStackTrace();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parameters = new HashMap<>();
                    parameters.put("email", email.getText().toString());
                    parameters.put("password", password.getText().toString());
                    return parameters;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
        else {
            Toast.makeText(this, R.string.internet_not_connected_message,Toast.LENGTH_SHORT).show();
        }
    }
}