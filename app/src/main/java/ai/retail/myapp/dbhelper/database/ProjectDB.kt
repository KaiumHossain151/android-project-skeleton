package ai.retail.myapp.dbhelper.database

import ai.retail.myapp.dbhelper.Converters
import ai.retail.myapp.dbhelper.DbConstants
import ai.retail.myapp.dbhelper.dao.Dao
import ai.retail.myapp.dbhelper.entities.PostEntity
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import javax.inject.Singleton

@Singleton
@Database(
    entities = [PostEntity::class],
    version = DbConstants.DB_VERSION,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ProjectDB : RoomDatabase(){
    abstract fun getAcquisitionDao(): Dao?
}