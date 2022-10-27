package maxwayapi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
abstract class BaseModel() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    var createdDate: Timestamp = Timestamp.valueOf(LocalDateTime.now())

    @JsonIgnore
    @UpdateTimestamp
    var updatedDate: Timestamp = Timestamp.valueOf(LocalDateTime.now())

    // TODO: 10/10/22 createdBy must be created in the future
}