package com.data.dhirajapp

import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.copyFromRealm
import io.realm.kotlin.ext.query
import io.realm.kotlin.types.RealmUUID
import javax.inject.Inject

class TestDao  @Inject constructor(private val realm: Realm){

    fun save(data:Test):Test? {
        return try {
            val uuid = RealmUUID.random().toString()
            data._localId = uuid
            realm.writeBlocking { this.copyToRealm(data, UpdatePolicy.ALL) }
            getBYUUID(uuid)
        }catch (e:Exception){
            null
        }
    }

    fun update(data: Test):Test?{
        return try {
            data._localId?.let { safeId->
                realm.writeBlocking { this.query<Test>("_localId==$0",safeId).first().find()?.let {
                    it.title=data.title
                }
                }
                getBYUUID(safeId)
            }
        }catch (e:Exception){
            null
        }
    }

    fun getBYUUID(uuid:String):Test?{
        return try {
            realm.query<Test>("_localId==$0",uuid)
                .find()
                .copyFromRealm()
                .firstOrNull()
        }catch (e:Exception){
            null
        }
    }

    fun getAll():MutableList<Test>?{
        return try {
            realm.query<Test>()
                .find()
                .copyFromRealm()
                .toMutableList()
        }catch (e:Exception){
            null
        }
    }
    }