package com.cgnexus.cards.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    /**
     * Returns the current auditor name for entities being tracked by JPA auditing.
     * This implementation returns a constant value 'ACCOUNT_MS' as the auditor.
     *
     * @return an Optional containing the auditor name "ACCOUNT_MS"
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNT_MS");
    }
}
