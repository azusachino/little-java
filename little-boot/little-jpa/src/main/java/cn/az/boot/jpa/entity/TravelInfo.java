package cn.az.boot.jpa.entity;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class TravelInfo {

    @Id
    @GeneratedValue
    private Integer travelId;
    @Column(nullable = false)
    private Integer workerId;
    private LocalDate travelStart;
    private LocalDate travelEnd;
    private String travelAddress;
    private String travelReason;
    private String remark;
}
