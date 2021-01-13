package cn.az.boot.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
