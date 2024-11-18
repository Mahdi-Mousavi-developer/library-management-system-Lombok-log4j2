package maktabSharifHw.model;


import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date create_time;

}
