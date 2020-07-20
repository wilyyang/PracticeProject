package com.example.practiceproject.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.practiceproject.R;
import com.google.android.material.snackbar.Snackbar;

public class Fragment_1 extends Fragment {
    public Fragment_1(){}
    View mainLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        mainLayout = getActivity().findViewById(R.id.mainLayout);
        Button button2 = view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getPermission()){
                    Toast.makeText(getContext(), "get Permission", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    public boolean getPermission() {
        // 1. 마시멜로 이상
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 1.1 전화걸기 권한 없음
            if (getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
                // 1.1.1 이전 요청 거부
                if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)==false) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
                    return false;
                } else {
                    // 1.1.2 이전 요청 거부 + 다시 묻지 않음
                    final Snackbar snackbar = Snackbar.make(mainLayout, "설정 이동", Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction("확인", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
                            startActivity(intent);
                        }
                    });
                    snackbar.show();
                    return false;
                }
            } else { // 1.2 전화걸기 권한 있음
                return true;
            }
        } else { // 2. 마시멜로 미만
            return true;  }  }
}
