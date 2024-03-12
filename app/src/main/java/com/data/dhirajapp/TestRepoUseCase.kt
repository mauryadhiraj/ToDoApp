package com.data.dhirajapp


interface TestRepoUseCase {

      fun save(data:Test):Test?

      fun update(data: Test):Test?

      fun get(uuid:String):Test?

      fun getAll():MutableList<Test>?

}