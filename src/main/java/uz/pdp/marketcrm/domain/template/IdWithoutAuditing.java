package uz.pdp.marketcrm.domain.template;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class IdWithoutAuditing {
    @Id
    @GeneratedValue(generator = "global_sequence")
    private Long id;
}
