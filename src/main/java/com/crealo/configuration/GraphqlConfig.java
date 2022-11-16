package com.crealo.configuration;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.util.regex.Pattern;

@Configuration
public class GraphqlConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        //var phoneNumberScalar = ExtendedScalars.newRegexScalar("PhoneNumber").addPattern(Pattern.compile("\\([0-9]*\\)[0-9]*")).build();
        final var emailScalar = ExtendedScalars.newRegexScalar("Email")
                .addPattern(Pattern.compile("(?:[a-z\\d!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z\\d!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z\\d](?:[a-z\\d-]*[a-z\\d])?\\.)+[a-z\\d](?:[a-z\\d-]*[a-z\\d])?|\\[(?:(?:25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d\\d?|[a-z\\d-]*[a-z\\d]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])"))
                .build();

        final var shortScalar = ExtendedScalars.newAliasedScalar("Short")
                .aliasedScalar(ExtendedScalars.GraphQLShort)
                .build();

        final var byteScalar = ExtendedScalars.newAliasedScalar("Byte")
                .aliasedScalar(ExtendedScalars.GraphQLByte)
                .build();

        return wiringBuilder -> wiringBuilder
                .scalar(ExtendedScalars.UUID)
                .scalar(ExtendedScalars.Json)
                .scalar(ExtendedScalars.Date)
                .scalar(ExtendedScalars.Time)
                .scalar(ExtendedScalars.LocalTime)
                .scalar(ExtendedScalars.DateTime)
                .scalar(ExtendedScalars.Url)
                .scalar(ExtendedScalars.PositiveInt)
                .scalar(ExtendedScalars.NonNegativeInt)
                .scalar(ExtendedScalars.PositiveFloat)
                .scalar(ExtendedScalars.NonNegativeFloat)
                .scalar(ExtendedScalars.Locale)
                .scalar(ExtendedScalars.Object)
                .scalar(emailScalar)
                .scalar(shortScalar)
                .scalar(byteScalar);
    }
}
