package com.medwiz.medwiz.repository.reviews
import com.medwiz.medwiz.data.network.ReviewApi
import com.medwiz.medwiz.model.Review
import retrofit2.Response
import java.util.ArrayList
import javax.inject.Inject

class ReviewRepository @Inject constructor(private val api: ReviewApi): ReviewRepoInterface {

    override suspend fun getAllReviewByDoctor(token:String,email: String): Response<ArrayList<Review>> {
        return api.getAllReviews(token,email)
    }
}