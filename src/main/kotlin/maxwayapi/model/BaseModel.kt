package maxwayapi.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
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

    @JsonIgnoreProperties(*["hibernateLazyInitializer", "handler"])
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    var createdBy: User? = null
}