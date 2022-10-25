package maxwayapi.service

import maxwayapi.model.Category
import maxwayapi.repository.CategoryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import kotlin.streams.toList

@SpringBootTest
class CategoryServiceTest() {

    @Mock private var repository: CategoryRepository? = null

    private var service: CategoryService? = null

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        service = CategoryService(repository!!)
    }

    @Test
    fun `user findAll method testing` () {
        // given
        val categories = listOf<Category>(
            Category("Category One", "this is the first Category"),
            Category("Category Two", "this is the second Category"),
            Category("Category Three", "this is the third Category"),
            Category("Category Four", "this is the fourth Category"),
            Category("Category One", "this is the first Category ever"),
        )
        given(service!!.getAllCategories()).willReturn(categories)

        // then
        val returned = service!!.getAllCategories()
        assertThat(returned.size == 5)
        assertThat(returned.stream().distinct().toList().size == 1)
        assertThat(returned.filter { it.name!!.length >= 12 }.size == 2)
    }
}