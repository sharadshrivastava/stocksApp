package com.test.app.domain.usecases

import android.content.Context
import com.test.app.R
import com.test.app.data.StocksRepositoryImpl
import com.test.app.data.network.Resource
import com.test.app.domain.mappers.toStockItem
import com.test.app.domain.models.dto.ResponseDto
import com.test.app.domain.models.ui.StocksItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Converting DTOs to UIObjects and doing other related computations
 * in domain layer as domain layer owns the business data & business logic and
 * what needs to be shown on UI is also a business decision/rule.
 */
class StocksUseCase @Inject constructor(
    private val repository: StocksRepositoryImpl,
    @ApplicationContext val context: Context?
) {

    suspend fun stocks(): Resource<List<StocksItem?>?> {
        val stocksResource = repository.stocks()

        return if (stocksResource.status == Resource.Status.SUCCESS) {
            processSuccessResponse(stocksResource)
        } else {
            Resource.error(stocksResource.message, null)
        }
    }

    private fun processSuccessResponse(stocksResource: Resource<ResponseDto?>): Resource<List<StocksItem?>?>{
        //checking empty condition
        if (stocksResource.data?.stocks.isNullOrEmpty()) {
            return Resource.error(context?.getString(R.string.no_data), null)
        }

        val stocks = mutableListOf<StocksItem>()
        //checking valid condition
        stocksResource.data?.stocks?.forEach {
            if (it?.isValid() == false) { //Assuming, only proper data should be displayed.
                return Resource.error(context?.getString(R.string.invalid_data), null)
            }
            //converting to UI objects.
            stocks.add(toStockItem(it))
        }
        return Resource.success(stocks)
    }
}
