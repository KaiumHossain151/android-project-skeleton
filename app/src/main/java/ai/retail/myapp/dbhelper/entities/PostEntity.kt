package ai.retail.myapp.dbhelper.entities

import ai.retail.myapp.dbhelper.DbConstants
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = DbConstants.ACQUISITION_TABLE
)
data class PostEntity (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "wallet_id")
    var wallet_id : String,

    @ColumnInfo(name = "date")
    var date : String,

    @ColumnInfo(name = "isUploaded")
    var isUploaded : Boolean,

    @ColumnInfo(name = "callType")
    var callType : String,

    @ColumnInfo(name = "callTag")
    var callTag : String,

    @ColumnInfo(name = "accuracy")
    var accuracy : Long,

    @ColumnInfo(name = "address")
    var address : String,

    @ColumnInfo(name = "qr_image")
    var qr_image : String,

    @ColumnInfo(name = "lat")
    var lat : Double,

    @ColumnInfo(name = "lng")
    var lng : Double,

    @ColumnInfo(name = "kam_whitelisting_number")
    var kam_whitelisting_number : String,

    @ColumnInfo(name = "followup_timestamp")
    var followup_timestamp : Long,

    @ColumnInfo(name = "outlet_image")
    var outlet_image : String,

    @ColumnInfo(name = "kam_name")
    var kam_name : String,

    @ColumnInfo(name = "wallet_no")
    var wallet_no : String,

    @ColumnInfo(name = "comment")
    var comment : String,

    @ColumnInfo(name = "brand_name")
    var brand_name : String,

    @ColumnInfo(name = "branch_name")
    var branch_name : String,

        ){
}