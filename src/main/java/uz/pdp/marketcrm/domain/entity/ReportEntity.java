package uz.pdp.marketcrm.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;
@Entity(name = "reports")
public class ReportEntity extends BaseEntity{
    @OneToMany
    private List<SaleEntity> sales;
    private double totalPrice;
}
