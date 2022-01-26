package ai.retail.myapp.di.modules

import ai.retail.myapp.data.DataManager
import ai.retail.myapp.data.DbHelper
import ai.retail.myapp.data.PreferenceHelper
import ai.retail.myapp.data.impl.DataManagerImpl
import ai.retail.myapp.data.impl.DbHelperImpl
import ai.retail.myapp.data.impl.PreferenceHelperImpl
import ai.retail.myapp.dbhelper.DbConstants
import ai.retail.myapp.dbhelper.database.ProjectDB
import ai.retail.myapp.di.*
import ai.retail.myapp.domain.repositories.HomeRepository
import ai.retail.myapp.domain.repositories.NumberVerificationRepo
import ai.retail.myapp.domain.repositories.impl.HomeRepositoryImpl
import ai.retail.myapp.domain.repositories.impl.NumberVerificationRepositoryImpl
import ai.retail.myapp.firebase.references.FirebaseReferences
import ai.retail.myapp.firebase.references.impl.FirebaseReferencesImpl
import ai.retail.myapp.firebase.storage.FirebaseStorageHelper
import ai.retail.myapp.firebase.storage.impl.FirebaseStorageHelperImpl
import ai.retail.myapp.helper.CrashlyticsHelper
import ai.retail.myapp.helper.LocationManager
import ai.retail.myapp.network.CustomAuthenticationClient
import ai.retail.myapp.network.interfaces.ApiService
import ai.retail.myapp.network.interfaces.CustomAuthApiService
import ai.retail.myapp.network.interfaces.impl.ApiServiceImpl
import ai.retail.myapp.utils.CommonTasks
import ai.retail.myapp.utils.Constants
import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @ApplicationContext
    fun provideContext(application: Application?): Context? {
        return application
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String? {
        return DbConstants.DB_NAME
    }


    @Provides
    fun providesUrl() = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun providesApiService() : ApiService =
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiServiceImpl(apiService: ApiService): ApiServiceImpl = ApiServiceImpl(apiService)

    @Provides
    @Singleton
    fun provideHomeRepository(dataManager: DataManager, apiService: ApiService) : HomeRepository = HomeRepositoryImpl(
            dataManager, apiService
    )

    @Provides
    @PreferenceInfo
    fun provideSharedPreferenceName(): String = PreferenceHelper.MODULE_NAME

    @Provides
    @PreferenceInfo
    fun provideSharedPreferenceVersion(): Int = PreferenceHelper.VERSION


    @Provides
    @Singleton
    @PreferenceInfo
    fun provideEncryptedSP(
            @ApplicationContext context: Context?,
            @PreferenceInfo moduleName: String?
    ): EncryptedSharedPreferences? {

         var encryptedSharedPreferences : EncryptedSharedPreferences

         try {
            val masterKEY = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
            encryptedSharedPreferences = EncryptedSharedPreferences
                .create(
                        moduleName!!,
                        masterKEY,
                        context!!,
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                ) as EncryptedSharedPreferences
        } catch (ex: Exception) {
             encryptedSharedPreferences = EncryptedSharedPreferences
                     .create(
                             moduleName!!,
                             CommonTasks.getAlphaNumericString(16),
                             context!!,
                             EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                             EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                     ) as EncryptedSharedPreferences
        }
        return encryptedSharedPreferences
    }


    @Provides
    @Singleton
    fun provideSharedPrefHelper(@PreferenceInfo encryptedSharedPreferences: EncryptedSharedPreferences?):
            PreferenceHelper = PreferenceHelperImpl(encryptedSharedPreferences)

    @Provides
    @Singleton
    fun provideSupportFactory() : SupportFactory? {
        try {
            val passPhrase : String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
            return SupportFactory(SQLiteDatabase.getBytes(passPhrase.toCharArray()))
        }catch (e: java.lang.Exception){
            return SupportFactory(SQLiteDatabase.getBytes(CommonTasks.getAlphaNumericString(16).toCharArray()))
        }
    }

    @Provides
    fun provideAppDatabase(
            @DatabaseInfo dbName: String?,
            @ApplicationContext context: Context?,
            supportFactory: SupportFactory?
    ): ProjectDB {
        return Room.databaseBuilder(context!!, ProjectDB::class.java, dbName!!)
            .fallbackToDestructiveMigration()
            .openHelperFactory(supportFactory)
            .build()
    }

    @Provides
    @Singleton
    fun getDataBaseReference() : DatabaseReference  {
        val firebaseDataBase : FirebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDataBase.setPersistenceEnabled(true)
        return firebaseDataBase.getReference()
    }

    @Provides
    @ApplicationScope
    fun getLocationManager(@ApplicationContext context: Context?): LocationManager? {
        return LocationManager(context)
    }

    @Provides
    @FirebaseStorageInfo
    fun provideFirebaseStorage(): String {
        return FirebaseStorageHelper.FIREBASE_STORAGE_URL
    }

    @Provides
    @ApplicationScope
    fun getFirebaseStorageReference(@FirebaseStorageInfo url: String?): StorageReference? {
        val firebaseStorage = FirebaseStorage.getInstance()
        firebaseStorage.maxUploadRetryTimeMillis = Constants.MAX_TRANSFER_RETRY_MILLIS
        return firebaseStorage.getReferenceFromUrl(url!!)
    }

    @Provides
    @ApplicationScope
    fun getFirebaseStorageHelper(storageReference: StorageReference): FirebaseStorageHelper {
        return FirebaseStorageHelperImpl(storageReference)
    }

    @Provides
    @Singleton
    fun getFirebaseReference(databaseReference: DatabaseReference) : FirebaseReferences {
        return FirebaseReferencesImpl(databaseReference)
    }


    @Provides
    fun provideDbHelper(projectDB: ProjectDB): DbHelper {
        return DbHelperImpl(projectDB)
    }

    @Provides
    @Singleton
    fun provideDataManager(dbHelper: DbHelper, preferenceHelper: PreferenceHelper, firebaseReferences: FirebaseReferences) :
            DataManager = DataManagerImpl(dbHelper, preferenceHelper,firebaseReferences)


    @Provides
    @Singleton
    fun getFirebaseCrashlytics(): FirebaseCrashlytics {
        return FirebaseCrashlytics.getInstance()
    }

    @Provides
    @Singleton
    fun getCrashlyticsHelper(crashlytics: FirebaseCrashlytics): CrashlyticsHelper {
        return CrashlyticsHelper(crashlytics)
    }

    @Provides
    @Singleton
    fun getNumberVerificationRepo(dataManager: DataManager, firebaseAuth: FirebaseAuth, customAuthApiService: CustomAuthApiService) : NumberVerificationRepo {
        return NumberVerificationRepositoryImpl(dataManager, firebaseAuth,customAuthApiService)
    }

    @Provides
    @Singleton
    fun getFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
    @Provides
    @Singleton
    fun getFirebaseUser(firebaseAuth: FirebaseAuth): FirebaseUser {
        return firebaseAuth.currentUser!!
    }

    @Provides
    @Singleton
    fun getCustomAuthAPiClient() : CustomAuthenticationClient {
        return CustomAuthenticationClient()
    }

    @Provides
    @Singleton
    fun getCustomAuthAPiService(customAuthenticationClient: CustomAuthenticationClient) : CustomAuthApiService{
        return customAuthenticationClient.getRetrofitDataClient().create(CustomAuthApiService::class.java)
    }

}