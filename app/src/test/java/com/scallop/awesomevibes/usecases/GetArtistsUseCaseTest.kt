package com.scallop.awesomevibes.usecases

import com.scallop.awesomevibes.CoroutinesTestRule
import com.scallop.awesomevibes.fakes.FakeLocalDataSource
import com.scallop.awesomevibes.fakes.FakeRemoteDataSource
import com.scallop.awesomevibes.utils.TestEntityUtils
import com.scallop.data.repository.MusicRepositoryImpl
import com.scallop.domain.usecases.GetArtistsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetArtistsUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val fakeArtists = TestEntityUtils.getRandomArtists(20)

    @Test
    fun `Getting flow from remote service with expected results`() = runBlockingTest {
        val repository =
            MusicRepositoryImpl(FakeRemoteDataSource(fakeArtists), FakeLocalDataSource())
        val useCase = GetArtistsUseCase(repository)

        useCase.getArtists("a", 0).collect {
            assert(it == fakeArtists)
        }
    }
}