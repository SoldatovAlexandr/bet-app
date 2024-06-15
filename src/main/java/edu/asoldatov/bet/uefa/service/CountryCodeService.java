package edu.asoldatov.bet.uefa.service;

import edu.asoldatov.bet.common.repository.CountryCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryCodeService {

    private final CountryCodeRepository countryCodeRepository;

    public String buildName(String name) {
        return countryCodeRepository.findByName(name)
                .map(countryCode -> getFlagEmoji(countryCode.getCode()) + " " + countryCode.getName())
                .orElse(name);
    }

    private static String getFlagEmoji(String countryCode) {
        StringBuilder flagEmoji = new StringBuilder();
        for (int i = 0; i < countryCode.length(); i++) {
            int codePoint = 127397 + countryCode.charAt(i);
            flagEmoji.appendCodePoint(codePoint);
        }
        return flagEmoji.toString();
    }

}
