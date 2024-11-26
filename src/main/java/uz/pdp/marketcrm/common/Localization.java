package uz.pdp.marketcrm.common;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;


import java.util.Locale;

@Component
@RequiredArgsConstructor
public class Localization {

    private final MessageSource source;

    public String localize(String key) {
        return localize(key, null, null);
    }

    public String localize(String key, Object[] params) {
        return localize(key, null, params);
    }

    public String localize(String key, String lang) {
        return localize(key, lang, null);
    }

    public String localize(String code, String langKey, Object[] params) {
        return source.getMessage(code, params, Locale.forLanguageTag(langKey == null ? "uz" : langKey));
    }
}