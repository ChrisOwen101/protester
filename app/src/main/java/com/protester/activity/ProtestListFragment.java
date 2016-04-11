package com.protester.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.FirebaseRecyclerAdapter;
import com.protester.R;
import com.protester.adapter.holder.ProtestLineHolder;
import com.protester.eventbus.EventBus;
import com.protester.eventbus.SetActionBarText;
import com.protester.helper.DatabaseHelper;
import com.protester.helper.FragmentHelper;
import com.protester.pojo.Protest;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProtestListFragment extends Fragment {

    @Bind(R.id.recycleView) RecyclerView recyclerView;
    private FirebaseRecyclerAdapter mAdapter;

    public static ProtestListFragment newInstance() {
        ProtestListFragment fragment = new ProtestListFragment();

        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_protest_list, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new DatabaseHelper().addProtest();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new FirebaseRecyclerAdapter<Protest, ProtestLineHolder>(Protest.class, R.layout.story_line, ProtestLineHolder.class, new DatabaseHelper().retrieveProtests()) {
            @Override
            public void populateViewHolder(ProtestLineHolder protestLineHolder, final Protest protest, int position) {
                protestLineHolder.headline.setText(protest.getName());
                protestLineHolder.storyContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentHelper.replaceFragment(getActivity(), ProtestFragment.newInstance(protest));
                    }
                });

            }
        };
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.post(new SetActionBarText("Protester"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
