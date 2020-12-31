package com.scallop.awesomevibes.di

import com.scallop.awesomevibes.mappers.AlbumsMapper
import com.scallop.awesomevibes.mappers.ArtistMapper
import com.scallop.awesomevibes.mappers.SongsMapper
import com.scallop.awesomevibes.ui.albums.AlbumsViewModel
import com.scallop.awesomevibes.ui.artists.ArtistsViewModel
import com.scallop.awesomevibes.ui.search.SearchViewModel
import com.scallop.awesomevibes.ui.songs.SongsViewModel
import com.scallop.data.api.ItunesApi
import com.scallop.data.mappers.MusicDataEntityMapper
import com.scallop.data.mappers.MusicEntityDataMapper
import com.scallop.data.repository.MusicLocalImpl
import com.scallop.data.repository.MusicRemoteImpl
import com.scallop.data.repository.MusicRepositoryImpl
import com.scallop.domain.repositories.MusicRepository
import com.scallop.domain.usecases.GetAlbumsUseCase
import com.scallop.domain.usecases.GetArtistsUseCase
import com.scallop.domain.usecases.GetSongsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val mRepositoryModules = module {
    single { MusicRemoteImpl(get()) }
    single {
        MusicLocalImpl(
            MusicEntityDataMapper(),
            MusicDataEntityMapper()
        )
    }
    single {
        @Suppress("USELESS_CAST") // It is important to maintain the dependency tree
        MusicRepositoryImpl(
            get(),
            get()
        ) as MusicRepository
    }
}

val mUseCaseModules = module {
    factory { GetArtistsUseCase(get()) }
    factory { GetAlbumsUseCase(get()) }
    factory { GetSongsUseCase(get()) }
}

val mNetworkModules = module {
    single { createNetworkClient(BASE_URL) }
    single { (get() as Retrofit).create(ItunesApi::class.java) }
}

/*val mLocalModules = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            MusicDatabase::class.java,
            "movie_items"
        ).build()
    }
}*/

val mViewModels = module {
    viewModel { SearchViewModel() }
    viewModel { ArtistsViewModel(get(), ArtistMapper()) }
    viewModel { AlbumsViewModel(get(), AlbumsMapper()) }
    viewModel { SongsViewModel(get(), SongsMapper()) }
}


private const val BASE_URL = "https://itunes.apple.com/"
