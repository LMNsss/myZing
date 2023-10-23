package com.ngoclm.myzing.base.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ngoclm.myzing.base.DAO.PlaylistDao
import com.ngoclm.myzing.base.entities.Playlist

@Database(entities = [Playlist::class], version = 1)
abstract class PlaylistDatabase : RoomDatabase() {
    abstract fun getPlaylist(): PlaylistDao

    companion object {
        @Volatile
        private var instance: PlaylistDatabase? = null

        fun getInstance(context: Context): PlaylistDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext, PlaylistDatabase::class.java, "AppDB1"
                ).allowMainThreadQueries().build()
            }
            return instance!!
        }

    }
}