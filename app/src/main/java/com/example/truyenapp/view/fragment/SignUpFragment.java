package com.example.truyenapp.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.view.activity.HomeActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpFragment extends Fragment {
    TextView changeFragmentSignIn, warningEmail, warningPass, warningRePass;
    EditText editEmail, editPass, editRePass;
    Button buttonSignIn;
    Database db;

    View view;

    public SignUpFragment() {
        // Required empty public constructor
        db = new Database(this.getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        init();
        setOnListener();
        return view;
    }

    private void init() {
        changeFragmentSignIn = view.findViewById(R.id.change_fragment_sign_in);
        editEmail = view.findViewById(R.id.edit_sign_in_email);
        editPass = view.findViewById(R.id.edit_sign_up_pass);
        editRePass = view.findViewById(R.id.edit_sign_up_re_pass);
        buttonSignIn = view.findViewById(R.id.button_sign_up);
    }

    private void setOnListener() {
        changeFragmentSignIn.setOnClickListener(view -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_sign, SignInFragment.class, null);
            fragmentTransaction.addToBackStack(this.getClass().getSimpleName());
            fragmentTransaction.commit();
        });
        buttonSignIn.setOnClickListener(view -> {

        });
    }

    private void handleSignIn() {
        String email = editEmail.getText().toString();
        String pass = editPass.getText().toString();
        String rePass = editRePass.getText().toString();

        if (email.length() != 0 && pass.length() != 0 && rePass.length() != 0) {
            if (validateEmail(email) == false) {
                showWarning(warningEmail, "Email không được để hơp lệ");
            } else if (validatePass(pass) == false) {
                showWarning(warningPass, "Mật khẩu không hợp lệ (ít nhất 8 ký tự phải bao gồm chữ in hoa, chữ số và ký tự đặc biết");
            } else if (!rePass.equals(pass)) {
                showWarning(warningRePass, "Mật khẩu không trùng nhau");
            } else {
                Boolean checkEmail = db.ckeckEmail(email);
                if (checkEmail == false && !email.equals("argoncomic@gmail.com")) {
                    Boolean insert = db.insertTaikhoan(email, pass);
                    if (insert == true) {
                        Toast.makeText(this.getActivity(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this.getActivity(), HomeActivity.class);
                        intent.putExtra("email", email);
                        intent.putExtra("pass", editPass.getText().toString());
                        startActivity(intent);
                    } else {
                        Toast.makeText(this.getActivity(), "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showWarning(warningEmail, "Email đã tồn tại !");
                }

            }
        } else {
            if (editEmail.getText().length() == 0)
                showWarning(warningEmail, "Email không được để trống");
            else
                hideWarning(warningEmail);
            if (warningPass.getText().length() == 0)
                showWarning(warningPass, "Mật khẩu không đươc để trống");
            else
                hideWarning(warningPass);
            if (warningPass.getText().length() == 0)
                showWarning(warningPass, "Mật khẩu nhập lại không trùng khớp");
            else
                hideWarning(warningPass);
        }
    }


    public static boolean validatePass(String pass) {
        String expression = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }

    private boolean validateEmail(String email) {
        return (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private void showWarning(TextView textView, String message) {
        textView.setText(message);
        textView.setTextColor(Color.RED);
        textView.setHeight(50);
    }

    private void hideWarning(TextView textView) {
        textView.setHeight(0);
    }
}