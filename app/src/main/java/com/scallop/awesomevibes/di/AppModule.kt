package com.scallop.awesomevibes.di

import com.scallop.awesomevibes.mappers.ArtistMapper
import com.scallop.awesomevibes.ui.artists.ArtistsViewModel
import com.scallop.data.api.ItunesApi
import com.scallop.data.mappers.MusicDataEntityMapper
import com.scallop.data.mappers.MusicEntityDataMapper
import com.scallop.data.repository.MusicLocalImpl
import com.scallop.data.repository.MusicRemoteImpl
import com.scallop.data.repository.MusicRepositoryImpl
import com.scallop.domain.repositories.MusicRepository
import com.scallop.domain.usecases.GetArtistsUseCase
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
    factory {
        GetArtistsUseCase(
            get()
        )
    }
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
    viewModel {
        ArtistsViewModel(get(), ArtistMapper())
    }
}


private const val BASE_URL = "https://itunes.apple.com/"
