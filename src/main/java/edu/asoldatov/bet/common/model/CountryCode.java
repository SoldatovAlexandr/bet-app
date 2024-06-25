package edu.asoldatov.bet.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "country_code")
public class CountryCode {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

}
