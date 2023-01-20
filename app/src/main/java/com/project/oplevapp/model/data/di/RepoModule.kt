package com.project.oplevapp.model.data.di

import com.project.oplevapp.model.interfaces.UserRepo
import com.project.oplevapp.model.repository.user.UserRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun providesFirebaseAuthRepo(
        repo: UserRepoImpl
    ): UserRepo

}