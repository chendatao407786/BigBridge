package bigbridge.mbds.unice.fr.bigbridge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bigbridge.mbds.unice.fr.bigbridge.api.IAuth;
import bigbridge.mbds.unice.fr.bigbridge.api.IUser;
import bigbridge.mbds.unice.fr.bigbridge.api.RetrofitInstance;
import bigbridge.mbds.unice.fr.bigbridge.api.model.Auth;
import bigbridge.mbds.unice.fr.bigbridge.api.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends Activity {
    private IAuth auth = RetrofitInstance.getRetrofitInstance().create(IAuth.class);
    private IUser user = RetrofitInstance.getRetrofitInstance().create(IUser.class);

    EditText editTextUsername;
    EditText editTextEmail;
    EditText editTextPassword;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editTextUsername = findViewById(R.id.username);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        btnSignUp = findViewById(R.id.signup_button);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp(editTextUsername.getText().toString().trim(), editTextEmail.getText().toString().trim(),editTextPassword.getText().toString().trim());
            }
        });
    }

    public void initUserInfo(String username){
        Call<ResponseBody> call = user.createUser(new User(username));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(SignUpActivity.this, "Created successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(SignUpActivity.this, "Created failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Created failed 2", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void signUp(final String username, String email, String password) {
        Call<ResponseBody> call = auth.createAuth(new Auth(email, username, password));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(SignUpActivity.this, "Created successfully", Toast.LENGTH_SHORT).show();
                    initUserInfo(username);
                    //for saving username and password
//                    PreferencesManager.getInstance(getApplicationContext()).saveUsername(username);
//                    PreferencesManager.getInstance(getApplicationContext()).savePwd(password);

                    //to redirect for login
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignUpActivity.this, "CreateUser error 0:/\n"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "CreateUser error 1:/\n" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
