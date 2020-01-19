package com.adjoe.albums;

import com.adjoe.utils.AlbumEntity;

import java.util.List;

public class AlbumsPresenter implements AlbumsContract.Presenter {
    private AlbumsContract.View view;
    private AlbumModel model;

    AlbumsPresenter(AlbumsContract.View view) {
        this.model = new AlbumModel(this);
        this.view = view;
    }

    @Override
    public void onViewCreated() {
        model.getData();
        view.setJobSchedule();
    }

    @Override
    public void onViewDestroyed() {
        this.view = null;
        this.model = null;
    }

    @Override
    public void onDataEmpty() {
        if (this.view != null)
            view.onDataEmpty();
    }

    @Override
    public void onError(String error) {
        if (this.view != null)
            view.onError(error);
    }

    @Override
    public void onDataLoaded(List<AlbumEntity> albums) {
        if (this.view != null)
            view.onDataLoaded(albums);
    }
}
