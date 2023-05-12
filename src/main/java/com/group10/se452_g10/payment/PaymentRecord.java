package com.group10.se452_g10.payment;

import com.group10.se452_g10.course.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment_record")

public class PaymentRecord {

    @Id
//    @JoinColumn(name = "course_id",columnDefinition = "VARCHAR(50)")
    private Long courseId;

    @NonNull
    @Column(name = "course_fee",nullable = false)
    private Float courseFee;

}
