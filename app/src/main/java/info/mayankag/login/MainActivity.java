package info.mayankag.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void Selection(View view)
    {
        switch(view.getId())
        {
            case R.id.post_select : startActivity(new Intent(this,POST_String_Login.class));
                break;
            case R.id.json_select : startActivity(new Intent(this,JSON_Login.class));
                break;
        }
        finish();
    }
}
