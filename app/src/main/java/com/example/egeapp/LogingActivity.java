package com.example.egeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;


import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogingActivity extends AppCompatActivity {

    public TextView message;
    //public static String OriginalPass="qwerty";
    //public static String OriginalLogin="Admin";

    /*public void onClickLogin(View v) {
        TextView mes = (TextView) findViewById(R.id.message);
        EditText pass = (EditText) findViewById(R.id.passwordLog);
        String passString = pass.getText().toString();
        EditText login = (EditText) findViewById(R.id.login);
        String loginString = login.getText().toString();
        if ((passString.equals(OriginalPass)) && (loginString.equals(OriginalLogin)))  {
            mes.setTextColor(Color.GREEN);
            mes.setText("Добро пожаловать!");
        }
        else {
            mes.setTextColor(Color.RED);
            mes.setText("Неверно введён логин или пароль!");
        }
    }*/
    public void StartRegisterActivity(View v){
        Intent intent = new Intent(this, RegActivity.class);
        startActivity(intent);
    }
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // [START config_signin]
        // Конфигурация для авторизации через Google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // [END config_signin]

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        setContentView(R.layout.activity_loging);
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Проверка авторизован ли пользователь
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    // [END on_start_check_user]

    // [START onactivityresult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Результат, возвращенный при запуске Intent из Google Sign In Api.get Sign In Intent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Вход в Google прошел успешно, пройдите аутентификацию с помощью Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Не удалось войти в Google, обновите пользовательский интерфейс соответствующим образом
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Успешно вошли в систему, обновление пользовательского интерфейса информацией о вошедшем пользователе
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // Если вход систему прошёл не удачно
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }
    // [END auth_with_google]

    // [START signin]
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    public void onClickSignIn(View view) {
        signIn();
    }
    public static void signOut() {
        FirebaseAuth.getInstance().signOut();
    }
    private void updateUI(FirebaseUser user) {
        if (user != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
    // Функция для кнопки авторизации пользователя
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public void loginUser(View view) {
        EditText emailEditText = findViewById(R.id.emailLog);
        EditText passwordEditText = findViewById(R.id.passwordLog);

        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        // Исправить баг
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Пожалуйста, введите e-mail для авторизации", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Пожалуйста, введите пароль для авторизации", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Авторизация успешна, выполняем дальнейшие действия
                            Toast.makeText(LogingActivity.this, "Авторизация прошла успешно", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LogingActivity.this, MainActivity.class);
                            startActivity(intent);
                            // Переход на другой экран или выполнение других действий
                        } else {
                            // Авторизация провалилась
                            Toast.makeText(LogingActivity.this, "Ошибка авторизации: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}