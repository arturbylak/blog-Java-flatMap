package com.bylak.map;

import com.bylak.domain.Domain;
import com.bylak.survey.Survey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

public class FlatMapTest {

    @Test
    public void shouldReturnAllDomains() {
        //given
        final List<Survey> surveys = buildExampleSurveys();

        //when
        final Collection<Domain> domains = flatMap(surveys);

        //then
        assertEqualDomains(domains, surveys);
    }

    private List<Survey> buildExampleSurveys() {
        final List<Survey> surveys = new ArrayList<>(2);

        final Survey firstSurvey = new Survey("first");
        firstSurvey.addDomain(Domain.BROWSING_EXPERIENCE);
        firstSurvey.addDomain(Domain.OPERATOR_RATE);
        surveys.add(firstSurvey);

        final Survey secondSurvey = new Survey("second");
        secondSurvey.addDomain(Domain.CALL_QUALITY);
        secondSurvey.addDomain(Domain.OPERATOR_RATE);
        surveys.add(secondSurvey);

        return surveys;
    }

    private Collection<Domain> flatMap(final List<Survey> surveys) {
        return surveys.stream()
                      .map(survey -> survey.getDomains())
                      .flatMap(domains -> domains.stream())
                      .collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean assertEqualDomains(final Collection<Domain> domains, final List<Survey> surveys) {
        final Collection<Domain> oldDomains = new ArrayList<>(4);
        surveys.stream().forEach(survey -> oldDomains.addAll(survey.getDomains()));

        return oldDomains.containsAll(domains) && domains.containsAll(oldDomains);
    }
}