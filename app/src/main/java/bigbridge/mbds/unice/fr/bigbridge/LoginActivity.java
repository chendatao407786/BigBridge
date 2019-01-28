package bigbridge.mbds.unice.fr.bigbridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bigbridge.mbds.unice.fr.bigbridge.api.IAuth;
import bigbridge.mbds.unice.fr.bigbridge.api.RetrofitInstance;
import bigbridge.mbds.unice.fr.bigbridge.api.model.Auth;
import bigbridge.mbds.unice.fr.bigbridge.fragment.PersonInfoFragment;
import bigbridge.mbds.unice.fr.bigbridge.util.PreferencesManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private IAuth auth = RetrofitInstance.getRetrofitInstance().create(IAuth.class);
    EditText mUsername;
    EditText mPassword;
    Button mSignInButton;
    Button mSignUpButton;
    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.signup_button:
                    Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                    startActivity(intent);
                    break;
                case R.id.signin_button:
                    signIn(mUsername.getText().toString().trim(),mPassword.getText().toString().trim());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mSignInButton = findViewById(R.id.signin_button);
        mSignInButton.setOnClickListener(mOnclickListener);
        mSignUpButton = findViewById(R.id.signup_button);
        mSignUpButton.setOnClickListener(mOnclickListener);
    }

    public void signIn(final String username, String password) {
        Call<ResponseBody> call = auth.login(new Auth(username,password));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Toast.makeText(LoginActivity.this, "Connected successfully", Toast.LENGTH_SHORT).show();

                        //to save data of current user for the next connexion
//                        PreferencesManager.getInstance(getApplicationContext()).saveUsername(username);
//                        PreferencesManager.getInstance(getApplicationContext()).savePwd(password);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("username",username);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, "connection error 0 :/\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "connection error 1:/\n"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "connection 2 failed :/\n" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
