package com.scallop.awesomevibes.ui.albums

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.scallop.awesomevibes.CoroutinesTestRule
import com.scallop.awesomevibes.entities.Data
import com.scallop.awesomevibes.entities.Status
import com.scallop.awesomevibes.fakes.FakeLocalDataSource
import com.scallop.awesomevibes.fakes.FakeRemoteDataSource
import com.scallop.awesomevibes.mappers.AlbumsMapper
import com.scallop.awesomevibes.mappers.ArtistMapper
import com.scallop.awesomevibes.utils.TestEntityUtils
import com.scallop.awesomevibes.utils.mock
import com.scallop.data.repository.MusicRepositoryImpl
import com.scallop.domain.usecases.GetAlbumsUseCase
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
class AlbumsViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val fakeAlbums = TestEntityUtils.getRandomAlbums(20)
    private val mapper = AlbumsMapper()

    private lateinit var mViewModel: AlbumsViewModel

    private val observer: Observer<Data<*>> = mock()


    @Before
    fun setUp() {
        val repository =
            MusicRepositoryImpl(FakeRemoteDataSource(fakeAlbums), FakeLocalDataSource())
        val useCase = GetAlbumsUseCase(repository)

        mViewModel = AlbumsViewModel("", useCase, mapper)
        mViewModel.data.observeForever(observer)
    }

    @After
    fun tearDown() {
        clearInvocations(observer)
        mViewModel.data.removeObserver(observer)
    }

    @Test
    fun `Getting flow from remote service with expected results`() {
        mViewModel.getAlbums(0)

        val captor = ArgumentCaptor.forClass(Data::class.java)
        captor.run {
            verify(observer).onChanged(capture())
            assertEquals(Status.LOADING, value.responseType)
            verify(observer, times(2)).onChanged(capture())
            assertEquals(Status.SUCCESSFUL, value.responseType)
            assertEquals(mapper.mapAlbum(fakeAlbums), value.data)
        }
    }
}
