package com.example.deyvi.juanfutbol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.deyvi.juanfutbol.R.string.password;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {
    private EditText  edtEmail;
    private EditText  edtPassword;
    private Button    btnEntrar;
    private TextView  tvRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initControl();


    }

    public void initControl(){

        edtEmail     = (EditText) findViewById(R.id.edtEmail);
        edtPassword  = (EditText) findViewById(R.id.edtPasword);
        btnEntrar    = (Button) findViewById(R.id.btnEntrar);
        tvRegistro   = (TextView)findViewById(R.id.tvRegistro);

        btnEntrar.setOnClickListener(this);
        tvRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar:
                String email   = edtEmail.getText().toString().trim();
                String pasword = edtPassword.getText().toString().trim();

                if(validateEmail(email) && isValidPassword(pasword) ){
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "El Email o el password fallaron, intenta de nuevo ", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tvRegistro:
                Toast.makeText(this, "Esta opción esta desabilitada", Toast.LENGTH_SHORT).show();
                break;
        }

    }



    public boolean validateEmail(String email) {

        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }


}
