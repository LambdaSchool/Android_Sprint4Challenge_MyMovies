package com.lambdaschool.sprint4challenge_mymovies;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DetailViewFragment extends Fragment {

    private DetailViewViewModel mViewModel;

    public static DetailViewFragment newInstance() {
        return new DetailViewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.detail_view_fragment, container, false );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        mViewModel = ViewModelProviders.of( this ).get( DetailViewViewModel.class );
        // TODO: Use the ViewModel
    }

}
