package com.bylak.survey;

import com.bylak.domain.Domain;
import java.util.ArrayList;
import java.util.Collection;

public final class Survey {
    private final String name;
    private final Collection<Domain> domains;

    public Survey(final String name) {
        this.name = name;
        this.domains = new ArrayList<>(0);
    }

    public void addDomain(final Domain domain) {
        this.domains.add(domain);
    }

    public String getName() {
        return name;
    }

    public Collection<Domain> getDomains() {
        return domains;
    }
}
