package maxwayapi.controller

import maxwayapi.dao.SuperResponse
import maxwayapi.dto.UserDto
import maxwayapi.service.UserService
import maxwayapi.utils.extensions.handleResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/v1/user"])
class UserController(@Autowired private val service: UserService) {
    @GetMapping(value = ["/all"])
    fun getAllOrders() = ResponseEntity.ok(SuperResponse.ALL_DATA.setData(service.getAllInstances()))

    @GetMapping(value = ["/{id}"])
    fun getOrderWithId(@PathVariable id: Long) = ResponseEntity.ok(SuperResponse.DATA.setData(service.getInstanceWithId(id)))

    @PostMapping(value = ["/create"])
    fun createUser(@RequestBody @Valid dto: UserDto) = service.create(dto).handleResponse()

    @PutMapping(value = ["/update/{id}"])
    fun updateUser(@RequestBody @Valid dto: UserDto, @PathVariable id: Long) = service.update(id, dto).handleResponse()

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun validationChecker(e: MethodArgumentNotValidException): HttpEntity<Any> {
        return CategoryController.argumentsNotValidExceptionHandler(e)
    }
}