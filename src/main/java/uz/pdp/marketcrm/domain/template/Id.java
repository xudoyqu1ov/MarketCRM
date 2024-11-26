package uz.pdp.marketcrm.domain.template;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@SequenceGenerator(
        name = "global_sequence",
        sequenceName = "global_sequence",
        initialValue = 81364790,
        allocationSize = 1
)
public class Id extends Auditable {
    @jakarta.persistence.Id
    @GeneratedValue(generator = "global_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
}
