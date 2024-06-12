package edu.asoldatov.bet.uefa.dto;

import lombok.Data;

@Data
public class UefaTeam {

    private Long id;
    private String countryCode;
    private String internationalName;
    private String associationLogoUrl;
    private String logoUrl;

}
