package com.data.dhirajapp

class TestRepo (private val testDao: TestDao):TestRepoUseCase{
    override fun save(data: Test): Test? {
       return testDao.save(data)
    }

    override fun update(data: Test): Test? {
        return testDao.update(data)
    }

    override fun get(uuid: String): Test? {
        return testDao.getBYUUID(uuid)
    }

    override fun getAll(): MutableList<Test>? {
      return testDao.getAll()
    }
}