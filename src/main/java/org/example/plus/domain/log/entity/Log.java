package org.example.plus.domain.log.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.plus.domain.common.entity.Timestamped;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "logs")
@AttributeOverride(name = "modifiedAt", column = @Column(insertable = false, updatable = false))
public class Log extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(updatable = false)
    private String url;

    @Column(updatable = false)
    private String requestMethod;

    @Column(updatable = false)
    private Long managerId;

    @Column(updatable = false)
    private String errorMessage;

    @Column(updatable = false)
    private String status;

    // 요청
    public Log(String url, String requestMethod, Long managerId) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.managerId = managerId;
    }

    // 요청 성공
    public Log(String url, String requestMethod, Long managerId, String status) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.managerId = managerId;
        this.status = status;
    }

    // 요청 실패
    public Log(String url, String requestMethod, Long managerId, String errorMessage, String status) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.managerId = managerId;
        this.errorMessage = errorMessage;
        this.status = status;
    }
}
