package com.niupule.niuapp.mvp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.niupule.niuapp.MainActivity;
import com.niupule.niuapp.R;
import com.niupule.niuapp.data.detail.LoginDetailData;
import com.niupule.niuapp.data.type.LoginType;
import com.niupule.niuapp.util.SharedUtil;
import com.niupule.niuapp.util.StringUtil;

import org.w3c.dom.Text;

/**
 * Coder: niupuyue (牛谱乐)
 * Date: 2018/8/24
 * Time: 17:48
 * Desc:
 * Version:
 */
public class SignUpFragment extends Fragment implements LoginContract.LoginView {

    private TextInputEditText username;
    private TextInputEditText password;
    private TextInputEditText repassword;
    private TextView gotoLogin;
    private AppCompatButton signup_btn;

    private LoginContract.LoginPresenter presenter;

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signup, container, false);
        initView(root);
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity activity = new LoginActivity();
                activity.showLoginFragment();
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pwd = password.getText().toString();
                String repwd = repassword.getText().toString();
                if (checkValid(name, pwd, repwd)) {
                    //执行注册操作
                    presenter.login(name, pwd, LoginType.TYPE_REGIST);
                }
            }
        });
        return root;
    }

    protected void initView(View root) {
        username = root.findViewById(R.id.signup_username);
        password = root.findViewById(R.id.signup_password);
        repassword = root.findViewById(R.id.sign_repassword);
        gotoLogin = root.findViewById(R.id.signup_gotologin);
        signup_btn = root.findViewById(R.id.signup_btn);
    }

    protected boolean checkValid(String name, String pwd, String repwd) {
        boolean flag = true;
        if (StringUtil.isInvalid(name) || StringUtil.isInvalid(pwd) || StringUtil.isInvalid(repwd) || !pwd.equals(repwd)) {
            Snackbar.make(gotoLogin, "注册填写信息失败，请重新填写", Snackbar.LENGTH_SHORT).show();
            username.setText("");
            password.setText("");
            repassword.setText("");
            flag = false;
        }
        return flag;
    }

    protected void gotMain() {
        //注册成功直接跳转到主页面
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);
    }

    @Override
    public void showLoginError(String error) {
        Snackbar.make(gotoLogin, "注册失败", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return isAdded() && isResumed();
    }

    @Override
    public void saveUserInfo2Predference(LoginDetailData loginDetailData) {
        int userId = loginDetailData.getId();
        String username = loginDetailData.getUsername();
        String password = loginDetailData.getPassword();
        String icon = loginDetailData.getIcon();
        SharedUtil.getInstance().setIcon(icon);
        SharedUtil.getInstance().setUserId(userId);
        SharedUtil.getInstance().setUsername(username);
        SharedUtil.getInstance().setPassword(password);
        SharedUtil.getInstance().setSkipLogin(true);
        gotMain();
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(getContext(), "网络连接失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(LoginContract.LoginPresenter presenter) {
        this.presenter = presenter;
    }
}
