package com.jaeun.myweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private boolean enabled;

    @ManyToMany
    @JoinTable(
            name ="user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    //ManyToOne 작성 하는 곳에 소유하는 것을 맵핑하는것이 가장 많이 사용됨.
    //cascade=remove 외부키로 연결된 외부클래스(외부테이블)을 같이 삭제시킨다.
    //부모노드가 없는 하위노드는 모두 삭제된다.
    //@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = false)

    //FetchType.EAGER(실행전에 조회 ToOne, @JsonIgnor을 사용해도 조회가 됨.) .LAZY(실행중에 조회 ToMany)
    //가장 좋은 방법 -> @EntityGraph를 그냥 사용하자(spring.io -> spring data jpa 참고)
    //@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    //@JsonIgnore
    private List<Board> boards = new ArrayList<>();
}
