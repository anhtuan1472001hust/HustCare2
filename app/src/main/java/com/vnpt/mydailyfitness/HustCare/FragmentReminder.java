package com.vnpt.mydailyfitness.HustCare;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.vnpt.mydailyfitness.R;

import Reminder.PillReminderActivity;

public class FragmentReminder extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_reminder, container, false);
        // Inflate the layout for this fragment
        ImageView imageView=inflate.findViewById(R.id.set_water);
        imageView.setOnClickListener(view -> {
            Intent intent=new Intent(getActivity(),InitUserInfo.class);
            startActivity(intent);
        });
        ImageView imageView2=inflate.findViewById(R.id.set_medicion);
        imageView2.setOnClickListener(view -> {
            Intent intent2=new Intent(getActivity(), PillReminderActivity.class);
            startActivity(intent2);
        });
        return inflate;
    }

}
