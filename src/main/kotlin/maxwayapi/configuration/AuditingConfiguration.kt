package maxwayapi.configuration

import maxwayapi.security.AuditAwareService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing
class AuditingConfiguration {
    @Bean
    fun auditAware() = AuditAwareService()
}