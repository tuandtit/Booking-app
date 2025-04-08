package com.cinema.booking_app.config.languages;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

@Configuration
@Slf4j
public class WebLocaleConfig implements WebMvcConfigurer {

    private static final List<Locale> SUPPORTED_LOCALES = List.of(
            new Locale("en"),
            new Locale("vi"),
            new Locale("fr")
    );

    @Bean
    public LocaleResolver localeResolver() {
        return new AcceptHeaderLocaleResolver() {
            @Override
            public Locale resolveLocale(HttpServletRequest request) {
                String headerLang = request.getHeader("Accept-Language");
                if (!StringUtils.hasText(headerLang)) {
                    return Locale.ENGLISH;
                }
                List<Locale.LanguageRange> ranges = Locale.LanguageRange.parse(headerLang);
                Locale matchedLocale = Locale.lookup(ranges, SUPPORTED_LOCALES);
                return matchedLocale != null ? matchedLocale : Locale.ENGLISH;
            }
        };
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("i18n/messages"); // e.g. i18n/messages_en.properties
        source.setDefaultEncoding("UTF-8");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }
}
