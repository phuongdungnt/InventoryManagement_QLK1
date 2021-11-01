package com.ntpd.inventorymanagement_qlk.Signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.huawei.agconnect.auth.AGConnectAuth;
import com.huawei.agconnect.auth.AGConnectUser;
import com.huawei.agconnect.auth.EmailUser;
import com.huawei.agconnect.auth.SignInResult;
import com.huawei.agconnect.auth.VerifyCodeResult;
import com.huawei.agconnect.auth.VerifyCodeSettings;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskExecutors;
import com.ntpd.inventorymanagement_qlk.Login.LoginActivity;
import com.ntpd.inventorymanagement_qlk.R;
import com.ntpd.inventorymanagement_qlk.module.user;
import com.ntpd.inventorymanagement_qlk.start.MainActivity;

import java.util.Locale;

import static com.huawei.agconnect.auth.VerifyCodeSettings.ACTION_REGISTER_LOGIN;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    EditText EditEmail, EditName, EditPassword, EditRetypePassword, EditVerify;
    TextView EmailAlert, NameAlert, PasswordAlert, RepasswordAlert;
    Button btnVerify;
    AGConnectUser userag;
    String userID;
    private TextView btnSignup, btnBackLogin;
    private AGConnectAuth huaweiAuth;
    private String email;
    private String name;
    private String password;
    private String confirmPassword;
    private String verCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnBackLogin = findViewById(R.id.btn_back_login);
        btnSignup = findViewById(R.id.btn_signup);
        EditEmail = findViewById(R.id.et_email);
        EditName = findViewById(R.id.et_name);
        EditPassword = findViewById(R.id.et_password);
        EditRetypePassword = findViewById(R.id.et_repassword);
        EditVerify = findViewById(R.id.edit_verify);
        btnVerify = findViewById(R.id.btn_verify);
        btnVerify.setOnClickListener(this);
        btnBackLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        huaweiAuth = AGConnectAuth.getInstance();

        //message
        EmailAlert = findViewById(R.id.email_alert);
        NameAlert = findViewById(R.id.name_alert);
        PasswordAlert = findViewById(R.id.pass_alert);
        RepasswordAlert = findViewById(R.id.repass_alert);


    }


    private boolean validateEmail() {
        String emailInput = EditEmail.getText().toString().trim();
        if (emailInput.isEmpty()) {
            EmailAlert.setText("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            EmailAlert.setText("Please enter valid email address");
            return false;
        } else {
            EmailAlert.setText("");
            return true;
        }
    }

    private boolean validateName() {
        String nameInput = EditName.getText().toString().trim();
        if (nameInput.isEmpty()) {
            NameAlert.setText("Field can't be empty");
            return false;
        } else {
            NameAlert.setText("");
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = EditPassword.getText().toString().trim();
        String repasswordInput = EditRetypePassword.getText().toString().trim();
        if (!passwordInput.isEmpty()) {
            if (passwordInput.length() < 5) {
                PasswordAlert.setText("Password must be at least 5 characters");
                return false;
            } else if (!passwordInput.equals(repasswordInput)) {
                RepasswordAlert.setText("Password would not be matched");
                return false;
            } else return true;
        } else {
            PasswordAlert.setText("Field can't be empty");
            return false;
        }
    }

    private void sendCodeVerification() {
        email = EditEmail.getText().toString();
        if (email.isEmpty() || email == null) {
            Toast.makeText(this, "Không được để trống Email !!! ", Toast.LENGTH_LONG).show();
        } else {
            VerifyCodeSettings settings = VerifyCodeSettings.newBuilder()
                    .action(ACTION_REGISTER_LOGIN)
                    .sendInterval(30)
                    .locale(Locale.ENGLISH)
                    .build();
            Task<VerifyCodeResult> task = huaweiAuth.requestVerifyCode(email, settings);
            task.addOnSuccessListener(TaskExecutors.uiThread(), new OnSuccessListener<VerifyCodeResult>() {
                @Override
                public void onSuccess(VerifyCodeResult verifyCodeResult) {
                    Toast.makeText(SignupActivity.this, "Kiểm tra mã xác minh trong Email của bạn !", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(TaskExecutors.uiThread(), new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    Log.d("VerifyCodeErr", e.getMessage());
                }
            });


        }
    }

    private void signUpWithEmail() {
        email = EditEmail.getText().toString();
        name = EditName.getText().toString();
        password = EditPassword.getText().toString();
        verCode = EditVerify.getText().toString();
        confirmPassword = EditRetypePassword.getText().toString();

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || name.isEmpty() || verCode.isEmpty()) {
            Toast.makeText(this, "Không được để trống !", Toast.LENGTH_LONG).show();
        } else if (!validatePassword()) {
            Toast.makeText(this, "Mật khẩu không đúng !", Toast.LENGTH_SHORT).show();
        } else {
            EmailUser emailUser = new EmailUser.Builder().setEmail(email)
                    .setVerifyCode(verCode)
                    .setPassword(password).build();
            AGConnectAuth.getInstance().createUser(emailUser)
                    .addOnCompleteListener(new OnCompleteListener<SignInResult>() {
                        @Override
                        public void onComplete(Task<SignInResult> task) {
                            if (task.isSuccessful()) {
                                userag = AGConnectAuth.getInstance().getCurrentUser();
                                userID = userag.getUid();
                                //insert in realtime db firebase
                                user us = new user(name, email, userID, password);

                                insContactDB(us, userID);
                                //call back login form
                                Intent intent = new Intent();
                                intent.putExtra("uid", userID);
                                intent.putExtra("email", email);
                                intent.putExtra("password", password);
                                setResult(RESULT_OK, intent);
                                Intent intt = new Intent(SignupActivity.this, MainActivity.class);
                                startActivity(intt);
                                finish();


                            } else {
                                Log.d("SignUpErr", task.getException().getMessage());
                                Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }


    private void insContactDB(user us, String userID) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        reference.child("users").child(userID).setValue(us);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:
                if (!validateEmail() || !validateName()) {
                    Toast.makeText(this, "Kiểm tra lại thông tin!", Toast.LENGTH_SHORT).show();

                } else {
                    //register account
                    signUpWithEmail();
                }

                break;
            case R.id.btn_verify:
                //send verify code
                sendCodeVerification();
                break;
            case R.id.btn_back_login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}