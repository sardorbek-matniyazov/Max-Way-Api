package maxwayapi.dao

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus

@JsonInclude(JsonInclude.Include.NON_NULL)
data class SuperResponse(
    val message: String? = null,
    val status: HttpStatus = HttpStatus.OK,
    val success: Boolean = true,
    var data: Any? = null
) {
    companion object {
        val CATEGORY_NOT_FOUND = SuperResponse("Category not found", HttpStatus.BAD_REQUEST, false)
        val PRODUCT_EXISTS = SuperResponse("Product already exists", HttpStatus.BAD_REQUEST, false)
        val PRODUCT_NOT_FOUND = SuperResponse("Product not found", HttpStatus.BAD_REQUEST, false)
        val USER_NOT_FOUND = SuperResponse("User not found", HttpStatus.BAD_REQUEST, false)
        val UPDATED_SUCCESSFULLY = SuperResponse("Updated successfully")
        val PHONE_NUMBER_TAKEN = SuperResponse("Phone Number Already Taken", HttpStatus.BAD_REQUEST, false)
        val CREATED_SUCCESSFULLY = SuperResponse("Created successfully")
        val CATEGORY_EXISTS = SuperResponse("Category already exists", HttpStatus.BAD_REQUEST, false)
    }

    fun setData(data: Any): SuperResponse {
        this.data = data
        return this
    }
}