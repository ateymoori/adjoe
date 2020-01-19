package com.adjoe.albums;

import com.adjoe.utils.AlbumEntity;

import java.util.List;

public interface AlbumsContract {


    interface View{
        void onDataEmpty();
        void onError(String error);
        void onDataLoaded(List<AlbumEntity> albums);
        void setJobSchedule();
    }
    interface Presenter{
        void onViewCreated();
        void onViewDestroyed();
        void onDataEmpty();
        void onError(String error);
        void onDataLoaded(List<AlbumEntity> albums);
    }
    interface Model{
        void getData() ;
    }
}
