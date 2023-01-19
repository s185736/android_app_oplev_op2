package com.project.oplevapp.data.firebase

import com.project.oplevapp.data.user.repo.auth.UserRepo
import com.project.oplevapp.data.user.repo.auth.UserRepoImpl
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