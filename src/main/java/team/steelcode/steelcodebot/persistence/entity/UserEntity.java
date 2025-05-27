package team.steelcode.steelcodebot.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserEntity {


    @Id
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Long points;


}
