package com.unsplash.stockwalls.domain.impl

import com.unsplash.stockwalls.common.ResultState
import com.unsplash.stockwalls.di.IoDispatcher
import com.unsplash.stockwalls.domain.contract.FetchPhotosUseCase
import com.unsplash.stockwalls.data.repository.contract.PhotoListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FetchPhotosUseCaseImpl @Inject constructor(
    private val photoListRepository: PhotoListRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FetchPhotosUseCase {

    override fun invoke(pageNo: Int) = flow {
        try {
            emit(ResultState.Loading)
            val venues = photoListRepository.getPhotoList(pageNo)
            emit(ResultState.Success(venues))
        } catch (e: HttpException) {
            emit(ResultState.Failure("stringUtils.somethingWentWrong()"))
        } catch (e: IOException) {
            emit(ResultState.Failure("stringUtils.noNetworkErrorMessage()"))
        }
    }.flowOn(ioDispatcher)
}
