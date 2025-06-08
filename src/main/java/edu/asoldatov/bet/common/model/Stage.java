package edu.asoldatov.bet.common.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "stage")
public class Stage {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "stage", nullable = false)
    private String stage;

    @Column(name = "status")
    private String status;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tournament> tournaments;
}
