package com.homework.demo.invitation.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class InvitationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @CreationTimestamp
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    @NotNull
    private String invitee;

    @Column(unique = true)
    private String email;

    private InvitationStatus invitationStatus;

    @PrePersist
    void setStatusToPending() {
        this.invitationStatus = InvitationStatus.PENDING;
    }

}
