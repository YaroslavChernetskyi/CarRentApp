package com.carapp.apigateway.feedback;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private Long carId;

    @Column
    @NonNull
    private Long userId;

    @Column
    @NonNull
    private String comment;
    @Column
    @NonNull
    private Integer stars;
}