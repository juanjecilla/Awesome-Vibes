package com.scallop.awesomevibes.ui.artists

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.scallop.awesomevibes.CoroutinesTestRule
import com.scallop.awesomevibes.entities.Data
import com.scallop.awesomevibes.entities.Status
import com.scallop.awesomevibes.fakes.FakeLocalDataSource
import com.scallop.awesomevibes.fakes.FakeRemoteDataSource
import com.scallop.awesomevibes.mappers.ArtistMapper
import com.scallop.awesomevibes.utils.TestEntityUtils
import com.scallop.awesomevibes.utils.mock
import com.scallop.data.repository.MusicRepositoryImpl
import com.scallop.domain.usecases.GetArtistsUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ArtistsViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val fakeArtists = TestEntityUtils.getRandomArtists(20)
    private val mapper = ArtistMapper()

    private lateinit var mViewModel: ArtistsViewModel

    private val observer: Observer<Data<*>> = mock()

    @Before
    fun setUp() {
        val repository =
            MusicRepositoryImpl(FakeRemoteDataSource(fakeArtists), FakeLocalDataSource())
        val useCase = GetArtistsUseCase(repository)

        mViewModel = ArtistsViewModel("", useCase, mapper)
        mViewModel.data.observeForever(observer)
    }

    @After
    fun tearDown() {
        clearInvocations(observer)
        mViewModel.data.removeObserver(observer)
    }

    @Test
    fun `Getting flow from remote service with expected results`() {
        // Data is requested during viewModel's init

        val captor = ArgumentCaptor.forClass(Data::class.java)
        captor.run {
            verify(observer, times(2)).onChanged(capture())
            assertEquals(Status.SUCCESSFUL, value.responseType)
            assertEquals(mapper.mapArtist(fakeArtists), value.data)
        }
        clearInvocations(observer)
    }
}
