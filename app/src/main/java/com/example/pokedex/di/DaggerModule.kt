import android.app.Application
import com.example.pokedex.data.repositories.PokemonRepository
import com.example.pokedex.data.repositories.PokemonRepositoryImpl
import com.example.pokedex.ui.viewmodels.PokemonViewModel
import com.example.pokedex.ui.viewmodels.PokemonViewModelImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object DaggerModule {
    @Provides
    @Singleton
    fun providePokemonRepository(application: Application): PokemonRepository {
        return PokemonRepositoryImpl(application)
    }

    @Provides
    fun providePokemonViewModel(application: Application, repository: PokemonRepository): PokemonViewModel {
        return PokemonViewModelImpl(application, repository)
    }
}