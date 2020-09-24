package com.carapp.search;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Builder
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NonNull
    @Column(name="maker")
    private String maker;

    @NonNull
    @Column(name="model")
    private String model;

    @NonNull
    @Column(name="year")
    private int year;

    @NonNull
    @Column(name="price")
    private int price;

    @Column
    @NonNull
    private String image;

   /* @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
*/
}

