package com.example.sunzh.gradleapplication.testdialogfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.sunzh.gradleapplication.R;

/**
 * Created by sunzh on 2017/3/28.
 */

public class EditNameDialogFragment extends DialogFragment {

    private static final String TAG = "EditNameDialogFragment";

    private EditText mUsername;
    private EditText mPassword;

    public interface LoginInputListener {
        void onLoginInputComplete(String username, String password);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_edit_name, container, false);
        return view;
    }

   /* @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_login_dialog, null);
        mUsername = (EditText) view.findViewById(R.id.id_txt_username);
        mPassword = (EditText) view.findViewById(R.id.id_txt_password);
        return new AlertDialog.Builder(getActivity())
                .setTitle("Sign in")
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoginInputListener listener = (LoginInputListener) getActivity();
                        listener.onLoginInputComplete(mUsername.getText().toString(), mPassword.getText().toString());
                    }
                })
                .setNegativeButton("取消", null)
                .create();
    }*/
}
