package ru.gitverse.bizzareowl.datacollector.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Embeddable;

@Embeddable
public record MessageSourceData(
        @Basic(optional = false)
        String id,

        @Basic(optional = false)
        String name,

        @Basic(optional = false)
        MessageSource source
) {
}
