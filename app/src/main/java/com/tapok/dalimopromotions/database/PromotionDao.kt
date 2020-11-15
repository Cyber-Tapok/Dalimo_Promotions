package com.tapok.dalimopromotions.database

import androidx.room.*
import com.tapok.dalimopromotions.model.Promotion

@Dao
interface PromotionDao {

    @Transaction
    fun updateData(promotionList: List<Promotion>) {
        deleteData()
        insertData(promotionList)
    }

    @Query("SELECT * FROM promotions")
    fun getData(): List<Promotion>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(promotionList: List<Promotion>)

    @Query("DELETE FROM promotions")
    fun deleteData()
}