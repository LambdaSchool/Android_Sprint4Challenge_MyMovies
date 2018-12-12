package com.lambdaschool.sprint4challenge_mymovies;

public interface ListViewUpdated {

    void onListItemDeleted(int position);
    void onListItemUpdated(int position, String title, boolean watched);
}
