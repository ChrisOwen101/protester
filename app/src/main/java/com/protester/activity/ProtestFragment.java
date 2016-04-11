package com.protester.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.protester.R;
import com.protester.eventbus.EventBus;
import com.protester.eventbus.SetActionBarText;
import com.protester.pojo.Protest;

import butterknife.ButterKnife;

public class ProtestFragment extends Fragment {


    public static ProtestFragment newInstance(Protest protest) {
        ProtestFragment fragment = new ProtestFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("protest", protest);
        fragment.setArguments(bundle);

        return fragment;
    }

    private Protest getProtest(){
        return (Protest) getArguments().getSerializable("protest");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_protest, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.post(new SetActionBarText("Protest"));
    }

}
